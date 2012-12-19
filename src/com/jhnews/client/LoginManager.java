package com.jhnews.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jhnews.shared.FieldVerifier;
import com.jhnews.shared.Session;
import com.jhnews.shared.User;
import com.jhnews.shared.UserTags;

/**
 * Handles login, logout and registration. Abstracts away the GWT specific
 * elements.
 * @author Group 8
 * 
 */
public class LoginManager {

	private static LoginManager instance;
	private List<LoginListener> loginListeners;
	private RestrictedServiceAsync restrictedService = GWT.create(RestrictedService.class);

	/**
	 * Private default constructor for the singleton pattern
	 */
	private LoginManager() {
		if (restrictedService == null) {
			restrictedService = GWT.create(RestrictedService.class);
		}
		loginListeners = new ArrayList<LoginListener>();
	}
	
	/**
	 * Get an instance of the LoginManager.
	 * @return The login manager
	 */
	public static LoginManager getInstance() {
		if (instance == null) {
			instance = new LoginManager();
		}
		return instance;
	}
	
	/**
	 * Returns the current user session ID, or null if the user has no session ID
	 * @return The session ID
	 */
	public String getSessionID() {
		return Cookies.getCookie("sid");
	}

	/**
	 * Returns the user name for the user logged in
	 * @return The user name
	 */
	public String getUsername() {
		return Cookies.getCookie("username");
	}

	/**
	 * Adds log in listeners
	 * @param listener The listener to be added to the list
	 */
	public void addLoginListener(LoginListener listener) {
		loginListeners.add(listener);
	}
	
	/**
	 * Gets and returns the list of LoginListeners
	 * @return the list of LoginListeners
	 */
	public List<LoginListener> getLoginListener() {
		return loginListeners;
	}
	
	
	/**
	 * Removes log in listeners
	 * @param listener The listener to be removed to the list
	 */
	public void removeLoginListener(LoginListener listener) {
		loginListeners.remove(listener);
	}

	/**
	 * Provides an asyn method for finding out if the user is logged on. Pass in
	 * a LoginInManagerCallback, where the code to execute in the case that we
	 * are logged on is in onSuccess(), and onFail() in the case that the user
	 * is not logged in
	 * 
	 * @param callback
	 *            The callback, as described above. Executes at the completion
	 *            of the call.
	 */
	public void isLoggedOn(final LoginManagerCallback<Boolean> callback) {

		AsyncCallback<Boolean> loggedInCallBack = new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				if (callback != null) {
					callback.onFail();
				}

			}

			@Override
			public void onSuccess(Boolean result) {
				if (callback != null) {
					if (result != null && result) {
						callback.onSuccess(result);
					} else {
						callback.onFail();
					}
				}

			}
		};
		restrictedService.isLoggedIn(getSessionID(), loggedInCallBack);
	}

	/**
	 * Attempts to log the user in with the specified credentials. In the case
	 * of a successful login, the callback's onSuccessmethod gets called with
	 * the session object. In the case of a failure for any reason, the
	 * onFailure method gets called
	 * 
	 * @param username
	 *            The users username
	 * @param password
	 *            The users password
	 * @param callback
	 *            The callback, as described above. Executes at the completion
	 *            of the call.
	 */
	public void logIn(String username, String password,
			final LoginManagerCallback<Session> callback) {
		AsyncCallback<Session> logInCallBack = new AsyncCallback<Session>() {

			@Override
			public void onFailure(Throwable caught) {
				if (callback != null) {
					callback.onFail();
				}

			}

			@Override
			public void onSuccess(Session result) {
				if (callback != null) {
					if (result != null) {
						
						createSessionCookie(result);
						callback.onSuccess(result);
					} else {
						callback.onFail();
					}
				}
				if (result != null) {
					for (LoginListener listener : loginListeners) {
						listener.onLogin();
					}
				}
			}
		};
		restrictedService.logIn(username, password, logInCallBack);
	}
	
	/**
	 * Logs the user out
	 * @param callback Code that is executed when the method returns
	 */
	public void logOut(final LoginManagerCallback<Void> callback) {
		AsyncCallback<Void> logOutCallBack = new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				if (callback != null) {
					callback.onFail();
				}

			}

			@Override
			public void onSuccess(Void result) {
				for (LoginListener listener : loginListeners) {
					listener.onLogout();
				}
				if (callback != null) {
					if (result != null) {
						callback.onSuccess(result);
					} else {
						callback.onFail();
					}
				}
			}
		};
		restrictedService.logOut(getSessionID(), logOutCallBack);
	}

	/**
	 * Attempts to register the user with the specified credentials. I the case
	 * of a successful registration, the callback's onSuccess method is called.
	 * If the user fails to login, the onFail method is called
	 * @param user The user to register
	 * @param callback
	 *            The callback, as described above. Executes at the completion
	 *            of the call.
	 */
	public void register(User user, String password, final LoginManagerCallback<Session> callback) {
		if (FieldVerifier.isValidUserNameAndPassword(user.getUsername(), password)) {
			AsyncCallback<Session> registerCallback = new AsyncCallback<Session>() {

				@Override
				public void onFailure(Throwable caught) {
					if (callback != null) {
						callback.onFail();
					}

				}

				@Override
				public void onSuccess(Session result) {
					if (callback != null) {
						if (result != null) {
							createSessionCookie(result);
							callback.onSuccess(result);
						} else {
							callback.onFail();
						}
					}
				}
			};
			restrictedService.register(user, password, registerCallback);
		}
		else {
			if (callback != null) {
				callback.onFail();
			}
		}

	}

	/**
	 * Creates a new session cookie
	 * @param newSession The information for the cookie
	 */
	public void createSessionCookie(Session newSession) {
		Cookies.setCookie("sid", newSession.getSessionID(),
				newSession.getExpireDate(), null, "/", false);
		Cookies.setCookie("username", newSession.getUser().getUsername(),
				newSession.getExpireDate(), null, "/", false);
	}
	
	/**
	 * Checks if the current user is an admin
	 * @param callback The callback to describe the result
	 */
	public void isAdmin(final LoginManagerCallback<Boolean> callback) {

		AsyncCallback<Boolean> adminCallBack = new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				if (callback != null) {
					callback.onFail();
				}

			}

			@Override
			public void onSuccess(Boolean result) {
				if (callback != null) {
					if (result != null && result) {
						callback.onSuccess(result);
					} else {
						callback.onFail();
					}
				}
			}
		};
		restrictedService.isAdmin(getSessionID(), adminCallBack);
	}

	public void getUser(final LoginManagerCallback<User> callback) {
		AsyncCallback<User> userCallback = new AsyncCallback<User>() {

			@Override
			public void onFailure(Throwable caught) {
				if (callback != null) {
					callback.onFail();
				}
			}

			@Override
			public void onSuccess(User result) {
				if (callback != null) {
					if (result != null) {
						callback.onSuccess(result);
					} else {
						callback.onFail();
					}
				}
			}
		};
		restrictedService.getUser(getSessionID(), userCallback);
	}
	
	public void saveUser(User user, final LoginManagerCallback<Void> callback) {
		AsyncCallback<Void> userCallback = new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				if (callback != null) {
					callback.onFail();
				}
			}

			@Override
			public void onSuccess(Void result) {
				if (callback != null) {
					callback.onSuccess(result);
				}
			}
		};
		restrictedService.saveUserTags(getSessionID(), user, userCallback);
	}
}
