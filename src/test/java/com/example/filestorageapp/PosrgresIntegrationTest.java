package com.example.filestorageapp;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PosrgresIntegrationTest {
    private static final String USER = "postgres";
    private static final String PWD = "postgres";
    private static final String DB_NAME = "postgres";
    private static final Integer DB_PORT = 5432;



    @Container
    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:10.10")
            .withDatabaseName(DB_NAME)
            .withUsername(USER)
            .withPassword(PWD)
            .withExposedPorts(DB_PORT);

    @BeforeAll
    static void setUp() {
        postgresContainer.start();
    }

    @AfterAll
    static void closeConnection() {
        postgresContainer.close();
    }

    @Test
    @Order(1)
    void containerIsRunning() {
        Assertions.assertTrue(postgresContainer.isRunning());
    }

    @Test
    @Order(2)
    void whenSelectQueryExecuted_thenResulstsReturned() throws SQLException {
        String jdbcUrl = postgresContainer.getJdbcUrl();
        String username = postgresContainer.getUsername();
        String password = postgresContainer.getPassword();

        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

        ResultSet resultSet = connection
                .createStatement()
                .executeQuery(postgresContainer.getTestQueryString());

        resultSet.next();

        int result = resultSet.getInt(1);

        Assertions.assertEquals(1, result);
    }
}


