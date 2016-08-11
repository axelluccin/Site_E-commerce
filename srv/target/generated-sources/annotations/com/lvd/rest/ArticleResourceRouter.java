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

public class ArticleResourceRouter extends RestxRouter {

    public ArticleResourceRouter(
                    final ArticleResource resource,
                    final EntityRequestBodyReaderRegistry readerRegistry,
                    final EntityResponseWriterRegistry writerRegistry,
                    final MainStringConverter converter,
                    final Optional<Validator> validator,
                    final RestxSecurityManager securityManager) {
        super(
            "default", "ArticleResourceRouter", new RestxRoute[] {
        new StdEntityRoute<Void, java.lang.Iterable<com.lvd.domain.Article>>("default#ArticleResource#getAll",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<java.lang.Iterable<com.lvd.domain.Article>>build(Types.newParameterizedType(java.lang.Iterable.class, com.lvd.domain.Article.class), Optional.<String>absent()),
                new StdRestxRequestMatcher("GET", "/article/all"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<java.lang.Iterable<com.lvd.domain.Article>> doRoute(RestxRequest request, RestxRequestMatch match, Void body) throws IOException {
                securityManager.check(request, open());
                return Optional.of(resource.getAll(
                        
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                

                operation.responseClass = "LIST[Article]";
                operation.inEntitySchemaKey = "";
                operation.outEntitySchemaKey = "com.lvd.domain.Article";
                operation.sourceLocation = "com.lvd.rest.ArticleResource#getAll()";
            }
        },
        new StdEntityRoute<Void, java.lang.Iterable<com.lvd.domain.Article>>("default#ArticleResource#getConditional",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<java.lang.Iterable<com.lvd.domain.Article>>build(Types.newParameterizedType(java.lang.Iterable.class, com.lvd.domain.Article.class), Optional.<String>absent()),
                new StdRestxRequestMatcher("GET", "/article/all/{category}"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<java.lang.Iterable<com.lvd.domain.Article>> doRoute(RestxRequest request, RestxRequestMatch match, Void body) throws IOException {
                securityManager.check(request, open());
                return Optional.of(resource.getConditional(
                        /* [PATH] category */ converter.convert(match.getPathParam("category"), int.class)
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                                OperationParameterDescription category = new OperationParameterDescription();
                category.name = "category";
                category.paramType = OperationParameterDescription.ParamType.path;
                category.dataType = "int";
                category.schemaKey = "int";
                category.required = true;
                operation.parameters.add(category);


                operation.responseClass = "LIST[Article]";
                operation.inEntitySchemaKey = "";
                operation.outEntitySchemaKey = "com.lvd.domain.Article";
                operation.sourceLocation = "com.lvd.rest.ArticleResource#getConditional(int)";
            }
        },
        new StdEntityRoute<Void, com.lvd.domain.Article>("default#ArticleResource#get",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<com.lvd.domain.Article>build(com.lvd.domain.Article.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("GET", "/article/get/{id}"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<com.lvd.domain.Article> doRoute(RestxRequest request, RestxRequestMatch match, Void body) throws IOException {
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


                operation.responseClass = "Article";
                operation.inEntitySchemaKey = "";
                operation.outEntitySchemaKey = "com.lvd.domain.Article";
                operation.sourceLocation = "com.lvd.rest.ArticleResource#get(int)";
            }
        },
        new StdEntityRoute<Void, com.lvd.domain.Article>("default#ArticleResource#lastOne",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<com.lvd.domain.Article>build(com.lvd.domain.Article.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("GET", "/article/last"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<com.lvd.domain.Article> doRoute(RestxRequest request, RestxRequestMatch match, Void body) throws IOException {
                securityManager.check(request, open());
                return Optional.of(resource.lastOne(
                        
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                

                operation.responseClass = "Article";
                operation.inEntitySchemaKey = "";
                operation.outEntitySchemaKey = "com.lvd.domain.Article";
                operation.sourceLocation = "com.lvd.rest.ArticleResource#lastOne()";
            }
        },
        new StdEntityRoute<com.lvd.domain.Article, com.lvd.domain.Article>("default#ArticleResource#save",
                readerRegistry.<com.lvd.domain.Article>build(com.lvd.domain.Article.class, Optional.<String>absent()),
                writerRegistry.<com.lvd.domain.Article>build(com.lvd.domain.Article.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("POST", "/article/save"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<com.lvd.domain.Article> doRoute(RestxRequest request, RestxRequestMatch match, com.lvd.domain.Article body) throws IOException {
                securityManager.check(request, open());
                return Optional.of(resource.save(
                        /* [BODY] atl */ checkValid(validator, body)
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                                OperationParameterDescription atl = new OperationParameterDescription();
                atl.name = "atl";
                atl.paramType = OperationParameterDescription.ParamType.body;
                atl.dataType = "Article";
                atl.schemaKey = "com.lvd.domain.Article";
                atl.required = true;
                operation.parameters.add(atl);


                operation.responseClass = "Article";
                operation.inEntitySchemaKey = "com.lvd.domain.Article";
                operation.outEntitySchemaKey = "com.lvd.domain.Article";
                operation.sourceLocation = "com.lvd.rest.ArticleResource#save(com.lvd.domain.Article)";
            }
        },
        new StdEntityRoute<com.lvd.domain.Article, com.lvd.domain.Article>("default#ArticleResource#update",
                readerRegistry.<com.lvd.domain.Article>build(com.lvd.domain.Article.class, Optional.<String>absent()),
                writerRegistry.<com.lvd.domain.Article>build(com.lvd.domain.Article.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("PUT", "/article/update"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<com.lvd.domain.Article> doRoute(RestxRequest request, RestxRequestMatch match, com.lvd.domain.Article body) throws IOException {
                securityManager.check(request, open());
                return Optional.of(resource.update(
                        /* [BODY] atl */ checkValid(validator, body)
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                                OperationParameterDescription atl = new OperationParameterDescription();
                atl.name = "atl";
                atl.paramType = OperationParameterDescription.ParamType.body;
                atl.dataType = "Article";
                atl.schemaKey = "com.lvd.domain.Article";
                atl.required = true;
                operation.parameters.add(atl);


                operation.responseClass = "Article";
                operation.inEntitySchemaKey = "com.lvd.domain.Article";
                operation.outEntitySchemaKey = "com.lvd.domain.Article";
                operation.sourceLocation = "com.lvd.rest.ArticleResource#update(com.lvd.domain.Article)";
            }
        },
        });
    }

}
