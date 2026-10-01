package academy.devdojo.user_service.repository;

import academy.devdojo.user_service.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserData {
    private static final List<User> users = new ArrayList<>();

    {
        var joao = User.builder().id(1L).firstName("Joao").build();
        var rafael = User.builder().id(2L).firstName("Rafael").build();
        var samuel = User.builder().id(3L).firstName("Samuel").build();
        users.addAll(List.of(joao, rafael, samuel));
    }

    public List<User> getUsers() {
        return users;
    }
}
