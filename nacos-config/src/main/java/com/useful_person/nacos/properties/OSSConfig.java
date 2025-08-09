
package com.useful_person.nacos.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author peter
 *
 */
public class OSSConfig {

  @Getter
  @Setter
  private String endpoint = "";

  @Getter
  @Setter
  private String accessKeyId = "";

  @Getter
  @Setter
  private String accessKeySecret = "";

  @Getter
  @Setter
  private String bucketName = "";

  @Getter
  @Setter
  private String resourceUrl = "https://resources.useful-person.com";

  @Getter
  @Setter
  private String avatarDir = "avatar";
}
