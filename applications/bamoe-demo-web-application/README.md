# IBM Business Automation Manager Open Editions Demos - BAMOE Demo Web Application
This module contains an example `Tomcat Web Application`, which in turn invokes rules and business services in multiple ways, both embedded as well as remotely.

## How To Build 
Once you have configured your local development environment, you need to perform a Maven `build` of the repository.  This repository is built using `mvn clean install` by either the CI/CD pipeline or on a local developer workstation.  If deploying artifacts to an enterprise Maven repository, please use `mvn clean deploy`, which requires configuration of the `distributionManagement` section of your project's parent pom.xml.  This project is also configured to generate container images, as in the following example:

```shell
    ./deploy.sh
```

## How To Run & Test
Once you have deployed the web archive (.war) to your Tomcat server, you can launch the web application at the starting URL:  [BAMOE Demo Web Application](http://localhost:8080/bamoe-demo-web-application).  There are two major tesing areas of the web application, one which invokes rules [Banking Deposit Validation](http://localhost:8080/bamoe-demo-web-application/deposits.jsp) and one that invokes a decision model [Traffic Violations](http://localhost:8080/bamoe-demo-web-application/trafficViolation.jsp).  From there, use the supplied radio buttons to select if the rules/decision models are executed as `embedded` or `remote`.  If testing remote, be sure to start the following business services:

- - [**Banking Deposit Service**](../../business-services/quarkus/banking-deposit-service), which is a business service that depends on the embedded [Banking Deposit Rules](../../embedded/banking-deposit-rules/) project.  
- - [**Trafic Violation Service**](../../business-services/quarkus/traffic-violation-service), which is a business service that depends on the embedded [Traffic Violation Decision Model](../../embedded/traffic-violation-decision-model/) project.  

## Additional Information (*Appendicies*)
This repository is focused on business automation using [**IBM Business Automation Manager Open Editions**](https://www.ibm.com/docs/en/ibamoe/9.2.x) products, specifically the IBM build of [**Kogito**](https://kogito.kie.org/) known as **IBM Decision Manager Open Edition (DMOE)** and **IBM Process Automation Manager Open Edition (PAMOE)**, leveraging [**Quarkus**](https://quarkus.io/) or [**Spring Boot** _(currently for Decisions only)_](https://spring.io/) as the assoicated container runtime.  The following online documentation is available in order to learn various aspects of these products and frameworks:

- [**Apache Maven**](https://maven.apache.org/) is a free and open source software project management and comprehension tool. Based on  the concept of a project object model (POM), Maven can manage a project’s build, reporting and documentation from a central piece of  information. A **getting started guide** is available [here](http://maven.apache.org/guides/getting-started/).

- [**Git**](https://git-scm.com//) is a free and open source distributed version control system designed to handle everything from small to very large projects with speed and efficiency. There is a **handbook** available [here](https://guides.github.com/introduction/git-handbook/), as well as various **guides** for learning and working with Git available [here](https://guides.github.com/)
