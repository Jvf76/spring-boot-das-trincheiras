package academy.devdojo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class AnimePutRequest {
    @NotNull(message = "The field 'id' cannot be nulll")
    private Long id;
    @NotBlank(message = "The field 'name' is required")
    private String name;


}
