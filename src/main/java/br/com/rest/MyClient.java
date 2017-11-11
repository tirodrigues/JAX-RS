package br.com.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

public class MyClient {

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();

		String xml = "<customer>" + 
						"<first-name>Bill</first-name>" + 
						"<last-name>Burke</last-name>"+ 
						"<street>Rua Ariperana 106</street>" + 
						"<city>Boston</city>" + 
						"<state>MA</state>"+ 
						"<zip>02115</zip>" + 
						"<country>USA</country>" + 
					"</customer>";

		Response response = client.target("http://localhost:8080/services/customers").request().post(Entity.xml(xml));

		if (response.getStatus() != 201) {
			throw new RuntimeException("Failed to create");
		}

		String location = response.getLocation().toString();
		System.out.println("Location:	" + location);
		response.close();
		System.out.println("***	GET	Created	Customer	**");
		String customer = client.target(location).request().get(String.class);
		System.out.println(customer);
	}
}
