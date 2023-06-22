package ptr.amane.ifoodjavaclientapi.model;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Problem {

    private Integer status;
    private OffsetDateTime timestamp;
    private String userMessage;
    private List<Object> objects = new ArrayList<>();

//    private String type;
//    private String detail;
//    private String title;

    @Data
    public static class Object {

        private String name;
        private String userMessage;

    }
}
