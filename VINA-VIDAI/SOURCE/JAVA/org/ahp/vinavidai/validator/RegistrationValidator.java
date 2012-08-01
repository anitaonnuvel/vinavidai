/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at

 * http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.ahp.vinavidai.validator;

import static org.ahp.commons.validator.ValidatorConstants.FIRST_LAST_NAME_ALLOWED_REGEX;
import static org.ahp.commons.validator.ValidatorConstants.PASSWORD_ALLOWED_REGEX;

import java.util.LinkedHashSet;
import java.util.Set;

import org.ahp.commons.validator.ValidatorUtil;
import org.ahp.core.pojo.User;
import org.ahp.registration.RegistrationService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Anita Onnuvel
 * 
 * @spring.bean 
 *     name="registrationValidator"
 * 
 * @spring.property 
 *     name="registrationService" 
 *     ref="registrationService"
 */
public class RegistrationValidator extends BaseValidator implements Validator  {

    private RegistrationService mRegistrationService;

    public void setRegistrationService( RegistrationService pRegistrationService ) {
        this.mRegistrationService = pRegistrationService;
    }

    @Override
    public boolean supports( Class pClass) {
        return User.class.isAssignableFrom( pClass );
    }

    @Override
    public void validate( Object pUserUnderRegistration, Errors pErrors ) {
        User lUserUnderRegistration = ( User ) pUserUnderRegistration;        
        validateUserRegistration( lUserUnderRegistration, pErrors );
    }
    
    /**
     * 
     * @param pUser
     * @param pErrors
     * @return
     */
    public void validateUserRegistration( User pUser, Errors pErrors ) {
        populateErrors( pErrors, "firstName", validateFirstName( pUser.getFirstName(), REGISTRATION_USER_ERROR_KEY_PREFIX ) );
        populateErrors( pErrors, "lastName", validateLastName( pUser.getLastName(),  REGISTRATION_USER_ERROR_KEY_PREFIX ) );
        populateErrors( pErrors, "loginName", validateLoginName( pUser.getLoginName(), REGISTRATION_USER_ERROR_KEY_PREFIX ) );
        populateErrors( pErrors, "password", validatePassword( pUser.getPassword(), REGISTRATION_USER_ERROR_KEY_PREFIX ) );
        populateErrors( pErrors, "confirmPassword", validateConfirmPassword( pUser.getPassword(), pUser.getConfirmPassword(), REGISTRATION_USER_ERROR_KEY_PREFIX ) );
    }

    /**
     * 
     * @param pFirstName
     * @param pErrorKeyPrefix
     * @return
     */
    public Set<String> validateFirstName( String pFirstName, 
                                              String pErrorKeyPrefix ) {
        Set<String> lErrorKeySet = new LinkedHashSet<String>();
        if ( pFirstName == null || "".equals( pFirstName ) ) {
            lErrorKeySet.add( pErrorKeyPrefix + ".first.name.required" );
            return lErrorKeySet;
        }
        if ( !ValidatorUtil.validateAllowedCharacters( pFirstName, FIRST_LAST_NAME_ALLOWED_REGEX ) ) {
            lErrorKeySet.add( pErrorKeyPrefix + ".first.name.notvalid" );
        }
        return lErrorKeySet;
    }

    /**
     * 
     * @param pLastName
     * @param pErrorKeyPrefix
     * @return
     */
    public Set<String> validateLastName( String pLastName, 
                                         String pErrorKeyPrefix ) {
        Set<String> lErrorKeySet = new LinkedHashSet<String>();
        if ( pLastName == null || "".equals( pLastName ) ) {
            lErrorKeySet.add( pErrorKeyPrefix + ".last.name.required" );
            return lErrorKeySet;
        }
        if ( !ValidatorUtil.validateAllowedCharacters( pLastName, FIRST_LAST_NAME_ALLOWED_REGEX ) ) {
            lErrorKeySet.add( pErrorKeyPrefix + ".last.name.notvalid" );
        }
        return lErrorKeySet;
    }
    
    /**
     * 
     * @param pLoginName
     * @param pErrorKeyPrefix
     * @return
     */
    public Set<String> validateLoginName( String pLoginName, 
                                          String pErrorKeyPrefix ) {
        Set<String> lErrorKeySet = new LinkedHashSet<String>();
        lErrorKeySet.addAll( validateEmailAddress( pLoginName, REGISTRATION_USER_ERROR_KEY_PREFIX ) );
        User lUser = new User();
        lUser.setLoginName( pLoginName );
        if ( this.mRegistrationService.doesUserExist( lUser ) ) {
            lErrorKeySet.add( pErrorKeyPrefix + ".email.exists" );
        }
        return lErrorKeySet;
    }

    /**
     * 
     * @param pPassword
     * @param pErrorKeyPrefix
     * @return
     */
    public Set<String> validatePassword( String pPassword, 
                                         String pErrorKeyPrefix ) {
        Set<String> lErrorKeySet = new LinkedHashSet<String>();
        if ( pPassword == null || "".equals( pPassword ) ) {
            lErrorKeySet.add( pErrorKeyPrefix + ".password.required" );
            return lErrorKeySet;
        }
        if ( !ValidatorUtil.validateAllowedCharacters( pPassword, PASSWORD_ALLOWED_REGEX ) ) {
            lErrorKeySet.add( pErrorKeyPrefix + ".password.notvalid" );
        }
        return lErrorKeySet;
    }

    /**
     * 
     * @param pPassword
     * @param pConfirmPassword
     * @param pErrorKeyPrefix
     * @return
     */
    public Set<String> validateConfirmPassword( String pPassword, 
                                                String pConfirmPassword, 
                                                String pErrorKeyPrefix ) {
        Set<String> lErrorKeySet = new LinkedHashSet<String>();
        if ( pConfirmPassword == null || "".equals( pConfirmPassword ) ) {
            lErrorKeySet.add( pErrorKeyPrefix + ".confirmpassword.required" );
            return lErrorKeySet;
        }
        if ( !pPassword.equals( pConfirmPassword ) ) {
            lErrorKeySet.add( pErrorKeyPrefix + ".confirmpassword.mismatch" );
            return lErrorKeySet;
        }
        return lErrorKeySet;
    }
}
