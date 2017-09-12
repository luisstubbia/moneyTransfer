package moneyTransfer.controller;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class UserControllerTestLive {

	public static void main(String[] args) {
		ClientConfig config = new ClientConfig();

		Client client = ClientBuilder.newClient(config);

		WebTarget target = client.target(getBaseURI());

		String response = target.path("/").path("users").request().accept(MediaType.APPLICATION_JSON_TYPE)
				.get(Response.class).toString();

		String jsonAnswer = target.path("/").path("users").request().accept(MediaType.APPLICATION_JSON_TYPE)
				.get(String.class);

		System.out.println(response);
		System.out.println(jsonAnswer);
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/moneyTransfer").build();
	}
}
