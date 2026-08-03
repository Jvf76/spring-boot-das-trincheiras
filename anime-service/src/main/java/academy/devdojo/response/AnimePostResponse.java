package academy.devdojo.response;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class AnimePostResponse {
    private Long id;
    private String name;


}
