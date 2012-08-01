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
package org.ahp.core.messaging;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Anita Onnuvel
 * 
 * @spring.bean 
 *     name="ahpJmsProducer"
 * 
 * @spring.property 
 *     name="jmsConnectionFactory" 
 *     ref="jmsConnectionFactory"
 */
public class AhpJmsProducer {
    
    final static Logger LOGGER = LoggerFactory.getLogger( AhpJmsProducer.class );
    
    private ConnectionFactory mJmsConnectionFactory;
    
    @Autowired
    public void setJmsConnectionFactory( ConnectionFactory pJmsConnectionFactory ){
        this.mJmsConnectionFactory = pJmsConnectionFactory;
    }
        
    public void sendTextMessage( String pTextMessage, 
                                 AhpJmsDestinationTypes pAhpJmsDestinationTypes, 
                                 AhpJmsDestinationNames pAhpJmsDestinationNames ){
        Connection lConnection = null;
        Session lSession = null;
        try {
            lConnection = this.mJmsConnectionFactory.createConnection();
            lSession = lConnection.createSession( true, Session.AUTO_ACKNOWLEDGE );
            Destination lDestination = null;
            if ( pAhpJmsDestinationTypes.equals( AhpJmsDestinationTypes.Queue ) )
                lDestination = lSession.createQueue( pAhpJmsDestinationNames.toString() );
            else 
                lDestination = lSession.createTopic( pAhpJmsDestinationNames.toString() );
            MessageProducer lMessageProducer = lSession.createProducer( lDestination );
            lMessageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
            //lMessageProducer.setTimeToLive( timeToLive );
            Message lTextMessage = lSession.createTextMessage( pTextMessage );
            lMessageProducer.send( lTextMessage );
            lSession.commit();
        } catch ( JMSException exJms ) {
            LOGGER.error( "", exJms );
        } finally {
            try {
                lConnection.close();
            } catch ( JMSException exJms ) {
                LOGGER.error( "", exJms );
            }
        }
    }
    
}
