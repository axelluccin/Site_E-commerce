package com.lvd.repository;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import com.lvd.repository.CategoryRepo;

@Machine
public class CategoryRepoFactoryMachine extends SingleNameFactoryMachine<com.lvd.repository.CategoryRepo> {
    public static final Name<com.lvd.repository.CategoryRepo> NAME = Name.of(com.lvd.repository.CategoryRepo.class, "CategoryRepo");

    public CategoryRepoFactoryMachine() {
        super(0, new StdMachineEngine<com.lvd.repository.CategoryRepo>(NAME, 0, BoundlessComponentBox.FACTORY) {
private final Factory.Query<restx.jongo.JongoCollection> category = Factory.Query.byName(Name.of(restx.jongo.JongoCollection.class, "category")).mandatory();

            @Override
            public BillOfMaterials getBillOfMaterial() {
                return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
category
                ));
            }

            @Override
            protected com.lvd.repository.CategoryRepo doNewComponent(SatisfiedBOM satisfiedBOM) {
                return new CategoryRepo(
satisfiedBOM.getOne(category).get().getComponent()
                );
            }
        });
    }

}
