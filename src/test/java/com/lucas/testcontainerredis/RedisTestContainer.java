package com.lucas.testcontainerredis;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.concurrent.atomic.AtomicBoolean;

public class RedisTestContainer implements BeforeAllCallback {

    private static AtomicBoolean containerStarted = new AtomicBoolean(false);

    private static GenericContainer redis = new GenericContainer(DockerImageName.parse("redis"))
            .withExposedPorts(6379)
            .withCommand("redis-server --requirepass 12345");

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        if(!containerStarted.get()) {
            redis.start();

            System.setProperty("spring.data.redis.host", redis.getHost());
            System.setProperty("spring.data.redis.port", redis.getFirstMappedPort().toString());
            System.setProperty("spring.data.redis.password", "12345");

            containerStarted.set(true);
        }
    }

}
