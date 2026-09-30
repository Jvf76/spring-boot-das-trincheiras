package academy.devdojo.user_service.domain;

import lombok.EqualsAndHashCode;

public class User {
    private String firtsName;
    private String lastName;
    @EqualsAndHashCode.Include
    private Long id;
    private String email;
}
