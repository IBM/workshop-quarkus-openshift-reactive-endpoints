Navigator:
* [Workshop Description](https://nheidloff.github.io/workshop-quarkus-openshift-reactive-endpoints/)
* Lab 1: Create your Cloud Environment
* Lab 2: [Deploy Kafka via Script](lab2.md)
* Lab 3: [Deploy Postgres via Operator](lab3.md)
* Lab 4: [Deploy Sample Application](lab4.md)
* Lab 5: [Develop reactive Endpoints](lab5.md)
* Lab 6: [Invoke Endpoints reactively](lab6.md)
* Lab 7: [Deploy Service to OpenShift](lab7.md)
* Lab 8 (optional): [Use distributed Logging](lab8.md)

---

# Lab 1: Create your Cloud Environment

The main instructions of this workshop assume that you will use Red Hat OpenShift 4.3 on IBM Cloud. However you can also use [CodeReady Containers](https://github.com/code-ready/crc) to run OpenShift locally.

To use OpenShift on IBM Cloud and LogDNA in lab 8, an [IBM Cloud account](http://ibm.biz/nheidloff) is needed. It's free, doesn't expire and for the lite account no credit card is required.

We will use preconfigured [OpenShift on IBM Cloud](https://cloud.ibm.com/kubernetes/catalog/openshiftcluster) clusters in this hands-on workshop. You should have received information from your lab instructor to get access to one of these clusters.

### Step 1: Set up Terminal

When using OpenShift on IBM Cloud no client side setup is required for this workshop. Instead we will use the IBM Cloud Shell (Beta) which comes with all necessary CLIs (command line tools).

In your browser, login to the [IBM Cloud](https://cloud.ibm.com) Dashboard. Make sure you select your own account in the account list at the top, then click on the IBM Cloud Shell icon.

![](../images/cloud-shell-launch.png)

Note: Your workspace includes 500 MB of temporary storage. This session will close after an hour of inactivity. If you don't have any active sessions for an hour or you reach the 30-hour weekly usage limit, your workspace data is removed.

This is what you should see:

![](../images/cloud-shell.png)

When using OpenShift locally, you need a local terminal and the following tools: 

* [git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
* [curl](https://curl.haxx.se/download.html)
* [oc](https://docs.openshift.com/container-platform/4.3/welcome/index.html)
* [mvn](https://maven.apache.org/ref/3.6.3/maven-embedder/cli.html)
* Java 9 or higher

### Step 2: Get the Code

In the IBM Cloud Shell execute the following command:

```
$ git clone https://github.com/IBM/cloud-native-starter.git
```

![](../images/cloud-shell-clone.png)

### Step 3. Get Access to OpenShift

Open the [IBM Cloud Dashboard](https://cloud.ibm.com). In the row at the top switch from your **own** account to the **IBM account** given to you by the instructor from the pulldown in the uper right corner.

The select 'OpenShift' in the burger menu in the upper left corner followed by 'Clusters'.

![Select Open Shift in the menu](../images/openshift-console-launch1.png)

Click on your cluster.

![C](../images/openshift-console-launch2.png)

Open the OpenShift web console.

![Open the OpenShift web console](../images/openshift-console-launch3.png)

From the dropdown menu in the upper right of the page, click 'Copy Login Command'. 

![Key](../images/openshift-login1.png)

Click on 'Display Token', then copy and paste the command 'Log in with this token' into your terminal in the IBM Cloud Shell.

![Key](../images/openshift-login2.png)

Login to OpenShift in IBM Cloud Shell

```
$ oc login https://c1XX-XX-X.containers.cloud.ibm.com:XXXXX --token=xxxxxx'
```

![oc login in cloudshell](../images/openshift-login3.png)

---

__Continue with [Lab 2: Deploy Kafka via Script](lab2.md)__
