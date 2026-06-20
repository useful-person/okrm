package com.useful_person.nacos_config.properties;

/**
 *
 * @author peter
 *
 */
public final class SecurityConstants {
    public static final String DEFAULT_SESSION_KEY_IMAGE_CODE = "SESSION_KEY_IMAGE_CODE";
    public static final String DEFAULT_SESSION_KEY_SMS_CODE = "SESSION_KEY_SMS_CODE";
    public static final String DEFAULT_SESSION_KEY_EMAIL_CODE = "SESSION_KEY_EMAIL_CODE";
    public static final String DEFAULT_VALIDATOR_CODE_URL_PREFIX = "/code";
    public static final String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";
    public static final String DEFAULT_UNAUTHENTICATION_FAILURE_URL = "/authentication/failure";
    public static final String DEFAULT_SIGN_IN_PROCESSING_URL_FORM = "/authentication/form";
    public static final String DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE = "/authentication/mobile";
    public static final String DEFAULT_SIGN_UP_URL = "/signup";
    public static final String DEFAULT_SIGN_UP_MOBILE_URL = "/signup/mobile";
    public static final String DEFAULT_SIGN_UP_EMAIL_URL = "/signup/email";
    public static final String DEFAULT_SIGN_OUT_URL = "/logout";
    public static final String DEFAULT_ACTIVATE_URL_PREFIX = "/activate";
    public static final String DEFAULT_SIGN_IN_PAGE_URL = "/basic-signin.html";
    public static final String DEFAULT_SIGN_UP_PAGE_URL = "/basic-signup.html";
    public static final String DEFAULT_SIGN_OUT_PAGE_URL = "/basic-signout.html";
    public static final String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";
    public static final String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";
    public static final String DEFAULT_PARAMETER_NAME_CODE_EMAIL = "emailCode";
    public static final String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";
    public static final String DEFAULT_ROLE_NAME_PREFIX = "ROLE_";
    // 认证类型
    public static final String DEFAULT_AUTH_TYPE_USERNAME = "username";
    public static final String DEFAULT_AUTH_TYPE_MOBILE = "mobile";
    public static final String DEFAULT_AUTH_TYPE_EMAIL = "email";
    // 默认超级用户
    public static final String DEFAULT_SUPER_USER = "superuser";

    private SecurityConstants() {
        throw new IllegalStateException("Utility class");
    }

}
