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
package org.ahp.login.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.ahp.core.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Anita Onnuvel
 * 
 * @spring.bean id="loginDao"
 * 
 */
public class LoginDaoImpl implements ILoginDao {

	final static Logger LOGGER = LoggerFactory.getLogger(LoginDaoImpl.class);

	private EntityManager mEntityManager;

	@PersistenceContext(type = PersistenceContextType.TRANSACTION)
	public void setEntityManager(EntityManager entityManager) {
		this.mEntityManager = entityManager;
	}

	/**
	 * 
	 * @param pLoginName
	 * @return
	 */
	public User loadUserByLoginName(String pLoginName) {
		return (User) mEntityManager.createQuery(
				"SELECT user FROM User user WHERE LOWER(user.loginName) like LOWER('%"
						+ pLoginName + "%')").getSingleResult();
	}

	/**
	 * 
	 * @param pLoginId
	 * @return
	 */
	public User loadUserByLoginId(Long pLoginId) {
		return (User) mEntityManager.find(User.class, pLoginId);
	}
}
