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
import org.ahp.configuration.pojo.enums.AhpMessageResourceLoadType;
import org.ahp.configuration.pojo.enums.AhpMessageResourceType;

/**
 * 
 * @author Anita Onnuvel
 * 
 */
public class AhpMessageResource implements Serializable {

	private AhpLocale mAhpLocale;
	private String mName;
	private AhpMessageResourceLoadType mLoadType;
	private AhpMessageResourceType mResourceType;
	private String mResource;

	public AhpLocale getAhpLocale() {
		return mAhpLocale;
	}

	public void setAhpLocale(AhpLocale pAhpLocale) {
		mAhpLocale = pAhpLocale;
	}

	public String getName() {
		return mName;
	}

	public void setName(String pName) {
		mName = pName;
	}

	public AhpMessageResourceLoadType getLoadType() {
		return mLoadType;
	}

	public void setLoadType(AhpMessageResourceLoadType pLoadType) {
		mLoadType = pLoadType;
	}

	public AhpMessageResourceType getResourceType() {
		return mResourceType;
	}

	public void setResourceType(AhpMessageResourceType pResourceType) {
		mResourceType = pResourceType;
	}

	public String getResource() {
		return mResource;
	}

	public void setResource(String pResource) {
		mResource = pResource;
	}

	public String toString() {
		return AhpStringUtil.reflectionToString(this);
	}
}
