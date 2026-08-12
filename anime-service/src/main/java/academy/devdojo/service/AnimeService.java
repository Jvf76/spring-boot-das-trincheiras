package academy.devdojo.service;

import academy.devdojo.domain.Anime;
import academy.devdojo.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {
    private final AnimeRepository repository;



    public List<Anime> findAll(String name){
        return name == null ? repository.findAll() : repository.findByName(name);
    }

    public Anime findByIdOrThrowNotFound(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not Found"));
    }

    public Anime save(Anime anime){
        return repository.save(anime);
    }

    public void delete(Long id){
        var anime = findByIdOrThrowNotFound(id);
        repository.delete(anime);
    }

    public Anime update(Anime animeToUpdate){
        assertAnimeExists(animeToUpdate.getId());
        return repository.update(animeToUpdate);
    }

    public void assertAnimeExists(Long id){
        findByIdOrThrowNotFound(id);
    }


}
