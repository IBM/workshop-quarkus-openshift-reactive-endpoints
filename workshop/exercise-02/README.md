# Execise 2: Deploy Postgres via Operator

In this lab you'll deploy Postgres and set up a database which is used by the 'Articles' service.

### Step 1: Create Project

In the Cloud Shell enter the following command.

```
$ oc new-project postgres
```

![](../../images/setup-postgres1.png)

### Step 2: Install the Postgres Operator

Open the OperatorHub page and filter by 'postgres'. Open the operator 'PostgreSQL Operator by Dev4devs.com'.

![](../../images/setup-postgres2.png)

Click 'Install'.

![](../../images/setup-postgres3.png)

Create a subscription. Make sure your new project 'postgres' is selected in the combobox.

![](../../images/setup-postgres4.png)

### Step 3: Create the Database

Click on the operator.

![](../../images/setup-postgres5.png)

Click on 'Create Instance' in the 'Database Database' box.

![](../../images/setup-postgres6.png)

Edit the yaml. The database name needs to be changed to  'database-articles'. The namespace should be 'postgres' by default. After this click 'Create'.

![](../../images/setup-postgres7.png)

### Step 4: Verify the Installation 

On the 'Pods' page select the project 'postgres'. Make sure the pod 'database-articles....' is running.

![](../../images/setup-postgres10.png)
