package com.example.spring_6_rest_mvc_rsettemplate.client;

import com.example.spring_6_rest_mvc_rsettemplate.config.RestTemplateBuilderConfig;
import com.example.spring_6_rest_mvc_rsettemplate.model.JuiceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class JuiceClientImpl implements JuiceClient {

    private final RestTemplateBuilder restTemplateBuilder;

    private final String BASE_API_URL = "api/v1";
    private final String GET_JUICE_PATH = "%s/juice".formatted(BASE_API_URL);
    private final String GET_JUICE_BY_ID_PATH = "%s/juice/{juiceId}".formatted(BASE_API_URL);



    @Override
    public Page<JuiceDTO> listJuice(String juiceName) {

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(GET_JUICE_PATH);

        if (juiceName != null) uriComponentsBuilder.queryParam("juiceName", juiceName);

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<JuiceDtoPageImpl> responseEntity = restTemplate.getForEntity(uriComponentsBuilder.build()
                                                                                                        .toUriString(),
                                                                                    JuiceDtoPageImpl.class);
        return responseEntity.getBody();
    }

    @Override
    public JuiceDTO getJuiceById(UUID id) {

        RestTemplate restTemplate = restTemplateBuilder.build();
        JuiceDTO juiceDTO = restTemplate.getForObject(GET_JUICE_BY_ID_PATH, JuiceDTO.class, id);
        return juiceDTO;
    }

    @Override
    public JuiceDTO createJuice(JuiceDTO juiceDTO) {

        RestTemplate restTemplate = restTemplateBuilder.build();
        URI uri = restTemplate.postForLocation(GET_JUICE_PATH, juiceDTO);

        return restTemplate.getForObject(uri.getPath(), JuiceDTO.class);
    }

    @Override
    public JuiceDTO updateJuice(JuiceDTO juiceDTO) {

        RestTemplate restTemplate = restTemplateBuilder.build();

        restTemplate.put(GET_JUICE_BY_ID_PATH, juiceDTO, juiceDTO.getId());

        return getJuiceById(juiceDTO.getId());
    }

    @Override
    public void deleteJuice(JuiceDTO juiceDTO) {

        RestTemplate restTemplate = restTemplateBuilder.build();

        restTemplate.delete(GET_JUICE_BY_ID_PATH, juiceDTO.getId());


    }
}
