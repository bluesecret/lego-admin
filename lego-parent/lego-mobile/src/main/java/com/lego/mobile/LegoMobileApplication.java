package com.lego.mobile;

import com.lego.core.common.ServiceStartType;
import com.lego.core.data.hibernate.jpa.RepositoryFactoryBean;
import com.lego.core.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"com.lego"})
@EntityScan("com.lego.**.entity")
@MapperScan({"com.lego.**.mapper"})
@EnableFeignClients({"com.lego.core.feign", "com.lego.mobile.feign"})
@ConditionalOnProperty(name = "lego.start-type", havingValue = ServiceStartType.microservice)
@EnableJpaRepositories(value = "com.lego.**.dao", repositoryFactoryBeanClass = RepositoryFactoryBean.class)
public class LegoMobileApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(LegoMobileApplication.class);
    }

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(LegoMobileApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = StringUtil.trim(env.getProperty("server.servlet.context-path"));
        log.info("\n----------------------------------------------------------\n" +
            "Application LegoMobile is running! Access URLs:\n" +
            "Local: \t\thttp://:" + ip + ":" + port + path + "/\n" +
            "----------------------------------------------------------");
    }
}
