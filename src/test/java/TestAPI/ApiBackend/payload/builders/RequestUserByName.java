package TestAPI.ApiBackend.payload.builders;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestUserByName {
    private String jsonrpc;
    private String method;
    private int id;
    private Params params;

    @Data
    @Builder
    public static class Params {
        private String username;
    }
}
