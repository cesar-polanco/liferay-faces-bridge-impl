/**
 * Copyright (c) 2000-2016 Liferay, Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liferay.faces.bridge.internal;

import javax.portlet.PortletConfig;
import javax.portlet.faces.Bridge;
import javax.portlet.faces.BridgePublicRenderParameterHandler;
import javax.portlet.faces.BridgePublicRenderParameterHandlerFactory;

import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;


/**
 * @author  Neil Griffin
 */
public class BridgePublicRenderParameterHandlerFactoryImpl extends BridgePublicRenderParameterHandlerFactory {

	// Logger
	private static final Logger logger = LoggerFactory.getLogger(BridgePublicRenderParameterHandlerFactoryImpl.class);

	@Override
	public BridgePublicRenderParameterHandler getBridgePublicRenderParameterHandler(PortletConfig portletConfig) {

		BridgePublicRenderParameterHandler bridgePublicRenderParameterHandler = null;

		// TCK TestPage016: initMethodTest
		String initParamName = Bridge.BRIDGE_PACKAGE_PREFIX + Bridge.BRIDGE_PUBLIC_RENDER_PARAMETER_HANDLER;
		String bridgePublicRenderParameterHandlerClass = portletConfig.getInitParameter(initParamName);

		if (bridgePublicRenderParameterHandlerClass != null) {

			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

			try {
				Class<?> clazz = classLoader.loadClass(bridgePublicRenderParameterHandlerClass);
				bridgePublicRenderParameterHandler = (BridgePublicRenderParameterHandler) clazz.newInstance();
			}
			catch (Exception e) {
				logger.error(e);
			}
		}

		return bridgePublicRenderParameterHandler;
	}

	@Override
	public BridgePublicRenderParameterHandlerFactory getWrapped() {

		// Since this is the factory instance provided by the bridge, it will never wrap another factory.
		return null;
	}
}