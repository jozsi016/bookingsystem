package hu.bookingsystem.controller;

import hu.bookingsystem.model.Room;
import hu.bookingsystem.service.RoomService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RoomController.class)
public class RoomControllerMvcTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RoomService roomServiceMock;


    @Test
    public void shouldReturnRoom() throws Exception {
        Room room = new Room(3, 5000);
        String expected = "{\"id\":3,\"unitPrice\":5000.0}";
        Mockito.when(roomServiceMock.getRoomById(any())).thenReturn(room);
        this.mockMvc.perform(get("/room/3")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(expected)));
    }
}
