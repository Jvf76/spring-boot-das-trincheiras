package academy.devdojo.Config;

import academy.devdojo.external.dependency.Connection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ConnectionConfiguration {
    @Bean
    public Connection connectionMySql() {
        return new Connection("localhost", "devdojo", "goku");
    }

    @Bean(name = "connection")
    @Primary
    public Connection connectionMongo() {
        return new Connection("localhost", "devdojo", "goku");

    }
}
