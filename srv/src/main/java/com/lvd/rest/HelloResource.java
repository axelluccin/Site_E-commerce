package com.lvd.rest;

import com.google.common.collect.ImmutableSet;
import com.lvd.domain.Message;
import com.lvd.Roles;
import com.sun.deploy.net.HttpRequest;
import org.eclipse.jetty.server.Request;
import org.joda.time.DateTime;
import restx.annotations.GET;
import restx.annotations.POST;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

@Component @RestxResource
public class HelloResource {

    /**
     * Say hello to currently logged in user.
     * <p>
     * Authorized only for principals with Roles.HELLO_ROLE role.
     *
     * @return a Message to say hello
     */
    @GET("/message")
    @RolesAllowed(Roles.HELLO_ROLE)
    public Message sayHello() {
        return new Message().setMessage(String.format(
                "hello %s, it's %s",
                RestxSession.current().getPrincipal().get().getName(),
                DateTime.now().toString("HH:mm:ss")));
    }

    /**
     * Say hello to anybody.
     * <p>
     * Does not require authentication.
     *
     * @return a Message to say hello
     */
    @GET("/hello")
    @PermitAll
    public Message helloPublic(String who) {
        return new Message().setMessage(String.format(
                "hello %s, it's %s",
                who, DateTime.now().toString("HH:mm:ss")));
    }

    public static class MyPOJO {
        @NotNull
        String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    @POST("/mypojo")
    @PermitAll
    public MyPOJO helloPojo(MyPOJO pojo) {
        pojo.setValue("hello " + pojo.getValue());
        return pojo;
    }

    @GET("/test")
    @PermitAll
    public Session test() {
        String sessionKey = RestxSession.current().get(String.class, Session.SESSION_DEF_KEY).get();
        RestxPrincipal principal = RestxSession.current().getPrincipal().get();

        return new Session(sessionKey, principal);
    }
}