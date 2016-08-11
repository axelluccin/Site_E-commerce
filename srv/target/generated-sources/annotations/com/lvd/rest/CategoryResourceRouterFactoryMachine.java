package com.lvd.rest;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import com.lvd.rest.CategoryResourceRouter;

@Machine
public class CategoryResourceRouterFactoryMachine extends SingleNameFactoryMachine<com.lvd.rest.CategoryResourceRouter> {
    public static final Name<com.lvd.rest.CategoryResourceRouter> NAME = Name.of(com.lvd.rest.CategoryResourceRouter.class, "CategoryResourceRouter");

    public CategoryResourceRouterFactoryMachine() {
        super(0, new StdMachineEngine<com.lvd.rest.CategoryResourceRouter>(NAME, 0, BoundlessComponentBox.FACTORY) {
private final Factory.Query<com.lvd.rest.CategoryResource> resource = Factory.Query.byClass(com.lvd.rest.CategoryResource.class).mandatory();
private final Factory.Query<restx.entity.EntityRequestBodyReaderRegistry> readerRegistry = Factory.Query.byClass(restx.entity.EntityRequestBodyReaderRegistry.class).mandatory();
private final Factory.Query<restx.entity.EntityResponseWriterRegistry> writerRegistry = Factory.Query.byClass(restx.entity.EntityResponseWriterRegistry.class).mandatory();
private final Factory.Query<restx.converters.MainStringConverter> converter = Factory.Query.byClass(restx.converters.MainStringConverter.class).mandatory();
private final Factory.Query<javax.validation.Validator> validator = Factory.Query.byClass(javax.validation.Validator.class).optional();
private final Factory.Query<restx.security.RestxSecurityManager> securityManager = Factory.Query.byClass(restx.security.RestxSecurityManager.class).mandatory();

            @Override
            public BillOfMaterials getBillOfMaterial() {
                return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
resource,
readerRegistry,
writerRegistry,
converter,
validator,
securityManager
                ));
            }

            @Override
            protected com.lvd.rest.CategoryResourceRouter doNewComponent(SatisfiedBOM satisfiedBOM) {
                return new CategoryResourceRouter(
satisfiedBOM.getOne(resource).get().getComponent(),
satisfiedBOM.getOne(readerRegistry).get().getComponent(),
satisfiedBOM.getOne(writerRegistry).get().getComponent(),
satisfiedBOM.getOne(converter).get().getComponent(),
satisfiedBOM.getOneAsComponent(validator),
satisfiedBOM.getOne(securityManager).get().getComponent()
                );
            }
        });
    }

}
