package com.mirae.test.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TestDAO {

	protected Log log = LogFactory.getLog(TestDAO.class);

	@Autowired
	@Qualifier("test_oracle1")
	SqlSessionTemplate template1;



	public <P> Integer insert (P parameter) {
		return template1.insert("test.test_insert", parameter);
	}

	public <R, P> List<R> selectList (P parameter) {
		return template1.selectList("test.test_select", parameter);
	}



}
