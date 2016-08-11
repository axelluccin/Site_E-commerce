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

public class CartResourceRouter extends RestxRouter {

    public CartResourceRouter(
                    final CartResource resource,
                    final EntityRequestBodyReaderRegistry readerRegistry,
                    final EntityResponseWriterRegistry writerRegistry,
                    final MainStringConverter converter,
                    final Optional<Validator> validator,
                    final RestxSecurityManager securityManager) {
        super(
            "default", "CartResourceRouter", new RestxRoute[] {
        new StdEntityRoute<Void, java.lang.Iterable<com.lvd.domain.Cart>>("default#CartResource#getAll",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<java.lang.Iterable<com.lvd.domain.Cart>>build(Types.newParameterizedType(java.lang.Iterable.class, com.lvd.domain.Cart.class), Optional.<String>absent()),
                new StdRestxRequestMatcher("GET", "/cart/all"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<java.lang.Iterable<com.lvd.domain.Cart>> doRoute(RestxRequest request, RestxRequestMatch match, Void body) throws IOException {
                securityManager.check(request, open());
                return Optional.of(resource.getAll(
                        
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                

                operation.responseClass = "LIST[Cart]";
                operation.inEntitySchemaKey = "";
                operation.outEntitySchemaKey = "com.lvd.domain.Cart";
                operation.sourceLocation = "com.lvd.rest.CartResource#getAll()";
            }
        },
        new StdEntityRoute<Void, com.lvd.domain.Cart>("default#CartResource#get",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<com.lvd.domain.Cart>build(com.lvd.domain.Cart.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("GET", "/cart/get/{user}"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<com.lvd.domain.Cart> doRoute(RestxRequest request, RestxRequestMatch match, Void body) throws IOException {
                securityManager.check(request, open());
                return Optional.of(resource.get(
                        /* [PATH] user */ converter.convert(match.getPathParam("user"), int.class)
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                                OperationParameterDescription user = new OperationParameterDescription();
                user.name = "user";
                user.paramType = OperationParameterDescription.ParamType.path;
                user.dataType = "int";
                user.schemaKey = "int";
                user.required = true;
                operation.parameters.add(user);


                operation.responseClass = "Cart";
                operation.inEntitySchemaKey = "";
                operation.outEntitySchemaKey = "com.lvd.domain.Cart";
                operation.sourceLocation = "com.lvd.rest.CartResource#get(int)";
            }
        },
        new StdEntityRoute<Void, com.lvd.domain.Cart>("default#CartResource#lastOne",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<com.lvd.domain.Cart>build(com.lvd.domain.Cart.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("GET", "/cart/last"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<com.lvd.domain.Cart> doRoute(RestxRequest request, RestxRequestMatch match, Void body) throws IOException {
                securityManager.check(request, open());
                return Optional.of(resource.lastOne(
                        
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                

                operation.responseClass = "Cart";
                operation.inEntitySchemaKey = "";
                operation.outEntitySchemaKey = "com.lvd.domain.Cart";
                operation.sourceLocation = "com.lvd.rest.CartResource#lastOne()";
            }
        },
        new StdEntityRoute<Void, java.lang.Iterable<com.lvd.domain.Cart>>("default#CartResource#getArchive",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<java.lang.Iterable<com.lvd.domain.Cart>>build(Types.newParameterizedType(java.lang.Iterable.class, com.lvd.domain.Cart.class), Optional.<String>absent()),
                new StdRestxRequestMatcher("GET", "/cart/get/archive/{user}"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<java.lang.Iterable<com.lvd.domain.Cart>> doRoute(RestxRequest request, RestxRequestMatch match, Void body) throws IOException {
                securityManager.check(request, open());
                return Optional.of(resource.getArchive(
                        /* [PATH] user */ converter.convert(match.getPathParam("user"), int.class)
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                                OperationParameterDescription user = new OperationParameterDescription();
                user.name = "user";
                user.paramType = OperationParameterDescription.ParamType.path;
                user.dataType = "int";
                user.schemaKey = "int";
                user.required = true;
                operation.parameters.add(user);


                operation.responseClass = "LIST[Cart]";
                operation.inEntitySchemaKey = "";
                operation.outEntitySchemaKey = "com.lvd.domain.Cart";
                operation.sourceLocation = "com.lvd.rest.CartResource#getArchive(int)";
            }
        },
        new StdEntityRoute<com.lvd.domain.Cart, com.lvd.domain.Cart>("default#CartResource#save",
                readerRegistry.<com.lvd.domain.Cart>build(com.lvd.domain.Cart.class, Optional.<String>absent()),
                writerRegistry.<com.lvd.domain.Cart>build(com.lvd.domain.Cart.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("POST", "/cart/save"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<com.lvd.domain.Cart> doRoute(RestxRequest request, RestxRequestMatch match, com.lvd.domain.Cart body) throws IOException {
                securityManager.check(request, open());
                return Optional.of(resource.save(
                        /* [BODY] ct */ checkValid(validator, body)
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                                OperationParameterDescription ct = new OperationParameterDescription();
                ct.name = "ct";
                ct.paramType = OperationParameterDescription.ParamType.body;
                ct.dataType = "Cart";
                ct.schemaKey = "com.lvd.domain.Cart";
                ct.required = true;
                operation.parameters.add(ct);


                operation.responseClass = "Cart";
                operation.inEntitySchemaKey = "com.lvd.domain.Cart";
                operation.outEntitySchemaKey = "com.lvd.domain.Cart";
                operation.sourceLocation = "com.lvd.rest.CartResource#save(com.lvd.domain.Cart)";
            }
        },
        new StdEntityRoute<com.lvd.domain.Cart, com.lvd.domain.Cart>("default#CartResource#update",
                readerRegistry.<com.lvd.domain.Cart>build(com.lvd.domain.Cart.class, Optional.<String>absent()),
                writerRegistry.<com.lvd.domain.Cart>build(com.lvd.domain.Cart.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("PUT", "/cart/update"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<com.lvd.domain.Cart> doRoute(RestxRequest request, RestxRequestMatch match, com.lvd.domain.Cart body) throws IOException {
                securityManager.check(request, open());
                return Optional.of(resource.update(
                        /* [BODY] ct */ checkValid(validator, body)
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                                OperationParameterDescription ct = new OperationParameterDescription();
                ct.name = "ct";
                ct.paramType = OperationParameterDescription.ParamType.body;
                ct.dataType = "Cart";
                ct.schemaKey = "com.lvd.domain.Cart";
                ct.required = true;
                operation.parameters.add(ct);


                operation.responseClass = "Cart";
                operation.inEntitySchemaKey = "com.lvd.domain.Cart";
                operation.outEntitySchemaKey = "com.lvd.domain.Cart";
                operation.sourceLocation = "com.lvd.rest.CartResource#update(com.lvd.domain.Cart)";
            }
        },
        new StdEntityRoute<java.util.Map<java.lang.String,java.lang.String>, Empty>("default#CartResource#validated",
                readerRegistry.<java.util.Map<java.lang.String,java.lang.String>>build(Types.newParameterizedType(java.util.Map.class, java.lang.String.class, java.lang.String.class), Optional.<String>absent()),
                writerRegistry.<Empty>build(void.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("POST", "/cart/lvd/validate"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<Empty> doRoute(RestxRequest request, RestxRequestMatch match, java.util.Map<java.lang.String,java.lang.String> body) throws IOException {
                securityManager.check(request, open());
                resource.validated(
                        /* [BODY] cart */ checkValid(validator, body)
                );
                return Optional.of(Empty.EMPTY);
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                                OperationParameterDescription cart = new OperationParameterDescription();
                cart.name = "cart";
                cart.paramType = OperationParameterDescription.ParamType.body;
                cart.dataType = "String>";
                cart.schemaKey = "";
                cart.required = true;
                operation.parameters.add(cart);


                operation.responseClass = "void";
                operation.inEntitySchemaKey = "";
                operation.outEntitySchemaKey = "";
                operation.sourceLocation = "com.lvd.rest.CartResource#validated(java.util.Map<java.lang.String,java.lang.String>)";
            }
        },
        });
    }

}
