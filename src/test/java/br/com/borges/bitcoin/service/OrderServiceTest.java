package br.com.borges.bitcoin.service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.core.SecurityContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import br.com.borges.bitcoin.model.Order;
import br.com.borges.bitcoin.model.User;
import br.com.borges.bitcoin.repository.OrderRepository;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

@QuarkusTest
public class OrderServiceTest {

	@InjectMock
	OrderRepository orderRepository;
	
	@Inject
	OrderService orderService;
	
	@Test
	public void testAddOrderSuccess() {
		
		PanacheMock.mock(User.class);
		
		User user = new User();
		user.setId(1l);
		user.setUsername("teste");
		
		mockUserFindByIdOptional(user);
		SecurityContext mockSecurityContext = mockSecurityContext(user);
		
		Order order = new Order();
		order.setPrice(BigDecimal.TEN);
		order.setType("COMPRA");
		order.setUser(user);
		Mockito.doNothing().when(orderRepository).persist(ArgumentMatchers.any(Order.class));
		
		orderService.insert(mockSecurityContext, order);
		Mockito.verify(orderRepository, Mockito.times(1)).persist(order);
		
	}

	@Test
	public void testAddOrderUsuarioInexistente() {

		PanacheMock.mock(User.class);

		User user = new User();
		user.setId(1l);
		user.setUsername("teste");

		User user2 = new User();
		user2.setId(2l);
		user2.setUsername("teste");

		mockUserFindByIdOptional(user);
		SecurityContext mockSecurityContext = mockSecurityContext(user);

		Order order = new Order();
		order.setPrice(BigDecimal.TEN);
		order.setType("COMPRA");
		order.setUser(user2);
		Mockito.doNothing().when(orderRepository).persist(ArgumentMatchers.any(Order.class));

		Assertions.assertThrows(RuntimeException.class, ()-> orderService.insert(mockSecurityContext, order));
		Mockito.verify(orderRepository, Mockito.times(0)).persist(order);
	}

	private void mockUserFindByIdOptional(User user) {
		Optional<PanacheEntityBase> maybeUser = Optional.of(user);
		Mockito.when(User.findByIdOptional(user.getId())).thenReturn(maybeUser);
	}

	private SecurityContext mockSecurityContext(User user) {
		SecurityContext mockSecurityContext = Mockito.mock(SecurityContext.class);
		Mockito.when(mockSecurityContext.getUserPrincipal()).thenReturn(new Principal() {
			@Override
			public String getName() {
				return user.getUsername();
			}
		});
		
		return mockSecurityContext;
	}
}
