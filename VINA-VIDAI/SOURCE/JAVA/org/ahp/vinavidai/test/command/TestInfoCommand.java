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
package org.ahp.vinavidai.test.command;

import org.ahp.commons.form.AhpAbstractCommandObject;
import org.ahp.vinavidai.pojo.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anita Onnuvel
 * 
 */
public class TestInfoCommand extends AhpAbstractCommandObject {

    private static final long serialVersionUID = 1L;

    final static Logger LOGGER = LoggerFactory.getLogger( TestInfoCommand.class );

    private Test mTest;
    private String mAccessKey;

    public Test getTest() {
        return mTest;
    }

    public void setTest( Test pTest ) {
        mTest = pTest;
    }

    public String getAccessKey() {
        return mAccessKey;
    }

    public void setAccessKey( String pAccessKey ) {
        mAccessKey = pAccessKey;
    }
}
