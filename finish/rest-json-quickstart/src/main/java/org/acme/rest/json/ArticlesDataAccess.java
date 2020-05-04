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
    private static final String ARTICLES_DNS = "articles-reactive";
    private static final int ARTICLES_PORT = 8080;

    private String articlesHost = ARTICLES_DNS;
    private int articlesPort = ARTICLES_PORT;

    private ArticlesService articlesService;

    @PostConstruct
    void initialize() {

        URI apiUrl = UriBuilder.fromUri("http://{host}:{port}/v2/articles").build(articlesHost, articlesPort);
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
