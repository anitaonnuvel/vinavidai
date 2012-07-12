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

import org.ahp.login.form.LoginForm;
import org.apache.struts.action.ActionErrors;

/**
 * 
 * @author Anita Onnuvel
 * 
 */
public final class LoginValidator extends BaseValidator {

    /**
     * 
     * @param pLoginForm
     * @param pActionErrors
     */
    public static void validateLoginForm( LoginForm pLoginForm,
            ActionErrors pActionErrors ) {
        validateLoginName( pLoginForm.getLoginName(), pActionErrors,
                LOGIN_ERROR_KEY_PREFIX );
        validateLoginPassword( pLoginForm.getPassword(), pActionErrors,
                LOGIN_ERROR_KEY_PREFIX );
    }

}
