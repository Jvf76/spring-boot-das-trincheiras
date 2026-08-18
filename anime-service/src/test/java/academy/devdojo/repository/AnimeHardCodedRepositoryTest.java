package academy.devdojo.repository;

import academy.devdojo.domain.Anime;
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
class AnimeHardCodedRepositoryTest {
    @InjectMocks
    private AnimeHardCodedRepository repository;
    @Mock
    private AnimeData animeData;
    private  List<Anime> animesList;

    @BeforeEach
    void init() {
        var fullMetal = Anime.builder().id(1L).name("Full Metal Brotherood").build();
        var steinsGate = Anime.builder().id(2L).name("steinsGate").build();
        var mashle = Anime.builder().id(3L).name("mashle").build();
        animesList = new ArrayList<>(List.of(fullMetal, steinsGate, mashle));

    }


    @Test
    @DisplayName("findAll return a list with all animes")
    @Order(1)
    void findAll_ReturnsAllAnimes_WhenSuccessful() {
        BDDMockito.when(animeData.getAnimes()).thenReturn(animesList);

        var animes = repository.findAll();
        Assertions.assertThat(animes).isNotNull().hasSize(3);
    }

    @Test
    @DisplayName("findAll return a animes witch given id")
    @Order(2)
    void findById_ReturnsAllAnimesById_WhenSuccessful() {
        BDDMockito.when(animeData.getAnimes()).thenReturn(animesList);
        var expectedAnime = animesList.getFirst();
        var animes = repository.findById(expectedAnime.getId());
        Assertions.assertThat(animes).isPresent().contains(expectedAnime);
    }

    @Test
    @DisplayName("findByName return empty animes when name is null")
    @Order(3)
    void findById_ReturnsEmptyList_WhenNameIsNull() {
        BDDMockito.when(animeData.getAnimes()).thenReturn(animesList);

        var animes = repository.findByName(null);
        Assertions.assertThat(animes).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("findByName return empty animes when name is null")
    @Order(4)
    void findByName_ReturnsFoundAnimeInList_WhenNameIsFound() {
        BDDMockito.when(animeData.getAnimes()).thenReturn(animesList);
        var expectedAnime = animesList.getFirst();

        var animes = repository.findByName(expectedAnime.getName());
        Assertions.assertThat(animes).hasSize(1).contains(expectedAnime);
    }

    @Test
    @DisplayName("findByName return empty animes when name is null")
    @Order(5)
    void save_CreatesAnime_WhenSucessful() {
        BDDMockito.when(animeData.getAnimes()).thenReturn(animesList);

        var animesToSave = Anime.builder().id(99L).name("One Piece").build(); // cria o anime
        var producer = repository.save(animesToSave); // salva o anime que ficou em animesToSave

        Assertions.assertThat(producer).isEqualTo(animesToSave).hasNoNullFieldsOrProperties();// verifica animes, confirma se é igual ao producerToSave

        var animeSavedOptional = repository.findById(animesToSave.getId());
        Assertions.assertThat(animeSavedOptional).isPresent().contains(animesToSave);

    }

    @Test
    @DisplayName("delete remove producer")
    @Order(6)
    void Delete_UpdateAnime_WhenSuccessful() {
        BDDMockito.when(animeData.getAnimes()).thenReturn(animesList);

        var animeToDelete = animesList.getFirst();
        repository.delete(animeToDelete);

        var animes = repository.findAll();

        Assertions.assertThat(animes).isNotEmpty().doesNotContain(animeToDelete);
    }


    @Test
    @DisplayName("update producer")
    @Order(7)
    void update_CreatesAnime_WhenSucessful() {
        BDDMockito.when(animeData.getAnimes()).thenReturn(animesList);

        var producerToUpdate = this.animesList.getFirst();
        producerToUpdate.setName("Aniplex");

        repository.update(producerToUpdate);

        Assertions.assertThat(this.animesList).contains(producerToUpdate);

        var produceUpdatedOptional = repository.findById(producerToUpdate.getId());
        Assertions.assertThat(produceUpdatedOptional).isPresent();
        Assertions.assertThat(produceUpdatedOptional.get().getName()).isEqualTo(producerToUpdate.getName());
    }

}

