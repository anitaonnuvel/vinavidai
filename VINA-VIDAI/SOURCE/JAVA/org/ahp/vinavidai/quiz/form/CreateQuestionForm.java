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
import org.ahp.vinavidai.validator.QuestionValidator;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anita Onnuvel
 * 
 * @struts.form name="CreateQuestionForm"
 */
public class CreateQuestionForm extends QuestionForm {

	private static final long serialVersionUID = 1L;

	final static Logger LOGGER = LoggerFactory
			.getLogger(CreateQuestionForm.class);

	/** Hidden Fields **/
	private String mHiddenDeleteOptionIndex;

	@Override
	protected void resetForm(ActionMapping pActionMapping,
			HttpServletRequest pHttpServletRequest) {
	}

	@Override
	public ActionErrors validate(ActionMapping pActionMapping,
			HttpServletRequest pHttpServletRequest) {
		ActionErrors lActionErrors = new ActionErrors();
		String lSubmitAction = StringUtils.trimToNull(this.getSubmitAction());
		if (lSubmitAction != null) {
			if (lSubmitAction.equals(SubmitActions.CONTINUE.toString())
					|| lSubmitAction.equals(SubmitActions.COMPLETE.toString())
					|| lSubmitAction.equals(SubmitActions.NEXT.toString())) {
				QuestionValidator.validateCreateQuestionForm(this,
						lActionErrors);
			}
			if (lSubmitAction.equals(SubmitActions.CANCEL.toString())) {
			}
			if (lSubmitAction.equals(SubmitActions.GO.toString())) {
			}
		}
		return lActionErrors;
	}

	@Override
	public String getQuestionOperationType() {
		return "Create";
	}
}
