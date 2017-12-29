# ThousandEyes Tech Test - Denis Craig

A simple messaging REST API service written in Java 8.

## Notes
I built this app as if it was to be deployed and operated in a Production environment. Therefore have included a full
Continuous Integration pipeline and followed the [12 Factor Application](https://12factor.net/) pattern as best as I could.

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
curl http://localhost:8080/v1/person/1/messages?search=define
```

### Get Followers

Gets a list of followers for a given person
```
/v1/person/{id}/followers
```

curl example:
```
curl http://localhost:8080/v1/person/1/followers
```

### Get Following

Gets a list of people the given person is following
```
/v1/person/{id}/following
```

curl example:
```
curl http://localhost:8080/v1/person/1/following
```

### Follow

Set a person to follow a new person.
```
/v1/person/{id}/follow/{personToFollowId}
```

curl example:
```
curl -X PUT http://localhost:8080/v1/person/1/follow/2
```

### Unfollow

Remove a person from a given person's following list.
```
/v1/person/{id}/follow/{personToUnfollowId}
```

curl example:
```
curl -X DELETE http://localhost:8080/v1/person/1/follow/2
```

# Todo

* Harden database access with a service user
* Add HTTP Basic auth to all the endpoints
* Write a pretty client
* Add service logging
* Add continuous integration

Nice to have (do these in separate branches and submit without):
* Distance to another user
* Endpoint for a list of all users sorted by popularity (do this in a single SQL query)