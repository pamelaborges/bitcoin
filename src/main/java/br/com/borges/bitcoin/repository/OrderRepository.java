package br.com.borges.bitcoin.repository;

import javax.enterprise.context.ApplicationScoped;

import br.com.borges.bitcoin.model.Order;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class OrderRepository implements PanacheRepository<Order> {

}
