package academy.devdojo.service;

import academy.devdojo.domain.Anime;
import academy.devdojo.domain.Anime;
import academy.devdojo.repository.AnimeHardCodedRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @DisplayName("findAll return a list with all animes when argument is null")
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
    @DisplayName("findAll return a animes witch given id")
    @Order(4)
    void findById_ReturnsAllAnimesById_WhenSuccessful() {
        var expectedAnime = animeList.getFirst();
        BDDMockito.when(repository.findById(expectedAnime.getId())).thenReturn(Optional.of(expectedAnime));

        var animes = service.findByIdOrThrowNotFound(expectedAnime.getId());

        Assertions.assertThat(animes).isEqualTo(expectedAnime);
    }
    @Test
    @DisplayName("findById throws ResponseStatusException when is not found")
    @Order(5)
    void findById_ThrowsResponseStatusException_WhenAnimeIsNotFound() {
        var expectedAnime = animeList.getFirst();
        BDDMockito.when(repository.findById(expectedAnime.getId())).thenReturn(Optional.empty());

        Assertions.assertThatException()
                .isThrownBy(() -> service.findByIdOrThrowNotFound(expectedAnime.getId()))
                .isInstanceOf(ResponseStatusException.class);


    }

    @Test
    @DisplayName("save creates a anime")
    @Order(6)
    void save_CreatesAnime_WhenSucessful() {
        var animesToSave = Anime.builder().id(99L).name("MAPPA").build();// cria o anime

        BDDMockito.when(repository.save(animesToSave)).thenReturn(animesToSave);

        var savedAnime = service.save(animesToSave);

        Assertions.assertThat(savedAnime).isEqualTo(animesToSave).hasNoNullFieldsOrProperties();// verifica animes, confirma se é igual ao animeToSave

    }

    @Test
    @DisplayName("delete remove anime")
    @Order(7)
    void delete_RemoveAnime_WhenSuccessful() {
        var animeToDelete = animeList.getFirst();
        BDDMockito.when(repository.findById(animeToDelete.getId())).thenReturn(Optional.of(animeToDelete));
        BDDMockito.doNothing().when(repository).delete(animeToDelete);


        Assertions.assertThatNoException().isThrownBy(() -> service.delete(animeToDelete.getId()));
    }

    @Test
    @DisplayName("delete remove anime")
    @Order(8)
    void delete_ThrowResponseStatusException_WhenAnimeIsNotFound() {
        var animeToDelete = animeList.getFirst();
        BDDMockito.when(repository.findById(animeToDelete.getId())).thenReturn(Optional.empty());

        Assertions.assertThatException()
                .isThrownBy(() -> service.delete(animeToDelete.getId()))
                .isInstanceOf(ResponseStatusException.class);

    }


    @Test
    @DisplayName("update anime")
    @Order(9)
    void update_UpdateAnime_WhenSucessful() {
        var animeToUpdate = animeList.getFirst();
        animeToUpdate.setName("GrandBlue");

        BDDMockito.when(repository.findById(animeToUpdate.getId())).thenReturn(Optional.of(animeToUpdate));
        BDDMockito.doNothing().when(repository).update(animeToUpdate);


        Assertions.assertThatNoException().isThrownBy(() -> service.update(animeToUpdate));

    }

    @Test
    @DisplayName("update anime")
    @Order(10)
    void update_ThrowsResponseStatusException_WhenAnimeIsNotFound() {
        var animeToUpdate = animeList.getFirst();

        BDDMockito.when(repository.findById(animeToUpdate.getId())).thenReturn(Optional.empty());


        Assertions.assertThatException()
                .isThrownBy(() -> service.update(animeToUpdate))
                .isInstanceOf(ResponseStatusException.class);

    }
}
