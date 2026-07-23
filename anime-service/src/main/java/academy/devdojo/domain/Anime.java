package academy.devdojo.domain;

import java.util.List;

public class Anime {
    long id;
    String name;

    public Anime(long id, String name){
        this.id = id;
        this.name = name;
    }

    private static final List<Anime> Anime = List.of();
    public static void main(String[] args) {
        Anime anime = new Anime(1,"naruto");
        Anime anime2 = new Anime(2,"one piece");
    }

}
