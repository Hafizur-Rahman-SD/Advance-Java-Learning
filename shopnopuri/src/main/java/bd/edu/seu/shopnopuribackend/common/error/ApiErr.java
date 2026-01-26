package bd.edu.seu.shopnopuribackend.common.error;

import lombok.*;

import java.time.Instant;
import java.util.Map;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class ApiErr {
    private Instant ts;
    private int status;
    private String err;     // e.g. NOT_FOUND / BAD_REQUEST
    private String msg;     // user-friendly message
    private String path;    // request path
    private Map<String, Object> details; // field errors etc.
}
