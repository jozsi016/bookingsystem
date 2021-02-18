package hu.bookingsystem;

import hu.bookingsystem.repository.RoomRepository;
import hu.bookingsystem.service.RoomService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public RoomService getRoomService(RoomRepository roomRepository){
        return new RoomService(roomRepository);
    }

}
