package com.useful_person.nacos.properties;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author peter
 *
 */
public class MailVerificationProperties {

  @Getter
  @Setter
  private int expireIn = 60 * 60 * 24 * 2;

}
