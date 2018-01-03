package challenge.filter;

import challenge.model.Person;
import challenge.persistence.query.GetPersonQuery;
import challenge.persistence.query.PeopleQueryHandler;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <h1>PersonAuthenticationFilter</h1>
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class PersonAuthenticationFilter extends GenericFilterBean {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private final PeopleQueryHandler peopleQueryHandler;

    @Autowired
    public PersonAuthenticationFilter(PeopleQueryHandler peopleQueryHandler) {
        this.peopleQueryHandler = peopleQueryHandler;
    }

    /**
     * Authenticate the Basic Auth user against the expected person with the given ID in the datastore
     * @param request the HTTP Servlet Request
     * @param response the HTTP Servlet Response
     * @param chain the next filter on the chain
     * @throws IOException if a string encoding doesn't match the expected type
     * @throws ServletException if an exception is thrown by the next filter on the chain
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String url = ((HttpServletRequest)request).getRequestURL().toString();

        // Only run this filter if going to the person controller
        if (!url.contains("person")) {
            chain.doFilter(request, response);
            return;
        }

        // This is nasty :( Can't think a better way to get the path variable in the filter context
        int id = Integer.parseInt(url.split("/")[5]);

        List<Person> people = peopleQueryHandler.handle(new GetPersonQuery(id));
        if (people == null || people.isEmpty()) {
            ((HttpServletResponse)response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            chain.doFilter(request, response);
            return;
        }

        Person person = people.get(0);

        String credentials = getCredentials(((HttpServletRequest)request).getHeader(AUTHORIZATION_HEADER));
        if (StringUtils.isEmpty(credentials)) {
            ((HttpServletResponse)response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            chain.doFilter(request, response);
            return;
        }

        String[] splitCreds = credentials.split(":");
        String username = splitCreds[0];
        String password = splitCreds[1];
        if (StringUtils.isEmpty(username)
                || !username.equals(person.getHandle())
                || StringUtils.isEmpty(password)
                || !password.equals(person.getHandle())) {
            ((HttpServletResponse)response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            chain.doFilter(request, response);
            return;
        }

        chain.doFilter(request, response);
    }

    /**
     * Gets the decoded credentials string from the Authorization header
     * @param authHeader the Authorization HTTP header string
     * @return the decoded basic authentication string
     * @throws UnsupportedEncodingException if the string encoding doesn't match the expected type
     */
    private String getCredentials(String authHeader) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(authHeader)) {
            return null;
        }

        StringTokenizer st = new StringTokenizer(authHeader);
        if (!st.hasMoreTokens()) {
            return null;
        }

        String basic = st.nextToken();
        if (!basic.equalsIgnoreCase("Basic")) {
            return null;
        }

        return new String(Base64.decodeBase64(st.nextToken()), "UTF-8");
    }
}
