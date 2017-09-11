package com.moneyTransfer.util;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * 
 * @author luis.stubbia
 *
 */
@Provider
public class HandlerServiceExceptionMapper implements ExceptionMapper<ServiceException> {

	@Override
	public Response toResponse(ServiceException exception) {
		int status = 400;
		if (exception.getErrors().size() == 1) {
			status = exception.getErrors().get(0).getStatus();
		}
		return Response.status(status).entity(exception.getErrors()).type(MediaType.APPLICATION_JSON_TYPE).build();
	}
}
