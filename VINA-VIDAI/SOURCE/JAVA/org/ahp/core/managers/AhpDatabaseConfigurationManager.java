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
package org.ahp.core.managers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ahp.configuration.pojo.AhpConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Anita Onnuvel
 * 
 */
final class AhpDatabaseConfigurationManager implements IAhpConfigurationManager {

    /** DOCUMENT ME! */
    final static Logger LOGGER = LoggerFactory
            .getLogger( AhpDatabaseConfigurationManager.class );

    @PersistenceContext
    private EntityManager mEntityManager;

    @Override
    public void init( AhpConfiguration pAhpConfiguration ) {
    }

    @Override
    public void start() {
    }

    @Override
    public void stop() {
    }

    @Override
    public void destroy() {
    }

    /**
     * DOCUMENT ME!
     */
    @Override
    public AhpConfiguration configure( String[] pResourceUriArray,
            String pRealPath ) {
        AhpConfiguration lAhpConfiguration = new AhpConfiguration();
        return lAhpConfiguration;
        /*
         * Map<String,String> lDbProperties = new HashMap<String,String>();
         * Query lQuery = mEntityManager.createQuery(
         * "SELECT DISTINCT configuration FROM AhpConfiguration configuration where configuration.environment = ?1 ORDER BY configuration.configurationName ASC"
         * ); lQuery.setParameter( 1, pEnvironment ); List lResultList =
         * lQuery.getResultList(); if( lResultList.size() != 0 ){ Iterator
         * lResultListIterator = lResultList.iterator(); while(
         * lResultListIterator.hasNext() ) { AhpConfiguration lAhpConfiguration
         * = ( AhpConfiguration ) lResultListIterator.next(); lDbProperties.put(
         * lAhpConfiguration.getConfigurationName(),
         * lAhpConfiguration.getConfigurationValue() ); } }
         */
    }

}