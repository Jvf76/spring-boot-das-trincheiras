package academy.devdojo.user_service.service;

import academy.devdojo.user_service.repository.UserHardCodedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserHardCodedRepository repository;
}
