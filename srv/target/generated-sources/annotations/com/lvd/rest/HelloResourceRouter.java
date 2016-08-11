package com.lvd.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.common.base.Optional;
import static com.google.common.base.Preconditions.checkNotNull;

import restx.common.Types;
import restx.*;
import restx.entity.*;
import restx.http.*;
import restx.factory.*;
import restx.security.*;
import static restx.security.Permissions.*;
import restx.description.*;
import restx.converters.MainStringConverter;
import static restx.common.MorePreconditions.checkPresent;

import javax.validation.Validator;
import static restx.validation.Validations.checkValid;

import java.io.IOException;
import java.io.PrintWriter;

@Component(priority = 0)

public class HelloResourceRouter extends RestxRouter {

    public HelloResourceRouter(
                    final HelloResource resource,
                    final EntityRequestBodyReaderRegistry readerRegistry,
                    final EntityResponseWriterRegistry writerRegistry,
                    final MainStringConverter converter,
                    final Optional<Validator> validator,
                    final RestxSecurityManager securityManager) {
        super(
            "default", "HelloResourceRouter", new RestxRoute[] {
        new StdEntityRoute<Void, com.lvd.domain.Message>("default#HelloResource#sayHello",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<com.lvd.domain.Message>build(com.lvd.domain.Message.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("GET", "/message"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<com.lvd.domain.Message> doRoute(RestxRequest request, RestxRequestMatch match, Void body) throws IOException {
                securityManager.check(request, hasRole("hello"));
                return Optional.of(resource.sayHello(
                        
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                

                operation.responseClass = "Message";
                operation.inEntitySchemaKey = "";
                operation.outEntitySchemaKey = "com.lvd.domain.Message";
                operation.sourceLocation = "com.lvd.rest.HelloResource#sayHello()";
            }
        },
        new StdEntityRoute<Void, com.lvd.domain.Message>("default#HelloResource#helloPublic",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<com.lvd.domain.Message>build(com.lvd.domain.Message.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("GET", "/hello"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<com.lvd.domain.Message> doRoute(RestxRequest request, RestxRequestMatch match, Void body) throws IOException {
                securityManager.check(request, open());
                return Optional.of(resource.helloPublic(
                        /* [QUERY] who */ checkPresent(request.getQueryParam("who"), "query param who is required")
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                                OperationParameterDescription who = new OperationParameterDescription();
                who.name = "who";
                who.paramType = OperationParameterDescription.ParamType.query;
                who.dataType = "string";
                who.schemaKey = "";
                who.required = true;
                operation.parameters.add(who);


                operation.responseClass = "Message";
                operation.inEntitySchemaKey = "";
                operation.outEntitySchemaKey = "com.lvd.domain.Message";
                operation.sourceLocation = "com.lvd.rest.HelloResource#helloPublic(java.lang.String)";
            }
        },
        new StdEntityRoute<com.lvd.rest.HelloResource.MyPOJO, com.lvd.rest.HelloResource.MyPOJO>("default#HelloResource#helloPojo",
                readerRegistry.<com.lvd.rest.HelloResource.MyPOJO>build(com.lvd.rest.HelloResource.MyPOJO.class, Optional.<String>absent()),
                writerRegistry.<com.lvd.rest.HelloResource.MyPOJO>build(com.lvd.rest.HelloResource.MyPOJO.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("POST", "/mypojo"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<com.lvd.rest.HelloResource.MyPOJO> doRoute(RestxRequest request, RestxRequestMatch match, com.lvd.rest.HelloResource.MyPOJO body) throws IOException {
                securityManager.check(request, open());
                return Optional.of(resource.helloPojo(
                        /* [BODY] pojo */ checkValid(validator, body)
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                                OperationParameterDescription pojo = new OperationParameterDescription();
                pojo.name = "pojo";
                pojo.paramType = OperationParameterDescription.ParamType.body;
                pojo.dataType = "MyPOJO";
                pojo.schemaKey = "com.lvd.rest.HelloResource.MyPOJO";
                pojo.required = true;
                operation.parameters.add(pojo);


                operation.responseClass = "MyPOJO";
                operation.inEntitySchemaKey = "com.lvd.rest.HelloResource.MyPOJO";
                operation.outEntitySchemaKey = "com.lvd.rest.HelloResource.MyPOJO";
                operation.sourceLocation = "com.lvd.rest.HelloResource#helloPojo(com.lvd.rest.HelloResource.MyPOJO)";
            }
        },
        new StdEntityRoute<Void, restx.security.Session>("default#HelloResource#test",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<restx.security.Session>build(restx.security.Session.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("GET", "/test"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<restx.security.Session> doRoute(RestxRequest request, RestxRequestMatch match, Void body) throws IOException {
                securityManager.check(request, open());
                return Optional.of(resource.test(
                        
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                

                operation.responseClass = "Session";
                operation.inEntitySchemaKey = "";
                operation.outEntitySchemaKey = "restx.security.Session";
                operation.sourceLocation = "com.lvd.rest.HelloResource#test()";
            }
        },
        });
    }

}
