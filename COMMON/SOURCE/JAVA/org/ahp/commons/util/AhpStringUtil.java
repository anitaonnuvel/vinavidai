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

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Anita Onnuvel
 * 
 */
public class AhpStringUtil {

    final static Logger LOGGER = LoggerFactory.getLogger( AhpStringUtil.class );

    /**
     * 
     * @param pObject
     * @return
     */
    public static String reflectionToString( Object pObject ) {
        return ToStringBuilder.reflectionToString( pObject,
                ToStringStyle.MULTI_LINE_STYLE );
    }

    /**
     * 
     * @param pListToConvert
     * @param pDelimiter
     * @return
     */
    public static String listToString( List<String> pListToConvert ) {
        return listToString( pListToConvert, "," );
    }

    /**
     * 
     * @param pListToConvert
     * @param pDelimiter
     * @return
     */
    public static String listToString( List<String> pListToConvert,
            String pDelimiter ) {
        StringBuilder lStringBuilder = new StringBuilder();
        if ( pListToConvert != null ) {
            for ( int i = 0; i < pListToConvert.size(); i++ ) {
                if ( i == pListToConvert.size() - 1 )
                    lStringBuilder.append( pListToConvert.get( i ) );
                else
                    lStringBuilder.append( pListToConvert.get( i ) ).append(
                            pDelimiter );
            }
        }
        return lStringBuilder.toString();
    }

    public static String listToQuotedString( List<String> pListToConvert ) {
        return listToQuotedString( pListToConvert, "," );
    }

    /**
     * 
     * @param pListToConvert
     * @param pDelimiter
     * @return
     */
    public static String listToQuotedString( List<String> pListToConvert,
            String pDelimiter ) {
        StringBuilder lStringBuilder = new StringBuilder();
        if ( pListToConvert != null ) {
            for ( int i = 0; i < pListToConvert.size(); i++ ) {
                if ( i == pListToConvert.size() - 1 )
                    lStringBuilder.append( "'" + pListToConvert.get( i ) + "'" );
                else
                    lStringBuilder.append( "'" + pListToConvert.get( i ) + "'" )
                            .append( pDelimiter );
            }
        }
        return lStringBuilder.toString();
    }
}
