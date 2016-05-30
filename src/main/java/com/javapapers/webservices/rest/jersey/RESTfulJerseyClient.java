package com.javapapers.webservices.rest.jersey;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.codec.binary.Base64;

//http://stackoverflow.com/questions/3283234/http-basic-authentication-in-java-using-httpclient
public class RESTfulJerseyClient {

    private static final String webServiceURI = "http://localhost:8080/Authentication";

    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost:8080/Authentication/rest/helloworld");

            Base64 b = new Base64();
            String encoding = b.encodeAsString("admin:admin".getBytes());

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Basic " + encoding);
            InputStream content = (InputStream) connection.getInputStream();
            BufferedReader in
                    = new BufferedReader(new InputStreamReader(content));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }

//		ClientConfig clientConfig = new ClientConfig();
//             
//		Client client = ClientBuilder.newClient(clientConfig);
//        
//		URI serviceURI = UriBuilder.fromUri(webServiceURI).build();
//           
//		WebTarget webTarget = client.target(serviceURI);
//
//		// response
//		System.out.println(webTarget.path("rest").path("helloworld").request()
//				.accept(MediaType.TEXT_PLAIN).get(Response.class).toString());
//
//		// text
//		System.out.println(webTarget.path("rest").path("helloworld").request()
//				.accept(MediaType.TEXT_PLAIN).get(String.class));
//
//		// xml
//		System.out.println(webTarget.path("rest").path("helloworld").request()
//				.accept(MediaType.TEXT_XML).get(String.class));
//
//		// html
//		System.out.println(webTarget.path("rest").path("helloworld").request()
//				.accept(MediaType.TEXT_HTML).get(String.class));
    }
}
