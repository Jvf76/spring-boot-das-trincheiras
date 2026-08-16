package academy.devdojo.repository;

import academy.devdojo.domain.Producer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);

    }



    @Test
    @DisplayName("findAll return a list with all producers")
    void findAll_ReturnsAllProducers_WhenSuccessful() {
        var producers = repository.findAll();
        org.assertj.core.api.Assertions.assertThat(producers).isNotNull().hasSize(3);
    }
}