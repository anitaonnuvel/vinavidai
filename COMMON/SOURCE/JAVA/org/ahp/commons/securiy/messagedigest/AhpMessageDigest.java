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
package org.ahp.commons.securiy.messagedigest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import org.ahp.commons.exceptions.AhpRuntimeException;

import sun.misc.BASE64Encoder;

/**
 * 
 * @author Anita Onnuvel
 * 
 */
public class AhpMessageDigest {

    /**
     * 
     * @param pClearText
     * @param pIterationCount
     * @return
     * @throws Exception
     */
    public static String createDigest( String pClearText, int pIterationCount ) {
        String lClearText = pClearText;
        MessageDigest lMessageDigest = null;
        for ( int i = 0; i < pIterationCount; i++ ) {
            try {
                lMessageDigest = MessageDigest.getInstance( "SHA-256" );
                lMessageDigest.reset();
                lMessageDigest.update( lClearText.getBytes( "UTF-16" ) );
                byte lDigestBytes[] = lMessageDigest.digest();
                lClearText = ( new BASE64Encoder() ).encode( lDigestBytes );
            } catch ( Exception ex ) {
                throw new AhpRuntimeException( "AHP", ex );
            }
        }
        return lClearText;
    }

    /**
     * 
     * @param pSaltLength
     * @return
     * @throws Exception
     */
    public static String createSalt( int pSaltLength ) throws Exception {
        // Set default length to 8 bytes
        if ( pSaltLength < 8 )
            pSaltLength = 8;
        try {
            Random lSecureRandom = SecureRandom.getInstance( "SHA1PRNG" );
            byte[] lSaltBytes = new byte[pSaltLength];
            lSecureRandom.nextBytes( lSaltBytes );
            String lSalt = new BASE64Encoder().encode( lSaltBytes );
            return lSalt;
        } catch ( NoSuchAlgorithmException exNoSuchAlgorithm ) {
            throw new Exception( exNoSuchAlgorithm );
        }
    }

    /**
     * 
     * @param pPasswordClearText
     * @return
     * @throws Exception
     */
    public static String createDigest( String pClearText, 
                                       boolean pUseSalt, 
                                       int pIterationCount ) throws Exception {
        return createDigest( pClearText, 100 );
    }

    /**
     * 
     * @param pPasswordClearText
     * @return
     * @throws Exception
     */
    public static String createDigest( String pClearText ) {
        return createDigest( pClearText, 1 );
    }
}
