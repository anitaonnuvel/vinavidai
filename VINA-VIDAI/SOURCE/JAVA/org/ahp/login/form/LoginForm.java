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
package org.ahp.login.form;

import javax.servlet.http.HttpServletRequest;

import org.ahp.commons.form.AhpAbstractForm;
import org.ahp.login.enums.SubmitActions;
import org.ahp.login.validator.LoginValidator;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anita Onnuvel
 * 
 * @struts.form
 *     name="LoginForm"
 */
public class LoginForm extends AhpAbstractForm {

    private static final long serialVersionUID = 1L;

    final static Logger LOGGER = LoggerFactory.getLogger( LoginForm.class );

    private String loginName;
    private String password;

    @Override
    protected void resetForm( ActionMapping pActionMapping, HttpServletRequest pHttpServletRequest ) {
    }

    @Override
    public ActionErrors validate( ActionMapping pActionMapping, HttpServletRequest pHttpServletRequest ) {
        ActionErrors lActionErrors = new ActionErrors();
        String lSubmitAction = StringUtils.trimToNull( this.getSubmitAction() );
        if ( lSubmitAction != null && SubmitActions.LOGIN.toString().equals( ( lSubmitAction ) ) ) {
            LoginValidator.validateLoginForm( this, lActionErrors );
        }
        return lActionErrors;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName( String pLoginName ) {
        loginName = pLoginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String pPassword ) {
        password = pPassword;
    }

}
