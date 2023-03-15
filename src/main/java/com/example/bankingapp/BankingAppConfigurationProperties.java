package com.example.bankingapp;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "banking")
public class BankingAppConfigurationProperties {

    private long transferThreshold = Long.MAX_VALUE;

}
