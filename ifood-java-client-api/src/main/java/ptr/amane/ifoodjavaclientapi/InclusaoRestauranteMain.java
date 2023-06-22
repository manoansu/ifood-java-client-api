package ptr.amane.ifoodjavaclientapi;

import org.springframework.web.client.RestTemplate;
import pt.amane.ifoodjavaclient.api.ClientApiExceptionn;
import pt.amane.ifoodjavaclient.api.RestauranteClient;
import pt.amane.ifoodjavaclient.input.CidadeIdInput;
import pt.amane.ifoodjavaclient.input.CozinhaIdInput;
import pt.amane.ifoodjavaclient.input.EnderecoInput;
import pt.amane.ifoodjavaclient.input.RestauranteInput;
import pt.amane.ifoodjavaclient.model.RestauranteDTO;

import java.math.BigDecimal;

public class InclusaoRestauranteMain {

    public static void main(String[] args) {

        try {
            var restTemplate = new RestTemplate();
            var restauranteClient = new RestauranteClient(
                    restTemplate, "http://api.algafood.local:8080");

            var cozinha = new CozinhaIdInput();
            cozinha.setId(1L);

            var cidade = new CidadeIdInput();
            cidade.setId(1L);

            var endereco = new EnderecoInput();
            endereco.setCidade(cidade);
            endereco.setCep("38500-111");
            endereco.setLogradouro("Rua Xyz");
            endereco.setNumero("300");
            endereco.setBairro("Centro");

            var restaurante = new RestauranteInput();
            restaurante.setNome("Comida Mineira");
            restaurante.setTaxaFrete(new BigDecimal(9.5));
            restaurante.setCozinha(new CozinhaIdInput());
            restaurante.setCozinha(cozinha);
            restaurante.setEndereco(endereco);

            RestauranteDTO restauranteDTO = restauranteClient.adicionar(restaurante);

            System.out.println(restauranteDTO);
        } catch (ClientApiExceptionn e) {
            if (e.getProblem() != null) {
                System.out.println(e.getProblem().getUserMessage());

                e.getProblem().getObjects().stream()
                        .forEach(p -> System.out.println("- " + p.getUserMessage()));

            } else {
                System.out.println("Erro desconhecido");
                e.printStackTrace();
            }
        }
    }

}

