package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

    private SQLHelper() {
    }

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection(System.getProperty("db.url"), "app", "9mREsvXDs9Gk89Ef");
    }

    @SneakyThrows
    public static String getCreditRequestStatus() {
        var sql = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        try (var conn = getConn()) {
            return QUERY_RUNNER.query(conn, sql, new ScalarHandler<>());
        }
    }
    // Метод для очистки всех таблиц БД
    @SneakyThrows
    public static void cleanDatabase() {
        var deleteOrderEntity = "DELETE FROM order_entity";
        var deletePaymentEntity = "DELETE FROM payment_entity";
        var deleteCreditRequestEntity = "DELETE FROM credit_request_entity";

        try (var conn = getConn()) {
            QUERY_RUNNER.update(conn, deleteOrderEntity);
            QUERY_RUNNER.update(conn, deletePaymentEntity);
            QUERY_RUNNER.update(conn, deleteCreditRequestEntity);
        }
    }

}


