package academy.devdojo.domain;

import jdk.jshell.Snippet;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Anime {
    @EqualsAndHashCode.Include
    Long id;
    String name;


}
