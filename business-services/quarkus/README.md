# IBM Business Automation Manager Open Editions Demos - Quarkus Business Service Projects

# Overview
The purpose of this module module is to provide a set of business service demonstrations, which can be run as standalone or as a container deployed to Kubernetes, using the Quarkus runtime.

## Business Service
A Java application embedding Business Models, such as Workflows (BPMN), Decisions (DMN), Rules (DRL), or spreadsheet Decision Tables (XLS or XLSX). A Business Service also contains BAMOE libraries that power the execution of those Business models, making REST and/or GraphQL API endpoints available for system-to-system integration. A Business Service is a microservice built on top of Quarkus or Spring Boot.

## Business Service project
A Java project that is modelled as an Apache Maven project, and that contains all the artifacts needed to build a Business Service using the BAMOE components. It may include any or all of the following artifacts: BPMN, DMN, DRL rules, DRL decision tables, SCESIM test scenarios, as well as Java Data models. It can be stored in any of the supported source code controls systems such as Git and built into a Business Service.

The following demonstration projects available are:

- [**Quarkus Banking Deposit Service**](./banking-deposit-service) is a Quarkus-based business service which demonstrates the use of simple banking deposit rules.
- [**Quarkus Banking Service**](./banking-service) is a Quarkus-based business service which demonstrates the use of simple banking deposit rules.
- [**Quarkus Hiring Service**](./hiring-service) is a Quarkus-based business service which demonstrates a simple hiring business process.
- [**Quarkus Traffic Violoation Service**](./traffic-violation-service) is a Quarkus-based business service which demonstrates the use of a decision model which determins a driver's license status.

## Additional Information (*Appendicies*)
This repository is focused on business automation using [**IBM Business Automation Manager Open Editions**](https://www.ibm.com/docs/en/ibamoe/9.3.x) products, specifically the IBM build of [**Kogito**](https://kogito.kie.org/) known as **IBM Decision Manager Open Edition (DMOE)** and **IBM Process Automation Manager Open Edition (PAMOE)**, leveraging [**Quarkus**](https://quarkus.io/) or [**Spring Boot**](https://spring.io/) as the assoicated container runtime.  The following online documentation is available in order to learn various aspects of these products and frameworks:

- [**Apache Maven**](https://maven.apache.org/) is a free and open source software project management and comprehension tool. Based on  the concept of a project object model (POM), Maven can manage a project’s build, reporting and documentation from a central piece of  information. A **getting started guide** is available [here](http://maven.apache.org/guides/getting-started/).

- [**Git**](https://git-scm.com//) is a free and open source distributed version control system designed to handle everything from small to very large projects with speed and efficiency. There is a **handbook** available [here](https://guides.github.com/introduction/git-handbook/), as well as various **guides** for learning and working with Git available [here](https://guides.github.com/)

- [**Quarkus**](https://quarkus.io/) - Traditional Java stacks were engineered for monolithic applications with long startup times and large memory requirements in a world where the cloud, containers, and Kubernetes did not exist. Java frameworks needed to evolve to meet the needs of this new world.  Quarkus was created to enable Java developers to create applications for a modern, cloud-native world. Quarkus is a Kubernetes-native Java framework tailored for GraalVM and HotSpot, crafted from best-of-breed Java libraries and standards. The goal is to make Java the leading platform in Kubernetes and serverless environments while offering developers a framework to address a wider range of distributed application architectures.  You can find a useful introdution to this technology at [**Getting Started with Quarkus**](https://quarkus.io/get-started/).
