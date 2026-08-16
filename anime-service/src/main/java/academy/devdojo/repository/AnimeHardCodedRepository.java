package academy.devdojo.repository;

import academy.devdojo.domain.Anime;

import java.util.ArrayList;
import java.util.List;

public class AnimeHardCodedRepository {

    private static final List<Anime> animes = new ArrayList<>();



    public List<Anime> findAll(){
        return animes;
    }
}
