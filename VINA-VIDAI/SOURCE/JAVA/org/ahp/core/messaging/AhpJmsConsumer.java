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

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Anita Onnuvel
 *
 */
public class AhpJmsConsumer implements MessageListener {
    
    final static Logger LOGGER = LoggerFactory.getLogger( AhpJmsConsumer.class );
    
    private ConnectionFactory mJmsConnectionFactory;
    
    @Autowired
    public void setJmsConnectionFactory( ConnectionFactory pJmsConnectionFactory ){
        this.mJmsConnectionFactory = pJmsConnectionFactory;
    }
        
    /* (non-Javadoc)
     * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
     */
    @Override
    public void onMessage( Message pMessage ) {
        if ( pMessage instanceof TextMessage ) {
            try {
                System.out.println( ( ( TextMessage) pMessage).getText() );
            }
            catch ( JMSException exJms ) {
                throw new RuntimeException( exJms );
            }
        }
        else {
            throw new IllegalArgumentException( "Message must be of type TextMessage" );
        }
    }
    
}
