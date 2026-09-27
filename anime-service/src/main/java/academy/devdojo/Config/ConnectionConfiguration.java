package academy.devdojo.Config;

import academy.devdojo.external.dependency.Connection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ConnectionConfiguration {
    @Value("${server.database.url}")
    private String url;
    @Value("${server.database.password}")
    private String password;
    @Value("${server.database.username}")
    private String username;
    @Bean
    public Connection connectionMySql() {
        return new Connection(url, username, password);
    }

    @Bean(name = "connection")
    @Primary
    public Connection connectionMongo() {
        return new Connection("localhost", "devdojo", "goku");

    }
}
