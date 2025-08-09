
package com.useful_person.nacos.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author peter
 *
 */
public class MailConfig {

  @Getter
  @Setter
  private String host;

  @Getter
  @Setter
  private String username;

  @Getter
  @Setter
  private String password;

  @Getter
  @Setter
  private String port;

  @Getter
  @Setter
  private String defaultEncoding;

  @Getter
  @Setter
  private long timeout;

  @Getter
  @Setter
  private long connectiontimeout;

  @Getter
  @Setter
  private long writetimeout;

  @Getter
  @Setter
  private String debug;
}
