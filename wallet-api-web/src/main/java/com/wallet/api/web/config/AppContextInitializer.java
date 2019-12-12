package com.wallet.api.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Padmaka Wijayagoonawardena
 * Date: 12/12/19
 */

@Component
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.wallet.api.*")
@Profile({"prod", "test"})
public class AppContextInitializer {
}
