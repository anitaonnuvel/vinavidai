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
package org.ahp.login.validator;

import static org.ahp.commons.validator.ValidatorConstants.EMAIL_ADDRESS_ALLOWED_REGEX;

import java.util.LinkedHashSet;
import java.util.Set;

import org.ahp.commons.validator.AbstractValidator;
import org.ahp.commons.validator.ValidatorUtil;
import org.apache.struts.action.ActionErrors;

/**
 * 
 * @author Anita Onnuvel
 * 
 */
public class BaseValidator extends AbstractValidator {

    public static final String LOGIN_ERROR_KEY_PREFIX = "login";

    /**
     * 
     * @param pLoginName
     * @param pActionErrors
     * @param pErrorKeyPrefix
     */
    @SuppressWarnings("unchecked")
    public static void validateLoginName( String pLoginName,
            ActionErrors pActionErrors, String pErrorKeyPrefix ) {
        Set<String> lErrorKeySet = new LinkedHashSet<String>();
        if ( pLoginName == null || "".equals( pLoginName ) ) {
            lErrorKeySet.add( pErrorKeyPrefix + ".loginname.required" );
            AbstractValidator
                    .populateActionErrors( pActionErrors, lErrorKeySet );
            return;
        }
        if ( !ValidatorUtil.validateAllowedCharacters( pLoginName,
                EMAIL_ADDRESS_ALLOWED_REGEX ) ) {
            lErrorKeySet.add( pErrorKeyPrefix + ".loginname.notvalid" );
        }
        AbstractValidator.populateActionErrors( pActionErrors, lErrorKeySet );
    }

    /**
     * 
     * @param pLoginPassword
     * @param pActionErrors
     * @param pErrorKeyPrefix
     */
    @SuppressWarnings("unchecked")
    public static void validateLoginPassword( String pLoginPassword,
            ActionErrors pActionErrors, String pErrorKeyPrefix ) {

        Set<String> lErrorKeySet = new LinkedHashSet<String>();
        if ( pLoginPassword == null || "".equals( pLoginPassword ) ) {
            lErrorKeySet.add( pErrorKeyPrefix + ".password.required" );
            AbstractValidator
                    .populateActionErrors( pActionErrors, lErrorKeySet );
            return;
        }

    }
}
