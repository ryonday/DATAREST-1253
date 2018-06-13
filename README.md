# Spring-Data-REST `PUT` is Broken with DynamoDB #

This project uses the neat [Spring-Data-DynamoDB](https://github.com/derjust/spring-data-dynamodb) project.

Note that PUT operations work well with Spring Boot 1.5.x (Spring-Data-REST 2).

I've traced through both versions side-by-side, and for whatever reason, Spring Data REST tries to convert 
the primary key using a hard-coded `ConversionService` (this is at `PersistentEntityResourceHandlerMethodArgumentResolver:68`).

The version of Spring-Data-REST used in Spring Boot 1.5.x instead used a pathway that used the fully-fledged converter
subject to the Spring Data REST configuration (please see `RestConfig` in this project for where I register this converter).


## How-to-run ##

I am using the embedded DynamoDB for this project. It requires SQLite libraries to be available in a known spot.
The Gradle `test` task will copy these to the build output directory from the test dependencies configuration.
This means that even when running these tests from, say, IntelliJ, you'll need to manually execute the `test`
task first, before the application will come up.

I have a test (`SdrPutIsBrokeApplicationTests::put_is_broken`) that illustrates this problem. 
