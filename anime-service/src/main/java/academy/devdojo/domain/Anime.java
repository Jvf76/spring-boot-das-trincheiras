package academy.devdojo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class Anime {
    Long id;
    String name;

    public Anime(long id, String name){
        this.id = id;
        this.name = name;
    }

    private static final List<Anime> ANIMES = new ArrayList<>();


    public static  List<Anime> listarAnime(){
        return ANIMES;
    }



}
