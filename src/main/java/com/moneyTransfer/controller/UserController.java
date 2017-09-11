package com.moneyTransfer.controller;

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.moneyTransfer.model.User;
import com.moneyTransfer.service.UserService;
import com.moneyTransfer.service.impl.UserServiceImpl;
import com.moneyTransfer.util.BussinesError;
import com.moneyTransfer.util.ErrorCode;
import com.moneyTransfer.util.ServiceException;

@Path("users")
public class UserController {

	private UserService userService;

	public UserController() {
		userService = new UserServiceImpl();
	}

	@GET
	@Produces("application/json")
	public Response getUsers() {
		Set<User> users = userService.getUsers();
		return Response.ok(users).build();
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response getUser(@PathParam("id") Long id) {
		User user = userService.findUser(id);
		if (user == null) {
			throw new ServiceException(new BussinesError(ErrorCode.USER_NOT_FOUND));
		}
		return Response.ok(user).build();
	}

	@POST
	@Path("/create")
	@Consumes("application/json")
	public Response addUser(User user) {
		userService.addOrUpdateUser(user);
		return Response.ok().build();
	}

	@PUT
	@Path("/update")
	@Consumes("application/json")
	public Response updateUser(User user) {
		userService.addOrUpdateUser(user);
		return Response.ok().build();
	}

	@DELETE
	@Path("/delete/{id}")
	@Consumes("application/json")
	public Response deleteUser(@PathParam("id") Long id) {
		userService.deleteUser(id);
		return Response.ok().build();
	}
}
