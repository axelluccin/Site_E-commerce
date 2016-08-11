package com.lvd.rest;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import com.lvd.rest.UserResource;

@Machine
public class UserResourceFactoryMachine extends SingleNameFactoryMachine<com.lvd.rest.UserResource> {
    public static final Name<com.lvd.rest.UserResource> NAME = Name.of(com.lvd.rest.UserResource.class, "UserResource");

    public UserResourceFactoryMachine() {
        super(0, new StdMachineEngine<com.lvd.rest.UserResource>(NAME, 0, BoundlessComponentBox.FACTORY) {
private final Factory.Query<com.lvd.repository.UserRepo> repo = Factory.Query.byClass(com.lvd.repository.UserRepo.class).mandatory();
private final Factory.Query<restx.security.BasicPrincipalAuthenticator> authenticator = Factory.Query.byClass(restx.security.BasicPrincipalAuthenticator.class).mandatory();
private final Factory.Query<restx.common.UUIDGenerator> uuidGenerator = Factory.Query.byClass(restx.common.UUIDGenerator.class).mandatory();

            @Override
            public BillOfMaterials getBillOfMaterial() {
                return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
repo,
authenticator,
uuidGenerator
                ));
            }

            @Override
            protected com.lvd.rest.UserResource doNewComponent(SatisfiedBOM satisfiedBOM) {
                return new UserResource(
satisfiedBOM.getOne(repo).get().getComponent(),
satisfiedBOM.getOne(authenticator).get().getComponent(),
satisfiedBOM.getOne(uuidGenerator).get().getComponent()
                );
            }
        });
    }

}
