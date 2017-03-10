package com.intransition.labs;

import org.pegdown.PegDownProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.ui.context.ThemeSource;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ThemeResolver;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

import java.util.Locale;

@SpringBootApplication
public class LyubinApplication {

    public static void main(String[] args) {
        SpringApplication.run(LyubinApplication.class, args);
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:i18n/messages");
        messageSource.setCacheSeconds(3600);
        messageSource.setFallbackToSystemLocale(false);

        return messageSource;

    }

    @Bean
    public LocaleResolver localeResolver() {
        com.intransition.labs.LocaleResolver localeResolver = new com.intransition.labs.LocaleResolver();
        localeResolver.setDefaultLocale(new Locale("en_US"));

        return localeResolver;
    }

    @Bean
    public SpringDataDialect springDataDialect() {
        return new SpringDataDialect();
    }

    @Bean
    public ThemeSource themeSource() {
        ResourceBundleThemeSource themeSource = new ResourceBundleThemeSource();
        themeSource.setBasenamePrefix("themes/");
        return themeSource;
    }

    @Bean
    public ThemeResolver themeResolver() {
        ThemeResolver themeResolver = new com.intransition.labs.thymeleaf.ThemeResolver();
        return themeResolver;
    }

    @Bean("pegdown")
    public PegDownProcessor pegdown() {
        PegDownProcessor pegDownProcessor = new PegDownProcessor();
        return pegDownProcessor;
    }

}
