# fetch basic image
FROM maven:3.3.9-jdk-8

#Set environment variable for setting host to 0.0.0.0
ENV HOST='0.0.0.0'
# application placed into /opt/app
RUN mkdir -p /opt/app
WORKDIR /opt/app

# selectively add the POM file and
# install dependencies
COPY pom.xml /opt/app/
RUN mvn install

# rest of the project
COPY src /opt/app/src
RUN mvn package

# execute it
CMD ["mvn", "exec:java"]

