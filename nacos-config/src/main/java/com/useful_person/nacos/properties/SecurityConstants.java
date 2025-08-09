package com.useful_person.nacos.properties;

/**
 *
 * @author peter
 *
 */
public interface SecurityConstants {

  String DEFAULT_SESSION_KEY_IMAGE_CODE = "SESSION_KEY_IMAGE_CODE";

  String DEFAULT_SESSION_KEY_SMS_CODE = "SESSION_KEY_SMS_CODE";

  String DEFAULT_SESSION_KEY_EMAIL_CODE = "SESSION_KEY_EMAIL_CODE";

  String DEFAULT_VALIDATOR_CODE_URL_PREFIX = "/code";

  String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";

  String DEFAULT_UNAUTHENTICATION_FAILURE_URL = "/authentication/failure";

  String DEFAULT_SIGN_IN_PROCESSING_URL_FORM = "/authentication/form";

  String DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE = "/authentication/mobile";

  String DEFAULT_SIGN_UP_URL = "/signup";

  String DEFAULT_SIGN_UP_MOBILE_URL = "/signup/mobile";

  String DEFAULT_SIGN_UP_EMAIL_URL = "/signup/email";

  String DEFAULT_SIGN_OUT_URL = "/logout";

  String DEFAULT_ACTIVATE_URL_PREFIX = "/activate";

  String DEFAULT_SIGN_IN_PAGE_URL = "/basic-signin.html";

  String DEFAULT_SIGN_UP_PAGE_URL = "/basic-signup.html";

  String DEFAULT_SIGN_OUT_PAGE_URL = "/basic-signout.html";

  String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";

  String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";

  String DEFAULT_PARAMETER_NAME_CODE_EMAIL = "emailCode";

  String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";

  String DEFAULT_ROLE_NAME_PREFIX = "ROLE_";

  // 认证类型
  String DEFAULT_AUTH_TYPE_USERNAME = "username";
  String DEFAULT_AUTH_TYPE_MOBILE = "mobile";
  String DEFAULT_AUTH_TYPE_EMAIL = "email";

  // 默认超级用户
  String DEFAULT_SUPER_USER = "superuser";

}
