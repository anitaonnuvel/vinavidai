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
package org.ahp.commons.action;

import org.ahp.commons.form.AhpAbstractForm;
import org.apache.struts.action.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Anita Onnuvel
 * 
 * The Class AhpAbstractAction.
 */
public abstract class AhpAbstractAction extends Action {

	/** The Constant LOGGER. */
	final static Logger LOGGER = LoggerFactory
			.getLogger(AhpAbstractAction.class);

	/** The Constant PREVIOUS_ACTION. */
	public static final String PREVIOUS_ACTION = "Previous";
	
	/** The Constant NEXT_ACTION. */
	public static final String NEXT_ACTION = "Next";
	
	/** The Constant BACK_BUTTON_USER_MESSAGE. */
	public static final String BACK_BUTTON_USER_MESSAGE = "An error has occurred, most likely from viewing a screen using the back button.  Please try the operation again.";

	/**
	 * Clear navigation.
	 *
	 * @param pAbstractForm the abstract form
	 */
	public void clearNavigation(AhpAbstractForm pAbstractForm) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("clearNavigation :: starts");
		pAbstractForm.setSubmitAction(null);
		pAbstractForm.setNextPage(null);
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("clearNavigation :: ends");
	}

}
