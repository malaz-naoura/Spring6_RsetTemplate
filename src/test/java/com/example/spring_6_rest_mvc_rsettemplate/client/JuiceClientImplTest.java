package com.example.spring_6_rest_mvc_rsettemplate.client;

import com.example.spring_6_rest_mvc_rsettemplate.model.JuiceDTO;
import com.example.spring_6_rest_mvc_rsettemplate.model.JuiceStyle;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JuiceClientImplTest {

    @Autowired
    JuiceClient juiceClient;

    @Test
    void listJuice() {
        List<JuiceDTO> juiceDTOList = juiceClient.listJuice(null)
                                                 .getContent();
        assertNotEquals(juiceDTOList.size(), 0);

    }

    void listJuiceWithJuiceName() {
        List<JuiceDTO> juiceDTOList = juiceClient.listJuice("All Ame...")
                                                 .getContent();
        assertNotEquals(juiceDTOList.size(), 0);
    }

    @Test
    void getJuiceById() {

        UUID randomId = juiceClient.listJuice(null)
                                   .getContent()
                                   .get(0)
                                   .getId();
        JuiceDTO juiceDTO = juiceClient.getJuiceById(randomId);
        assertNotNull(juiceDTO.getId());
    }

    @Test
    void createJuice() {
        JuiceDTO juiceDTO = JuiceDTO.builder()
                                    .name("juice_1")
                                    .juiceStyle(JuiceStyle.LEMON)
                                    .price(BigDecimal.ONE)
                                    .upc("upc")
                                    .build();

        JuiceDTO savedJuice = juiceClient.createJuice(juiceDTO);
        assertNotNull(savedJuice);
    }

    @Test
    void updateJuice() {
        JuiceDTO juiceDTO = JuiceDTO.builder()
                                    .name("juice_1")
                                    .juiceStyle(JuiceStyle.LEMON)
                                    .price(BigDecimal.ONE)
                                    .upc("upc")
                                    .build();

        juiceDTO= juiceClient.createJuice(juiceDTO);

        String newNameForJuice="juice_2";
        juiceDTO.setName(newNameForJuice);

        JuiceDTO updatedJuice= juiceClient.updateJuice(juiceDTO);

        assertEquals(updatedJuice.getName(),newNameForJuice);

    }

    @Test
    void deleteJuice() {
        JuiceDTO juiceDTO = JuiceDTO.builder()
                                    .name("juice_1")
                                    .juiceStyle(JuiceStyle.LEMON)
                                    .price(BigDecimal.ONE)
                                    .upc("upc")
                                    .build();

        JuiceDTO savedJuice= juiceClient.createJuice(juiceDTO);

        juiceClient.deleteJuice(savedJuice);

        assertThrows(HttpClientErrorException.class,() -> {
            juiceClient.getJuiceById(savedJuice.getId());
        });
    }
}