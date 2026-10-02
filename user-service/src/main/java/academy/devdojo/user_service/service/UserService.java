package academy.devdojo.user_service.service;

import academy.devdojo.user_service.domain.User;
import academy.devdojo.user_service.repository.UserHardCodedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserHardCodedRepository repository;

    public List<User> findAll(String firstName){
        return firstName == null ? repository.findAll() : repository.findFirstName(firstName);
    }

    public User findByIdOrThrowNotFound(Long id){
        return repository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"USER NOT FOUND"));
    }

    public User saved(User user){
        return repository.save(user);
    }

    public void delete(Long id){
        var user = findByIdOrThrowNotFound(id);
        repository.delete(user);
    }

    public void update(User userToUpdate){
        assertAnimeExists(userToUpdate.getId());
        repository.update(userToUpdate);
    }

    public void assertAnimeExists(Long id){
        findByIdOrThrowNotFound(id);
    }
}
