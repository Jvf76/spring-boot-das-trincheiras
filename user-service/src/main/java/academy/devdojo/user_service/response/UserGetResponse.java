package academy.devdojo.user_service.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserGetResponse {
    private Long id;
    private String firstName;
}
