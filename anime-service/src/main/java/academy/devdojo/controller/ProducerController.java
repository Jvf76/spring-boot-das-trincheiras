package academy.devdojo.controller;

import academy.devdojo.domain.Anime;
import academy.devdojo.domain.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("v1/producers")
@Slf4j
public class ProducerController {



    @GetMapping
    public List<Producer> listAll(@RequestParam(required = false) String name) {

        var producers = Producer.getProducers();
        if (name == null) {
            return producers;
        }

        return producers.stream()
                .filter(producer -> producer.getName().equalsIgnoreCase(name))
                .toList();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "x-api-key=1234")
    public Producer post(@RequestBody Producer producer, @RequestHeader HttpHeaders headers) {
        log.info("{}",headers);
        long idAleatorio = ThreadLocalRandom.current().nextLong(1, 1000);

        producer.setId(idAleatorio);
        Producer.getProducers().add(producer);

        return producer;
    }

    @GetMapping("/{id}")
    public Producer findById(@PathVariable Long id) {
        return Producer.getProducers()
                .stream()
                .filter(producer -> producer.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}