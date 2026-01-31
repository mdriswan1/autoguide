package autoguide.db;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
/**
 * This class is used to configure the data source by using the hikari cp(Third party API)
 */
public class HikariConnection {
		private static HikariDataSource dataSource;
		/**
		 * In this static block we configure the hikari data source 
		 * why put it in the static block : dont want to configure again and again
		 */
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
		
		/**
		 * @return this method will return the data source, where the method call;
		 */
		public static DataSource getDataSource() {
			return dataSource;
		}
}
