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
package org.ahp.vinavidai.quiz.form;

import javax.servlet.http.HttpServletRequest;

import org.ahp.vinavidai.enums.SubmitActions;
import org.ahp.vinavidai.validator.QuizValidator;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anita Onnuvel
 * 
 * @struts.form name="CreateQuizForm"
 */
public class CreateQuizForm extends QuizForm {

	private static final long serialVersionUID = 1L;

	final static Logger LOGGER = LoggerFactory.getLogger(CreateQuizForm.class);

	@Override
	public ActionErrors validate(ActionMapping pActionMapping,
			HttpServletRequest pHttpServletRequest) {
		ActionErrors lActionErrors = new ActionErrors();
		if (super.isSubmitAction(SubmitActions.SAVE)
				|| super.isSubmitAction(SubmitActions.SAVE_AND_ADD_QUESTIONS)) {
			QuizValidator.validateCreateQuizForm(this, lActionErrors);
		}
		return lActionErrors;
	}

	@Override
	public String getQuizOperationType() {
		return "Create";
	}

}
