package com.lvd.rest;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import com.lvd.rest.CategoryResource;

@Machine
public class CategoryResourceFactoryMachine extends SingleNameFactoryMachine<com.lvd.rest.CategoryResource> {
    public static final Name<com.lvd.rest.CategoryResource> NAME = Name.of(com.lvd.rest.CategoryResource.class, "CategoryResource");

    public CategoryResourceFactoryMachine() {
        super(0, new StdMachineEngine<com.lvd.rest.CategoryResource>(NAME, 0, BoundlessComponentBox.FACTORY) {
private final Factory.Query<com.lvd.repository.CategoryRepo> repo = Factory.Query.byClass(com.lvd.repository.CategoryRepo.class).mandatory();

            @Override
            public BillOfMaterials getBillOfMaterial() {
                return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
repo
                ));
            }

            @Override
            protected com.lvd.rest.CategoryResource doNewComponent(SatisfiedBOM satisfiedBOM) {
                return new CategoryResource(
satisfiedBOM.getOne(repo).get().getComponent()
                );
            }
        });
    }

}
