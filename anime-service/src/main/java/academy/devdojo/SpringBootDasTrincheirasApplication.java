package academy.devdojo;

import academy.devdojo.Config.ConnectionConfigurationPropeties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.Arrays;

@SpringBootApplication
@EnableConfigurationProperties(ConnectionConfigurationPropeties.class)
public class SpringBootDasTrincheirasApplication {

    public static void main(String[] args) {
        var applicationContext = SpringApplication.run(SpringBootDasTrincheirasApplication.class, args);
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
    }

}
