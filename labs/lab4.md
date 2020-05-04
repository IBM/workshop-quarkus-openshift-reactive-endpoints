Navigator:
* [Workshop Description](https://nheidloff.github.io/workshop-quarkus-openshift-reactive-messaging/)
* Lab 1: [Create your Cloud Environment](lab1.md)
* Lab 2: [Deploy Kafka via Script](lab2.md)
* Lab 3: [Deploy Postgres via Operator](lab3.md)
* Lab 4: Deploy Sample Application
* Lab 5: [Reactive Messaging with MicroProfile](lab5.md)
* Lab 6: [Server Sent Events](lab6.md)
* Lab 7: [Vert.x Event Bus](lab7.md)

---

# Lab 4: Deploy Sample Application

In this lab you'll deploy the sample application which consists of three microservices and a web application.

### Step 1: Create Project

Invoke the following command in the Cloud Shell to create a new project.

```
$ oc new-project cloud-native-starter
```

![sample app](../images/deploy-app0.png)

### Step 2: Deploy Services and Web Application

Invoke the following command in the Cloud Shell to deploy the 'Articles' service.

```
$ $ROOT_FOLDER/os4-scripts/deploy-articles-reactive-postgres-via-oc.sh
```

![sample app](../images/deploy-app1.png)

Invoke the following command in the Cloud Shell to deploy the 'Authors' service.

```
$ $ROOT_FOLDER/os4-scripts/deploy-authors-via-oc.sh
```

![sample app](../images/deploy-app2.png)

Invoke the following command in the Cloud Shell to deploy the 'Web-API' service.

```
$ $ROOT_FOLDER/os4-scripts/deploy-web-api-reactive-via-oc.sh
```

![sample app](../images/deploy-app3.png)

Invoke the following command in the Cloud Shell to deploy the web app.

```
$ $ROOT_FOLDER/os4-scripts/deploy-web-app-reactive-via-oc.sh
```

![sample app](../images/deploy-app4.png)

### Step 3: Verify the Installation 

Make sure all four pods in the 'cloud-native-starter' project are running. Note that it takes a couple of minutes until this happens.

![sample app](../images/verify-app1.png)

The previous steps have create build configs, builds and image streams.

![sample app](../images/verify-app2.png)

![sample app](../images/verify-app3.png)

![sample app](../images/verify-app4.png)

To launch the application get the URLs via the following command.

```
$ $ROOT_FOLDER/os4-scripts/show-urls.sh
```

![sample app](../images/verify-app5.png)

Open the web application in a browser. Then invoke the curl post command. The web application should show the new entry.

![sample app](../images/verify-app6.png)

---

__Continue with [Lab 5: Reactive Messaging with MicroProfile](lab5.md)__
