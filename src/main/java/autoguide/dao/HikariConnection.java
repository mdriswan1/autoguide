package autoguide.dao;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariConnection {
		private static HikariDataSource dataSource;
		static {
			HikariConfig config=new HikariConfig();
			config.setJdbcUrl("jdbc:postgresql://localhost:5432/autoguide");
			config.setUsername("postgres");
			config.setPassword("postgres");
			
			config.setMinimumIdle(5);
			config.setMaximumPoolSize(10);
			config.setConnectionTimeout(20000);
			config.setIdleTimeout(600000);
			config.setMaxLifetime(18000000);
			config.setDataSourceJNDI("jdbc/autoguide");
			
			config.setPoolName("hikari_pool");
			dataSource=new HikariDataSource(config);
			
			
		}
		
		
		public static DataSource getDataSource() {
			return dataSource;
		}
}
