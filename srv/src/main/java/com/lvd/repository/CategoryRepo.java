package com.lvd.repository;

import com.lvd.domain.Category;
import com.mongodb.DBObject;
import org.jongo.ResultHandler;
import restx.factory.Component;
import restx.jongo.JongoCollection;

import javax.inject.Named;
import java.util.Iterator;

/**
 * Created by charlesvienne on 24/02/2016.
 */
@Component
public class CategoryRepo implements DatabaseRepo {

    private JongoCollection category;

    public Iterable<Category> getAll() {
        Iterable<Category> ctgs = category.get().find().as(Category.class);
        return ctgs;
    }

    public Category get(int id){
        Category ctg = category.get().findOne("{id : #}", id).as(Category.class);
        return ctg;
    }

    public Iterable<Category> getConditional(int idType) {
        Iterable<Category> ctgs = category.get().find("{idType : #}", idType).as(Category.class);
        return ctgs;
    }

    public Category lastOne() {
        Iterable<Category> ctgs = category.get().find().sort("{ _id : -1 }").limit(1).as(Category.class);
        Iterator<Category> iter = ctgs.iterator();
        Category ctg = new Category();
        while (iter.hasNext()) {
            ctg = iter.next();
        }
        return ctg;
    }

    public Category save(Object o) {
        Category ctg = (Category) o ;
        category.get().insert(ctg);
        return ctg;
    }

    public Category update(Object o) {
        Category ctg = (Category) o ;
        category.get().update("{id : #}", ctg.getId()).with(ctg);
        return ctg;
    }

    public int delete(int id) {
        category.get().remove("{id : #}", id);
        return 1;
    }

    public CategoryRepo(@Named("category") JongoCollection category) {
        this.category = category;
    }
}
