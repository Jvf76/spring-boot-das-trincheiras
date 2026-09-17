package academy.devdojo.controller;

import academy.devdojo.domain.Anime;
import academy.devdojo.repository.AnimeData;
import academy.devdojo.repository.AnimeHardCodedRepository;
import academy.devdojo.service.AnimeService;
import org.junit.jupiter.api.*;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@WebMvcTest(controllers = ProducerController.class) // vai startar apenas o necessário para fazer o teste da camada WEB
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ComponentScan(basePackages = {"outside.devdojo", "academy.devdojo"})
class AnimeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoSpyBean
    private AnimeHardCodedRepository repository;
    private AnimeService service;
    private List<Anime> animeList;
    @Autowired
    private ResourceLoader resourceLoader;

    @BeforeEach
    void init() {
        var kaiju = Anime.builder().id(1L).name("kaiju").build();
        var ninjaJamui = Anime.builder().id(2L).name("ninjaJamui").build();
        var kimetsuNoYaiba = Anime.builder().id(3L).name("kimetsuNoYaiba").build();

        animeList = new ArrayList<>(List.of(ninjaJamui, kaiju, kimetsuNoYaiba));

    }

    @Test
    @DisplayName("GET v1/animes return a list with all animes when argument is null")
    @Order(1)
    void findAll_ReturnsAllProducers_WhenArgumentIsNull() throws Exception {
        BDDMockito.when(service.getAnimes()).thenReturn(animeList);
        var response = readResourceFile("anime/get-anime-null-name-200.json");
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/animes")).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().json(response));
    }

    @Test
    @DisplayName("GET v1/animes?param=Ufotable returns list with object when name exists")
    @Order(2)
    void findAll_ReturnsFoundProducerInList_WhenNameIsFound() throws Exception {
        BDDMockito.when(service.getAnimes()).thenReturn(animeList);
        var response = readResourceFile("anime/get-anime-ufotable-name-200.json");
        var name = "Ufotable";
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/animes").param("name", name)).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().json(response));
    }
    private String readResourceFile(String fileName) throws IOException {
        var file = resourceLoader.getResource("classpath:%s".formatted(fileName)).getFile();
        return new String(Files.readAllBytes(file.toPath()));
    }

}
