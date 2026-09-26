package academy.devdojo.commons;

import academy.devdojo.domain.Anime;
import academy.devdojo.domain.Producer;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class AnimeUtils {
    public List<Anime> newAnimeList(){

        var onePiece = Anime.builder().id(1L).name("One Piece").build();
        var bleach = Anime.builder().id(2L).name("Bleach").build();
        var dbz = Anime.builder().id(3L).name("DBZ").build();

        return new ArrayList<>(List.of(onePiece, bleach, dbz));
    }

    public Anime newAnimeToSave(){
        return Anime.builder().id(99L).name("DBZ").build();
    }
}
