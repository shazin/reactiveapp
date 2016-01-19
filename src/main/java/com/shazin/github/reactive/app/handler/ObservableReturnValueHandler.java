package com.shazin.github.reactive.app.handler;

import java.util.Vector;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncUtils;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import rx.Observable;
import rx.Observer;

public class ObservableReturnValueHandler implements HandlerMethodReturnValueHandler {

	public boolean supportsReturnType(MethodParameter returnType) {
		Class parameterType = returnType.getParameterType();
		return Observable.class.isAssignableFrom(parameterType);
	}

	public void handleReturnValue(Object returnValue,
			MethodParameter returnType, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest) throws Exception {
		if(returnValue == null) {
			mavContainer.setRequestHandled(true);
			return;
		}
		
		final DeferredResult<Object> deferredResult = new DeferredResult<Object>();
		Observable observable = (Observable) returnValue;
		observable.subscribe(result -> deferredResult.setResult(result), errors -> deferredResult.setErrorResult(errors));
		
		WebAsyncUtils.getAsyncManager(webRequest).startDeferredResultProcessing(deferredResult, mavContainer);
		
	}
	
	

}
