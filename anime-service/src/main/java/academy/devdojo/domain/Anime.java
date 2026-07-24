package academy.devdojo.domain;

import lombok.Getter;

import java.util.List;
@Getter
public class Anime {
    long id;
    String name;

    public Anime(long id, String name){
        this.id = id;
        this.name = name;
    }


    public static  List<Anime> listarAnime(){
        Anime anime = new Anime(1,"naruto");
        Anime anime2 = new Anime(2,"one piece");

        return List.of(anime,anime2);
    }



}
