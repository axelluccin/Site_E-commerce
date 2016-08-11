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

public class CategoryResourceRouter extends RestxRouter {

    public CategoryResourceRouter(
                    final CategoryResource resource,
                    final EntityRequestBodyReaderRegistry readerRegistry,
                    final EntityResponseWriterRegistry writerRegistry,
                    final MainStringConverter converter,
                    final Optional<Validator> validator,
                    final RestxSecurityManager securityManager) {
        super(
            "default", "CategoryResourceRouter", new RestxRoute[] {
        new StdEntityRoute<Void, java.lang.Iterable<com.lvd.domain.Category>>("default#CategoryResource#getAll",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<java.lang.Iterable<com.lvd.domain.Category>>build(Types.newParameterizedType(java.lang.Iterable.class, com.lvd.domain.Category.class), Optional.<String>absent()),
                new StdRestxRequestMatcher("GET", "/category/all"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<java.lang.Iterable<com.lvd.domain.Category>> doRoute(RestxRequest request, RestxRequestMatch match, Void body) throws IOException {
                securityManager.check(request, open());
                return Optional.of(resource.getAll(
                        
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                

                operation.responseClass = "LIST[Category]";
                operation.inEntitySchemaKey = "";
                operation.outEntitySchemaKey = "com.lvd.domain.Category";
                operation.sourceLocation = "com.lvd.rest.CategoryResource#getAll()";
            }
        },
        new StdEntityRoute<Void, java.lang.Iterable<com.lvd.domain.Category>>("default#CategoryResource#getConditional",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<java.lang.Iterable<com.lvd.domain.Category>>build(Types.newParameterizedType(java.lang.Iterable.class, com.lvd.domain.Category.class), Optional.<String>absent()),
                new StdRestxRequestMatcher("GET", "/category/all/{type}"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<java.lang.Iterable<com.lvd.domain.Category>> doRoute(RestxRequest request, RestxRequestMatch match, Void body) throws IOException {
                securityManager.check(request, open());
                return Optional.of(resource.getConditional(
                        /* [PATH] type */ converter.convert(match.getPathParam("type"), int.class)
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                                OperationParameterDescription type = new OperationParameterDescription();
                type.name = "type";
                type.paramType = OperationParameterDescription.ParamType.path;
                type.dataType = "int";
                type.schemaKey = "int";
                type.required = true;
                operation.parameters.add(type);


                operation.responseClass = "LIST[Category]";
                operation.inEntitySchemaKey = "";
                operation.outEntitySchemaKey = "com.lvd.domain.Category";
                operation.sourceLocation = "com.lvd.rest.CategoryResource#getConditional(int)";
            }
        },
        new StdEntityRoute<Void, com.lvd.domain.Category>("default#CategoryResource#get",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<com.lvd.domain.Category>build(com.lvd.domain.Category.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("GET", "/category/get/{id}"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<com.lvd.domain.Category> doRoute(RestxRequest request, RestxRequestMatch match, Void body) throws IOException {
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


                operation.responseClass = "Category";
                operation.inEntitySchemaKey = "";
                operation.outEntitySchemaKey = "com.lvd.domain.Category";
                operation.sourceLocation = "com.lvd.rest.CategoryResource#get(int)";
            }
        },
        new StdEntityRoute<Void, com.lvd.domain.Category>("default#CategoryResource#lastOne",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<com.lvd.domain.Category>build(com.lvd.domain.Category.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("GET", "/category/last"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<com.lvd.domain.Category> doRoute(RestxRequest request, RestxRequestMatch match, Void body) throws IOException {
                securityManager.check(request, open());
                return Optional.of(resource.lastOne(
                        
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                

                operation.responseClass = "Category";
                operation.inEntitySchemaKey = "";
                operation.outEntitySchemaKey = "com.lvd.domain.Category";
                operation.sourceLocation = "com.lvd.rest.CategoryResource#lastOne()";
            }
        },
        new StdEntityRoute<com.lvd.domain.Category, com.lvd.domain.Category>("default#CategoryResource#save",
                readerRegistry.<com.lvd.domain.Category>build(com.lvd.domain.Category.class, Optional.<String>absent()),
                writerRegistry.<com.lvd.domain.Category>build(com.lvd.domain.Category.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("POST", "/category/save"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<com.lvd.domain.Category> doRoute(RestxRequest request, RestxRequestMatch match, com.lvd.domain.Category body) throws IOException {
                securityManager.check(request, open());
                return Optional.of(resource.save(
                        /* [BODY] ctg */ checkValid(validator, body)
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                                OperationParameterDescription ctg = new OperationParameterDescription();
                ctg.name = "ctg";
                ctg.paramType = OperationParameterDescription.ParamType.body;
                ctg.dataType = "Category";
                ctg.schemaKey = "com.lvd.domain.Category";
                ctg.required = true;
                operation.parameters.add(ctg);


                operation.responseClass = "Category";
                operation.inEntitySchemaKey = "com.lvd.domain.Category";
                operation.outEntitySchemaKey = "com.lvd.domain.Category";
                operation.sourceLocation = "com.lvd.rest.CategoryResource#save(com.lvd.domain.Category)";
            }
        },
        new StdEntityRoute<com.lvd.domain.Category, com.lvd.domain.Category>("default#CategoryResource#update",
                readerRegistry.<com.lvd.domain.Category>build(com.lvd.domain.Category.class, Optional.<String>absent()),
                writerRegistry.<com.lvd.domain.Category>build(com.lvd.domain.Category.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("PUT", "/category/update"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<com.lvd.domain.Category> doRoute(RestxRequest request, RestxRequestMatch match, com.lvd.domain.Category body) throws IOException {
                securityManager.check(request, open());
                return Optional.of(resource.update(
                        /* [BODY] ctg */ checkValid(validator, body)
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                                OperationParameterDescription ctg = new OperationParameterDescription();
                ctg.name = "ctg";
                ctg.paramType = OperationParameterDescription.ParamType.body;
                ctg.dataType = "Category";
                ctg.schemaKey = "com.lvd.domain.Category";
                ctg.required = true;
                operation.parameters.add(ctg);


                operation.responseClass = "Category";
                operation.inEntitySchemaKey = "com.lvd.domain.Category";
                operation.outEntitySchemaKey = "com.lvd.domain.Category";
                operation.sourceLocation = "com.lvd.rest.CategoryResource#update(com.lvd.domain.Category)";
            }
        },
        });
    }

}
