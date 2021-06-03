package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/users_data.properties")
public interface UsersDataConfig extends Config {
    @Key("otus_login")
    String otusLogin();
    @Key("otus_password")
    String otusPassword();
    @Key("otus_birthdate")
    String otusBirthdate();
    @Key("otus_city")
    String otusCity();
    @Key("otus_country")
    String otusCountry();
    @Key("otus_eng_level")
    String otusEngLevel();
}