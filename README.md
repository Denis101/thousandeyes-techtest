# ThousandEyes Tech Test - Denis Craig

A simple messaging REST API service written in Java 8.

## Running

### Prerequisites
* java 8 JDK
* gradle
* node.js (to build the client)
* docker
* docker-compose

Both dockerfiles currently assume fully built artifacts exist. You can build them by running `make` in the root directory.

To run the full application, you can simply run `docker-compose up` in the root directory. This will build the client and server containers, which you can then access by navigating to `http://localhost` on your browser.

## Notes
I built this app as if it was to be deployed and operated in a Production environment. Therefore have included a full
Continuous Integration pipeline and followed the [12 Factor Application](https://12factor.net/) pattern as best as I could.

The client is built using Angular 5 and the angular-cli package. It's pretty rudimentary, and lacks proper authentication. You need to know the person's id, username, and password (both the handle in this case) to be able to login.
I did this as a bit of an excuse to learn and play around with Angular.

There a bunch of things I would like to do that I never had the time to, including:
* Environment variable configuration for the client, passed into the Docker container
* Dynamically adding the people to the authenticated endpoints
* Easily hookable secret management
* Swagger behind an admin authenticated endpoint (you can access it with any person's credentials)
* Hardening the database access properly, so the REST API has only the access it needs
* The authentication filter is a bit nasty, I feel like there's a better way to do this with Spring Security
* Unit test coverage isn't as good as it should be. This always seems to be the case though.. (no unit tests in client)
* Styling with SCSS and compiling as part of the webpack build

## API reference

You can access the Swagger docs by running the API locally and hitting the following URL:
```
http://localhost:8080/swagger-ui.html
```

### Get Messages

Gets a list of messages for the given person
```
/v1/person/{id}/messages[?search={search}]
```

curl example:
```
curl -H "Authorization: $(echo -n 'batman:batman' | openssl base64 | awk '{ print "Basic "$1 }')" http://localhost:8080/v1/person/1/messages?search=define
```

### Get Followers

Gets a list of followers for a given person
```
/v1/person/{id}/followers
```

curl example:
```
curl -H "Authorization: $(echo -n 'batman:batman' | openssl base64 | awk '{ print "Basic "$1 }')" http://localhost:8080/v1/person/1/followers
```

### Get Following

Gets a list of people the given person is following
```
/v1/person/{id}/following
```

curl example:
```
curl -H "Authorization: $(echo -n 'batman:batman' | openssl base64 | awk '{ print "Basic "$1 }')" http://localhost:8080/v1/person/1/following
```

### Follow

Set a person to follow a new person.
```
/v1/person/{id}/follow/{personToFollowId}
```

curl example:
```
curl -X PUT -H "Authorization: $(echo -n 'batman:batman' | openssl base64 | awk '{ print "Basic "$1 }')" http://localhost:8080/v1/person/1/follow/2
```

### Unfollow

Remove a person from a given person's following list.
```
/v1/person/{id}/follow/{personToUnfollowId}
```

curl example:
```
curl -X DELETE -H "Authorization: $(echo -n 'batman:batman' | openssl base64 | awk '{ print "Basic "$1 }')" http://localhost:8080/v1/person/1/follow/2
```

# Todo

* Add service logging
* Add continuous integration
