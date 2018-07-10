package bds.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/error").setViewName("error");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/routes").setViewName("routes");
        registry.addViewController("/routes/route1").setViewName("route1");
        registry.addViewController("/routes/route2").setViewName("route2");
        registry.addViewController("/routes/route3").setViewName("route3");
        registry.addViewController("/routes/route4").setViewName("route4");
        registry.addViewController("/registerClient").setViewName("registerClient");
        registry.addViewController("/registerClient/registerClientExtended").setViewName("registerClientExtended");
        registry.addViewController("/registerClient/registerClientSimple").setViewName("registerClientSimple");
        registry.addViewController("/registerManager").setViewName("registerManager");
        registry.addViewController("/registerManager/registerManagerExtended").setViewName("registerManagerExtended");
        registry.addViewController("/registerManager/registerManagerSimple").setViewName("registerManagerSimple");
        registry.addViewController("/payments").setViewName("payments");
        registry.addViewController("/payments/onlinePayment").setViewName("onlinePayment");
        registry.addViewController("/payments/bankPayment").setViewName("bankPayment");
        registry.addViewController("/payments/archivePayment").setViewName("archivePayment");
    }
}

