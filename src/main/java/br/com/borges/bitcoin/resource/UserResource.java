package br.com.borges.bitcoin.resource;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import br.com.borges.bitcoin.model.User;

@Path("/users")
public class UserResource {

	@POST
	@Transactional
	@Consumes(MediaType.APPLICATION_JSON)
	public void insert(User user) {
		User.persist(user);
	}
}
