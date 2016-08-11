package com.lvd.repository;

import java.io.IOException;
import java.util.List;

/**
 * Created by charlesvienne on 24/02/2016.
 */
public interface DatabaseRepo {

    // get all object from collection with other key
    public Iterable getAll();

    // get one object from collection with id
    public Object get(int id);

    //get Last Object from collection
    public Object lastOne();

    // save object
    public Object save(Object o);

    // update object
    public Object update(Object o) throws IOException;

    // delete object
    public int delete(int id);

}
