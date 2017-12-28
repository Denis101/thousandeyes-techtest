# ThousandEyes Tech Test - Denis Craig

A simple messaging REST API service written in Java 8.

# Notes
I built this app as if it was to be deployed and operated in a Production environment. Therefore have included a full
Continuous Integration pipeline and followed the [12 Factor Application](https://12factor.net/) pattern as best as I could.

# Todo

* Add unit tests for the following:
    * Commands
    * Queries
* Search query working for the messages endpoint
* Harden database access with a service user
* Add HTTP Basic auth to all the endpoints
* Write a pretty client
* Add service logging
* Add continuous integration

Nice to have (do these in separate branches and submit without):
* Distance to another user
* Endpoint for a list of all users sorted by popularity (do this in a single SQL query)