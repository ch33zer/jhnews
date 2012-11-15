package com.jhnews.client;

/**When a service is called, it is called asynchronously, and non-blocking. This callback executes when the service completes. The generic parameter is the Type of the result of the call. For example, if the call returns a String, the generic parameter would be String
 * @author Group 8
 *
 * @param <T> The return type of the call you are making. If the method returns string, T should be String too.
 */
public interface LoginManagerCallback<T> {
	
	/** 
	 * If the call succeeded, then this method is called. For example, if we try to login, and the user successfully logs in, then this method is called. You can be sure that this parameter will never be null, because a null parameter represents a failed call
	 *@param result The result of the call
	 */
	public void onSuccess(T result);
	
	/**
	 * If the call fails for any reason (even logical ones) then this method is called. For instance, in a login method, if the user's password is incorrect, then this method is called.
	 */
	public void onFail();
}
