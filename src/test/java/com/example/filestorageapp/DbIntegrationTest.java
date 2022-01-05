package com.example.filestorageapp;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class DbIntegrationTest {
    private static final String USER = "postgres";
    private static final String PWD = "postgres";
    private static final String DB_NAME = "postgres";
    private static final Integer DB_PORT = 5454;

    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:10.10")
            .withDatabaseName(DB_NAME)
            .withUsername(USER)
            .withPassword(PWD)
            .withExposedPorts(DB_PORT);

    @BeforeAll
    static void setUp() {
        postgres.start();
    }

    @AfterAll
    static void closeConnection() {
        postgres.close();
    }

    @Test
    void containerIsRunning() {
        System.out.println(postgres.getTestQueryString());
        Assertions.assertTrue(postgres.isRunning());
    }
}


