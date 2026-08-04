package academy.devdojo.response;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class AnimeGetResponse {
    private Long id;
    private String name;


}
