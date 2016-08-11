package com.lvd;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import com.lvd.AppModule;


@Machine
public class AppModuleFactoryMachine extends DefaultFactoryMachine {
    private static final AppModule module = new AppModule();

    public AppModuleFactoryMachine() {
        super(0, new MachineEngine[] {
            new StdMachineEngine<restx.security.SignatureKey>(Name.of(restx.security.SignatureKey.class, "signatureKey"), 0, BoundlessComponentBox.FACTORY) {
        
                @Override
                public BillOfMaterials getBillOfMaterial() {
                    return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
        
                    ));
                }

                @Override
                public restx.security.SignatureKey doNewComponent(SatisfiedBOM satisfiedBOM) {
	                
	                    return module.signatureKey(
	        
	                    );
	                
                }
            },
            new StdMachineEngine<java.lang.String>(Name.of(java.lang.String.class, "restx.admin.password"), 0, BoundlessComponentBox.FACTORY) {
        
                @Override
                public BillOfMaterials getBillOfMaterial() {
                    return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
        
                    ));
                }

                @Override
                public java.lang.String doNewComponent(SatisfiedBOM satisfiedBOM) {
	                
	                    return module.restxAdminPassword(
	        
	                    );
	                
                }
            },
            new StdMachineEngine<java.lang.String>(Name.of(java.lang.String.class, "mongo.db"), 0, BoundlessComponentBox.FACTORY) {
        
                @Override
                public BillOfMaterials getBillOfMaterial() {
                    return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
        
                    ));
                }

                @Override
                public java.lang.String doNewComponent(SatisfiedBOM satisfiedBOM) {
	                
	                    return module.mongoDb(
	        
	                    );
	                
                }
            },
            new StdMachineEngine<java.lang.String>(Name.of(java.lang.String.class, "mongo.uri"), 0, BoundlessComponentBox.FACTORY) {
        
                @Override
                public BillOfMaterials getBillOfMaterial() {
                    return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
        
                    ));
                }

                @Override
                public java.lang.String doNewComponent(SatisfiedBOM satisfiedBOM) {
	                
	                    return module.mongoUri(
	        
	                    );
	                
                }
            },
            new StdMachineEngine<restx.factory.AutoStartable>(Name.of(restx.factory.AutoStartable.class, "mongoConnectionLogger"), 0, BoundlessComponentBox.FACTORY) {
        private final Factory.Query<java.lang.String> serverId = Factory.Query.byName(Name.of(java.lang.String.class, "restx.server.id")).optional();
private final Factory.Query<com.mongodb.MongoClient> client = Factory.Query.byName(Name.of(com.mongodb.MongoClient.class, "mongoClient")).mandatory();
                @Override
                public BillOfMaterials getBillOfMaterial() {
                    return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
        serverId,
client
                    ));
                }

                @Override
                public restx.factory.AutoStartable doNewComponent(SatisfiedBOM satisfiedBOM) {
	                
	                    return module.mongoConnectionLogger(
	        java.util.Optional.ofNullable(satisfiedBOM.getOneAsComponent(serverId).orNull()),
satisfiedBOM.getOne(client).get().getComponent()
	                    );
	                
                }
            },
            new StdMachineEngine<restx.config.ConfigSupplier>(Name.of(restx.config.ConfigSupplier.class, "appConfigSupplier"), 0, BoundlessComponentBox.FACTORY) {
        private final Factory.Query<restx.config.ConfigLoader> configLoader = Factory.Query.byClass(restx.config.ConfigLoader.class).mandatory();
                @Override
                public BillOfMaterials getBillOfMaterial() {
                    return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
        configLoader
                    ));
                }

                @Override
                public restx.config.ConfigSupplier doNewComponent(SatisfiedBOM satisfiedBOM) {
	                
	                    return module.appConfigSupplier(
	        satisfiedBOM.getOne(configLoader).get().getComponent()
	                    );
	                
                }
            },
            new StdMachineEngine<restx.security.CredentialsStrategy>(Name.of(restx.security.CredentialsStrategy.class, "credentialsStrategy"), 0, BoundlessComponentBox.FACTORY) {
        
                @Override
                public BillOfMaterials getBillOfMaterial() {
                    return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
        
                    ));
                }

                @Override
                public restx.security.CredentialsStrategy doNewComponent(SatisfiedBOM satisfiedBOM) {
	                
	                    return module.credentialsStrategy(
	        
	                    );
	                
                }
            },
            new StdMachineEngine<restx.security.BasicPrincipalAuthenticator>(Name.of(restx.security.BasicPrincipalAuthenticator.class, "basicPrincipalAuthenticator"), 0, BoundlessComponentBox.FACTORY) {
        private final Factory.Query<restx.security.SecuritySettings> securitySettings = Factory.Query.byClass(restx.security.SecuritySettings.class).mandatory();
private final Factory.Query<restx.security.CredentialsStrategy> credentialsStrategy = Factory.Query.byClass(restx.security.CredentialsStrategy.class).mandatory();
private final Factory.Query<java.lang.String> defaultAdminPasswordHash = Factory.Query.byName(Name.of(java.lang.String.class, "restx.admin.passwordHash")).mandatory();
private final Factory.Query<com.fasterxml.jackson.databind.ObjectMapper> mapper = Factory.Query.byClass(com.fasterxml.jackson.databind.ObjectMapper.class).mandatory();
                @Override
                public BillOfMaterials getBillOfMaterial() {
                    return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
        securitySettings,
credentialsStrategy,
defaultAdminPasswordHash,
mapper
                    ));
                }

                @Override
                public restx.security.BasicPrincipalAuthenticator doNewComponent(SatisfiedBOM satisfiedBOM) {
	                
	                    return module.basicPrincipalAuthenticator(
	        satisfiedBOM.getOne(securitySettings).get().getComponent(),
satisfiedBOM.getOne(credentialsStrategy).get().getComponent(),
satisfiedBOM.getOne(defaultAdminPasswordHash).get().getComponent(),
satisfiedBOM.getOne(mapper).get().getComponent()
	                    );
	                
                }
            },

        });
    }
}
