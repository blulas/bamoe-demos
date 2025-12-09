# IBM Business Automation Manager Open Editions Demos - Spring Boot Business Service Projects

# Overview
The purpose of this module module is to provide a set of business service demonstrations, which can be run as standalone or as a container deployed to Kubernetes, using the Spring Boot runtime.

## Business Service
A Java application embedding Business Models, such as Workflows (BPMN), Decisions (DMN), Rules (DRL), or spreadsheet Decision Tables (XLS or XLSX). A Business Service also contains BAMOE libraries that power the execution of those Business models, making REST and/or GraphQL API endpoints available for system-to-system integration. A Business Service is a microservice built on top of Quarkus or Spring Boot.

## Business Service project
A Java project that is modelled as an Apache Maven project, and that contains all the artifacts needed to build a Business Service using the BAMOE components. It may include any or all of the following artifacts: BPMN, DMN, DRL rules, DRL decision tables, SCESIM test scenarios, as well as Java Data models. It can be stored in any of the supported source code controls systems such as Git and built into a Business Service.

The following demonstration projects available are:

- [**Spring Boot Banking Deposit Service**](./banking-deposit-service) is a Quarkus-based business service which demonstrates the use of simple banking deposit rules.
- [**Spring Boot Hiring Service**](./hiring-service) is a Quarkus-based business service which demonstrates a simple hiring business process.
- [**Spring Boot Traffic Violoation Service**](./traffic-violation-service) is a Quarkus-based business service which demonstrates the use of a decision model which determins a driver's license status.

## Additional Information (*Appendicies*)
This repository is focused on business automation using [**IBM Business Automation Manager Open Editions**](https://www.ibm.com/docs/en/ibamoe/9.3.x) products, specifically the IBM build of [**Kogito**](https://kogito.kie.org/) known as **IBM Decision Manager Open Edition (DMOE)** and **IBM Process Automation Manager Open Edition (PAMOE)**, leveraging [**Quarkus**](https://quarkus.io/) or [**Spring Boot**](https://spring.io/) as the assoicated container runtime.  The following online documentation is available in order to learn various aspects of these products and frameworks:

- [**Apache Maven**](https://maven.apache.org/) is a free and open source software project management and comprehension tool. Based on  the concept of a project object model (POM), Maven can manage a project’s build, reporting and documentation from a central piece of  information. A **getting started guide** is available [here](http://maven.apache.org/guides/getting-started/).

- [**Git**](https://git-scm.com//) is a free and open source distributed version control system designed to handle everything from small to very large projects with speed and efficiency. There is a **handbook** available [here](https://guides.github.com/introduction/git-handbook/), as well as various **guides** for learning and working with Git available [here](https://guides.github.com/)

- [**Spring Boot**](https://spring.io/) - Spring makes programming Java quicker, easier, and safer for everybody. Spring’s focus on speed, simplicity, and productivity has made it the world's most popular Java framework.  Spring’s flexible libraries are trusted by developers all over the world. Spring delivers delightful experiences to millions of end-users every day.  Spring’s flexible and comprehensive set of extensions and third-party libraries let developers build almost any application imaginable.  Spring Boot transforms how you approach Java programming tasks, radically streamlining your experience. Spring Boot combines necessities such as an application context and an auto-configured, embedded web server to make microservice development a cinch. To go even faster, you can combine Spring Boot with Spring Cloud’s rich set of supporting libraries, servers, patterns, and templates, to safely deploy entire microservices-based architectures into the cloud, in record time.  You can find a useful introdution to this technology at [**Getting Started with Spring Boot**](https://spring.io/quickstart).
