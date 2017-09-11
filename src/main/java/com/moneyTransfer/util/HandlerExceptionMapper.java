package com.moneyTransfer.util;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class HandlerExceptionMapper implements ExceptionMapper<Throwable> {
	
	@Override
	public Response toResponse(Throwable exception) {
		BussinesError error = new BussinesError(exception.getMessage(), parserStackTrace(exception));
		return Response.status(500)
				.entity(error)
				.type(MediaType.APPLICATION_JSON_TYPE)
				.build();
	}

	/**
	 * Parser stack trace.
	 * 
	 * @param ex
	 * @return
	 */
	private List<String> parserStackTrace(Throwable ex) {
		if (ex == null) {
			return null;
		}
		List<String> trace = new ArrayList<String>();
		trace.add(ex.getClass().getName() + ": " + ex.getMessage());
		StackTraceElement[] stackTraceElems = ex.getStackTrace();
		Throwable cause = ex.getCause();
		if (cause != null) {
			addRootCause(cause, stackTraceElems, trace);
		}
		return trace;
	}

	/**
	 * 
	 * Retrieve root cause for the specified stack trace.
	 * 
	 */
	private void addRootCause(Throwable ex, StackTraceElement[] causedTrace, List<String> stackTraceElems) {
		StackTraceElement[] trace = ex.getStackTrace();
		int m = trace.length - 1, n = causedTrace.length - 1;
		while (m >= 0 && n >= 0 && trace[m].equals(causedTrace[n])) {
			m--;
			n--;
		}
		int framesInCommon = trace.length - 1 - m;
		stackTraceElems.add("Throw by : " + ex.getClass().getName() + ":" + ex.getMessage() + " ---.");
		for (int i = 0; i <= m; i++) {
			stackTraceElems.add("in " + trace[i]);
		}
		if (framesInCommon != 0) {
			stackTraceElems.add("... " + framesInCommon + " more");
		}
		Throwable ourCause = ex.getCause();
		if (ourCause != null) {
			addRootCause(ourCause, trace, stackTraceElems);
		}
	}
}
