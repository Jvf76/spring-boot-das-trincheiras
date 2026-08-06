package academy.devdojo.request;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class AnimePutRequest {
    private Long id;
    private String name;


}
