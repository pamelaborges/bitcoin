package br.com.borges.bitcoin.resource;

import javax.annotation.security.PermitAll;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import br.com.borges.bitcoin.model.User;
import br.com.borges.bitcoin.resource.dto.UserRoleDto;

@Path("/users")
public class UserResource {

	@POST
	@Transactional
	@Consumes(MediaType.APPLICATION_JSON)
	@PermitAll
	public void insert(User user) {
		User.insert(user);
	}
	
	
	@POST
	@Path("/role")
	@Transactional
	@Consumes(MediaType.APPLICATION_JSON)
	@PermitAll
	public void insertRole(UserRoleDto userRole) {
		User user = User.findById(userRole.getUserId());
		user.setRole(userRole.getRole());
		User.persist(user);
	}
}
