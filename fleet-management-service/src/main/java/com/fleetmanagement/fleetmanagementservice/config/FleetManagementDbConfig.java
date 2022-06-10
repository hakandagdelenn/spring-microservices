package com.fleetmanagement.fleetmanagementservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author hakan.dagdelen
 */

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.fleetmanagement.fleetmanagementservice.repository")
public class FleetManagementDbConfig {

}
