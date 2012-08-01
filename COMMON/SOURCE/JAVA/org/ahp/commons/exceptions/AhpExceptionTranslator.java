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
package org.ahp.commons.exceptions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.MissingResourceException;

/**
 * 
 * @author Anita Onnuvel
 * 
 */
public final class AhpExceptionTranslator {

    /**
     * 
     * @param pClass
     * @param pExceptionResourceKey
     * @param pExceptionResourceArguments
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static AhpRuntimeException newException( Class pClass, 
                                                    Throwable pThrowable, 
                                                    String pExceptionResourceKey,
                                                    Object[] pExceptionResourceArguments ) {                   
        try {
            Constructor lConstructor = pClass.getConstructor( String.class, Throwable.class );
            AhpRuntimeException lAhpRuntimeException = ( AhpRuntimeException ) lConstructor.newInstance( "", pThrowable );
        } catch ( InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException | SecurityException ex ) {
            ex.printStackTrace();
        }        
        return new AhpRuntimeException( "AHP.000.0000" );
    }

    /**
     * 
     * @param pClass
     * @param pExceptionResourceKey
     * @param pExceptionResourceArguments
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static AhpRuntimeException newException( Throwable pThrowable, String pExceptionResourceKey,
            Object[] pExceptionResourceArguments ) {
        return newException( AhpRuntimeException.class, pThrowable, pExceptionResourceKey, pExceptionResourceArguments );
    }

    public static void main( String[] pArgs ) {
        AhpExceptionTranslator.newException( AhpRuntimeException.class, new RuntimeException(), "AHP.001.0001", null );
    }

}
