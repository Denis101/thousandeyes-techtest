package challenge.spring;

import challenge.filter.PersonAuthenticationFilter;
import challenge.persistence.query.PeopleQueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan(basePackages = {"challenge"})
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(SecurityConfiguration.class);

    private static final String REALM = "TESERVER";
    private static final String USER_ROLE = "USER";

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//        NOTE: I wanted to configure the security roles to get the users from the database, had issues
//        as the Spring Security filters were being initialised before the data migration script runs.
//
//        PeopleQueryHandler handler = getApplicationContext().getBean(PeopleQueryHandler.class);
//        List<Person> people = handler.handle(new GetAllPeopleQuery());
//        if (people == null || people.isEmpty()) {
//            return;
//        }
//
//        for (Person person : people) {
//            auth.inMemoryAuthentication().withUser(person.getHandle()).password(person.getHandle()).roles(USER_ROLE);
//        }

        auth.inMemoryAuthentication().withUser("batman").password("batman").roles(USER_ROLE);
        auth.inMemoryAuthentication().withUser("superman").password("superman").roles(USER_ROLE);
        auth.inMemoryAuthentication().withUser("catwoman").password("catwoman").roles(USER_ROLE);
        auth.inMemoryAuthentication().withUser("daredevil").password("daredevil").roles(USER_ROLE);
        auth.inMemoryAuthentication().withUser("alfred").password("alfred").roles(USER_ROLE);
        auth.inMemoryAuthentication().withUser("dococ").password("dococ").roles(USER_ROLE);
        auth.inMemoryAuthentication().withUser("zod").password("zod").roles(USER_ROLE);
        auth.inMemoryAuthentication().withUser("spiderman").password("spiderman").roles(USER_ROLE);
        auth.inMemoryAuthentication().withUser("ironman").password("ironman").roles(USER_ROLE);
        auth.inMemoryAuthentication().withUser("profx").password("profx").roles(USER_ROLE);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/h2-console/*").permitAll();

        http.authorizeRequests()
            .anyRequest().authenticated()
            .and().httpBasic().realmName(REALM)
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.csrf().disable();
        http.headers().frameOptions().disable();

        http.addFilterAfter(
                new PersonAuthenticationFilter(getApplicationContext().getBean(PeopleQueryHandler.class)),
                BasicAuthenticationFilter.class);
    }

    @Bean
    BasicAuthenticationFilter basicAuthFilter(AuthenticationManager authenticationManager, BasicAuthenticationEntryPoint basicAuthEntryPoint) {
        return new BasicAuthenticationFilter(authenticationManager, basicAuthEntryPoint());
    }

    @Bean
    BasicAuthenticationEntryPoint basicAuthEntryPoint() {
        BasicAuthenticationEntryPoint bauth = new BasicAuthenticationEntryPoint();
        bauth.setRealmName(REALM);
        return bauth;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
            }
        };
    }
}