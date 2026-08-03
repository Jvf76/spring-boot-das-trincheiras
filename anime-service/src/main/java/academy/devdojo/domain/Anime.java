package academy.devdojo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Getter
@Setter
public class Anime {
    Long id;
    String name;

    @Getter
    private static final List<Anime> animes = new ArrayList<>();

    public static List<Anime> listarAnime() {
        return animes;
    }

}
