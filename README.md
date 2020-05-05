# Workshop: Reactive Endpoints with Quarkus on OpenShift

In this workshop you'll learn how to implement reactive endpoints with Java, Quarkus and MicroProfile. An end-to-end sample application will be deployed to Red Hat OpenShift on IBM Cloud.

The code is available as open source as part of the [Cloud Native Starter](https://github.com/IBM/cloud-native-starter/tree/master/reactive) project. 

One key benefit of reactive systems and reactive REST endpoints is efficiency. This workshop describes how to use reactive systems and reactive programming to achieve faster response times. Especially in public clouds where costs depend on CPU, RAM and compute durations this model saves money.

The workshop uses a sample application to demonstrate reactive functionality. The simple application displays links to articles and author information.

The sample contains a 'Web-API' service with two versions of the endpoint '/articles', one uses imperative code, the other one reactive code. In this workshop you'll re-implement a simplified version of the reactive version yourselves.

The reactive stack of this sample provides response times that take less than half of the time compared to the imperative stack: Reactive: 793 ms - Imperative: 1956 ms. If you want to know more details, you can read the [documentation](https://github.com/IBM/cloud-native-starter/blob/master/reactive/documentation/LoadTests.md) of the performance tests.

This diagram explains the complete flow. The 'API Client' invokes the 'Web-API' service which implements a backend-for-frontend pattern. The 'Web-API' service invokes the two microservices 'Authors' and 'Articles'. The 'Articles' service reads data from Postgres.

<kbd><img src="images/architecture2.png" /></kbd>

Note that in this workshop you will deploy the full application as described in the previous diagram. But to simplify the workshop you'll re-implement a simpler version of the 'Web-API' service which only invokes the 'Articles' service.

<kbd><img src="images/architecture1.png" /></kbd>

## Objectives

After you complete this workshop, you'll understand the following reactive functionality:

* Reactive REST endpoints via CompletionStage
* Exception handling in chained reactive invocations
* Timeouts via CompletableFuture
* Reactive REST invocations via MicroProfile REST Client

This workshop is for beginners and takes one hour.

*The intention of this workshop is not to explain every aspect of reactive programming, but to explain core reactive principles and to deploy a complete reactive application which you can inspect after the workshop in more detail.*

## Get Started

These are the labs of this workshop, go through all of them in sequence, start with lab 1:

* Lab 1: [Create your Cloud Environment](labs/lab1.md)
* Lab 2: [Deploy Kafka via Script](labs/lab2.md)
* Lab 3: [Deploy Postgres via Operator](labs/lab3.md)
* Lab 4: [Deploy Sample Application](labs/lab4.md)
* Lab 5: [Develop reactive Endpoints](labs/lab5.md)
* Lab 6: [Invoke Endpoints reactively](labs/lab6.md)
* Lab 7: [Deploy Service to OpenShift](labs/lab7.md)
* Lab 8 (optional): [Use distributed Logging](labs/lab8.md)

## What to do next

The [presentation](images/ReactiveMicroservices.pdf) as well as the [blogs](https://github.com/IBM/cloud-native-starter/tree/master/reactive#blogs) describe the functionality in more detail.

There is a second workshop which uses the same sample application. That workshop [Reactive Messaging with Quarkus on OpenShift](https://nheidloff.github.io/workshop-quarkus-openshift-reactive-messaging/) focusses on messaging with Kafka.

