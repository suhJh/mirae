package com.mirae.test.config;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
public class TestDBConfig {

	protected Log log = LogFactory.getLog(TestDBConfig.class);

	@Bean
	@ConfigurationProperties(prefix="test.datasource")
	public DataSource createDataSource() {
		log.info("DataSource : DataSource bean creation for test db");
		return DataSourceBuilder.create().build();
	}


	@Bean(name="factory1")
	public SqlSessionFactory sqlSessionFactory1(DataSource ds) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(ds);
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*.xml"));
		log.info("SqlSessionFactory : SqlSessionFactory bean creation for test db");
		return bean.getObject();
	}

	@Bean(name="test_oracle1")
	public SqlSessionTemplate sqlSession1(@Qualifier("factory1") SqlSessionFactory factoryBean) {
		log.info("SqlSessionTeplate : SqlSessionTemplate bean creation for test db");
		return new SqlSessionTemplate(factoryBean);
	}

}
