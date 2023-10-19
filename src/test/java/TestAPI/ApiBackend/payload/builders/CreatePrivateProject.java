package TestAPI.ApiBackend.payload.builders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePrivateProject {
    private String jsonrpc;
    private String method;
    private long id;
    private String[] params;
}
