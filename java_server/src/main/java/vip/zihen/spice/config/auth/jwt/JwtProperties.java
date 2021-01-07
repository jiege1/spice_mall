package vip.zihen.spice.config.auth.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String header;

    private Long expiresIn;

    private String issuer;

    private String secret;

    private String audience;

}
