package com.yunqing.democonfigurationproperties.utilsReadYaml;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentInitializer implements CommandLineRunner {

    private final Environment environment;

    public EnvironmentInitializer(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void run(String... args) throws Exception {
        ConfigUtils.setEnvironment(environment);
    }
}