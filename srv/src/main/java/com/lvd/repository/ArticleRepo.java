package com.lvd.repository;

import com.lvd.domain.Article;
import com.mongodb.DBObject;
import org.jongo.ResultHandler;
import restx.factory.Component;
import restx.jongo.JongoCollection;

import javax.inject.Named;
import java.security.Principal;
import java.util.Iterator;

/**
 * Created by charlesvienne on 23/02/2016.
 */
@Component
public class ArticleRepo implements DatabaseRepo {

    private JongoCollection article;


    public Iterable<Article> getAll() {
        Iterable<Article> atls = article.get().find().as(Article.class);
        return atls;
    }

    public Article get(int id){
        Article atl = article.get().findOne("{id : #}", id).as(Article.class);
        return atl;
    }

    public Iterable<Article> getConditional(int category) {
        Iterable<Article> atls = article.get().find("{idCategory : #}", category).as(Article.class);
        return atls;
    }
    public Article lastOne() {
        Iterable<Article> atls = article.get().find().sort("{ _id : -1 }").limit(1).as(Article.class);
        Iterator<Article> iter = atls.iterator();
        Article atl = new Article();
        while (iter.hasNext()) {
            atl = iter.next();
        }

        return atl;
    }
    public Article save(Object o) {
        Article atl = (Article) o ;
        article.get().insert(atl);
        return atl;
    }

    public Article update(Object o) {
        Article atl = (Article) o ;
        article.get().update("{id : #}", atl.getId()).with(atl);
        return atl;
    }

    public int delete(int id) {
        article.get().remove("{id : #}", id);
        return 1;
    }

    public ArticleRepo(@Named("article") JongoCollection article) {
        this.article = article;
    }
}
