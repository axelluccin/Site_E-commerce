package com.lvd.rest;

import com.lvd.domain.User;
import com.lvd.repository.UserPrincipal;
import com.lvd.repository.UserRepo;
import restx.annotations.GET;
import restx.annotations.POST;
import restx.annotations.PUT;
import restx.annotations.RestxResource;
import restx.common.UUIDGenerator;
import restx.factory.Component;
import restx.security.*;

import javax.validation.constraints.NotNull;
import java.security.NoSuchAlgorithmException;

/**
 * Created by charlesvienne on 01/03/2016.
 */
@Component
@RestxResource
public class UserResource {

    private final BasicPrincipalAuthenticator authenticator;
    private final UUIDGenerator uuidGenerator;

    private final UserRepo repository;

    public UserResource(UserRepo repo, BasicPrincipalAuthenticator authenticator, UUIDGenerator uuidGenerator) {
        this.repository = repo;
        this.authenticator = authenticator;
        this.uuidGenerator = uuidGenerator;
    }

    @GET("/user/:id")
    @PermitAll
    public User get(int id) {
        return repository.get(id);
    }

    @POST("/user/authenticate")
    @PermitAll
    public Session authenticate(User user_1) {
        UserPrincipal user = repository.authenticate(user_1);
        if(user != null) {
            String sessionKey = uuidGenerator.doGenerate();
            RestxSession.current().authenticateAs(user);
            RestxSession.current().define(String.class, Session.SESSION_DEF_KEY, sessionKey);
            return new Session(sessionKey, user);
        }
        else { return null; }
    }

    @POST("/user/save")
    @PermitAll
    public User save(User user) {
        return repository.save(user);
    }

    @PUT("/user/update")
    @PermitAll
    public User update(User profil) {
        return repository.update(profil);
    }

    /*
    @POST("/user/sessions")
    @PermitAll
    public Session jeTest() {
        String sessionKey = uuidGenerator.doGenerate();
        RestxSession.current().authenticateAs(repository.get(0));
        RestxSession.current().define(String.class, Session.SESSION_DEF_KEY, sessionKey);
        return new Session(sessionKey, repository.get(0));
    }

    @GET("/user/aaaa/current")
    @PermitAll
    public Session currentSession() {
        String sessionKey = RestxSession.current().get(String.class, Session.SESSION_DEF_KEY).get();
        RestxPrincipal principal = RestxSession.current().getPrincipal().get();
        return new Session(sessionKey, principal);
    }
    */
}
