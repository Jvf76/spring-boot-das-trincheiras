package academy.devdojo.user_service.repository;

import academy.devdojo.user_service.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserHardCodedRepositoryTest {
    @InjectMocks
    private UserHardCodedRepository repository;
    @Mock
    private UserData userData;
    private List<User> userList;

    @BeforeEach
    void init() {
        var joao = User.builder().id(1L).firstName("Joao").build();
        var rafael = User.builder().id(2L).firstName("Rafael").build();
        var samuel = User.builder().id(3L).firstName("Samuel").build();
        userList = new ArrayList<>(List.of(joao, rafael, samuel));

    }


    @Test
    @DisplayName("findAll return a list with all users")
    @Order(1)
    void findAll_ReturnsAllUsers_WhenSuccessful() {
        BDDMockito.when(userData.getUsers()).thenReturn(userList);

        var users = repository.findAll();
        Assertions.assertThat(users).isNotNull().hasSize(3);
    }

    @Test
    @DisplayName("findAll return a users witch given id")
    @Order(2)
    void findById_ReturnsAllUsersById_WhenSuccessful() {
        BDDMockito.when(userData.getUsers()).thenReturn(userList);
        var expectedUser = userList.getFirst();
        var users = repository.findById(expectedUser.getId());
        Assertions.assertThat(users).isPresent().contains(expectedUser);
    }

    @Test
    @DisplayName("findByName return empty users when name is null")
    @Order(3)
    void findById_ReturnsEmptyList_WhenNameIsNull() {
        BDDMockito.when(userData.getUsers()).thenReturn(userList);

        var users = repository.findFirstName(null);
        Assertions.assertThat(users).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("findByName return empty users when name is null")
    @Order(4)
    void findByName_ReturnsFoundUserInList_WhenNameIsFound() {
        BDDMockito.when(userData.getUsers()).thenReturn(userList);
        var expectedUser = userList.getFirst();

        var users = repository.findFirstName(expectedUser.getFirstName());
        Assertions.assertThat(users).hasSize(1).contains(expectedUser);
    }

    @Test
    @DisplayName("findByName return empty users when name is null")
    @Order(5)
    void save_CreatesUser_WhenSucessful() {
        BDDMockito.when(userData.getUsers()).thenReturn(userList);

        var usersToSave = User.builder().id(99L).firstName("Samuel").lastName("Silva").email("samuel@gmail").build(); // cria o anime
        var user = repository.save(usersToSave); // salva o user que ficou em usersToSave

        Assertions.assertThat(user).isEqualTo(usersToSave).hasNoNullFieldsOrProperties();// verifica users, confirma se é igual ao producerToSave

        var userSavedOptional = repository.findById(usersToSave.getId());
        Assertions.assertThat(userSavedOptional).isPresent().contains(usersToSave);

    }

    @Test
    @DisplayName("delete remove producer")
    @Order(6)
    void Delete_UpdateUser_WhenSuccessful() {
        BDDMockito.when(userData.getUsers()).thenReturn(userList);

        var animeToDelete = userList.getFirst();
        repository.delete(animeToDelete);

        var users = repository.findAll();

        Assertions.assertThat(users).isNotEmpty().doesNotContain(animeToDelete);
    }


    @Test
    @DisplayName("update producer")
    @Order(7)
    void update_CreatesUser_WhenSucessful() {
        BDDMockito.when(userData.getUsers()).thenReturn(userList);

        var producerToUpdate = this.userList.getFirst();
        producerToUpdate.setFirstName("Samuel");

        repository.update(producerToUpdate);

        Assertions.assertThat(this.userList).contains(producerToUpdate);

        var produceUpdatedOptional = repository.findById(producerToUpdate.getId());
        Assertions.assertThat(produceUpdatedOptional).isPresent();
        Assertions.assertThat(produceUpdatedOptional.get().getFirstName()).isEqualTo(producerToUpdate.getFirstName());
    }

}

