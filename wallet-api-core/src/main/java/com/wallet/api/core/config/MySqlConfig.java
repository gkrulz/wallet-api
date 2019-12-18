package com.wallet.api.core.config;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

/**
 * @author Padmaka Wijayagoonawardena
 * Date: 12/12/19
 */
@Configuration
@Profile({"prod", "test"})
public class MySqlConfig {

    private static final Logger logger = LoggerFactory.getLogger(MySqlConfig.class);

    @Bean(name = "datasource")
    public DataSource getDatasource() {
        logger.info("Connecting to mysql DB");

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPortNumber(3306);
        dataSource.setDatabaseName("WALLET");
        dataSource.setUser("root");
        dataSource.setPassword("gvt123");

        return dataSource;
    }
}
