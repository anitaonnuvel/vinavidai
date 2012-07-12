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
package org.ahp.vinavidai.validator;

import org.ahp.commons.validator.AbstractValidator;

/**
 * 
 * @author Anita Onnuvel
 * 
 */
public class BaseValidator extends AbstractValidator {

    public static final String CREATE_QUIZ_ERROR_KEY_PREFIX = "create.quiz";
    public static final String EDIT_QUIZ_ERROR_KEY_PREFIX = "edit.quiz";
    public static final String CREATE_QUESTION_ERROR_KEY_PREFIX = "create.question";
    public static final String EDIT_QUESTION_ERROR_KEY_PREFIX = "edit.question";
    public static final String MANAGE_QUIZ_ERROR_KEY_PREFIX = "manage.quiz";
    public static final String PUBLISH_QUIZ_ERROR_KEY_PREFIX = "publish.quiz";
}
