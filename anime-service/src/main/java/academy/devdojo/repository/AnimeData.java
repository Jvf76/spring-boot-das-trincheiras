package academy.devdojo.repository;

import academy.devdojo.domain.Anime;
import academy.devdojo.domain.Anime;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class AnimeData {
    private static final List<Anime> animes = new ArrayList<>();
    {
        var ninjaJamui = Anime.builder().id(1L).name("ninjaJamui").build();
        var kaiju = Anime.builder().id(2L).name("kaiju").build();
        var kimetsuNoYaiba = Anime.builder().id(3L).name("kimetsuNoYaiba").build();
        animes.addAll(List.of(ninjaJamui, kaiju, kimetsuNoYaiba));
    }
    public List<Anime> getAnimes(){
        return animes;
    }
}
