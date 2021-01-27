package br.com.borges.bitcoin.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;

@Entity
@UserDefinition
public class User extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String cpf;

	@Username
	private String username;

	@Password
	private String password;
	
	@Roles
	private String role;

	public Long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	public String getUsername() {
		return username;
	}

	@JsonbTransient
	public String getPassword() {
		return password;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	public String getRole() {
		return role;
	}

	public static void insert (User user) {
		user.password = BcryptUtil.bcryptHash(user.password);
		User.persist(user);
	}
}
