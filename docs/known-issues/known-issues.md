# Known Issues

### (2021/02/16) Quarkus needs Apache Maven 3.6.2+, which is not included in IBM Cloud Shell

Thanks to Sbusiso Mkhombe and Karim Deif, because they pointed this out and documented the workaround for the Cloud Shell.

* Information in [Quarkus](https://quarkus.io/get-started/)

![](../images/i-quarkus-01.png)

* Problem in IBM Cloud Shell

![](../images/i-quarkus-02.png)


#### Setup Maven in the IBM Cloud Shell

So in you `cloud-native-starter/reactive` directory perform the following tasks:

##### Step 1: Change to your reactive directory in your cloud-native-starter project.

```sh
cd ~/cloud-native-starter/reactive
```

##### Step 2: Download the latest mvn as a compressed file.

```sh
wget https://downloads.apache.org/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz
```

##### Step 3: Uncompress the Apache Maven file and create adirectory in the same location called apache-maven-3.6.3

```sh
tar -xvf apache-maven-3.6.3-bin.tar.gz
```

##### Step 4: Add the mvn binary located in apache-maven-3.6.3/bin to the $PATH variable

```sh
export PATH=~/cloud-native-starter/reactive/apache-maven-3.6.3/bin:$PATH
```

##### Step 5: Verify the used Maven version

 This should return a message that shows you are using mvn version like 3.6.3

```sh
 mvn --version
```
  
Continue with the execises as usual.

#### Setup a local environment

To run the development exercises can you also to setup following tools on your machine:

* [git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
* [curl](https://curl.haxx.se/download.html)
* [oc](https://docs.openshift.com/container-platform/4.3/welcome/index.html)
* [mvn](https://maven.apache.org/ref/3.6.3/maven-embedder/cli.html)
* Java 9 or higher

or check out this [Docker image](https://hub.docker.com/r/tsuedbroecker/cns-workshop-tools) which contains the actual tools.