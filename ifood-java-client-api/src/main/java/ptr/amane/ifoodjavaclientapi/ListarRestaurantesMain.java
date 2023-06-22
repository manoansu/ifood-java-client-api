package ptr.amane.ifoodjavaclientapi;

import org.springframework.web.client.RestTemplate;
import pt.amane.ifoodjavaclient.api.ClientApiExceptionn;
import pt.amane.ifoodjavaclient.api.RestauranteClient;

public class ListarRestaurantesMain {

    public static void main(String[] args) {

        try {
            RestTemplate restTemplate = new RestTemplate();

            RestauranteClient restauranteClient = new RestauranteClient(restTemplate, "http://localhost:8080");

            restauranteClient.findAll().stream()
                    .forEach(restaurante -> System.out.println());
        }catch (ClientApiExceptionn e) {
            if (e.getProblem() != null) {
                System.out.println(e.getProblem());
                System.out.println(e.getProblem().getUserMessage());
            }else {
                System.out.println("Erro desconhecido");
                e.getStackTrace();
            }
        }
    }
}
