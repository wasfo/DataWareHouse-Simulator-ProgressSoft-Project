package org.example.controller;

import org.example.dto.FxDealDto;
import org.example.service.FXDealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deals")
public class FXDealController {
    @Autowired
    private FXDealService fxDealService;
    private static final Logger LOGGER = LoggerFactory.getLogger(FXDealController.class);

    @PostMapping("/save")
    public void importDeal(@RequestBody FxDealDto deal) {

        try {
            LOGGER.info("Validating deal with id {}", deal.getUniqueId());
            fxDealService.validateFxDealDto(deal);
        } catch (Exception e) {
            LOGGER.error("Validation failed for id {}, cause {}", deal.getUniqueId(), e.getMessage());
            return;
        }

        if (fxDealService.exists(deal.getUniqueId())) {
            LOGGER.error("fxDeal with id {} already exists", deal.getUniqueId());
            return;
        }

        if (fxDealService.save(deal)) {
            LOGGER.info("fxDeal with id {} saved successfully", deal.getUniqueId());
        }
    }

}
