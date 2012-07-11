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
package org.ahp.commons.util;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Anita Onnuvel
 *
 */
public final class AhpTimeUtil {
	
    final static Logger LOGGER = LoggerFactory.getLogger( AhpTimeUtil.class );
    
    /**
     * 
     * @return
     */
    public static Set<String> getHourInDay(){
    	Set<String> lHours = new LinkedHashSet<String>();
    	for ( int i = 0 ; i <= 23; i++ ) {
    		lHours.add( "" + i );
    	}
    	return lHours;
    }

    /**
     * 
     * @return
     */
    public static Set<String> getHourInAmPm(){    	
    	Set<String> lHours = new LinkedHashSet<String>();
    	for ( int i = 0 ; i <= 12; i++ ) {
    		lHours.add( "" + i + "AM");
    	}
    	for ( int i = 1 ; i <= 11; i++ ) {
    		lHours.add( "" + i + "PM");
    	}
    	return lHours;
    }

    /**
     * 
     * @return
     */
    public static Set<String> getMinutes(){
    	Set<String> lMinutes = new LinkedHashSet<String>();
    	for ( int i = 0 ; i <= 59; i++ ) {
    		lMinutes.add( ""+ i );
    	}
    	return lMinutes;
    }

    /**
     * 
     * @return
     */
    public static Set<String> getSeconds(){
    	Set<String> lSeconds = new LinkedHashSet<String>();
    	for ( int i = 0 ; i <= 59; i++ ) {
    		lSeconds.add( "" + i );
    	}
    	return lSeconds;
    }

}
