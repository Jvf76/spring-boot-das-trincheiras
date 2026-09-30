package academy.devdojo.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "database")
public record ConnectionConfigurationPropeties(String url, String password, String username) {

}

