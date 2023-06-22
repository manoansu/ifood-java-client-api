package ptr.amane.ifoodjavaclientapi.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClientResponseException;
import pt.amane.ifoodjavaclient.model.Problem;

@Slf4j
public class ClientApiExceptionn extends RuntimeException {

    private static final long serialVersionUID = 1L;
    @Getter
    private Problem problem;

    public ClientApiExceptionn(String message, RestClientResponseException cause) {
        super(message, cause);

        System.out.println(cause.getResponseBodyAsString());
        deserializarProblem(cause);
    }

    private void deserializarProblem(RestClientResponseException cause) {

        //ObjectMapper deserializa uma string.
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //ObjectMapper deserializa DataTime.
        mapper.registerModule(new JavaTimeModule());
        mapper.findAndRegisterModules();

        try {
            this.problem = mapper.readValue(cause.getResponseBodyAsString(), Problem.class);
        }catch (RestClientResponseException e) {
            log.warn("Não foi possivel desserializar a resposta em um problema,", e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
