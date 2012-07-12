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

import org.ahp.commons.validator.AbstractValidator;
import org.ahp.commons.validator.ValidatorUtil;
import org.ahp.vinavidai.enums.QuestionType;
import org.ahp.vinavidai.pojo.Option;
import org.ahp.vinavidai.quiz.form.CreateQuestionForm;
import org.ahp.vinavidai.quiz.form.EditQuestionForm;
import org.apache.struts.action.ActionErrors;

/**
 * 
 * @author Anita Onnuvel
 * 
 */
public final class QuestionValidator extends BaseValidator {

    @SuppressWarnings("unchecked")
    public static void validateQuestionDescription(
            String pQuestionDescription, String pErrorKeyPrefix,
            ActionErrors pActionErrors ) {
        Set<String> lErrorKeySet = new LinkedHashSet<String>();
        if ( pQuestionDescription == null || "".equals( pQuestionDescription ) ) {
            lErrorKeySet.add( pErrorKeyPrefix + ".description.required" );
            AbstractValidator
                    .populateActionErrors( pActionErrors, lErrorKeySet );
            return;
        }
    }

    @SuppressWarnings("unchecked")
    public static void validateQuestionDuration(
            String pResponseDurationPerQuestionHours,
            String pResponseDurationPerQuestionMinutes,
            String pResponseDurationPerQuestionSeconds, String pErrorKeyPrefix,
            ActionErrors pActionErrors ) {
        /*
         * Set<String> lErrorKeySet = new LinkedHashSet<String>(); long
         * lQuestionDuration = TimeUnit.MILLISECONDS.convert( Long.parseLong(
         * pResponseDurationPerQuestionHours ), TimeUnit.HOURS ) +
         * TimeUnit.MILLISECONDS.convert( Long.parseLong(
         * pResponseDurationPerQuestionMinutes ), TimeUnit.MINUTES ) +
         * TimeUnit.MILLISECONDS.convert( Long.parseLong(
         * pResponseDurationPerQuestionSeconds ), TimeUnit.SECONDS ) ; Calendar
         * lCalendar = Calendar.getInstance(); lCalendar.setTime( pQuizDuration
         * ); if ( pQuizDuration!= null && lQuestionDuration >
         * lCalendar.getTimeInMillis() ) { lErrorKeySet.add( pErrorKeyPrefix +
         * ".duration.exceeds" ); } AbstractValidator.populateActionErrors(
         * pActionErrors, lErrorKeySet );
         */}

    @SuppressWarnings("unchecked")
    public static void validateQuestionCategory( long pQuestionCategory,
            String pErrorKeyPrefix, ActionErrors pActionErrors ) {
        Set<String> lErrorKeySet = new LinkedHashSet<String>();
        if ( pQuestionCategory == 0 ) {
            lErrorKeySet.add( pErrorKeyPrefix + ".category.required" );
            AbstractValidator
                    .populateActionErrors( pActionErrors, lErrorKeySet );
            return;
        }
    }

    @SuppressWarnings("unchecked")
    public static void validateQuestionSkillLevel( long pQuestionSkillLevel,
            String pErrorKeyPrefix, ActionErrors pActionErrors ) {
        Set<String> lErrorKeySet = new LinkedHashSet<String>();
        if ( pQuestionSkillLevel == 0 ) {
            lErrorKeySet.add( pErrorKeyPrefix + ".skilllevel.required" );
            AbstractValidator
                    .populateActionErrors( pActionErrors, lErrorKeySet );
            return;
        }
    }

    @SuppressWarnings("unchecked")
    public static void validateQuestionPoints( String pQuestionPoints,
            String pErrorKeyPrefix, ActionErrors pActionErrors ) {
        Set<String> lErrorKeySet = new LinkedHashSet<String>();
        if ( pQuestionPoints == null || "".equals( pQuestionPoints ) ) {
            lErrorKeySet.add( pErrorKeyPrefix + ".points.required" );
            AbstractValidator
                    .populateActionErrors( pActionErrors, lErrorKeySet );
            return;
        }
        if ( ValidatorUtil.isNumeric( pQuestionPoints ) ) {
            lErrorKeySet.add( pErrorKeyPrefix + ".points.invalid" );
        }
        AbstractValidator.populateActionErrors( pActionErrors, lErrorKeySet );
    }

