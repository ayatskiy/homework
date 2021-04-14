package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/websiteconfig.properties")
public interface OtusWebsiteConfig extends Config {
    String url();
    String title();
}