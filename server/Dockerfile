FROM ubuntu:16.04
MAINTAINER Denis Craig <admin@deniscraig.com>

# Update package cache and install the JDK (could make this smaller by only using the JRE)
RUN apt-get update && apt-get install -y apt-transport-https openjdk-8-jdk

# Create the application directory and copy the application over
RUN mkdir -p /app
WORKDIR /app
ADD ./build/libs/thousandeyes-techtest-1.0.0-SNAPSHOT.jar /app/thousandeyes-techtest.jar

# Setup our user to run the app
RUN adduser --disabled-password --gecos "ThousandEyes Tech Test" teuser
RUN chown -R teuser. /app
USER teuser

EXPOSE 8080

ENV JAVA_OPTS="-Xms1024M -Xmx2048M"
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/thousandeyes-techtest.jar"]