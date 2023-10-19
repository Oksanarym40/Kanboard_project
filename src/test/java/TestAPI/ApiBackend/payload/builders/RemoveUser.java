package TestAPI.ApiBackend.payload.builders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RemoveUser {
    private String jsonrpc;
    private String method;
    private int id;
    private Params params;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Params {
        private int user_id;
    }
}