package neighborHub;

import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import neighborHub.model.Entity.FareInfo;
import neighborHub.service.FareInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NeighborHubApplication {
	public static void main(String[] args) {
		SpringApplication.run(NeighborHubApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
