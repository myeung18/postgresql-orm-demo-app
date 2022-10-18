# Quarkus (Hibernate ORM and RESTEasy) demo app for PostgreSQL compatible databases

## Requirements

To compile and run this demo app you will need:

- JDK 11+
- GraalVM

## Running the demo App in Openshift with Service Binding

Modify the `src/main/resources/application.properties` for your docker image url, and then perform the following:
```shell
$ ./mvnw clean package -DskipTests -Dquarkus.container-image.push=true

$ oc apply -f src/deploy-app.yaml
```

### Follow the documentation in [RHODA/DBaaS](https://github.com/RHEcosystemAppEng/dbaas-operator) to setup
PostgreSQL/CockroachDB provider account and connection. After that, you can use SBO to bind the connection to this demo app.

## Building the demo

Modify the `src/main/resources/application.properties` for your database server, and then perform the following:

```shell
$ ./mvnw package
```
Make sure there is a PostgreSQL instance running (Quarkus automatically starts one for dev and test mode). 

```shell
# To set up a PostgreSQL database with Docker:
$ docker run --rm=true --name quarkus_test -e POSTGRES_USER=quarkus_test -e POSTGRES_PASSWORD=quarkus_test -e POSTGRES_DB=quarkus_test -p 5432:5432 postgres:13.3
```

## Running the demo

```shell
$ ./mvnw quarkus:dev
```
