package com.lvd.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;

/**
 * Created by charlesvienne on 25/02/2016.
 */
public class Cart {
    private int id;
    private int idUser;
    private String idArticles;
    private int toValid;

    public String getIdArticles() {
        return idArticles;
    }

    public void setIdArticles(String idArticles) {
        this.idArticles = idArticles;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getToValid() {
        return toValid;
    }

    public void setToValid(int toValid) {
        this.toValid = toValid;
    }
}
