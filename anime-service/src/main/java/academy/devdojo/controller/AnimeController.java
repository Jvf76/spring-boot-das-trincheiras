package academy.devdojo.controller;

import academy.devdojo.mapper.AnimeMapper;
import academy.devdojo.request.AnimePostRequest;
import academy.devdojo.request.AnimePutRequest;
import academy.devdojo.response.AnimeGetResponse;
import academy.devdojo.response.AnimePostResponse;
import academy.devdojo.service.AnimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/animes")
@Slf4j
@RequiredArgsConstructor
public class AnimeController {
    private final AnimeMapper mapper;
    private final AnimeService service;



    @GetMapping
    public ResponseEntity<List<AnimeGetResponse>> listAll(@RequestParam(required = false) String name) {

        var animesList = service.findAll(name);

        var response = mapper.toAnimeGetResponseList(animesList);

        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<AnimeGetResponse> findById(@PathVariable Long id) {

        var animes = service.findByIdOrThrowNotFound(id);

        var response = mapper.toAnimeGetResponse(animes);

        return ResponseEntity.ok(response);
    }


    @PostMapping
    public ResponseEntity<AnimePostResponse> save(@RequestBody AnimePostRequest animePostRequest) {

        var animes = mapper.toAnime(animePostRequest);

        var animeSaved = service.save(animes);

        var response = mapper.toAnimePostResponse(animeSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.debug("Request to delete anime by id: {}", id);

        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody AnimePutRequest request) {
        log.debug("Request to update anime by id: {}", request);


        var animeUpdated = mapper.toAnime(request);
        service.update(animeUpdated);

        return ResponseEntity.noContent().build();
    }

}