    @SuppressWarnings("unchecked")
    public static void validateOptions( List<Option> pOptions,
            String pErrorKeyPrefix, ActionErrors pActionErrors ) {
        Set<String> lErrorKeySet = new LinkedHashSet<String>();
        boolean lOptionSelected = false;
        int lOptionNumber = 1;
        for ( Option lOption : pOptions ) {
            if ( lOption.getOptionDescription() == null
                    || "".equals( lOption.getOptionDescription() ) ) {
                AbstractValidator.populateActionErrorsWithReplacementTokens(
                        pActionErrors, pErrorKeyPrefix
                                + ".multiplechoice.option.required",
                        new Object[] { lOptionNumber } );
            }
            if ( lOption.getAnswer() ) {
                lOptionSelected = lOptionSelected | true;
            }
            lOptionNumber++;
        }
        if ( !lOptionSelected ) {
            lErrorKeySet.add( pErrorKeyPrefix
                    + ".multiplechoice.option.notselected" );
        }
        AbstractValidator.populateActionErrors( pActionErrors, lErrorKeySet );
    }

    @SuppressWarnings("unchecked")
    public static void validateMatchingOptions( List<Option> pOptions,
            String pErrorKeyPrefix, ActionErrors pActionErrors ) {
        Set<String> lErrorKeySet = new LinkedHashSet<String>();
        int lOptionNumber = 1;
        for ( Option lOption : pOptions ) {
            if ( lOption.getOptionDescription() == null
                    || "".equals( lOption.getOptionDescription() ) ) {
                AbstractValidator.populateActionErrorsWithReplacementTokens(
                        pActionErrors, pErrorKeyPrefix
                                + ".matching.option.required",
                        new Object[] { lOptionNumber } );
            }
            if ( lOption.getOptionMatch() == null
                    || "".equals( lOption.getOptionMatch() ) ) {
                AbstractValidator.populateActionErrorsWithReplacementTokens(
                        pActionErrors, pErrorKeyPrefix
                                + ".matching.optionmatch.required",
                        new Object[] { lOptionNumber } );
            }
            lOptionNumber++;
        }
    }

    @SuppressWarnings("unchecked")
    public static void validateOrderingOptions( List<Option> pOptions,
            String pErrorKeyPrefix, ActionErrors pActionErrors ) {
        Set<String> lErrorKeySet = new LinkedHashSet<String>();
        int lOptionNumber = 1;
        for ( Option lOption : pOptions ) {
            if ( lOption.getOptionDescription() == null
                    || "".equals( lOption.getOptionDescription() ) ) {
                AbstractValidator.populateActionErrorsWithReplacementTokens(
                        pActionErrors, pErrorKeyPrefix
                                + ".ordering.option.required",
                        new Object[] { lOptionNumber } );
            }
            lOptionNumber++;
        }
    }

    @SuppressWarnings("unchecked")
    public static void validateTrueOrFalseOptions( List<Option> pOptions,
            String pErrorKeyPrefix, ActionErrors pActionErrors ) {
        Set<String> lErrorKeySet = new LinkedHashSet<String>();
        int lOptionNumber = 1;
        for ( Option lOption : pOptions ) {
        }
        AbstractValidator.populateActionErrors( pActionErrors, lErrorKeySet );
    }

    @SuppressWarnings("unchecked")
    public static void validateDescriptiveOptions( List<Option> pOptions,
            String pErrorKeyPrefix, ActionErrors pActionErrors ) {
        Set<String> lErrorKeySet = new LinkedHashSet<String>();
        Option lOption = pOptions.get( 0 );
        if ( lOption.getDescriptionQuestionMaximumSizeTypeStr() == null
                || "".equals( lOption
                        .getDescriptionQuestionMaximumSizeTypeStr() ) ) {
            lErrorKeySet.add( pErrorKeyPrefix
                    + ".descriptive.maxsizetype.required" );
        }
        if ( lOption.getDescriptionQuestionMaximumSize() == 0 ) {
            lErrorKeySet
                    .add( pErrorKeyPrefix + ".descriptive.maxsize.required" );
        }
        if ( lOption.getDescriptionQuestionMaximumSize() < 0 ) {
            lErrorKeySet
                    .add( pErrorKeyPrefix + ".descriptive.maxsize.positive" );
        }

        AbstractValidator.populateActionErrors( pActionErrors, lErrorKeySet );
    }

    @SuppressWarnings("unchecked")
    public static void validateFillInTheBlankQuestion(
            String pQuestionDescription, String pErrorKeyPrefix,
            ActionErrors pActionErrors ) {
        Set<String> lErrorKeySet = new LinkedHashSet<String>();
        if ( pQuestionDescription == null || "".equals( pQuestionDescription ) ) {
            lErrorKeySet.add( pErrorKeyPrefix + ".points.required" );
            AbstractValidator
                    .populateActionErrors( pActionErrors, lErrorKeySet );
            return;
        }
        AbstractValidator.populateActionErrors( pActionErrors, lErrorKeySet );
    }

