package academy.devdojo.user_service.repository;

import academy.devdojo.user_service.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserHardCodedRepository {
    private final UserData userData;

    public List<User> findAll() {
        return userData.getUsers();
    }

    public List<User> findFirstName(String firstName) {
        return userData.getUsers()
                .stream()
                .filter(user -> user.getFirstName().equalsIgnoreCase(firstName))
                .toList();
    }

    public Optional<User> findById(Long id){
        return userData.getUsers()
                .stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    public User save(User users){
       userData.getUsers().add(users);
       return users;
    }
}
