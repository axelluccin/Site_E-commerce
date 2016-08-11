package com.lvd.repository;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import com.lvd.repository.TypeRepo;

@Machine
public class TypeRepoFactoryMachine extends SingleNameFactoryMachine<com.lvd.repository.TypeRepo> {
    public static final Name<com.lvd.repository.TypeRepo> NAME = Name.of(com.lvd.repository.TypeRepo.class, "TypeRepo");

    public TypeRepoFactoryMachine() {
        super(0, new StdMachineEngine<com.lvd.repository.TypeRepo>(NAME, 0, BoundlessComponentBox.FACTORY) {
private final Factory.Query<restx.jongo.JongoCollection> type = Factory.Query.byName(Name.of(restx.jongo.JongoCollection.class, "type")).mandatory();

            @Override
            public BillOfMaterials getBillOfMaterial() {
                return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
type
                ));
            }

            @Override
            protected com.lvd.repository.TypeRepo doNewComponent(SatisfiedBOM satisfiedBOM) {
                return new TypeRepo(
satisfiedBOM.getOne(type).get().getComponent()
                );
            }
        });
    }

}
