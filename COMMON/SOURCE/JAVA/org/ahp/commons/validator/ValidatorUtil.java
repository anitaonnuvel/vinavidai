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
package org.ahp.commons.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Anita Onnuvel
 * 
 */
public final class ValidatorUtil {

    final static Logger LOGGER = LoggerFactory.getLogger( ValidatorUtil.class );

    /**
     * 
     * @param pInputString
     * @param pRegex
     * @return
     */
    public static boolean validateAllowedCharacters( String pInputString, String pRegex ) {
        Pattern lPattern = Pattern.compile( pRegex );
        Matcher lMatcher = lPattern.matcher( pInputString );
        return lMatcher.matches();
    }

    public static boolean isAlphaNumeric( String pInputString ) {
        return StringUtils.isAlphanumeric( pInputString );
    }

    public static boolean isAlpha( String pInputString ) {
        return StringUtils.isAlpha( pInputString );
    }

    public static boolean isNumeric( String pInputString ) {
        return StringUtils.isNumeric( pInputString );
    }

}
