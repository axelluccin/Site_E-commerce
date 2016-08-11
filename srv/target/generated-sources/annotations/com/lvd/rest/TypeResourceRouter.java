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

public class TypeResourceRouter extends RestxRouter {

    public TypeResourceRouter(
                    final TypeResource resource,
                    final EntityRequestBodyReaderRegistry readerRegistry,
                    final EntityResponseWriterRegistry writerRegistry,
                    final MainStringConverter converter,
                    final Optional<Validator> validator,
                    final RestxSecurityManager securityManager) {
        super(
            "default", "TypeResourceRouter", new RestxRoute[] {
        new StdEntityRoute<Void, java.lang.Iterable<com.lvd.domain.Type>>("default#TypeResource#getAll",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<java.lang.Iterable<com.lvd.domain.Type>>build(Types.newParameterizedType(java.lang.Iterable.class, com.lvd.domain.Type.class), Optional.<String>absent()),
                new StdRestxRequestMatcher("GET", "/type/all"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<java.lang.Iterable<com.lvd.domain.Type>> doRoute(RestxRequest request, RestxRequestMatch match, Void body) throws IOException {
                securityManager.check(request, open());
                return Optional.of(resource.getAll(
                        
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                

                operation.responseClass = "LIST[Type]";
                operation.inEntitySchemaKey = "";
                operation.outEntitySchemaKey = "com.lvd.domain.Type";
                operation.sourceLocation = "com.lvd.rest.TypeResource#getAll()";
            }
        },
        new StdEntityRoute<Void, com.lvd.domain.Type>("default#TypeResource#get",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<com.lvd.domain.Type>build(com.lvd.domain.Type.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("GET", "/type/get/{id}"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<com.lvd.domain.Type> doRoute(RestxRequest request, RestxRequestMatch match, Void body) throws IOException {
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


                operation.responseClass = "Type";
                operation.inEntitySchemaKey = "";
                operation.outEntitySchemaKey = "com.lvd.domain.Type";
                operation.sourceLocation = "com.lvd.rest.TypeResource#get(int)";
            }
        },
        new StdEntityRoute<Void, com.lvd.domain.Type>("default#TypeResource#lastOne",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<com.lvd.domain.Type>build(com.lvd.domain.Type.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("GET", "/type/last"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<com.lvd.domain.Type> doRoute(RestxRequest request, RestxRequestMatch match, Void body) throws IOException {
                securityManager.check(request, open());
                return Optional.of(resource.lastOne(
                        
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                

                operation.responseClass = "Type";
                operation.inEntitySchemaKey = "";
                operation.outEntitySchemaKey = "com.lvd.domain.Type";
                operation.sourceLocation = "com.lvd.rest.TypeResource#lastOne()";
            }
        },
        new StdEntityRoute<com.lvd.domain.Type, com.lvd.domain.Type>("default#TypeResource#save",
                readerRegistry.<com.lvd.domain.Type>build(com.lvd.domain.Type.class, Optional.<String>absent()),
                writerRegistry.<com.lvd.domain.Type>build(com.lvd.domain.Type.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("POST", "/type/save"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<com.lvd.domain.Type> doRoute(RestxRequest request, RestxRequestMatch match, com.lvd.domain.Type body) throws IOException {
                securityManager.check(request, open());
                return Optional.of(resource.save(
                        /* [BODY] tp */ checkValid(validator, body)
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                                OperationParameterDescription tp = new OperationParameterDescription();
                tp.name = "tp";
                tp.paramType = OperationParameterDescription.ParamType.body;
                tp.dataType = "Type";
                tp.schemaKey = "com.lvd.domain.Type";
                tp.required = true;
                operation.parameters.add(tp);


                operation.responseClass = "Type";
                operation.inEntitySchemaKey = "com.lvd.domain.Type";
                operation.outEntitySchemaKey = "com.lvd.domain.Type";
                operation.sourceLocation = "com.lvd.rest.TypeResource#save(com.lvd.domain.Type)";
            }
        },
        new StdEntityRoute<com.lvd.domain.Type, com.lvd.domain.Type>("default#TypeResource#update",
                readerRegistry.<com.lvd.domain.Type>build(com.lvd.domain.Type.class, Optional.<String>absent()),
                writerRegistry.<com.lvd.domain.Type>build(com.lvd.domain.Type.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("PUT", "/type/update"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<com.lvd.domain.Type> doRoute(RestxRequest request, RestxRequestMatch match, com.lvd.domain.Type body) throws IOException {
                securityManager.check(request, open());
                return Optional.of(resource.update(
                        /* [BODY] tp */ checkValid(validator, body)
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                                OperationParameterDescription tp = new OperationParameterDescription();
                tp.name = "tp";
                tp.paramType = OperationParameterDescription.ParamType.body;
                tp.dataType = "Type";
                tp.schemaKey = "com.lvd.domain.Type";
                tp.required = true;
                operation.parameters.add(tp);


                operation.responseClass = "Type";
                operation.inEntitySchemaKey = "com.lvd.domain.Type";
                operation.outEntitySchemaKey = "com.lvd.domain.Type";
                operation.sourceLocation = "com.lvd.rest.TypeResource#update(com.lvd.domain.Type)";
            }
        },
        });
    }

}
