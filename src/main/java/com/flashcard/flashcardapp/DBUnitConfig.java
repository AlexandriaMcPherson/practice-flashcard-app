package com.flashcard.flashcardapp;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;
// import org.dbunit

@Configuration
public class DBUnitConfig {

    @Autowired
    private DataSource dataSource;

    // @Bean
    // public DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection() {
    //     DatabaseConfigBean bean = new DatabaseConfigBean();
    //     bean.setDatatypeFactory(new MySqlDataTypeFactory());

    //     DatabaseDataSourceConnectionFactoryBean dbConnectionFactory = new com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean(dataSource);
    //     dbConnectionFactory.setDatabaseConfig(bean);
    //     return dbConnectionFactory;
    // }
}

