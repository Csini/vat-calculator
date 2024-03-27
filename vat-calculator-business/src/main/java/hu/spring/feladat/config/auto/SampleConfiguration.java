package hu.spring.feladat.config.auto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@ConfigurationProperties(prefix = "owner")
@Data
@Validated
public class SampleConfiguration {
    private boolean enabled = true;
    private OwnerDetails ownerDetails= new OwnerDetails();
}
