/*
 * Copyright 2012 Anita Onnuvel
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ahp.vinavidai.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.ahp.vinavidai.pojo.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Anita Onnuvel
 * 
 * @spring.bean id="testDao"
 * 
 */
public class TestDaoImpl implements ITestDao {

    final static Logger LOGGER = LoggerFactory.getLogger( TestDaoImpl.class );

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager mEntityManager;

    final String LOAD_TEST_BY_ACCESS_KEY = " SELECT testBean FROM Test AS testBean WHERE testBean.testAccessKey = ?";

    /**
	 * 
	 */
    public Test loadTest( String pTestAccessKey ) {
        Query lQuery = mEntityManager.createQuery( LOAD_TEST_BY_ACCESS_KEY );
        lQuery.setParameter( 1, pTestAccessKey );
        Test lTest = ( Test ) lQuery.getSingleResult();
        return lTest;
    }

}
