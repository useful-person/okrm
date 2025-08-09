package com.useful_person.nacos.properties;

/**
 *
 * @author peter
 *
 */
public class ValidatorCodeProperties {

  private ImageCodeProperties image = new ImageCodeProperties();

  private SmsCodeProperties sms = new SmsCodeProperties();

  public ImageCodeProperties getImage() {
    return image;
  }

  public void setImage(ImageCodeProperties image) {
    this.image = image;
  }

  public SmsCodeProperties getSms() {
    return sms;
  }

  public void setSms(SmsCodeProperties sms) {
    this.sms = sms;
  }
}
