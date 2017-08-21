//package com.vip.yyl.service.handler.http.auth;
//
//import com.vip.yyl.service.dto.ApiRequestDTO;
//import com.vip.yyl.service.dto.DigestAuthDTO;
//import org.apache.http.auth.AuthScope;
//import org.apache.http.auth.UsernamePasswordCredentials;
//import org.apache.http.client.AuthCache;
//import org.apache.http.client.CredentialsProvider;
//import org.apache.http.client.protocol.HttpClientContext;
//import org.apache.http.impl.auth.DigestScheme;
//import org.apache.http.impl.client.BasicAuthCache;
//import org.apache.http.impl.client.BasicCredentialsProvider;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DigestAuthHandler {
//
//    public void setCredentialsProvider(ApiRequestDTO apiRequestDTO, HttpClientBuilder clientBuilder) {
//	DigestAuthDTO digestAuthDTO = apiRequestDTO.getDigestAuthDTO();
//	if (digestAuthDTO == null) {
//	    return;
//	}
//	String userName = digestAuthDTO.getUsername();
//	String password = digestAuthDTO.getPassword();
//	if (userName == null || userName.isEmpty() || password == null || password.isEmpty()) {
//	    return;
//	}
//	CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//	credentialsProvider.setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT, AuthScope.ANY_REALM),
//		new UsernamePasswordCredentials(userName, password));
//
//	clientBuilder.setDefaultCredentialsProvider(credentialsProvider);
//    }
//
//    public HttpClientContext preemptive() {
//	AuthCache authCache = new BasicAuthCache();
//
//	DigestScheme digestAuth = new DigestScheme();
//
//	digestAuth.overrideParamter("realm", "");
//	digestAuth.overrideParamter("nonce", "");
//
//	// TODO : Add target
//	// authCache.put(target, digestAuth);
//	HttpClientContext localContext = HttpClientContext.create();
//	localContext.setAuthCache(authCache);
//
//	return localContext;
//    }
//}
