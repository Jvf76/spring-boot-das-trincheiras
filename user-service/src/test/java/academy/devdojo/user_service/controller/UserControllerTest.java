package academy.devdojo.user_service.controller;

import academy.devdojo.user_service.commons.FileUtils;
import academy.devdojo.user_service.commons.UserUtils;
import academy.devdojo.user_service.domain.User;
import academy.devdojo.user_service.repository.UserData;
import academy.devdojo.user_service.repository.UserHardCodedRepository;
import org.junit.jupiter.api.*;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@WebMvcTest(controllers = UserController.class) // vai startar apenas o necessário para fazer o teste da camada WEB
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest {
    private static final String URL = "/v1/users";
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private UserData userData;
    @MockitoSpyBean
    private UserHardCodedRepository repository;
    private List<User> userList;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private UserUtils userUtils;
    @Autowired
    private FileUtils fileUtils;

    @BeforeEach
    void init() {
        userList = userUtils.newUserList();
    }

    @Test
    @DisplayName("GET v1/users return a list with all users when argument is null")
    @Order(1)
    void findAll_ReturnsAllUser_WhenArgumentIsNull() throws Exception {
        BDDMockito.when(userData.getUsers()).thenReturn(userList);
        var response = fileUtils.readResourceFile("users/get-user-null-name-200.json");

        mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(response));
    }


}
