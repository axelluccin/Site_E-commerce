package com.lvd.rest;

import com.lvd.domain.Type;
import com.lvd.repository.TypeRepo;
import restx.annotations.*;
import restx.factory.Component;
import restx.security.PermitAll;

/**
 * Created by charlesvienne on 24/02/2016.
 */

@Component
@RestxResource
public class TypeResource {
    private final TypeRepo repository;

    public TypeResource(TypeRepo repo) {
        this.repository = repo;
    }

    @GET("/type/all")
    @PermitAll
    public Iterable<Type> getAll() {
        return repository.getAll();
    }

    @GET("/type/get/{id}")
    @PermitAll
    public Type get(int id) {
        return repository.get(id);
    }

    @GET("/type/last")
    @PermitAll
    public Type lastOne() {
        return repository.lastOne();
    }

    @POST("/type/save")
    @PermitAll
    public Type save(Type tp) {
        return repository.save(tp);
    }

    @PUT("/type/update")
    @PermitAll
    public Type update(Type tp) {
        return repository.update(tp);
    }

   /* @DELETE("/type/delete/{id}")
    @PermitAll
    public int delete(int id) {
        return repository.delete(id);
    }
*/
}
