package com.lvd.rest;

import com.lvd.domain.Article;
import com.lvd.repository.ArticleRepo;
import restx.annotations.*;
import restx.factory.Component;
import restx.security.PermitAll;

/**
 * Created by charlesvienne on 23/02/2016.
 */
@Component
@RestxResource
public class ArticleResource {
    private final ArticleRepo repository;

    public ArticleResource(ArticleRepo repo) {
        this.repository = repo;
    }

    @GET("/article/all")
    @PermitAll
    public Iterable<Article> getAll() {
        return repository.getAll();
    }

    @GET("/article/all/{category}")
    @PermitAll
    public Iterable<Article> getConditional(int category) {
        return repository.getConditional(category);
    }

    @GET("/article/get/{id}")
    @PermitAll
    public Article get(int id) {
        return repository.get(id);
    }

    @GET("/article/last")
    @PermitAll
    public Article lastOne() {
        return repository.lastOne();
    }

    @POST("/article/save")
    @PermitAll
    public Article save(Article atl) {
        return repository.save(atl);
    }

    @PUT("/article/update")
    @PermitAll
    public Article update(Article atl) {
        return repository.update(atl);
    }

    /*@DELETE("/article/delete/{id}")
    @PermitAll
    public int delete(int id) {
        return repository.delete(id);
    }*/
}


