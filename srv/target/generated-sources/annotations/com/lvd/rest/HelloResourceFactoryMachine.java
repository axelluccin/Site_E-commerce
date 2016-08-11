package com.lvd.rest;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import com.lvd.rest.HelloResource;

@Machine
public class HelloResourceFactoryMachine extends SingleNameFactoryMachine<com.lvd.rest.HelloResource> {
    public static final Name<com.lvd.rest.HelloResource> NAME = Name.of(com.lvd.rest.HelloResource.class, "HelloResource");

    public HelloResourceFactoryMachine() {
        super(0, new StdMachineEngine<com.lvd.rest.HelloResource>(NAME, 0, BoundlessComponentBox.FACTORY) {


            @Override
            public BillOfMaterials getBillOfMaterial() {
                return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(

                ));
            }

            @Override
            protected com.lvd.rest.HelloResource doNewComponent(SatisfiedBOM satisfiedBOM) {
                return new HelloResource(

                );
            }
        });
    }

}
