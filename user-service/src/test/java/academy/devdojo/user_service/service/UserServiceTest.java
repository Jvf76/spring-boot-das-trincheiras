package academy.devdojo.user_service.service;

import academy.devdojo.user_service.commons.UserUtils;
import academy.devdojo.user_service.domain.User;
import academy.devdojo.user_service.repository.UserHardCodedRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {
    @InjectMocks
    public UserService service;
    @Mock
    public UserHardCodedRepository repository;
    private List<User> userList;
    @InjectMocks
    UserUtils userUtils;

    @BeforeEach
// prepara uma lista nova antes de cada teste
    void init() {
        userList = userUtils.newUserList();
    }

    @Test
    @DisplayName("findAll returns list with found object when name exists")
    @Order(1)
    void findAll_ReturnsAllUsers_WhenArgumentIsNull() {
        BDDMockito.when(repository.findAll()).thenReturn(userList);

        var resultado = service.findAll(null);

        Assertions.assertThat(resultado).isNotNull().hasSameElementsAs(userList);
    }

    @Test
    @DisplayName("findAll returns list with found object when name exists")
    @Order(2)
    void findByName_ReturnsUser_WhenNameIsFound() {
        var user = userList.getFirst();
        var expectedUserFound = singletonList(user);
        BDDMockito.when(repository.findFirstName(user.getFirstName())).thenReturn(expectedUserFound);

        var userFound = service.findAll(user.getFirstName());
        Assertions.assertThat(userFound).containsExactlyElementsOf(expectedUserFound);
    }

    @Test
    @DisplayName("findAll returns empty List when name is not found ")
    @Order(3)
    void find_ReturnsListEmpty_WhenNameIsNotFound(){
        var name = "José";

        BDDMockito.when(repository.findFirstName(name)).thenReturn(emptyList());

        var userFound = service.findAll(name);

        Assertions.assertThat(userFound).isEmpty();
    }


    @Test
    @DisplayName("findAll return a user witch given id")
    @Order(4)
    void findById_ReturnsAllUsersById_WhenSuccessful() {
        var expectedUser = userList.getFirst();
        BDDMockito.when(repository.findById(expectedUser.getId())).thenReturn(Optional.of(expectedUser));

        var users = service.findByIdOrThrowNotFound(expectedUser.getId());

        Assertions.assertThat(users).isEqualTo(expectedUser);
    }

    @Test
    @DisplayName("findById throws ResponseStatusException when is not found")
    @Order(5)
    void findById_ThrowsResponseStatusException_WhenAnimeIsNotFound() {
        var expectedUsers = userList.getFirst();
        BDDMockito.when(repository.findById(expectedUsers.getId())).thenReturn(Optional.empty());

        Assertions.assertThatException()
                .isThrownBy(() -> service.findByIdOrThrowNotFound(expectedUsers.getId()))
                .isInstanceOf(ResponseStatusException.class);


    }

    @Test
    @DisplayName("save creates a user")
    @Order(6)
    void save_CreatesUser_WhenSucessful(){
        var userToSave = User.builder().firstName("José").lastName("Silva").id(3L).email("josesilva@gmail").build();

        BDDMockito.when(repository.save(userToSave)).thenReturn(userToSave);

        var userSaved = service.saved(userToSave);

        Assertions.assertThat(userSaved).isEqualTo(userToSave).hasNoNullFieldsOrProperties();
    }
}
