package ptr.amane.ifoodjavaclientapi.input;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RestauranteInput {

    private String nome;
    private BigDecimal taxaFrete;

    private CozinhaIdInput cozinha;
    private EnderecoInput endereco;
}
