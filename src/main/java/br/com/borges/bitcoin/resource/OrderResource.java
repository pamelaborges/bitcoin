package br.com.borges.bitcoin.resource;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import br.com.borges.bitcoin.model.Order;
import br.com.borges.bitcoin.service.OrderService;

@Path("/orders")
public class OrderResource {

	@Inject
	OrderService service;

	@POST
	@Transactional
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed("USER")
	public void insert(@Context SecurityContext securityContext, Order order) {
		service.insert(securityContext, order);
	}

}
