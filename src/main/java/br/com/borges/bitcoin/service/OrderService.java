package br.com.borges.bitcoin.service;

import java.time.LocalDate;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.SecurityContext;

import br.com.borges.bitcoin.model.Order;
import br.com.borges.bitcoin.model.User;
import br.com.borges.bitcoin.repository.OrderRepository;

@ApplicationScoped
public class OrderService {
	
	@Inject
	OrderRepository repository;
	
	public void insert(SecurityContext securityContext, Order order) {
		
		Optional<User> maybeUser = User.findByIdOptional(order.getUser().getId());
		
		User user = maybeUser.orElseThrow(() -> { throw new RuntimeException("Usuário inexistente"); });
		
		if(!user.getUsername().equals(securityContext.getUserPrincipal().getName())) {
			throw new RuntimeException("Operação não permitida");
		}
		
		order.setCreated(LocalDate.now());
		order.setStatus("ENVIADA");
		order.setUser(user);
		repository.persist(order);
	}
}
