package com.example.spring_6_rest_mvc_rsettemplate.client;

import com.example.spring_6_rest_mvc_rsettemplate.model.JuiceDTO;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true,value = "pageable")
public class JuiceDtoPageImpl extends PageImpl<JuiceDTO> {


    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public JuiceDtoPageImpl(@JsonProperty("content") List<JuiceDTO> content,
                            @JsonProperty("number") int number,
                            @JsonProperty("size") int size,
                            @JsonProperty("totalElements") long total) {
        super(content, PageRequest.of(number,size), total);
    }

}
