package com.sist.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// application-datasource.xml => jar 
@Configuration
@MapperScan(basePackages = {"com.sist.mapper"})
// <mybatis-spring base-package="">
public class DataBaseConfig {
  // 데이터베이스 연결 정보 => DataSource : interface => 구현된 클래스 BasicDataSource
  @Bean("ds")
  public DataSource dataSource()
  {
	  BasicDataSource ds=new BasicDataSource();
	  ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
	  ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
	  ds.setUsername("hr");
	  ds.setPassword("happy");
	  return ds;
  }
  // MyBatis => JPA 연결 => SqlSessionFactory => 구현된 클래스 SqlSessionFactoryBean
  @Bean("ssf")
  public SqlSessionFactory sqlSessionFactory() throws Exception
  {
	  SqlSessionFactoryBean ssf=
			  new SqlSessionFactoryBean();
	  ssf.setDataSource(dataSource());
	  return ssf.getObject();
  }
  // Mapper구현 MapperFactoryBean 
}
