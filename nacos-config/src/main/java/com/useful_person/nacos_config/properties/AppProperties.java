package com.useful_person.nacos_config.properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author peter
 *
 */
@Component
@ConfigurationProperties(prefix = "okrm.app")
public class AppProperties {

    private String openPort = "80";
    @Getter
    @Setter
    private String host = "//localhost";

    @Getter
    @Setter
    private int port = 8080;

    @Getter
    @Setter
    private String mail;

    @Getter
    @Setter
    private FileProperties file = new FileProperties();

    public String getOrigin() {
        String origin = this.host;
        String portStr = StringUtils.trim(String.valueOf(this.port));
        if (StringUtils.isNotBlank(portStr) && !openPort.equals(portStr)) {
            origin += (":" + this.port);
        }
        return origin;
    }
}
