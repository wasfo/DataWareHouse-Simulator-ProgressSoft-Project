package org.example.service;
import jakarta.validation.ConstraintViolation;
import org.example.dto.FxDealDto;
import org.example.model.FXDeal;
import org.example.repository.FXDealRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import java.util.Optional;
import java.util.Set;


@Service
public class FXDealService {


    private final FXDealRepository fxDealRepository;
    private final LocalValidatorFactoryBean validator;
    private static final Logger LOGGER = LoggerFactory.getLogger(FXDealService.class);


    @Autowired
    public FXDealService(FXDealRepository fxDealRepository, LocalValidatorFactoryBean validator) {
        this.fxDealRepository = fxDealRepository;
        this.validator = validator;
    }

    public void validateFxDealDto(FxDealDto fxDealDto) {
        Set<ConstraintViolation<FxDealDto>> violations = validator.validate(fxDealDto);
        LOGGER.info("Violations are  {} ", violations);

        if (!violations.isEmpty()) {
            throw new IllegalArgumentException("Validation failed: " + violations);
        }
    }
    public boolean save(FxDealDto dto) {
        try {
            fxDealRepository.save(mapDtoToEntity(dto));
            return true;
        } catch (Exception e) {
            LOGGER.error("Error Saving fxDeal with id: {}, Exception Message {} ", dto.getUniqueId(), e.getMessage());
        }
        return false;
    }

    public boolean exists(String uniqueId) {
        return fxDealRepository.existsById(uniqueId);
    }

    public FXDeal mapDtoToEntity(FxDealDto dto) {
        return Optional.ofNullable(dto)
                .map(deal -> {
                    FXDeal entity = new FXDeal();
                    entity.setUniqueId(dto.getUniqueId());
                    entity.setFromCurrency(dto.getFromCurrency());
                    entity.setToCurrency(dto.getToCurrency());
                    entity.setTimestamp(dto.getTimestamp());
                    entity.setAmount(dto.getAmount());
                    return entity;
                }).orElse(null);
    }
}
