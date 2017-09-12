package com.moneyTransfer.controller;

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.moneyTransfer.model.Account;
import com.moneyTransfer.model.Transaction;
import com.moneyTransfer.service.AccountService;
import com.moneyTransfer.service.impl.AccountServiceImpl;
import com.moneyTransfer.util.BussinesError;
import com.moneyTransfer.util.ErrorCode;
import com.moneyTransfer.util.ServiceException;

@Path("accounts")
public class AccountController {

	private AccountService accountService;

	public AccountController() {
		accountService = AccountServiceImpl.instance();
	}

	@GET
	@Produces("application/json")
	public Response getAllAccounts() {
		Set<Account> accounts = accountService.getAccounts();
		accounts.forEach(ac->{ac.setMovements(null);});
		return Response.ok(accounts).build();
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response getAccount(@PathParam("id") Long id) {
		Account account = accountService.findAccount(id);
		if (account == null) {
			throw new ServiceException(new BussinesError(ErrorCode.ACCOUNT_NOT_FOUND));
		}
		account.getMovements().forEach(mv->{mv.getAccountTo().setMovements(null);});
		return Response.ok(account).build();
	}



	@DELETE
	@Path("/delete/{id}")
	@Consumes("application/json")
	public Response deleteAccount(@PathParam("id") Long id) {
		accountService.deleteAccount(id);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/transaction/delete/{id}")
	@Consumes("application/json")
	public Response deleteTransaction(@PathParam("id") Long id) {
		accountService.deleteTransaction(id);
		return Response.ok().build();
	}
	
	@POST
	@Path("/transaction/add/{fromId}/{toId}")
	@Consumes("application/json")
	public Response addTx(@PathParam("fromId") Long fromId, @PathParam("toId") Long toId, Transaction tx) {
		accountService.addTransaction(fromId, toId, tx);
		return Response.ok().build();
	}
}
