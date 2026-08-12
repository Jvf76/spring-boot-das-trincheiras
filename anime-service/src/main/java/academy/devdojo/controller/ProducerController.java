package academy.devdojo.controller;

import academy.devdojo.mapper.ProducerMapper;
import academy.devdojo.request.ProducerPostRequest;
import academy.devdojo.request.ProducerPutRequest;
import academy.devdojo.response.ProducerGetResponse;
import academy.devdojo.service.ProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/producers")
@Slf4j
@RequiredArgsConstructor
public class ProducerController {
    private final ProducerMapper MAPPER;
    private final ProducerService service;


    @GetMapping
    public ResponseEntity<List<ProducerGetResponse>> listAll(@RequestParam(required = false) String name) {
        var producers = service.findAll(name);

        var response = MAPPER.toProducerGetResponseList(producers);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProducerGetResponse> findById(@PathVariable Long id) {

        var producer = service.findByIdOrThrowNotFound(id);

        var producerGetResponse = MAPPER.toProducerGetResponse(producer);

        return ResponseEntity.ok(producerGetResponse);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "x-api-key")
    public ResponseEntity<ProducerGetResponse> save(@RequestBody ProducerPostRequest producerPostRequest, @RequestHeader HttpHeaders headers) {
        log.info("{}", headers);
        var producer = MAPPER.toProducer(producerPostRequest);//o mapper transforma um objeto producerPostRequest em um objeto producer

        var producerSaved = service.save(producer);

        var producerGetResponse = MAPPER.toProducerGetResponse(producerSaved);// o mapper transforma um objeto producer  em um objeto producerGetResponse


        return ResponseEntity.status(HttpStatus.CREATED).body(producerGetResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleById(@PathVariable Long id) {
        log.debug("Request to delete producer by id: {}", id);

        service.delete(id);

        return ResponseEntity.noContent().build();
    }


    @PutMapping
    public ResponseEntity<Void> update(@RequestBody ProducerPutRequest request) {
        log.debug("Requeste to update producers by id: {}", request);

        var producerToUpdate = MAPPER.toProducer(request);

        service.update(producerToUpdate);
        return ResponseEntity.noContent().build();
    }


}