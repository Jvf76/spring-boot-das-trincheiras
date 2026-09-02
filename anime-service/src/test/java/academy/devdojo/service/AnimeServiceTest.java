package academy.devdojo.service;

import academy.devdojo.domain.Anime;
import academy.devdojo.repository.AnimeHardCodedRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

@ExtendWith(MockitoExtension.class) // inicializa os objtos anotados com @Mock e @InjectMocks
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AnimeServiceTest {
    @InjectMocks
    public AnimeService service;
    @Mock
    public AnimeHardCodedRepository repository;
    private List<Anime> animeList;

    @BeforeEach
        // prepara uma lista nova antes de cada teste
    void init() {
        var hunter = Anime.builder().id(1L).name("HXH").build();
        var onePiece = Anime.builder().id(2L).name("One Piece").build();
        var naruto = Anime.builder().id(3L).name("Naruto").build();
        animeList = new ArrayList<>(List.of(hunter, onePiece, naruto));

    }

    @Test
    @DisplayName("findAll return a list with all producers when argument is null")
    @Order(1)
    void findAll_ReturnsAllAnimes_WhenArgumentIsNull() {

        BDDMockito.when(repository.findAll()).thenReturn(animeList);

        var resultado = service.findAll(null);

        Assertions.assertThat(resultado).isNotNull().hasSameElementsAs(animeList);
    }

    @Test
    @DisplayName("findAll returns list with found object when name exists")
    @Order(2)
    void findByName_ReturnsAnime_WhenNameIsFound() {
        var anime = animeList.getFirst();
        var expectedAnimeFound = singletonList(anime);

        BDDMockito.when(repository.findByName(anime.getName())).thenReturn(expectedAnimeFound);

        var animeFound = service.findAll(anime.getName());

        Assertions.assertThat(animeFound).containsExactlyElementsOf(expectedAnimeFound);
    }

    @Test
    @DisplayName("findAll returns empty List when name is not found ")
    @Order(3)
    void find_ReturnsListEmpty_WhenNameIsNotFound() {
        var name = "Bleach";
        BDDMockito.when(repository.findByName(name)).thenReturn(emptyList());

        var animeFound = service.findAll(name);

        Assertions.assertThat(animeFound).isEmpty();
    }

    @Test
    @DisplayName("save creates animes ")
    @Order(4)
    void save_CreatesAnime_WhenSucessful() {
        var animeToSave = Anime.builder().id(2L).name("One Piece").build();

        BDDMockito.when(repository.save(animeToSave)).thenReturn(animeToSave);

        var animeSave = service.save(animeToSave);

        Assertions.assertThat(animeSave).isEqualTo(animeToSave).hasNoNullFieldsOrProperties();
    }
}
