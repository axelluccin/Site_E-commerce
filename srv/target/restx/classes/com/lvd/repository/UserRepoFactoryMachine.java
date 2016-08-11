package com.lvd.repository;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import com.lvd.repository.UserRepo;

@Machine
public class UserRepoFactoryMachine extends SingleNameFactoryMachine<com.lvd.repository.UserRepo> {
    public static final Name<com.lvd.repository.UserRepo> NAME = Name.of(com.lvd.repository.UserRepo.class, "UserRepo");

    public UserRepoFactoryMachine() {
        super(0, new StdMachineEngine<com.lvd.repository.UserRepo>(NAME, 0, BoundlessComponentBox.FACTORY) {
private final Factory.Query<restx.jongo.JongoCollection> user = Factory.Query.byName(Name.of(restx.jongo.JongoCollection.class, "user")).mandatory();

            @Override
            public BillOfMaterials getBillOfMaterial() {
                return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
user
                ));
            }

            @Override
            protected com.lvd.repository.UserRepo doNewComponent(SatisfiedBOM satisfiedBOM) {
                return new UserRepo(
satisfiedBOM.getOne(user).get().getComponent()
                );
            }
        });
    }

}
