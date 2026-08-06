package academy.devdojo.request;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class AnimePutResponse {
    private Long id;
    private String name;


}
