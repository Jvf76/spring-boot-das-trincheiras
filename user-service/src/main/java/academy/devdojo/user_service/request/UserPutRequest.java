package academy.devdojo.user_service.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserPutRequest {
    private Long id;
    private String firstName;
}
