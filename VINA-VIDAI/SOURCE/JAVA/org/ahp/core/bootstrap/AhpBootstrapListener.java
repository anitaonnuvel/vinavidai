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
package org.ahp.core.bootstrap;

import static org.ahp.core.constants.AhpConstants.AHP_CONFIGURATION;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.ahp.commons.exceptions.AhpRuntimeException;
import org.ahp.commons.util.AhpResourceUtil;
import org.ahp.core.managers.IAhpBootstrapManager;

/**
 * 
 * @author Anita Onnuvel
 * 
 * @spring.bean id="ahpBootstrapListener"
 * 
 * @spring.property name="ahpBootstrapManager" ref="ahpBootstrapManager"
 */
public class AhpBootstrapListener implements ServletContextListener {

	private IAhpBootstrapManager mAhpBootstrapManager;

	/**
	 * Default Constructor
	 */
	public AhpBootstrapListener() {
		super();
	}

	/**
	 * 
	 * @param pAhpBootstrapManager
	 */
	public void setAhpBootstrapManager(IAhpBootstrapManager pAhpBootstrapManager) {
		this.mAhpBootstrapManager = pAhpBootstrapManager;
	}

	/**
	 * Bootstrap APH
	 * 
	 */
	public void contextInitialized(ServletContextEvent pServletContextEvent) {
		try {
			ServletContext lServletContext = pServletContextEvent
					.getServletContext();
			String lAhpConfiguration = lServletContext
					.getInitParameter(AHP_CONFIGURATION);
			String lRealPath = lServletContext.getRealPath("/");
			StringBuilder lAhpConfigurationFileStringBuilder = new StringBuilder();
			for (String lAhpConfigFile : lAhpConfiguration.split(",")) {
				if (!AhpResourceUtil.isClassPathResource(lAhpConfigFile)
						|| !AhpResourceUtil.isClassPathResource(lAhpConfigFile)) {
					Path lAhpConfigurationFilePath = Paths.get(lRealPath
							+ lAhpConfigFile);
					lAhpConfigurationFileStringBuilder
							.append(lAhpConfigurationFilePath.toUri() + ",");
				} else {
					lAhpConfigurationFileStringBuilder.append(lAhpConfigFile);
				}
			}
			System.out.println("ConfigurationFiles - "
					+ lAhpConfigurationFileStringBuilder.toString());
			this.mAhpBootstrapManager.bootstrap(
					lAhpConfigurationFileStringBuilder.toString().split(","),
					lRealPath);
		} catch (AhpRuntimeException exAhpRuntime) {
			throw exAhpRuntime;
		} catch (Exception ex) {
			throw new AhpRuntimeException("AHP.001.0001", ex);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent pServletContextEvent) {
	}
}
