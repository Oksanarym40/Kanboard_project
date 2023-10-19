package TestAPI.ApiBackend.payload.builders;

import lombok.*;


    @Data
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public class CreateUser {
         private String jsonrpc;
         private String method;
         private int id;
         private params params;

        @Data
        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        @Builder
        public static class params {
            private String username;
            private String password;
        }
    }