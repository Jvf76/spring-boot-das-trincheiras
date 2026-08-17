package academy.devdojo.repository;

import academy.devdojo.domain.Producer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    void findAll_ReturnsAllProducers_WhenSuccessful() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);

        var producers = repository.findAll();
        org.assertj.core.api.Assertions.assertThat(producers).isNotNull().hasSize(3);
    }

    @Test
    @DisplayName("findAll return a producers witch given id")
    void findById_ReturnsAllProducersById_WhenSuccessful() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);
        var expectedProducer = producerList.getFirst();
        var producers = repository.findById(expectedProducer.getId());
        org.assertj.core.api.Assertions.assertThat(producers).isPresent().contains(expectedProducer);
    }

    @Test
    @DisplayName("findByName return empty producers when name is null")
    void findById_ReturnsEmptyList_WhenNameIsNull() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);

        var producers = repository.findByName(null);
        org.assertj.core.api.Assertions.assertThat(producers).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("findByName return empty producers when name is null")
    void findById_ReturnsFoundProducerInList_WhenNameIsFound() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);
        var expectedProducer = producerList.getFirst();

        var producers = repository.findByName(expectedProducer.getName());
        org.assertj.core.api.Assertions.assertThat(producers).hasSize(1).contains(expectedProducer);
    }

    @Test
    @DisplayName("findByName return empty producers when name is null")
    void save_CreatesProducer_WhenSucessful() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);

        var producersToSave = Producer.builder().id(99L).name("MAPPA").createdAt(LocalDateTime.now()).build();
        var producer = repository.save(producersToSave);

        Assertions.assertThat(producer).isEqualTo(producersToSave).hasNoNullFieldsOrProperties();

        var producerSavedOptional = repository.findById(producersToSave.getId());
        Assertions.assertThat(producerSavedOptional).isPresent().contains(producersToSave);

    }

}