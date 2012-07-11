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
package org.ahp.core.businessdelegate;

import org.ahp.commons.businessdelegate.AhpAbstractBusinessDelegate;
import org.ahp.core.pojo.Audit;
import org.ahp.core.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Anita Onnuvel
 *
 */
public abstract class AhpBusinessDelegate extends AhpAbstractBusinessDelegate {

	final static Logger LOGGER = LoggerFactory.getLogger(AhpBusinessDelegate.class);

	protected User mUser = null;

	/**
	 * 
	 * @param pUser
	 */
	public AhpBusinessDelegate() {
	}

	/**
	 * 
	 * @param pUser
	 */
	public AhpBusinessDelegate(User pUser) {
		mUser = pUser;
	}

	/**
	 * 
	 * @param pUser
	 * @return
	 */
	public static Audit createAudit(User pUser) {
		java.util.Date lTodayDate = new java.sql.Timestamp(
				System.currentTimeMillis());
		Audit lAudit = new Audit();
		lAudit.setCreatedBy(pUser.getUserId());
		lAudit.setCreatedDate(lTodayDate);
		lAudit.setLastUpdatedBy(pUser.getUserId());
		lAudit.setLastUpdatedDate(lTodayDate);
		return lAudit;

	}

	/**
	 * @param pUser
	 * @param pTodayDate
	 * @return
	 */
	public static Audit createAudit(User pUser, java.util.Date pTodayDate) {
		Audit lAudit = new Audit();
		lAudit.setCreatedBy(pUser.getUserId());
		lAudit.setCreatedDate(pTodayDate);
		lAudit.setLastUpdatedBy(pUser.getUserId());
		lAudit.setLastUpdatedDate(pTodayDate);
		return lAudit;
	}

}
