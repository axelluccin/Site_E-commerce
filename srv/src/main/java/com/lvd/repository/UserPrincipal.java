package com.lvd.repository;

import restx.security.RestxPrincipal;

/**
 * Created by charlesvienne on 16/03/2016.
 */
public interface UserPrincipal extends RestxPrincipal {
    public int getId();
    public String getFirstname();
    public String getLogin();
    public String getMail();
}
