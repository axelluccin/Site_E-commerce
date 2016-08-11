package com.lvd.rest;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import com.lvd.rest.TypeResource;

@Machine
public class TypeResourceFactoryMachine extends SingleNameFactoryMachine<com.lvd.rest.TypeResource> {
    public static final Name<com.lvd.rest.TypeResource> NAME = Name.of(com.lvd.rest.TypeResource.class, "TypeResource");

    public TypeResourceFactoryMachine() {
        super(0, new StdMachineEngine<com.lvd.rest.TypeResource>(NAME, 0, BoundlessComponentBox.FACTORY) {
private final Factory.Query<com.lvd.repository.TypeRepo> repo = Factory.Query.byClass(com.lvd.repository.TypeRepo.class).mandatory();

            @Override
            public BillOfMaterials getBillOfMaterial() {
                return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
repo
                ));
            }

            @Override
            protected com.lvd.rest.TypeResource doNewComponent(SatisfiedBOM satisfiedBOM) {
                return new TypeResource(
satisfiedBOM.getOne(repo).get().getComponent()
                );
            }
        });
    }

}
