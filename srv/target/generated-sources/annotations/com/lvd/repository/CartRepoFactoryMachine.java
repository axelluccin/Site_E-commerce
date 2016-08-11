package com.lvd.repository;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import com.lvd.repository.CartRepo;

@Machine
public class CartRepoFactoryMachine extends SingleNameFactoryMachine<com.lvd.repository.CartRepo> {
    public static final Name<com.lvd.repository.CartRepo> NAME = Name.of(com.lvd.repository.CartRepo.class, "CartRepo");

    public CartRepoFactoryMachine() {
        super(0, new StdMachineEngine<com.lvd.repository.CartRepo>(NAME, 0, BoundlessComponentBox.FACTORY) {
private final Factory.Query<restx.jongo.JongoCollection> cart = Factory.Query.byName(Name.of(restx.jongo.JongoCollection.class, "cart")).mandatory();
private final Factory.Query<restx.jongo.JongoCollection> article = Factory.Query.byName(Name.of(restx.jongo.JongoCollection.class, "article")).mandatory();
private final Factory.Query<restx.jongo.JongoCollection> user = Factory.Query.byName(Name.of(restx.jongo.JongoCollection.class, "user")).mandatory();

            @Override
            public BillOfMaterials getBillOfMaterial() {
                return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
cart,
article,
user
                ));
            }

            @Override
            protected com.lvd.repository.CartRepo doNewComponent(SatisfiedBOM satisfiedBOM) {
                return new CartRepo(
satisfiedBOM.getOne(cart).get().getComponent(),
satisfiedBOM.getOne(article).get().getComponent(),
satisfiedBOM.getOne(user).get().getComponent()
                );
            }
        });
    }

}
