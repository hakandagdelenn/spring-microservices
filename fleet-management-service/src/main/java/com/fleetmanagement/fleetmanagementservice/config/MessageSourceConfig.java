package com.fleetmanagement.fleetmanagementservice.config;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import static com.fleetmanagement.fleetmanagementservice.util.FleetManagementConstants.BASE_NAME;
import static com.fleetmanagement.fleetmanagementservice.util.FleetManagementConstants.ENCODING;

/**
 * @author hakan.dagdelen
 */
@Configuration
public class MessageSourceConfig {

  public MessageSourceConfig() {
    // TODO document why this constructor is empty
  }

  @Bean
  public static MessageSource messageSource() {

    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

    messageSource.setBasename(BASE_NAME);
    messageSource.setDefaultEncoding(ENCODING);
    return messageSource;
  }

  public static String getMessage(String message, String... dynamicValues) {
    return messageSource().getMessage(message,
                                      dynamicValues, Locale.getDefault());
  }
}
