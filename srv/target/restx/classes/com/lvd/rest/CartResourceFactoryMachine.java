package com.lvd.rest;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import com.lvd.rest.CartResource;

@Machine
public class CartResourceFactoryMachine extends SingleNameFactoryMachine<com.lvd.rest.CartResource> {
    public static final Name<com.lvd.rest.CartResource> NAME = Name.of(com.lvd.rest.CartResource.class, "CartResource");

    public CartResourceFactoryMachine() {
        super(0, new StdMachineEngine<com.lvd.rest.CartResource>(NAME, 0, BoundlessComponentBox.FACTORY) {
private final Factory.Query<com.lvd.repository.CartRepo> repo = Factory.Query.byClass(com.lvd.repository.CartRepo.class).mandatory();

            @Override
            public BillOfMaterials getBillOfMaterial() {
                return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
repo
                ));
            }

            @Override
            protected com.lvd.rest.CartResource doNewComponent(SatisfiedBOM satisfiedBOM) {
                return new CartResource(
satisfiedBOM.getOne(repo).get().getComponent()
                );
            }
        });
    }

}
