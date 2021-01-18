package br.com.borges.bitcoin.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import br.com.borges.bitcoin.model.Bitcoin;
import br.com.borges.bitcoin.service.BitcoinService;

@Path("/bitcoins")
public class BitcoinResource {

	@Inject
	@RestClient
	private BitcoinService bitcoinService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Bitcoin> list() {
		return bitcoinService.list();
	}
}
