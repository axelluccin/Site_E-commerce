package com.lvd.rest;

import com.lvd.domain.Cart;
import com.lvd.repository.CartRepo;
import restx.annotations.*;
import restx.factory.Component;
import restx.security.PermitAll;

import java.io.IOException;
import java.util.Map;

/**
 * Created by charlesvienne on 25/02/2016.
 */
@Component
@RestxResource
public class CartResource {
    private final CartRepo repository;

    public CartResource(CartRepo repo) {
        this.repository = repo;
    }

    @GET("/cart/all")
    @PermitAll
    public Iterable<Cart> getAll() {
        return repository.getAll();
    }

    @GET("/cart/get/{user}")
    @PermitAll
    public Cart get(int user) {
        return repository.get(user);
    }

    @GET("/cart/last")
    @PermitAll
    public Cart lastOne() {
        return repository.lastOne();
    }

    @GET("/cart/get/archive/{user}")
    @PermitAll
    public Iterable<Cart> getArchive(int user) {
        return repository.getArchive(user);
    }


    @POST("/cart/save")
    @PermitAll
    public Cart save(Cart ct) {
        return repository.save(ct);
    }

    @PUT("/cart/update")
    @PermitAll
    public Cart update(Cart ct) throws IOException {
        return repository.update(ct);
    }

    @POST("/cart/lvd/validate")
    @PermitAll
    public void validated(Map<String, String> cart) {
        repository.validated(Integer.parseInt(cart.get("idUser")));
    }


   /* @DELETE("/cart/delete/{id}")
    @PermitAll
    public int delete(int id) {
        return repository.delete(id);
    }
*/
}
