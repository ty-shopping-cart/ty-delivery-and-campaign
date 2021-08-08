package com.trendyol.tydeliveryandcampaign.service.impl;

import com.trendyol.tydeliveryandcampaign.dto.ParameterDto;
import com.trendyol.tydeliveryandcampaign.model.Parameter;
import com.trendyol.tydeliveryandcampaign.repository.ParameterRepository;
import com.trendyol.tydeliveryandcampaign.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParameterServiceImpl implements ParameterService {

    private ParameterRepository parameterRepository;

    @Autowired
    public ParameterServiceImpl(ParameterRepository parameterRepository) {
        this.parameterRepository = parameterRepository;
    }

    @Override
    public String addParameter(ParameterDto parameterDto) {
        Parameter parameter = new Parameter();
        parameter.setKey(parameterDto.getKey());
        parameter.setValue(parameterDto.getValue());
        parameterRepository.save(parameter);

        return "New parameter added.";
    }

    @Override
    public ParameterDto getParameter(String key) {
         Parameter parameter = parameterRepository.findByKey(key);
         ParameterDto parameterDto = new ParameterDto(parameter.getKey(),parameter.getValue());
         return parameterDto;
    }

    @Override
    public ParameterDto updateParameter(ParameterDto parameterDto) {
        Parameter parameter = parameterRepository.findByKey(parameterDto.getKey());
        parameter.setKey(parameterDto.getKey());
        parameter.setValue(parameterDto.getValue());
        parameterRepository.save(parameter);

        return parameterDto;
    }
}
