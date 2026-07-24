package academy.devdojo.controller;

import academy.devdojo.domain.Anime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.Optional;

@RestController
@RequestMapping("v1/animes")
@Slf4j
public class AnimeController {


    @GetMapping
    public List<String> ListAll() throws InterruptedException {
        log.info(Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(1);
        return List.of("One piece", "Naruto", "HXH");
    }

    @GetMapping("/lista")
    public List<Anime> listarAnime(@RequestParam (required = false) String name){
        return Anime.listarAnime()
                .stream()
                .filter(anime -> anime.getName().equalsIgnoreCase(name))
                .toList();
    }

    @GetMapping("/{id}")
    public Anime findById(@PathVariable Long id){
        return Anime.listarAnime()
                .stream()
                .filter(anime -> anime.getId().equals(id))
                .findFirst().orElse(null);
    }


}
