package top.zhacker.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class MsConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsConfigApplication.class, args);
	}
}
