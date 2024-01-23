package config;

import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JooqConfig {
  private final Configuration configuration;

  public JooqConfig(String url, String username, String password) throws SQLException {
    Connection connection = DriverManager.getConnection(url, username, password);
    configuration = new DefaultConfiguration()
            .set(SQLDialect.POSTGRES) // Adjust dialect if needed
            .set(connection);
  }

  public DSLContext createDSLContext() {
    return DSL.using(configuration);
  }
}
