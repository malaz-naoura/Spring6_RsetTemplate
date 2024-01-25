package com.example.spring_6_rest_mvc_rsettemplate.client;

import com.example.spring_6_rest_mvc_rsettemplate.model.JuiceDTO;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface JuiceClient {

    Page<JuiceDTO> listJuice(String juiceName);

    JuiceDTO getJuiceById(UUID id);

    JuiceDTO createJuice(JuiceDTO juiceDTO);

    JuiceDTO updateJuice(JuiceDTO juiceDTO);

    void deleteJuice(JuiceDTO juiceDTO);

}
