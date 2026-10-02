package academy.devdojo.user_service.commons;

import academy.devdojo.user_service.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserUtils {
    public List<User> newUserList() {


        var joao = User.builder().id(1L).firstName("Joao").build();
        var rafael = User.builder().id(2L).firstName("Rafael").build();
        var samuel = User.builder().id(3L).firstName("Samuel").build();

        return new ArrayList<>(List.of(joao, rafael, samuel));

    }

    public User newAnimeToSave() {
        return User.builder().id(29L).firstName("Joao").build();
    }
}
