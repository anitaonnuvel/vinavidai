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
package org.ahp.registration;

import org.ahp.commons.businessdelegate.AhpAbstractBusinessDelegate;
import org.ahp.commons.exceptions.AhpRuntimeException;
import org.ahp.core.businessdelegate.AhpBusinessDelegate;
import org.ahp.core.pojo.Audit;
import org.ahp.core.pojo.User;
import org.ahp.registration.dao.IRegistrationDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anita Onnuvel
 * 
 * @spring.bean id="registrationService"
 * 
 * @spring.property name="registrationDao" ref="registrationDao"
 */
public class RegistrationService extends AhpAbstractBusinessDelegate {
    
    final static Logger LOGGER = LoggerFactory.getLogger( RegistrationService.class );
    
    private IRegistrationDao mRegistrationDao;

    /**
     * @param pRegistrationDao the registrationDao to set
     */
    public void setRegistrationDao( IRegistrationDao pRegistrationDao ) {
        mRegistrationDao = pRegistrationDao;
    }
    
    /**
     * 
     * @param pUser
     */
    public void registerUser( User pUser ){
        if ( !this.mRegistrationDao.doesUserExist( pUser ) ) {
            Audit lAudit = AhpBusinessDelegate.createAudit( pUser );
            pUser.setAudit( lAudit );
            this.mRegistrationDao.createUser( pUser );
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
