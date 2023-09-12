package Vaistra.Managment;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VaistraApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaistraApplication.class, args);
	}
     @Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	 }
}
