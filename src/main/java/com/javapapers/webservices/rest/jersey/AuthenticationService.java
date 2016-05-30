package com.javapapers.webservices.rest.jersey;

import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;
//http://javapapers.com/web-service/restful-services-http-basic-authentication/
public class AuthenticationService {
	public boolean authenticate(String authCredentials) {

		if (null == authCredentials)
			return false;
		// header value format will be "Basic encodedstring" for Basic
		// authentication. Example "Basic YWRtaW46YWRtaW4="
		final String encodedUserPassword = authCredentials.replaceFirst("Basic"
				+ " ", "");
                System.out.println("encodedUserPassword " + encodedUserPassword);
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(
					encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		final StringTokenizer tokenizer = new StringTokenizer(
				usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();

		// we have fixed the userid and password as admin
		// call some UserService/LDAP here
                System.out.println("username " + username);
                System.out.println("password " + password);
		boolean authenticationStatus = "admin".equals(username)
				&& "admin".equals(password);
		return authenticationStatus;
	}
}
