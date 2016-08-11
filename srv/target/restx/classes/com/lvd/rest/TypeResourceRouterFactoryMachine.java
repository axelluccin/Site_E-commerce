package com.lvd.rest;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import com.lvd.rest.TypeResourceRouter;

@Machine
public class TypeResourceRouterFactoryMachine extends SingleNameFactoryMachine<com.lvd.rest.TypeResourceRouter> {
    public static final Name<com.lvd.rest.TypeResourceRouter> NAME = Name.of(com.lvd.rest.TypeResourceRouter.class, "TypeResourceRouter");

    public TypeResourceRouterFactoryMachine() {
        super(0, new StdMachineEngine<com.lvd.rest.TypeResourceRouter>(NAME, 0, BoundlessComponentBox.FACTORY) {
private final Factory.Query<com.lvd.rest.TypeResource> resource = Factory.Query.byClass(com.lvd.rest.TypeResource.class).mandatory();
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
            protected com.lvd.rest.TypeResourceRouter doNewComponent(SatisfiedBOM satisfiedBOM) {
                return new TypeResourceRouter(
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
