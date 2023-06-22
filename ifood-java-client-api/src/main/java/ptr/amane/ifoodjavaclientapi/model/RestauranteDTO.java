package ptr.amane.ifoodjavaclientapi.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RestauranteDTO {

    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private Boolean ativo;
    private Boolean aberto;

    private CozinhaDTO cozinha;
    private EnderecoDTO endereco;
}
