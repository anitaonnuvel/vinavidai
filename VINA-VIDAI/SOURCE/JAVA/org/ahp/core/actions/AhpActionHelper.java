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
package org.ahp.core.actions;

import static org.ahp.core.constants.HttpSessionAttributeConstants.LOGGED_IN_USER;

import javax.servlet.http.HttpServletRequest;

import org.ahp.core.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Anita Onnuvel
 *
 */
public final class AhpActionHelper {

	final static Logger LOGGER = LoggerFactory.getLogger(AhpActionHelper.class);

	/**
	 * 
	 * @param pHttpServletRequest
	 * @return
	 */
	public static User getLoggedInUser(HttpServletRequest pHttpServletRequest) {
		return (User) pHttpServletRequest.getSession().getAttribute(
				LOGGED_IN_USER);
	}

}
