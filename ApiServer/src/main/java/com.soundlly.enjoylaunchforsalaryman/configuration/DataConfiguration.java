package com.soundlly.enjoylaunchforsalaryman.configuration;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by soundllydev on 2016. 3. 15..
 */
@SuppressWarnings("ALL")
@Configuration
@EnableTransactionManagement
public class DataConfiguration {

  @Bean
  @Autowired
  public HibernateTemplate hibernateTemplate(DataSource dataSource) {
    return new HibernateTemplate(sessionFactory(dataSource));
  }

  @Bean
  public SessionFactory sessionFactory(DataSource dataSource) {
    return new LocalSessionFactoryBuilder(dataSource)
        .buildSessionFactory();
  }
}
