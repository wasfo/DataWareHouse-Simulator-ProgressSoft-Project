package org.example.controller;


import org.example.dto.FxDealDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/loadBalancer")
public class LoadBalancerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoadBalancerController.class);
    private final RestTemplate restTemplate;

    @Autowired
    public LoadBalancerController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/save/fxDeal")
    public void saveFxDealsInWareHouse(@RequestBody FxDealDto fxDealDto) {
        LOGGER.info("Accepting fxDeal {}", fxDealDto.toString());
        String url = "http://DataWareHouse/deals/save";
        HttpEntity<FxDealDto> entity = new HttpEntity<>(fxDealDto);
        restTemplate.exchange(url,
                HttpMethod.POST,
                entity,
                Void.class);

    }
}
