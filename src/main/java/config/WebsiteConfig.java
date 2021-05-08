package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/websites_config.properties")
public interface WebsiteConfig extends Config {
    @Key("otus_url")
    String otusUrl();
    @Key("otus_title")
    String otusTitle();
    @Key("otus_contactInfo")
    String otusContactInfo();
    @Key("otus_faq_answer")
    String otusAnswer();
    @Key("trash_email")
    String otusEmail();

    @Key("tele2_url")
    String tele2Url();
    @Key("tele2_shop_number_url")
    String tele2ShopNumberUrl();

    @Key("market_url")
    String marketUrl();
}