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
package org.ahp.login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ahp.commons.action.AhpAbstractDisplayAction;
import org.ahp.login.enums.NavigateActions;
import org.ahp.login.form.LoginForm;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anita Onnuvel
 * 
 * @struts.action 
 *     path="/DisplayLogin" 
 *     name="LoginForm" 
 *     scope="session"
 *     validate="false"
 * 
 * @struts.action-forward 
 *     name="DisplayLogin" 
 *     path="/login/Login.jsp"
 * 
 * @struts.action-forward 
 *     name="DisplayHomePage" 
 *     path="/login/Home.jsp"
 * 
 */
public class DisplayLogin extends AhpAbstractDisplayAction {

    final static Logger LOGGER = LoggerFactory.getLogger( DisplayLogin.class );

    @Override
    public ActionForward display( ActionMapping pActionMapping, 
                                  ActionForm pActionForm,
                                  HttpServletRequest pHttpServletRequest,
                                  HttpServletResponse pHttpServletResponse ) {
        LoginForm lLoginForm = ( LoginForm ) pActionForm;
        if ( NavigateActions.DisplayHomePage.toString().equals( StringUtils.trimToEmpty( lLoginForm.getNextPage() ) ) ) {
            return pActionMapping.findForward( NavigateActions.DisplayHomePage.toString() );
        }
        return pActionMapping.findForward( NavigateActions.DisplayLogin.toString() );
    }
}
