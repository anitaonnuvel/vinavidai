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
package org.ahp.vinavidai.validator;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.ahp.commons.validator.AbstractValidator;
import org.ahp.vinavidai.enums.DurationType;
import org.ahp.vinavidai.enums.SubmitActions;
import org.ahp.vinavidai.pojo.Category;
import org.ahp.vinavidai.quiz.form.CreateQuizForm;
import org.ahp.vinavidai.quiz.form.EditQuizForm;
import org.ahp.vinavidai.quiz.form.PublishQuizForm;
import org.ahp.vinavidai.quiz.manage.form.ManageQuizForm;
import org.apache.struts.action.ActionErrors;

/**
 * 
 * @author Anita Onnuvel
 * 
 */
public final class QuizValidator extends BaseValidator {

	@SuppressWarnings("unchecked")
	public static void validateQuizName( String pQuizName,
			String pErrorKeyPrefix, ActionErrors pActionErrors ) {
		Set<String> lErrorKeySet = new LinkedHashSet<String>();
		if ( pQuizName == null || "".equals( pQuizName ) ) {
			lErrorKeySet.add( pErrorKeyPrefix + ".name.required" );
			AbstractValidator
					.populateActionErrors( pActionErrors, lErrorKeySet );
			return;
		}
	}

	@SuppressWarnings("unchecked")
	public static void validateQuizDescription( String pQuizDescription,
			String pErrorKeyPrefix, ActionErrors pActionErrors ) {
		Set<String> lErrorKeySet = new LinkedHashSet<String>();
		if ( pQuizDescription == null || "".equals( pQuizDescription ) ) {
			lErrorKeySet.add( pErrorKeyPrefix + ".description.required" );
			AbstractValidator
					.populateActionErrors( pActionErrors, lErrorKeySet );
			return;
		}
	}

	@SuppressWarnings("unchecked")
	public static void validateTestDuration( String pQuizFixedDurationHours,
			String pQuizFixedDurationMinutes, String pErrorKeyPrefix,
			ActionErrors pActionErrors ) {
		Set<String> lErrorKeySet = new LinkedHashSet<String>();
		if ( ( pQuizFixedDurationHours == null || "0"
				.equals( pQuizFixedDurationHours ) )
				& ( pQuizFixedDurationMinutes == null || "0"
						.equals( pQuizFixedDurationMinutes ) ) ) {
			lErrorKeySet.add( pErrorKeyPrefix + ".duration.invalid" );
			AbstractValidator
					.populateActionErrors( pActionErrors, lErrorKeySet );
		}
	}

	@SuppressWarnings("unchecked")
	public static void validateTestAccessTime( String pQuizAccessStartTime,
			String pQuizAccessEndTime, String pErrorKeyPrefix,
			ActionErrors pActionErrors ) {
		Set<String> lErrorKeySet = new LinkedHashSet<String>();
		if ( ( pQuizAccessStartTime == null || "".equals( pQuizAccessStartTime ) )
				& ( pQuizAccessEndTime == null || ""
						.equals( pQuizAccessEndTime ) ) ) {
			lErrorKeySet.add( pErrorKeyPrefix + ".accesstime.required" );
			AbstractValidator
					.populateActionErrors( pActionErrors, lErrorKeySet );
			return;
		}
	}

	@SuppressWarnings("unchecked")
	public static void validateTestResponseDuration(
			String pResponseDurationPerQuestionHours,
			String pResponseDurationPerQuestionMinutes,
			String pResponseDurationPerQuestionSeconds,
			String pFixedDurationHours, String pFixedDurationMinutes,
			String pErrorKeyPrefix, ActionErrors pActionErrors ) {
		Set<String> lErrorKeySet = new LinkedHashSet<String>();
		if ( ( pResponseDurationPerQuestionHours == null || "0"
				.equals( pResponseDurationPerQuestionHours ) )
				& ( pResponseDurationPerQuestionMinutes == null || "0"
						.equals( pResponseDurationPerQuestionMinutes ) )
				& ( pResponseDurationPerQuestionSeconds == null || "0"
						.equals( pResponseDurationPerQuestionSeconds ) ) ) {
			lErrorKeySet.add( pErrorKeyPrefix + ".responseduration.invalid" );
			AbstractValidator
					.populateActionErrors( pActionErrors, lErrorKeySet );
			return;
		}
		long lQuizDuration = TimeUnit.MILLISECONDS.convert(
				Long.parseLong( pFixedDurationHours ), TimeUnit.HOURS )
				+ TimeUnit.MILLISECONDS.convert(
						Long.parseLong( pFixedDurationMinutes ),
						TimeUnit.MINUTES );
		long lQuestionDuration = TimeUnit.MILLISECONDS.convert(
				Long.parseLong( pResponseDurationPerQuestionHours ),
				TimeUnit.HOURS )
				+ TimeUnit.MILLISECONDS.convert(
						Long.parseLong( pResponseDurationPerQuestionMinutes ),
						TimeUnit.MINUTES )
				+ TimeUnit.MILLISECONDS.convert(
						Long.parseLong( pResponseDurationPerQuestionSeconds ),
						TimeUnit.SECONDS );
		if ( lQuestionDuration > lQuizDuration ) {
			lErrorKeySet.add( pErrorKeyPrefix + ".responseduration.exceeds" );
		}
		AbstractValidator.populateActionErrors( pActionErrors, lErrorKeySet );
	}

