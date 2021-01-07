package vip.zihen.spice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.TimeZone;

@MapperScan({"vip.zihen.spice.**.mapper"})
@SpringBootApplication
@EnableConfigurationProperties
public class SpiceApplication {

    public static void main(String[] args) {

        TimeZone timeZone = TimeZone.getTimeZone("Etc/GMT-8");
        TimeZone.setDefault(timeZone);

        SpringApplication.run(SpiceApplication.class, args);
    }

}
