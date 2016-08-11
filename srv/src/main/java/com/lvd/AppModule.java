package com.lvd;

import com.mongodb.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import restx.config.ConfigLoader;
import restx.config.ConfigSupplier;
import restx.factory.AutoStartable;
import restx.factory.Provides;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableSet;
import restx.mongo.MongoModule;
import restx.security.*;
import restx.factory.Module;
import restx.factory.Provides;
import javax.inject.Named;

import java.nio.file.Paths;
import java.util.Optional;

@Module
public class AppModule {

    private static final Logger logger = LoggerFactory.getLogger(AppModule.class);

    @Provides
    public SignatureKey signatureKey() {
         return new SignatureKey("638278e7-57e9-4ebd-a21e-d4ad8fd5b2ce -8249429854064527928 com.lvd com.lvd".getBytes(Charsets.UTF_8));
    }

    @Provides
    @Named("restx.admin.password")
    public String restxAdminPassword() {
        return "admin";
    }

    @Provides
    @Named("mongo.db")
    public String mongoDb() {
        return "market";
    }

    @Provides
    @Named("mongo.uri")
    public String mongoUri() {
        return "mongodb://192.168.1.17:27017";
    }

    @Provides
    public AutoStartable mongoConnectionLogger(final @Named("restx.server.id") Optional<String> serverId,
                                               final @Named(MongoModule.MONGO_CLIENT_NAME) MongoClient client) {
        return () -> logger.info("{} - connected to Mongo @ {}", serverId.orElse("-"), client.getAllAddress());
    }

    @Provides
    public ConfigSupplier appConfigSupplier(ConfigLoader configLoader) {
        // Load settings.properties in com.lvd package as a set of config entries
        return configLoader.fromResource("com/lvd/settings");
    }

    @Provides
    public CredentialsStrategy credentialsStrategy() {
        return new BCryptCredentialsStrategy();
    }

    @Provides
    public BasicPrincipalAuthenticator basicPrincipalAuthenticator(
            SecuritySettings securitySettings, CredentialsStrategy credentialsStrategy,
            @Named("restx.admin.passwordHash") String defaultAdminPasswordHash, ObjectMapper mapper) {
        return new StdBasicPrincipalAuthenticator(new StdUserService<>(
                // use file based users repository.
                // Developer's note: prefer another storage mechanism for your users if you need real user management
                // and better perf
                new FileBasedUserRepository<>(
                        StdUser.class, // this is the class for the User objects, that you can get in your app code
                        // with RestxSession.current().getPrincipal().get()
                        // it can be a custom user class, it just need to be json deserializable
                        mapper,

                        // this is the default restx admin, useful to access the restx admin console.
                        // if one user with restx-admin role is defined in the repository, this default user won't be
                        // available anymore
                        new StdUser("admin", ImmutableSet.<String>of("*")),

                        // the path where users are stored
                        Paths.get("data/users.json"),

                        // the path where credentials are stored. isolating both is a good practice in terms of security
                        // it is strongly recommended to follow this approach even if you use your own repository
                        Paths.get("data/credentials.json"),

                        // tells that we want to reload the files dynamically if they are touched.
                        // this has a performance impact, if you know your users / credentials never change without a
                        // restart you can disable this to get better perfs
                        true),
                credentialsStrategy, defaultAdminPasswordHash),
                securitySettings);
    }
}
