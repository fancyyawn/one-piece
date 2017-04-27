package top.zhacker.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

@EnableZipkinServer
@SpringBootApplication
public class MsZipkinServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsZipkinServerApplication.class, args);
	}
}
