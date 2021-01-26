package br.com.borges.bitcoin.resource;

import java.time.LocalDate;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import br.com.borges.bitcoin.model.Order;
import br.com.borges.bitcoin.repository.OrderRepository;

@Path("/orders")
public class OrderResource {

	@Inject
	OrderRepository repository;

	@POST
	@Transactional
	@Consumes(MediaType.APPLICATION_JSON)
	public void insert(Order order) {
		order.setCreated(LocalDate.now());
		order.setStatus("ENVIADA");
		repository.persist(order);
	}

}
