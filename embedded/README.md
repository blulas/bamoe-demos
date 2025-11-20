# IBM Business Automation Manager Open Editions Demos - Embedded Engine Projects

# Overview
The purpose of this repostitory module is to demonstrate how different types of BAMOE projects can utilize the embedded engine API's for things like rules, decision models, and stateless processes.  This architecture is mostly considered a `legacy` approach, since Drools has been around for over 20 years and some customers never adopted the remote KIE Server or Kogito approach.  Other reasons is that customer applications require the rules/data to be in the same process, with no remote calls whatsoever.  We call this the `BAMOE Embedded` approach.  

The following demonstration projects available are:

- [**Banking Deposit Rules**](./banking-deposit-rules) is is an example of an embedable rules (DRL or XLS) project.
- [**Banking Stateless Process**](./banking-process) is a is an example of an embedable stateless process (BPMN) project.
- [**Traffic Violation Decision Model**](./traffic-violation-decision-model) is an example of an embedable decision model (DMN) project.
- [**Customer Rules**](./customer-rules) is an example of an embeddable rules (DRL or XLS) project which utilizes legacy DRL features such as DSL's and rule templates.

## Additional Information (*Appendicies*)
This repository is focused on business automation using [**IBM Business Automation Manager Open Editions**](https://www.ibm.com/docs/en/ibamoe/9.3.x) products, specifically the IBM build of [**Kogito**](https://kogito.kie.org/) known as **IBM Decision Manager Open Edition (DMOE)** and **IBM Process Automation Manager Open Edition (PAMOE)**, leveraging [**Quarkus**](https://quarkus.io/) or [**Spring Boot**](https://spring.io/) as the assoicated container runtime.  The following online documentation is available in order to learn various aspects of these products and frameworks:

- [**Apache Maven**](https://maven.apache.org/) is a free and open source software project management and comprehension tool. Based on  the concept of a project object model (POM), Maven can manage a project’s build, reporting and documentation from a central piece of  information. A **getting started guide** is available [here](http://maven.apache.org/guides/getting-started/).

- [**Git**](https://git-scm.com//) is a free and open source distributed version control system designed to handle everything from small to very large projects with speed and efficiency. There is a **handbook** available [here](https://guides.github.com/introduction/git-handbook/), as well as various **guides** for learning and working with Git available [here](https://guides.github.com/)
