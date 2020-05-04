Navigator:
* [Workshop Description](https://nheidloff.github.io/workshop-quarkus-openshift-reactive-messaging/)
* Lab 1: [Create your Cloud Environment](lab1.md)
* Lab 2: [Deploy Kafka via Script](lab2.md)
* Lab 3: Deploy Postgres via Operator
* Lab 4: [Deploy Sample Application](lab4.md)
* Lab 5: [Reactive Messaging with MicroProfile](lab5.md)
* Lab 6: [Server Sent Events](lab6.md)
* Lab 7: [Vert.x Event Bus](lab7.md)

---

# Lab 3: Deploy Postgres via Operator

In this lab you'll deploy Postgres and set up a database which is used by the 'Articles' service.

### Step 1: Create Project

In the Cloud Shell enter the following command.

```
$ oc new-project postgres
```

![kafka deployment](../images/setup-postgres1.png)

### Step 2: Install the Postgres Operator

Open the OperatorHub page and filter by 'postgres'. Open the operator 'PostgreSQL Operator by Dev4devs.com'.

![kafka deployment](../images/setup-postgres2.png)

Click 'Install'.

![kafka deployment](../images/setup-postgres3.png)

Create a subscription. Make sure your new project 'postgres' is selected in the combobox.

![kafka deployment](../images/setup-postgres4.png)

### Step 3: Create the Database

Click on the operator.

![kafka deployment](../images/setup-postgres5.png)

Click on 'Create Instance' in the 'Database Database' box.

![kafka deployment](../images/setup-postgres6.png)

Edit the yaml. The database name needs to be changed to  'database-articles'. The namespace should be 'postgres' by default. After this click 'Create'.

![kafka deployment](../images/setup-postgres7.png)

### Step 4: Verify the Installation 

On the 'Pods' page select the project 'postgres'. Make sure the pod 'database-articles....' is running.

![kafka deployment](../images/setup-postgres10.png)

---

__Continue with [Lab 4: Deploy Sample Application](lab4.md)__
