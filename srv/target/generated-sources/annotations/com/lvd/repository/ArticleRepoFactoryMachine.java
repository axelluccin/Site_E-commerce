package com.lvd.repository;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import com.lvd.repository.ArticleRepo;

@Machine
public class ArticleRepoFactoryMachine extends SingleNameFactoryMachine<com.lvd.repository.ArticleRepo> {
    public static final Name<com.lvd.repository.ArticleRepo> NAME = Name.of(com.lvd.repository.ArticleRepo.class, "ArticleRepo");

    public ArticleRepoFactoryMachine() {
        super(0, new StdMachineEngine<com.lvd.repository.ArticleRepo>(NAME, 0, BoundlessComponentBox.FACTORY) {
private final Factory.Query<restx.jongo.JongoCollection> article = Factory.Query.byName(Name.of(restx.jongo.JongoCollection.class, "article")).mandatory();

            @Override
            public BillOfMaterials getBillOfMaterial() {
                return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
article
                ));
            }

            @Override
            protected com.lvd.repository.ArticleRepo doNewComponent(SatisfiedBOM satisfiedBOM) {
                return new ArticleRepo(
satisfiedBOM.getOne(article).get().getComponent()
                );
            }
        });
    }

}
