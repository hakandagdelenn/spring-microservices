## Fleet Management

## Dependency Management

Infrastructure established to manage dependency management to be used jointly in microservice architectures.

## Config Server

Config module, which is established to manage the configs of the dependent project in microservices from a single place.

## Open Source Tools

[MapStruct](https://mapstruct.org/documentation/stable/reference/html/) --> Open source tool used to manage mapper
methods used in projects

## Database Management

Mysql --> Open source database management system, which is used to manage the database of the project Note:
* In the project, one to many relationships were not used from an administrative point of view. * You can review the url
that explains the cause of the problem caused by the One To Many relationship [//]: #(
Path: https://stackoverflow.com/questions/30464782/how-to-maintain-bi-directional-relationships-with-spring-data-rest-and-jpa/30474303#30474303
; https://reflectoring.io/relations-with-spring-data-rest/#manyToOne-request)
* To install mysql, config must be run in the docker compose.yml file.

## Exception Handling

[ExceptionHandling](https://www.baeldung.com/rest-api-error-handling-best-practices?utm_campaign=rest&utm_content=exceptions&utm_medium=email&utm_source=email)
--> Example article for the exception handling management used.

## Ports

fleet-management : 5002; config-server : 8888;

## Arch Diagram

[ArchDiagramLink](https://app.diagrams.net/#G1MPvgTltmme8aSc9P3JpiO1telnl9qz-v)