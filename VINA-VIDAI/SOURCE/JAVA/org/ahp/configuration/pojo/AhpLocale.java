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
package org.ahp.configuration.pojo;

import java.io.Serializable;

import org.ahp.commons.util.AhpStringUtil;

/**
 * 
 * @author Anita Onnuvel
 * 
 */
public class AhpLocale implements Serializable {

    private String mLanguage;
    private String mCountry;
    private String mVariant;

    public String getLanguage() {
        return mLanguage;
    }

    public void setLanguage( String pLanguage ) {
        mLanguage = pLanguage;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry( String pCountry ) {
        mCountry = pCountry;
    }

    public String getVariant() {
        return mVariant;
    }

    public void setVariant( String pVariant ) {
        mVariant = pVariant;
    }

    public String toString() {
        return AhpStringUtil.reflectionToString( this );
    }

}
