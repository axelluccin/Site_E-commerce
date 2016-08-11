package com.lvd.rest;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import com.lvd.rest.ArticleResource;

@Machine
public class ArticleResourceFactoryMachine extends SingleNameFactoryMachine<com.lvd.rest.ArticleResource> {
    public static final Name<com.lvd.rest.ArticleResource> NAME = Name.of(com.lvd.rest.ArticleResource.class, "ArticleResource");

    public ArticleResourceFactoryMachine() {
        super(0, new StdMachineEngine<com.lvd.rest.ArticleResource>(NAME, 0, BoundlessComponentBox.FACTORY) {
private final Factory.Query<com.lvd.repository.ArticleRepo> repo = Factory.Query.byClass(com.lvd.repository.ArticleRepo.class).mandatory();

            @Override
            public BillOfMaterials getBillOfMaterial() {
                return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
repo
                ));
            }

            @Override
            protected com.lvd.rest.ArticleResource doNewComponent(SatisfiedBOM satisfiedBOM) {
                return new ArticleResource(
satisfiedBOM.getOne(repo).get().getComponent()
                );
            }
        });
    }

}
