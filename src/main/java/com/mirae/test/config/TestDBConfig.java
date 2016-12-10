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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class TestDBConfig {

	protected Log log = LogFactory.getLog(TestDBConfig.class);

	@Bean(name="test.ds")
	@ConfigurationProperties(prefix="test.datasource") //from application.properties
	public DataSource createDataSource() {
		DataSource ds = DataSourceBuilder.create().build();
		if(ds != null) {
			log.info("@bean(name=\"test.ds\") creation success");
		} else {
			new NullPointerException("@bean(name=\"test_DataSource\") creation failed");
		}
		return ds;
	}

	@Bean(name="test.tx")
    public PlatformTransactionManager transactionManager(@Qualifier("test.ds") DataSource ds) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(ds);
        transactionManager.setGlobalRollbackOnParticipationFailure(false);
        if(transactionManager != null) {
        	log.info("@bean(name=\"transactionManager\") creation success");
        }
        return transactionManager;
    }


	@Bean(name="test.ssf")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("test.ds") DataSource ds) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(ds);
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*.xml"));
		SqlSessionFactory ssf = bean.getObject();

		if(ssf != null) {
			log.info("@bean(name=\"test.ssf\") creation success");
		} else {
			new NullPointerException("@bean(name=\"test.ssf\") creation failed");
		}
		return ssf;
	}

	@Bean(name="test.sst")
	public SqlSessionTemplate sqlSession1(@Qualifier("test.ssf") SqlSessionFactory factoryBean) {
		SqlSessionTemplate sst = new SqlSessionTemplate(factoryBean);

		if(sst != null) {
			log.info("@bean(name=\"test.sst\") creation success");
		}

		return sst;
	}

}
