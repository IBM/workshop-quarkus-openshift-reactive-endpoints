Navigator:
* [Workshop Description](https://nheidloff.github.io/workshop-quarkus-openshift-reactive-endpoints/)
* Lab 1: [Create your Cloud Environment](lab1.md)
* Lab 2: [Deploy Kafka via Script](lab2.md)
* Lab 3: [Deploy Postgres via Operator](lab3.md)
* Lab 4: [Deploy Sample Application](lab4.md)
* Lab 5: [Develop reactive Endpoints](lab5.md)
* Lab 6: Invoke Endpoints reactively
* Lab 7: [Deploy Service to OpenShift](lab7.md)
* Lab 8 (optional): [Use distributed Logging](lab8.md)

---

# Lab 6: Invoke Endpoints reactively

In this lab you will learn how to invoke REST APIs reactively with [MicroProfile Rest Client](https://github.com/eclipse/microprofile-rest-client).

You will extend the service from the previous lab to invoke the 'Articles' service which runs on OpenShift.

![](../images/lab6.png)

### Step 1: Create Exception Handling Classes

The great thing about the MicroProfile REST Client is that it makes it really easy to invoke remote APIs of other services. As developer you don't have to worry about serialization/deserialization/etc. All you need to do is to define interfaces and some configuration.

In order to map HTTP response codes to Java exceptions, a ResponseExceptionMapper is used. Let's take a look.

Create the class [InvalidInputParameter.java](https://github.com/nheidloff/workshop-quarkus-openshift-reactive-endpoints/blob/master/finish/rest-json-quickstart/src/main/java/org/acme/rest/json/InvalidInputParameter.java). This exception is thrown by the 'Articles' service when the amount parameter is not correct, for example if the value is negative.

```
$ cd ~/rest-json-quickstart/src/main/java/org/acme/rest/json/
$ touch InvalidInputParameter.java
$ nano InvalidInputParameter.java
```

```
package org.acme.rest.json;

public class InvalidInputParameter extends RuntimeException {

	private static final long serialVersionUID = 2L;

	public InvalidInputParameter() {
	}

	public InvalidInputParameter(String message) {
		super(message);
	}
}
```

Exit the Editor via 'Ctrl-X', 'y' and 'Enter'.

Create the class [ExceptionMapperArticles.java](https://github.com/nheidloff/workshop-quarkus-openshift-reactive-endpoints/blob/master/finish/rest-json-quickstart/src/main/java/org/acme/rest/json/ExceptionMapperArticles.java). In this class the HTTP response code '204' is mapped to the InvalidInputParameter exception.


```
$ cd ~/rest-json-quickstart/src/main/java/org/acme/rest/json/
$ touch ExceptionMapperArticles.java
$ nano ExceptionMapperArticles.java
```

```
package org.acme.rest.json;

import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionMapperArticles implements ResponseExceptionMapper<InvalidInputParameter> {

    @Override
    public boolean handles(int status, MultivaluedMap<String, Object> headers) {
        return status == 204;
    }

    @Override
    public InvalidInputParameter toThrowable(Response response) {
        if (response.getStatus() == 204)
            return new InvalidInputParameter();
        return null;
    }
}
```

Exit the Editor via 'Ctrl-X', 'y' and 'Enter'.

### Step 2: Create the ArticlesService Interface

Next an interface of the service that is supposed to be invoked is defined. The implementation of this interface is provided magically by MicroProfile.

Create the class [ArticlesService.java](https://github.com/nheidloff/workshop-quarkus-openshift-reactive-endpoints/blob/master/finish/rest-json-quickstart/src/main/java/org/acme/rest/json/ArticlesService.java). To keep this as simple as possible, there is only one method to read a list of articles.

Note that the annotations @Get and @Produces can be confusing. These are the JAX-RS annotations you used in the previous lab. This time however they are not used to expose REST APIs, but to define how to invoke remote APIs.

Also note that the service does not return a Response object directly. Instead it returns a CompletionStage object with a Response object as described earlier. With the MicroProfile Rest Client you can invoke services both synchronously as well as asynchronously.

```
$ cd ~/rest-json-quickstart/src/main/java/org/acme/rest/json/
$ touch ArticlesService.java
$ nano ArticlesService.java
```

```
package org.acme.rest.json;

import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.CompletionStage;

@RegisterProvider(ExceptionMapperArticles.class)
public interface ArticlesService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    CompletionStage<List<Article>> getArticlesFromService(@QueryParam("amount") int amount);
}
```

Exit the Editor via 'Ctrl-X', 'y' and 'Enter'.

### Step 3: Create the Code to invoke Services 

Now let's write the code to invoke the 'Articles' service. Basically all you need to do is to define the URL of the endpoint and invoke a Java method. Check out the code below, especially the invocation of the service via 'articlesService.getArticlesFromService(amount)'.

Create the class [ArticlesDataAccess.java](https://github.com/nheidloff/workshop-quarkus-openshift-reactive-endpoints/blob/master/finish/rest-json-quickstart/src/main/java/org/acme/rest/json/ArticlesDataAccess.java).


```
$ cd ~/rest-json-quickstart/src/main/java/org/acme/rest/json/
$ touch ArticlesDataAccess.java
$ nano ArticlesDataAccess.java
```

```
package org.acme.rest.json;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class ArticlesDataAccess {

    private static final int MAXIMAL_DURATION = 5000;

    // this configuration needs to be used when running the code in OpenShift
    private static String urlArticlesServiceLocal = "http://articles-reactive:8080/v2/articles?amount=10";    

    // this configuration needs to be used when running this web-api service locally
    // run the following command to get this URL: os4scripts/show-urls.sh
    private static String urlArticlesServiceOpenShift = "http://articles-reactive-cloud-native-starter.niklas-heidloff-os-fra-162e406f043e20da9b0ef0731954a894-0000.eu-de.containers.appdomain.cloud/v2/articles?amount=10";

    private ArticlesService articlesService;

    @PostConstruct
    void initialize() {
        URI apiUrl = UriBuilder.fromUri(urlArticlesServiceOpenShift).build();
        articlesService = RestClientBuilder.newBuilder()
                .baseUri(apiUrl)
                .register(ExceptionMapperArticles.class)
                .build(ArticlesService.class);
    }

    public CompletionStage<List<Article>> getArticlesReactive(int amount) {
        return articlesService.getArticlesFromService(amount)
                .toCompletableFuture()
                .orTimeout(MAXIMAL_DURATION, TimeUnit.MILLISECONDS);
    }
}
```

In a second terminal run the following command to the the URL of your 'Articles' service.

```
$ cd $ROOT_FOLDER
$ os4scripts/show-urls.sh
```

![](../images/get-url.png)

Copy and paste the URL in the editor as the value of the varialbe 'urlArticlesServiceOpenShift'.

Exit the Editor via 'Ctrl-X', 'y' and 'Enter'.

In the last step you need to modify [ArticleResource.java](https://github.com/nheidloff/workshop-quarkus-openshift-reactive-endpoints/blob/master/finish/rest-json-quickstart/src/main/java/org/acme/rest/json/ArticleResource.java) from the previous lab to invoke the actual service rather than returning a sample article.

```
$ cd ~/rest-json-quickstart/src/main/java/org/acme/rest/json/
$ nano ArticleResource.java
```

```
    @Inject
    ArticlesDataAccess articlesDataAccess;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CompletionStage<Response> getArticles() {
        
        CompletableFuture<Response> future = new CompletableFuture<Response>();        

        articlesDataAccess.getArticlesReactive(10).thenApply(articles -> {
            JsonArray articlesAsJson;
            articlesAsJson = articles
                                .stream()
                                .map(article -> createJsonArticle(article))
                                .collect(JsonCollectors.toJsonArray());            
            
            return Response.ok(articlesAsJson).build();
        }).exceptionally(throwable -> {
            if (throwable.getCause().toString().equals(InvalidInputParameter.class.getName()))
                return Response.status(Response.Status.BAD_REQUEST).build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }).whenComplete((response, e) -> {
            future.complete(response);
        });
        return future;
    }
```



---

__Continue with [Lab 7: Deploy Service to OpenShift](lab7.md)__
