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

public class UserResourceRouter extends RestxRouter {

    public UserResourceRouter(
                    final UserResource resource,
                    final EntityRequestBodyReaderRegistry readerRegistry,
                    final EntityResponseWriterRegistry writerRegistry,
                    final MainStringConverter converter,
                    final Optional<Validator> validator,
                    final RestxSecurityManager securityManager) {
        super(
            "default", "UserResourceRouter", new RestxRoute[] {
        new StdEntityRoute<Void, com.lvd.domain.User>("default#UserResource#get",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<com.lvd.domain.User>build(com.lvd.domain.User.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("GET", "/user/:id"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<com.lvd.domain.User> doRoute(RestxRequest request, RestxRequestMatch match, Void body) throws IOException {
                securityManager.check(request, open());
                return Optional.of(resource.get(
                        /* [PATH] id */ converter.convert(match.getPathParam("id"), int.class)
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                                OperationParameterDescription id = new OperationParameterDescription();
                id.name = "id";
                id.paramType = OperationParameterDescription.ParamType.path;
                id.dataType = "int";
                id.schemaKey = "int";
                id.required = true;
                operation.parameters.add(id);


                operation.responseClass = "User";
                operation.inEntitySchemaKey = "";
                operation.outEntitySchemaKey = "com.lvd.domain.User";
                operation.sourceLocation = "com.lvd.rest.UserResource#get(int)";
            }
        },
        new StdEntityRoute<com.lvd.domain.User, restx.security.Session>("default#UserResource#authenticate",
                readerRegistry.<com.lvd.domain.User>build(com.lvd.domain.User.class, Optional.<String>absent()),
                writerRegistry.<restx.security.Session>build(restx.security.Session.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("POST", "/user/authenticate"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<restx.security.Session> doRoute(RestxRequest request, RestxRequestMatch match, com.lvd.domain.User body) throws IOException {
                securityManager.check(request, open());
                return Optional.of(resource.authenticate(
                        /* [BODY] user_1 */ checkValid(validator, body)
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                                OperationParameterDescription user_1 = new OperationParameterDescription();
                user_1.name = "user_1";
                user_1.paramType = OperationParameterDescription.ParamType.body;
                user_1.dataType = "User";
                user_1.schemaKey = "com.lvd.domain.User";
                user_1.required = true;
                operation.parameters.add(user_1);


                operation.responseClass = "Session";
                operation.inEntitySchemaKey = "com.lvd.domain.User";
                operation.outEntitySchemaKey = "restx.security.Session";
                operation.sourceLocation = "com.lvd.rest.UserResource#authenticate(com.lvd.domain.User)";
            }
        },
        new StdEntityRoute<com.lvd.domain.User, com.lvd.domain.User>("default#UserResource#save",
                readerRegistry.<com.lvd.domain.User>build(com.lvd.domain.User.class, Optional.<String>absent()),
                writerRegistry.<com.lvd.domain.User>build(com.lvd.domain.User.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("POST", "/user/save"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<com.lvd.domain.User> doRoute(RestxRequest request, RestxRequestMatch match, com.lvd.domain.User body) throws IOException {
                securityManager.check(request, open());
                return Optional.of(resource.save(
                        /* [BODY] user */ checkValid(validator, body)
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                                OperationParameterDescription user = new OperationParameterDescription();
                user.name = "user";
                user.paramType = OperationParameterDescription.ParamType.body;
                user.dataType = "User";
                user.schemaKey = "com.lvd.domain.User";
                user.required = true;
                operation.parameters.add(user);


                operation.responseClass = "User";
                operation.inEntitySchemaKey = "com.lvd.domain.User";
                operation.outEntitySchemaKey = "com.lvd.domain.User";
                operation.sourceLocation = "com.lvd.rest.UserResource#save(com.lvd.domain.User)";
            }
        },
        new StdEntityRoute<com.lvd.domain.User, com.lvd.domain.User>("default#UserResource#update",
                readerRegistry.<com.lvd.domain.User>build(com.lvd.domain.User.class, Optional.<String>absent()),
                writerRegistry.<com.lvd.domain.User>build(com.lvd.domain.User.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("PUT", "/user/update"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<com.lvd.domain.User> doRoute(RestxRequest request, RestxRequestMatch match, com.lvd.domain.User body) throws IOException {
                securityManager.check(request, open());
                return Optional.of(resource.update(
                        /* [BODY] profil */ checkValid(validator, body)
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                                OperationParameterDescription profil = new OperationParameterDescription();
                profil.name = "profil";
                profil.paramType = OperationParameterDescription.ParamType.body;
                profil.dataType = "User";
                profil.schemaKey = "com.lvd.domain.User";
                profil.required = true;
                operation.parameters.add(profil);


                operation.responseClass = "User";
                operation.inEntitySchemaKey = "com.lvd.domain.User";
                operation.outEntitySchemaKey = "com.lvd.domain.User";
                operation.sourceLocation = "com.lvd.rest.UserResource#update(com.lvd.domain.User)";
            }
        },
        });
    }

}
