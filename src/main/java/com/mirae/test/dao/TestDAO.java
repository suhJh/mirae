package com.mirae.test.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
public class TestDAO extends DefaultTransactionDefinition {

	protected Log log = LogFactory.getLog(TestDAO.class);

	@Autowired
	@Qualifier("test.sst")
	SqlSessionTemplate template;

	@Autowired
	@Qualifier("test.tx")
	PlatformTransactionManager tx;

	public <P> Integer insert (P parameter) {
		return template.insert("test.test_insert", parameter);
	}

	public <R, P> List<R> selectList (P parameter) {
		TransactionStatus status = tx.getTransaction(this);
		List<R> r = template.selectList("test.test_select", parameter);
		tx.commit(status);
		return r;
	}



}