	@SuppressWarnings("unchecked")
	public static void validateTestPassPercentile( String pQuizPassPercentile,
			String pErrorKeyPrefix, ActionErrors pActionErrors ) {
		Set<String> lErrorKeySet = new LinkedHashSet<String>();
		if ( pQuizPassPercentile == null || "".equals( pQuizPassPercentile ) ) {
			lErrorKeySet.add( pErrorKeyPrefix + ".passpercentile.required" );
			AbstractValidator
					.populateActionErrors( pActionErrors, lErrorKeySet );
			return;
		}
		int lQuizPassPercentile = Integer.parseInt( pQuizPassPercentile );
		if ( lQuizPassPercentile <= 0 || lQuizPassPercentile > 100 ) {
			lErrorKeySet.add( pErrorKeyPrefix + ".passpercentile.invalid" );
		}
		AbstractValidator.populateActionErrors( pActionErrors, lErrorKeySet );
	}

	/**
	 * 
	 * @param pCreateQuizForm
	 * @param pActionErrors
	 */
	public static void validateCreateQuizForm( CreateQuizForm pCreateQuizForm,
			ActionErrors pActionErrors ) {
		validateQuizName( pCreateQuizForm.getQuizName(),
				CREATE_QUIZ_ERROR_KEY_PREFIX, pActionErrors );
		validateQuizDescription( pCreateQuizForm.getQuizDescription(),
				CREATE_QUIZ_ERROR_KEY_PREFIX, pActionErrors );

	}

	/**
	 * 
	 * @param pCreateQuizForm
	 * @param pActionErrors
	 */
	public static void validateEditQuizForm( EditQuizForm pEditQuizForm,
			ActionErrors pActionErrors ) {
		validateQuizName( pEditQuizForm.getQuizName(),
				EDIT_QUIZ_ERROR_KEY_PREFIX, pActionErrors );
		validateQuizDescription( pEditQuizForm.getQuizDescription(),
				EDIT_QUIZ_ERROR_KEY_PREFIX, pActionErrors );
		validateCategory( pEditQuizForm.getCategory(),
				EDIT_QUIZ_ERROR_KEY_PREFIX, pActionErrors );
	}

	private static void validateCategory( List<Category> pCategory,
			String pEditQuizErrorKeyPrefix, ActionErrors pActionErrors ) {

	}

	/**
	 * 
	 * @param pPublishQuizForm
	 * @param pActionErrors
	 */
	public static void validatePublishQuizForm(
			PublishQuizForm pPublishQuizForm, ActionErrors pActionErrors ) {
		validateQuizName( pPublishQuizForm.getTestName(),
				PUBLISH_QUIZ_ERROR_KEY_PREFIX, pActionErrors );
		validateQuizDescription( pPublishQuizForm.getTestDescription(),
				PUBLISH_QUIZ_ERROR_KEY_PREFIX, pActionErrors );
		if ( pPublishQuizForm.getTestDurationType().equals(
				DurationType.Timed.toString() ) ) {
			validateTestDuration( pPublishQuizForm.getFixedDurationHours(),
					pPublishQuizForm.getFixedDurationMinutes(),
					PUBLISH_QUIZ_ERROR_KEY_PREFIX, pActionErrors );
		}
		if ( pPublishQuizForm.getTestAccessTimeType().equals(
				DurationType.Timed.toString() ) ) {
			validateTestAccessTime( pPublishQuizForm.getTestAccessStartTime(),
					pPublishQuizForm.getTestAccessStartTime(),
					PUBLISH_QUIZ_ERROR_KEY_PREFIX, pActionErrors );
		}
		if ( pPublishQuizForm.getResponseDurationPerQuestionType().equals(
				DurationType.Timed.toString() ) ) {
			validateTestResponseDuration(
					pPublishQuizForm.getResponseDurationPerQuestionHours(),
					pPublishQuizForm.getResponseDurationPerQuestionMinutes(),
					pPublishQuizForm.getResponseDurationPerQuestionSeconds(),
					pPublishQuizForm.getFixedDurationHours(),
					pPublishQuizForm.getFixedDurationMinutes(),
					PUBLISH_QUIZ_ERROR_KEY_PREFIX, pActionErrors );
		}
		validateTestPassPercentile( pPublishQuizForm.getTestPassPercentile(),
				PUBLISH_QUIZ_ERROR_KEY_PREFIX, pActionErrors );
	}

	/**
	 * 
	 * @param pCreateQuizForm
	 * @param pActionErrors
	 */
	public static void validateManageQuizForm( ManageQuizForm pManageQuizForm,
			ActionErrors pActionErrors ) {
		if ( pManageQuizForm.getSelectedQuizId() == null ) {
			String lReplacementToken = "";
			if ( pManageQuizForm.isSubmitAction( SubmitActions.EDIT ) )
				lReplacementToken = "Editing";
			if ( pManageQuizForm.isSubmitAction( SubmitActions.PUBLISH ) )
				lReplacementToken = "Publishing";
			if ( pManageQuizForm.isSubmitAction( SubmitActions.REPORTS ) )
				lReplacementToken = "viewing Reports";
			AbstractValidator.populateActionErrorsWithReplacementTokens(
					pActionErrors, MANAGE_QUIZ_ERROR_KEY_PREFIX
							+ ".notselected",
					new Object[] { lReplacementToken } );
		}
	}
}
