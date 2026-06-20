package com.useful_person.nacos_config.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author peter
 *
 */
public class TencentProperties {

  @Getter
  @Setter
  private WeappProperties weapp = new WeappProperties();
}
