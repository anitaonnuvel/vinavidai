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
package org.ahp.login;

import org.ahp.commons.businessdelegate.AhpAbstractBusinessDelegate;
import org.ahp.core.pojo.User;
import org.ahp.login.dao.ILoginDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Anita Onnuvel
 * 
 * @spring.bean id="loginService"
 * 
 * @spring.property name="loginDao" ref="loginDao"
 */
public class LoginService extends AhpAbstractBusinessDelegate {

	final static Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

	private ILoginDao mLoginDao;

	public void setLoginDao(ILoginDao pLoginDao) {
		mLoginDao = pLoginDao;
	}

	public boolean isUserAuthenticated(String pLoginName, String pPassword) {
		User lUser = mLoginDao.loadUserByLoginName(pLoginName);
		if (lUser != null) {
			if (lUser.getPassword().equals(pPassword)) {
				return true;
			}
		}
		return false;
	}

	public boolean doesUserExist(String pLoginName) {
		User lUser = mLoginDao.loadUserByLoginName(pLoginName);
		if (lUser != null) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param pLoginName
	 * @return
	 */
	public User loadUserByLoginName(String pLoginName) {
		return mLoginDao.loadUserByLoginName(pLoginName);
	}

	/**
	 * 
	 * @param pLoginName
	 * @return
	 */
	public User loadUserByLoginId(long pLoginId) {
		return mLoginDao.loadUserByLoginId(pLoginId);
	}
}
