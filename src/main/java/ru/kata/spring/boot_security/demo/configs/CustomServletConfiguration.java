package ru.kata.spring.boot_security.demo.configs;

import org.springframework.boot.web.server.MimeMappings;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;

public class CustomServletConfiguration implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
        mappings.remove("js");
        mappings.add("js", "application/javascript;charset=utf-8");
        mappings.remove("src/main/static/css");
        mappings.add("src/main/static/css", "text/css;charset=utf-8");
        factory.setMimeMappings(mappings);
        factory.setPort(8080);
    }
}
