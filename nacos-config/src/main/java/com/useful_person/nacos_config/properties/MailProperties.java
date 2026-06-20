package com.useful_person.nacos_config.properties;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author peter
 *
 */
public class MailProperties {

  @Getter
  @Setter
  private MailConfig config;

  @Getter
  @Setter
  private MailCodeProperties code;

  @Getter
  @Setter
  private MailVerificationProperties verification;

}
