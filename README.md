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
### Follow the DBaaS documentation to setup PostgreSQL provider and Connection:
[RHODA/DBaaS](https://github.com/RHEcosystemAppEng/dbaas-operator)

## Building the demo

Modify the `src/main/resources/application.properties` for your database server, and then perform the following:

```shell
$ ./mvnw package
```
Make sure you have a PostgreSQL instance running (Quarkus automatically starts one for dev and test mode). 

```shell
# set up a local postgresql database:
$ docker run --rm=true --name quarkus_test -e POSTGRES_USER=quarkus_test -e POSTGRES_PASSWORD=quarkus_test -e POSTGRES_DB=quarkus_test -p 5432:5432 postgres:13.3
```

## Running the demo

```shell
$ ./mvnw quarkus:dev
```
