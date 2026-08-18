package academy.devdojo.repository;

import academy.devdojo.domain.Producer;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProducerHardCodedRepositoryTest {
    @InjectMocks
    private ProducerHardCodedRepository repository;
    @Mock
    private ProducerData producerData;
    private final List<Producer> producerList = new ArrayList<>();

    @BeforeEach
    void init() {
        var uftable = Producer.builder().id(1L).name("Mappa").createdAt(LocalDateTime.now()).build();
        var witStudio = Producer.builder().id(2L).name("Kyoto Animation").createdAt(LocalDateTime.now()).build();
        var studioGhibli = Producer.builder().id(3L).name("Madhouse").createdAt(LocalDateTime.now()).build();
        producerList.addAll(List.of(uftable, witStudio, studioGhibli));

    }


    @Test
    @DisplayName("findAll return a list with all producers")
    @Order(1)
    void findAll_ReturnsAllProducers_WhenSuccessful() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);

        var producers = repository.findAll();
        org.assertj.core.api.Assertions.assertThat(producers).isNotNull().hasSize(3);
    }

    @Test
    @DisplayName("findAll return a producers witch given id")
    @Order(2)
    void findById_ReturnsAllProducersById_WhenSuccessful() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);
        var expectedProducer = producerList.getFirst();
        var producers = repository.findById(expectedProducer.getId());
        org.assertj.core.api.Assertions.assertThat(producers).isPresent().contains(expectedProducer);
    }

    @Test
    @DisplayName("findByName return empty producers when name is null")
    @Order(3)
    void findById_ReturnsEmptyList_WhenNameIsNull() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);

        var producers = repository.findByName(null);
        org.assertj.core.api.Assertions.assertThat(producers).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("findByName return empty producers when name is null")
    @Order(4)
    void findByName_ReturnsFoundProducerInList_WhenNameIsFound() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);
        var expectedProducer = producerList.getFirst();

        var producers = repository.findByName(expectedProducer.getName());
        org.assertj.core.api.Assertions.assertThat(producers).hasSize(1).contains(expectedProducer);
    }

    @Test
    @DisplayName("findByName return empty producers when name is null")
    @Order(5)
    void save_CreatesProducer_WhenSucessful() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);

        var producersToSave = Producer.builder().id(99L).name("MAPPA").createdAt(LocalDateTime.now()).build(); // cria o producer
        var producer = repository.save(producersToSave); // salva o producer que ficou em producersToSave

        Assertions.assertThat(producer).isEqualTo(producersToSave).hasNoNullFieldsOrProperties();// verifica producers, confirma se é igual ao producerToSave

        var producerSavedOptional = repository.findById(producersToSave.getId());
        Assertions.assertThat(producerSavedOptional).isPresent().contains(producersToSave);

    }

    @Test
    @DisplayName("delete remove producer")
    @Order(6)
    void Delete_UpdateProducer_WhenSuccessful() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);

        var producerToDelete = producerList.getFirst();
        repository.delete(producerToDelete);

        var producers = repository.findAll();

        Assertions.assertThat(producers).isNotEmpty().doesNotContain(producerToDelete);
    }


    @Test
    @DisplayName("update producer")
    @Order(7)
    void update_CreatesProducer_WhenSucessful() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);

        var producerToUpdate = this.producerList.getFirst();
        producerToUpdate.setName("Aniplex");

        repository.update(producerToUpdate);

        Assertions.assertThat(this.producerList).contains(producerToUpdate);

        var produceUpdatedOptional = repository.findById(producerToUpdate.getId());
        Assertions.assertThat(produceUpdatedOptional).isPresent();
        Assertions.assertThat(produceUpdatedOptional.get().getName()).isEqualTo(producerToUpdate.getName());
    }

}

