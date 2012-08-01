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
package org.ahp.login.forgot.password;

import static org.ahp.core.constants.HttpSessionAttributeConstants.LOGGED_IN_USER;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.ahp.core.pojo.User;
import org.ahp.login.forgot.password.service.ForgotPasswordService;
import org.ahp.vinavidai.validator.ForgotPasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Anita Onnuvel
 * 
 */
@Controller
@RequestMapping("/ForgotPassword")
public class ForgotPasswordController {
    
    @Autowired
    private ForgotPasswordService mForgotPasswordService;

    public void setRegistrationService( ForgotPasswordService pForgotPasswordService ) {
        this.mForgotPasswordService = pForgotPasswordService;
    }
    
    @Autowired
    private MessageSource mMessageSource;

    public void setMessageSource( MessageSource pMessageSource ) {
        this.mMessageSource = pMessageSource;
    }
    
    @Autowired
    private ForgotPasswordValidator mForgotPasswordValidator;

    public void setForgotPassword( ForgotPasswordValidator pForgotPasswordValidator ) {
        this.mForgotPasswordValidator = pForgotPasswordValidator;
    }

    @RequestMapping( method = RequestMethod.POST )
    public String registerUser( HttpServletRequest pHttpServletRequest,
                                @ModelAttribute( "user" ) User pUser, 
                                BindingResult pBindingResult, 
                                Model pModel ) throws IOException{
        System.out.println( pUser );        
        if ( pBindingResult.hasErrors() ) {
            return "/login/ForgotPassword.jsp";
        }
        this.mForgotPasswordValidator.validate( pUser, pBindingResult );
        if ( pBindingResult.hasErrors() ) {
            return "/login/ForgotPassword.jsp";
        }
        User lUserUnderResetPassword = mForgotPasswordService.resetPassword( pUser );
        pHttpServletRequest.getSession().setAttribute( LOGGED_IN_USER, lUserUnderResetPassword );
        return "redirect:/login/Home.jsp";
    }
    
    @RequestMapping( method = RequestMethod.GET )
    public String showPasswordResetFlow( ModelMap pModelMap, @ModelAttribute( "user" ) User pUser ) {
        System.out.println( "beginTest" );
        return "forward:/login/ForgotPassword.jsp";
    }
    
}
