
package com.useful_person.nacos.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author cbtpro
 *
 */
public class IdcardProperties {

  @Getter
  @Setter
  private String host = "https://idcard.market.alicloudapi.com";

  @Getter
  @Setter
  private String path = "/lianzhuo/idcard";

  @Getter
  @Setter
  private String appCode;

  @Getter
  @Setter
  private String appKey;

  @Getter
  @Setter
  private String appSecret;
}
