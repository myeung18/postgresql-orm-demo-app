# Quarkus demo app for CockraochDB cloud and PostgreSQL: Hibernate ORM and RESTEasy

## Requirements

To compile and run this demo you will need:

- JDK 11+
- GraalVM

## Running the demo in Openshift with Service Binding

Modify the `src/main/resources/application.properties` for your docker image url, and then perform the following:
```shell
$ ./mvnw clean package -DskipTests -Dquarkus.container-image.push=true

$ oc apply -f src/deploy-app.yaml
```

## Building the demo

Modify the `src/main/resources/application.properties` for your database server, and then perform the following:

```shell
$ ./mvnw package
```
We need to make sure you have a PostgreSQL instance running (Quarkus automatically starts one for dev and test mode). To set up a PostgreSQL database with Docker:

```shell
$ docker run --rm=true --name quarkus_test -e POSTGRES_USER=quarkus_test -e POSTGRES_PASSWORD=quarkus_test -e POSTGRES_DB=quarkus_test -p 5432:5432 postgres:13.3
```

## Running the demo

```shell
$ ./mvnw quarkus:dev
```

### Run Quarkus in JVM mode

When you're done iterating in developer mode, you can run the application as a
conventional jar file.

First compile it:

```
./mvnw package
```

Next we need to make sure you have a PostgreSQL instance running (Quarkus automatically starts one for dev and test mode). To set up a PostgreSQL database with Docker:

```shell
docker run --rm=true --name quarkus_test -e POSTGRES_USER=quarkus_test -e POSTGRES_PASSWORD=quarkus_test -e POSTGRES_DB=quarkus_test -p 5432:5432 postgres:13.3
```

Connection properties for the datasource are defined in the standard Quarkus configuration file,
`src/main/resources/application.properties`.

Then run it:

```shell
java -jar ./target/quarkus-app/quarkus-run.jar
```

