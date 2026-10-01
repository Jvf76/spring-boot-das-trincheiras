package academy.devdojo.user_service.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserPostRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
