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

    // Java 8
    public CompletionStage<List<Article>> getArticlesReactive(int amount) {
        return articlesService.getArticlesFromService(amount);
    }

    /*
    // Java 9 or higher
    public CompletionStage<List<Article>> getArticlesReactive(int amount) {
        return articlesService.getArticlesFromService(amount)
                .toCompletableFuture()
                .orTimeout(MAXIMAL_DURATION, TimeUnit.MILLISECONDS);
    }
    */
}
