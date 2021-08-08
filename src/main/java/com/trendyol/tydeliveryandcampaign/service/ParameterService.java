package com.trendyol.tydeliveryandcampaign.service;

import com.trendyol.tydeliveryandcampaign.dto.ParameterDto;

public interface ParameterService {
    String addParameter(ParameterDto parameterDto);
    ParameterDto getParameter(String key);
    ParameterDto updateParameter(ParameterDto parameterDto);
}
