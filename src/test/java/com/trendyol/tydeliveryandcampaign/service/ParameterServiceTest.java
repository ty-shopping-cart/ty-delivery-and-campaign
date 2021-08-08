package com.trendyol.tydeliveryandcampaign.service;

import com.trendyol.tydeliveryandcampaign.dto.ParameterDto;
import com.trendyol.tydeliveryandcampaign.model.Parameter;
import com.trendyol.tydeliveryandcampaign.repository.ParameterRepository;
import com.trendyol.tydeliveryandcampaign.service.impl.ParameterServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ParameterServiceTest {

    @Mock
    ParameterRepository parameterRepository;

    @InjectMocks
    ParameterServiceImpl parameterService;

    @Test
    void addParameter(){
        Parameter parameter = new Parameter();
        parameter.setKey("key");
        parameter.setValue("value");
        when(parameterRepository.save(any())).thenReturn(parameter);

        ParameterDto parameterDto = new ParameterDto("key", "value");
        parameterService.addParameter(parameterDto);
        Mockito.verify(parameterRepository, Mockito.times(1)).save(any(Parameter.class));
    }

    @Test
    void getParameter(){
        Parameter parameter = new Parameter();
        parameter.setKey("key");
        parameter.setValue("value");
        when(parameterRepository.findByKey(any())).thenReturn(parameter);

        ParameterDto parameterDto = parameterService.getParameter("key");
        assertThat(parameterDto.getKey()).isEqualTo(parameter.getKey());
        assertThat(parameterDto.getValue()).isEqualTo(parameter.getValue());
    }

    @Test
    void updateParameter(){
        Parameter parameter = new Parameter();
        parameter.setKey("key");
        parameter.setValue("value");
        when(parameterRepository.findByKey(any())).thenReturn(parameter);

        ParameterDto parameterDto = new ParameterDto("key", "value2");
        ParameterDto parameterDtoResult = parameterService.updateParameter(parameterDto);
        assertThat(parameterDtoResult.getKey()).isEqualTo(parameterDto.getKey());
        assertThat(parameterDtoResult.getValue()).isEqualTo(parameterDto.getValue());
    }
}
