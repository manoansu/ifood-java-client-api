package ptr.amane.ifoodjavaclientapi.api;

import lombok.AllArgsConstructor;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import pt.amane.ifoodjavaclient.input.RestauranteInput;
import pt.amane.ifoodjavaclient.model.RestauranteDTO;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class RestauranteClient {

    private static final String RESOURCE_PATH = "/restaurantess";

    private RestTemplate restTemplate;
    private String url;

    public List<RestauranteTO> findAll() {

        try {
            URI resourceUri = URI.create(url + RESOURCE_PATH);

            //restTemplate faz o get de path do endpoint ex: http://localhost:8080/restaurantes.
            // o restTemplate tem todos os verbos necessarios ex: GET, POST, PUT, DELETE..
            //restTemplate deserializa o resourceUri em json..
            RestauranteTO[] restaurantes =  restTemplate
                    .getForObject(resourceUri, RestauranteTO[].class);

            return Arrays.asList(restaurantes);
        }catch (RestClientResponseException e) {
            throw new ClientApiExceptionn(e.getMessage(), e);
        }
    }

    public RestauranteDTO adicionar(RestauranteInput restaurante) {
        var resourceUri = URI.create(url + RESOURCE_PATH);

        try {
            return restTemplate
                    .postForObject(resourceUri, restaurante, RestauranteTO.class);
        } catch (HttpClientErrorException e) {
            throw new ClientApiExceptionn(e.getMessage(), e);
        }
    }

}
