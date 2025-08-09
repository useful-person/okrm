package com.useful_person.nacos.properties;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author peter
 *
 */
public class BrowserProperties {

  @Getter
  @Setter
  private String signinPage = SecurityConstants.DEFAULT_SIGN_IN_PAGE_URL;

  @Getter
  @Setter
  private String signupPage = SecurityConstants.DEFAULT_SIGN_UP_PAGE_URL;

  @Getter
  @Setter
  private String signoutPage = SecurityConstants.DEFAULT_SIGN_OUT_PAGE_URL;

  @Getter
  @Setter
  private SigninType signinType = SigninType.JSON;

  // # 保留remenberMe 7天
  @Getter
  @Setter
  private int rememberMeSeconds = 7 * 24 * 60 * 60;

}
