package com.lvd.rest;

import com.lvd.domain.Category;
import com.lvd.repository.CategoryRepo;
import restx.annotations.*;
import restx.factory.Component;
import restx.security.PermitAll;

/**
 * Created by charlesvienne on 24/02/2016.
 */
@Component
@RestxResource
public class CategoryResource {
    private final CategoryRepo repository;

    public CategoryResource(CategoryRepo repo) {
        this.repository = repo;
    }

    @GET("/category/all")
    @PermitAll
    public Iterable<Category> getAll() {
        return repository.getAll();
    }

    @GET("/category/all/{type}")
    @PermitAll
    public Iterable<Category> getConditional(int type) {
        return repository.getConditional(type);
    }

    @GET("/category/get/{id}")
    @PermitAll
    public Category get(int id) {
        return repository.get(id);
    }

    @GET("/category/last")
    @PermitAll
    public Category lastOne() {
        return repository.lastOne();
    }

    @POST("/category/save")
    @PermitAll
    public Category save(Category ctg) {
        return repository.save(ctg);
    }

    @PUT("/category/update")
    @PermitAll
    public Category update(Category ctg) {
        return repository.update(ctg);
    }

   /* @DELETE("/category/delete/{id}")
    @PermitAll
    public int delete(int id) {
        return repository.delete(id);
    }
*/
}
