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

/**
 * 
 * @author Anita Onnuvel
 * 
 */
public class ValidatorConstants {

    public static final int EMAIL_ADDRESS_MAX_LENGTH = 100;
    public static final int EMAIL_ADDRESS_MIN_LENGTH = 7;
    public static final String EMAIL_ADDRESS_ALLOWED_REGEX = "^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9_\\.\\-]+\\.[A-Za-z]{2,4}$";
    public static final String ALPHA_NUMERIC_ALLOWED_REGEX = "[A-Za-z0-9]*";
    public static final String ALPHA_ALLOWED_REGEX = "[A-Za-z]*";
    public static final String NUMERIC_ALLOWED_REGEX = "[0-9]*";
    public static final String FIRST_LAST_NAME_ALLOWED_REGEX = "[A-Za-z0-9 -/\\.,]{1,60}";
    public static final String PASSWORD_ALLOWED_REGEX = "[A-Za-z0-9]*{8,20}";

}
