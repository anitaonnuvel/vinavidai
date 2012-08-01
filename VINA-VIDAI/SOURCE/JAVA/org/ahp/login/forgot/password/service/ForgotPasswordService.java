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
package org.ahp.login.forgot.password.service;

import org.ahp.commons.businessdelegate.AhpAbstractBusinessDelegate;
import org.ahp.commons.exceptions.AhpRuntimeException;
import org.ahp.core.businessdelegate.AhpBusinessDelegate;
import org.ahp.core.messaging.AhpJmsDestinationNames;
import org.ahp.core.messaging.AhpJmsDestinationTypes;
import org.ahp.core.messaging.AhpJmsProducer;
import org.ahp.core.pojo.Audit;
import org.ahp.core.pojo.User;
import org.ahp.login.dao.IForgotPasswordDao;
import org.ahp.login.dao.ILoginDao;
import org.ahp.registration.dao.IRegistrationDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anita Onnuvel
 * 
 * @spring.bean 
 *    id="forgotPasswordService"
 * 
 * @spring.property 
 *     name="registrationDao" 
 *     ref="registrationDao"
 * @spring.property 
 *     name="forgotPasswordDao" 
 *     ref="forgotPasswordDao"
 * @spring.property 
 *     name="loginDao" 
 *     ref="loginDao"
 * @spring.property 
 *     name="ahpJmsProducer" 
 *     ref="ahpJmsProducer"
 */
public class ForgotPasswordService extends AhpAbstractBusinessDelegate {
    
    final static Logger LOGGER = LoggerFactory.getLogger( ForgotPasswordService.class );
    
    private IRegistrationDao mRegistrationDao;
    
    private IForgotPasswordDao mForgotPasswordDao;
    
    private ILoginDao mLoginDao;
    
    private AhpJmsProducer mAhpJmsProducer;

    /**
     * @param pAhpJmsProducer the ahpJmsProducer to set
     */
    public void setAhpJmsProducer( AhpJmsProducer pAhpJmsProducer ) {
        this.mAhpJmsProducer = pAhpJmsProducer;
    }

    /**
     * @param pRegistrationDao the registrationDao to set
     */
    public void setRegistrationDao( IRegistrationDao pRegistrationDao ) {
        this.mRegistrationDao = pRegistrationDao;
    }
    
    /**
     * @param pRegistrationDao the registrationDao to set
     */
    public void setForgotPasswordDao( IForgotPasswordDao pForgotPasswordDao ) {
        this.mForgotPasswordDao = pForgotPasswordDao;
    }
    
    /**
     * @param pRegistrationDao the registrationDao to set
     */
    public void setLoginDao( ILoginDao pLoginDao ) {
        this.mLoginDao = pLoginDao;
    }

    /**
     * 
     * @param pUser
     */
    public User resetPassword( User pUser ){
        if ( this.mRegistrationDao.doesUserExist( pUser ) ) {
            User lUserUnderResetPassword = this.mLoginDao.loadUserByLoginName( pUser.getLoginName() );
            lUserUnderResetPassword.setPassword( pUser.getPassword() );
            Audit lAudit = AhpBusinessDelegate.createAudit( lUserUnderResetPassword );
            pUser.setAudit( lAudit );
            this.mForgotPasswordDao.updateUserPassword( lUserUnderResetPassword );
            this.mAhpJmsProducer.sendTextMessage( "Password is reset for" + pUser.getLoginName() , 
                                                  AhpJmsDestinationTypes.Queue, 
                                                  AhpJmsDestinationNames.QueueEmailForgotPassword );
            return lUserUnderResetPassword;
        } else {
            throw new AhpRuntimeException( "AHP.000.0001" );
        }
    }

    /**
     * 
     * @param pUser
     */
    public boolean doesUserExist( User pUser ){
        return this.mRegistrationDao.doesUserExist( pUser );
    }
    
}