    /**
     * 
     * @param pLoginForm
     * @param pActionErrors
     */
    public static void validateCreateQuestionForm(
            CreateQuestionForm pCreateQuestionForm, ActionErrors pActionErrors ) {
        validateQuestionDescription(
                pCreateQuestionForm.getQuestionDescription(),
                CREATE_QUESTION_ERROR_KEY_PREFIX, pActionErrors );
        validateQuestionCategory(
                pCreateQuestionForm.getSelectedQuestionCategory(),
                CREATE_QUESTION_ERROR_KEY_PREFIX, pActionErrors );
        validateQuestionSkillLevel(
                pCreateQuestionForm.getSelectedQuestionSkillLevel(),
                CREATE_QUESTION_ERROR_KEY_PREFIX, pActionErrors );

        if ( pCreateQuestionForm.getQuestionType().equals(
                QuestionType.MultipleChoice.toString() )
                | pCreateQuestionForm.getQuestionType().equals(
                        QuestionType.WordList.toString() ) ) {
            validateOptions( pCreateQuestionForm.getOptions(),
                    CREATE_QUESTION_ERROR_KEY_PREFIX, pActionErrors );
        }
        if ( pCreateQuestionForm.getQuestionType().equals(
                QuestionType.TrueOrFalse.toString() ) ) {
            /*
             * validateTrueOrFalseOptions( pCreateQuestionForm.getOptions(),
             * CREATE_QUESTION_ERROR_KEY_PREFIX, pActionErrors );
             */}
        if ( pCreateQuestionForm.getQuestionType().equals(
                QuestionType.Matching.toString() ) ) {
            validateMatchingOptions( pCreateQuestionForm.getOptions(),
                    CREATE_QUESTION_ERROR_KEY_PREFIX, pActionErrors );
        }
        if ( pCreateQuestionForm.getQuestionType().equals(
                QuestionType.Ordering.toString() ) ) {
            validateOrderingOptions( pCreateQuestionForm.getOptions(),
                    CREATE_QUESTION_ERROR_KEY_PREFIX, pActionErrors );
        }
        if ( pCreateQuestionForm.getQuestionType().equals(
                QuestionType.Descriptive.toString() ) ) {
            validateDescriptiveOptions( pCreateQuestionForm.getOptions(),
                    CREATE_QUESTION_ERROR_KEY_PREFIX, pActionErrors );
        }
    }

    /**
     * 
     * @param pLoginForm
     * @param pActionErrors
     */
    public static void validateEditQuestionForm(
            EditQuestionForm pCreateQuestionForm, ActionErrors pActionErrors ) {
        validateQuestionDescription(
                pCreateQuestionForm.getQuestionDescription(),
                CREATE_QUESTION_ERROR_KEY_PREFIX, pActionErrors );
        validateQuestionCategory(
                pCreateQuestionForm.getSelectedQuestionCategory(),
                CREATE_QUESTION_ERROR_KEY_PREFIX, pActionErrors );
        validateQuestionSkillLevel(
                pCreateQuestionForm.getSelectedQuestionSkillLevel(),
                CREATE_QUESTION_ERROR_KEY_PREFIX, pActionErrors );
        validateQuestionDuration(
                pCreateQuestionForm.getResponseDurationInHours(),
                pCreateQuestionForm.getResponseDurationInMinutes(),
                pCreateQuestionForm.getResponseDurationInSeconds(),
                CREATE_QUESTION_ERROR_KEY_PREFIX, pActionErrors );

        if ( pCreateQuestionForm.getQuestionType().equals(
                QuestionType.MultipleChoice.toString() )
                | pCreateQuestionForm.getQuestionType().equals(
                        QuestionType.WordList.toString() ) ) {
            validateOptions( pCreateQuestionForm.getOptions(),
                    CREATE_QUESTION_ERROR_KEY_PREFIX, pActionErrors );
        }
        if ( pCreateQuestionForm.getQuestionType().equals(
                QuestionType.TrueOrFalse.toString() ) ) {
            /*
             * validateTrueOrFalseOptions( pCreateQuestionForm.getOptions(),
             * CREATE_QUESTION_ERROR_KEY_PREFIX, pActionErrors );
             */}
        if ( pCreateQuestionForm.getQuestionType().equals(
                QuestionType.Matching.toString() ) ) {
            validateMatchingOptions( pCreateQuestionForm.getOptions(),
                    CREATE_QUESTION_ERROR_KEY_PREFIX, pActionErrors );
        }
        if ( pCreateQuestionForm.getQuestionType().equals(
                QuestionType.Ordering.toString() ) ) {
            validateOrderingOptions( pCreateQuestionForm.getOptions(),
                    CREATE_QUESTION_ERROR_KEY_PREFIX, pActionErrors );
        }
        if ( pCreateQuestionForm.getQuestionType().equals(
                QuestionType.Descriptive.toString() ) ) {
            validateDescriptiveOptions( pCreateQuestionForm.getOptions(),
                    CREATE_QUESTION_ERROR_KEY_PREFIX, pActionErrors );
        }
    }
}
