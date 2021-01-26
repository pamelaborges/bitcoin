package br.com.borges.bitcoin.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity(name = "BitcoinOrder")
public class Order extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal price;
	@Column(name= "type_order")
	private String type;
	private LocalDate created;
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setCreated(LocalDate date) {
		this.created = date;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public LocalDate getCreated() {
		return created;
	}

	public String getStatus() {
		return status;
	}

	public User getUser() {
		return user;
	}
	
	

	
}
