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
    void find_ReturnsListEmpty_WhenNameIsNotFound() {
        var name = "José";

        BDDMockito.when(repository.findFirstName(name)).thenReturn(emptyList());

        var userFound = service.findAll(name);

        Assertions.assertThat(userFound).isEmpty();
    }

    @Test
    @DisplayName("findAll return a users witch given id ")
    @Order(4)
    void findById_ReturnsAllUsersById_WhenSuccessful() {
        var expectedUser = userList.getFirst();
        BDDMockito.when(repository.findById(expectedUser.getId())).thenReturn(Optional.of(expectedUser));

        var user = service.findByIdOrThrowNotFound(expectedUser.getId());

        Assertions.assertThat(user).hasSameClassAs(expectedUser);
    }

    @Test
    @DisplayName("findAll return a users witch given id ")
    @Order(5)
    void findById_ThrowsResponseStatusException_WhenUserIsNotFound(){
        var expectedUser = userList.getFirst();
        BDDMockito.when(repository.findById(expectedUser.getId())).thenReturn((Optional.empty()));

        Assertions.assertThatException()
                .isThrownBy(() -> service.findByIdOrThrowNotFound(expectedUser.getId()))
                .isInstanceOf(ResponseStatusException.class);

    }

    @Test
    @DisplayName("save creates a user")
    @Order(6)
    void save_CreatesUser_WhenSucessful() {
        var userToSave = User.builder().id(99L).firstName("Jose").lastName("Silva").email("jose@gmail").build();
        BDDMockito.when(repository.save(userToSave)).thenReturn(userToSave);

        var userSaved = service.saved(userToSave);

        Assertions.assertThat(userSaved).isEqualTo(userToSave).hasNoNullFieldsOrProperties();

    }
    @Test
    @DisplayName("delete remove anime")
    @Order(7)
    void delete_RemoveAnime_WhenSuccessful(){
        var userToDelete = userList.getFirst();

        BDDMockito.when(repository.findById(userToDelete.getId())).thenReturn(Optional.of(userToDelete));
        BDDMockito.doNothing().when(repository).delete(userToDelete);

        Assertions.assertThatNoException().isThrownBy(() -> service.delete(userToDelete.getId()));


    }

    @Test
    @DisplayName("delete remove user")
    @Order(8)
    void delete_ThrowResponseStatusException_WhenUserIsNotFound() {
        var userToDelete = userList.getFirst();
        BDDMockito.when(repository.findById(userToDelete.getId())).thenReturn(Optional.empty());

        Assertions.assertThatException()
                .isThrownBy(() -> service.delete(userToDelete.getId()))
                .isInstanceOf(ResponseStatusException.class);

    }

    @Test
    @DisplayName("update user")
    @Order(9)
    void update_UpdateAnime_WhenSucessful() {
        var userToUpdate = userList.getFirst();
        userToUpdate.setFirstName("GrandBlue");

        BDDMockito.when(repository.findById(userToUpdate.getId())).thenReturn(Optional.of(userToUpdate));
        BDDMockito.doNothing().when(repository).update(userToUpdate);


        Assertions.assertThatNoException().isThrownBy(() -> service.update(userToUpdate));

    }

    @Test
    @DisplayName("update anime")
    @Order(10)
    void update_ThrowsResponseStatusException_WhenAnimeIsNotFound() {
        var userToUpdate = userList.getFirst();

        BDDMockito.when(repository.findById(userToUpdate.getId())).thenReturn(Optional.empty());


        Assertions.assertThatException()
                .isThrownBy(() -> service.update(userToUpdate))
                .isInstanceOf(ResponseStatusException.class);

    }

}
