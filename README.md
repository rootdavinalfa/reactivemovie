# Reactive Movie

This is an example of simple reactive API. Using Spring Boot 2.7

## How to
### What to install

1. Gradle (You can use the wrapper)
2. Java 17+
3. Liquibase (Optional)

### Configuration
You must change the configuration according to your environment. On application.properties and build.gradle on liquibase section

### Generate Table
Because of r2dbc still can't generate schema from code. We use liquibase as our database changelog.
`gradle update` to generate table or just run the application (Need change the application.properties liquibase)

### Run the application

You can run the application with `gradle bootRun` for running it without building a jar. or run `gradle bootJar` to build jar file and grab the jar file on build/libs

## Swagger Documentation
Swagger can be found at /swagger-ui.html

## Technology

### Reactive
We leverage reactive technology to provide non-blocking API from db to endpoint
### R2DBC
We use R2DBC because JDBC is blocking API. R2DBC leverage reactive, so we can achieve pure reactive service without blocking our API.
### Swagger using SpringDoc
We use springdoc instead of SpringFox because without some 'hack' springfox can't boot up the SpringBoot 2.6+, so we migrated to springdoc for ease configuration, still need to learn though.
### Liquibase
Liquibase is a version control for database. But in this repository, we use this for seeding the data and update our schema. Just that.

## Note
This repository is far from perfect. So your input may improve this example.