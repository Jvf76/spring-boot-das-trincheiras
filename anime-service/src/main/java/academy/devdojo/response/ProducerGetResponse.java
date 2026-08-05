package academy.devdojo.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Builder
@Getter
public class ProducerGetResponse {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
}
