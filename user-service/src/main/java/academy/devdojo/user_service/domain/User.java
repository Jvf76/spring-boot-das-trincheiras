package academy.devdojo.user_service.domain;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    private String firstName;
    private String lastName;
    @EqualsAndHashCode.Include
    private Long id;
    private String email;
}
