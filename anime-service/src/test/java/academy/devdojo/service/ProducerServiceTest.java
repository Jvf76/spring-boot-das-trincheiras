package academy.devdojo.service;

import academy.devdojo.domain.Producer;
import academy.devdojo.repository.ProducerHardCodedRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProducerServiceTest {
    @InjectMocks
    private ProducerService service;
    @Mock
    private ProducerHardCodedRepository repository;
    private List<Producer> producerList;

    @BeforeEach
    void init() {
        var uftable = Producer.builder().id(1L).name("Mappa").createdAt(LocalDateTime.now()).build();
        var witStudio = Producer.builder().id(2L).name("Kyoto Animation").createdAt(LocalDateTime.now()).build();
        var studioGhibli = Producer.builder().id(3L).name("Madhouse").createdAt(LocalDateTime.now()).build();
        producerList = new ArrayList<>(List.of(uftable, witStudio, studioGhibli));

    }

    @Test
    @DisplayName("findAll return a list with all producers when argument is null")
    @Order(1)
    void findAll_ReturnsAllProducers_WhenArgumentIsNull() {
        BDDMockito.when(repository.findAll()).thenReturn(producerList);

        var producers = repository.findAll();
        org.assertj.core.api.Assertions.assertThat(producers).isNotNull().hasSize(3);
    }

    @Test
    @DisplayName("findAll returns list with object when name exists")
    @Order(2)
    void findByName_ReturnsFoundProducerInList_WhenNameIsFound() {
        var producer = producerList.getFirst();
        var expectedProducerFound = singletonList(producer);
        BDDMockito.when(repository.findByName(producer.getName())).thenReturn(singletonList(producer));

        var producersFound = service.findAll(producer.getName());
        Assertions.assertThat(producersFound).containsAll(expectedProducerFound);

    }

    @Test
    @DisplayName("findByName return empty list when name is null")
    @Order(3)
    void findByName_ReturnsEmptyList_WhenNameIsNull() {
        var name = "not-found";
        BDDMockito.when(repository.findByName("not-found")).thenReturn(emptyList());

        var producers = service.findAll(name);
        org.assertj.core.api.Assertions.assertThat(producers).isNotNull().isEmpty();
    }


}