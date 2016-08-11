package com.lvd.repository;

import com.lvd.domain.Type;
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
public class TypeRepo {

    private JongoCollection type;

    public Iterable<Type> getAll() {
        Iterable<Type> tps = type.get().find().as(Type.class);
        return tps;
    }

    public Type get(int id){
        Type tp = type.get().findOne("{id : #}", id).as(Type.class);
        return tp;
    }

    public Type lastOne() {
        Iterable<Type> tps = type.get().find().sort("{ _id : -1 }").limit(1).as(Type.class);
        Iterator<Type> iter = tps.iterator();
        Type tp = new Type();
        while (iter.hasNext()) {
            tp = iter.next();
        }
        return tp;
    }
    public Type save(Object o) {
        Type tp = (Type) o;
        type.get().insert(tp);
        return tp;
    }

    public Type update(Object o) {
        Type tp = (Type) o;
        type.get().update("{id : #}", tp.getId()).with(tp);
        return tp;
    }

    public int delete(int id) {
        type.get().remove("{id : #}", id);
        return 1;
    }

    public TypeRepo(@Named("type") JongoCollection type) {
        this.type = type;
    }
}
