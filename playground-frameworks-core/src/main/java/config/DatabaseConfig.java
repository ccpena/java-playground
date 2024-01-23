package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
  private final String url = "jdbc:postgresql://localhost:5432/framework_test";
  private final String username = "postgres";
  private final String password = "postgres";

  public Connection getConnection() throws SQLException {
    return DriverManager.getConnection(url, username, password);
  }
}
