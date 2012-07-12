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
import java.util.Map;

import org.ahp.commons.util.AhpStringUtil;

/**
 * 
 * @author Anita Onnuvel
 * 
 */
public class AhpConfiguration implements Serializable {

    private AhpDeployment mAhpDeployment;
    private Map<String, AhpMessageResource> mAhpMessageResources;

    public AhpDeployment getAhpDeployment() {
        return mAhpDeployment;
    }

    public void setAhpDeployment( AhpDeployment pAhpDeployment ) {
        mAhpDeployment = pAhpDeployment;
    }

    public Map<String, AhpMessageResource> getAhpMessageResources() {
        return mAhpMessageResources;
    }

    public void setAhpMessageResources(
            Map<String, AhpMessageResource> pAhpMessageResources ) {
        mAhpMessageResources = pAhpMessageResources;
    }

    public String toString() {
        return AhpStringUtil.reflectionToString( this );
    }

}
