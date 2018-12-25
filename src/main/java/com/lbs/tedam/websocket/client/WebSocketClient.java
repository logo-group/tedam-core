/*
 * Copyright 2014-2019 Logo Business Solutions
 * (a.k.a. LOGO YAZILIM SAN. VE TIC. A.S)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.lbs.tedam.websocket.client;

import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import com.lbs.tedam.util.Constants;
import com.lbs.tedam.util.TedamProcessUtils;
import com.lbs.tedam.websocket.WebSocketException;

/**
 * Base class for web socket clients.
 */
@ClientEndpoint
public class WebSocketClient implements Serializable {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Web socket client session.
	 */
	private transient Session session = null;

	/**
	 * Web socket server URI.
	 */
	private String serverURI = null;

	/**
	 * Listener list for session operation.
	 */
	private List<WebSocketClientListener> listenerList = new ArrayList<>();

	/**
	 * One parameter constructor.
	 *
	 * @param serverURI Web socket server URI.
	 */
	public WebSocketClient(String serverURI) {
		this.serverURI = serverURI;
	}

	/**
	 * This method will be called when a web socket session is opened.
	 *
	 * @param session Session info on web socket session.
	 */
	@OnOpen
	public void onOpen(Session session) {
		setSession(session);
		for (WebSocketClientListener listener : listenerList)
			listener.onOpen(session);
	}

	/**
	 * This method will be called when a web socket session is closed.
	 *
	 * @param session     Session info on web socket session.
	 * @param closeReason Close reason for why session is closed.
	 */
	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		for (WebSocketClientListener listener : listenerList)
			listener.onClose(session, closeReason);
	}

	/**
	 * This method will be called when a web socket session received a message.
	 *
	 * @param message String message that is received on session.
	 */
	@OnMessage
	public void onMessage(String message) {
		for (WebSocketClientListener listener : listenerList)
			listener.onMessage(message);
	}

	/**
	 * This method will be called when a web socket session received an error.
	 *
	 * @param error Error instance.
	 */
	@OnError
	public void onError(Throwable error) {
		for (WebSocketClientListener listener : listenerList)
			listener.onError(error);
	}

	/**
	 * Connects to web socket server with given server URI. If there is a valid
	 * session, it will be closed. This method is one try method. If wants to
	 * connect until connection is successful, use reconnect().
	 *
	 * @throws WebSocketException Throws when an exception occurs during connection.
	 */
	public void connect() throws WebSocketException {
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		try {
			container.connectToServer(this, new URI(serverURI));
		} catch (DeploymentException | IOException | URISyntaxException e) {
			throw new WebSocketException("An exception occured during connecting to web socket server.", e);
		}
	}

	/**
	 * Connects to web socket server with given server URI. If there is a valid
	 * session, it will be closed. If conenction is not successful, will try again
	 * until connection is successful.
	 *
	 * @throws WebSocketException
	 */
	public synchronized void reconnect() {
		while (!isSessionValid()) {
			try {
				connect();
			} catch (WebSocketException e) {
				TedamProcessUtils.sleepThread(Constants.THREAD_SLEEP_TIME);
			}
		}
	}

	/**
	 * Disconnects from web socket server.
	 *
	 * @throws WebSocketException Throws when an exception occurs during
	 *                            disconnection.
	 */
	public void disconnect() throws WebSocketException {
		if (isSessionValid())
			try {
				getSession().close();
			} catch (IOException e) {
				throw new WebSocketException("An exception occured during closing connection.", e);
			}
	}

	/**
	 * Checks if session instance is valid or not.
	 *
	 * @return If session is not null and open then true, else false.
	 */
	public boolean isSessionValid() {
		return getSession() != null && getSession().isOpen();
	}

	/**
	 * @return the session
	 */
	public Session getSession() {
		return session;
	}

	/**
	 * @param session the session to set
	 */
	private void setSession(Session session) {
		this.session = session;
	}

	/**
	 * Adds WebSocketClientListener to list.
	 *
	 * @param listener Instance to add to list.
	 */
	public void addWebSocketClientListener(WebSocketClientListener listener) {
		listenerList.add(listener);
	}

	/**
	 * Gets web socket server URI given on constructor.
	 *
	 * @return Complete path of web socket server URI.
	 */
	public String getServerURI() {
		return serverURI;
	}

}
