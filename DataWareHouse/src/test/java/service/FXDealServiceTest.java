package service;

import jakarta.validation.ConstraintViolation;
import org.example.dto.FxDealDto;
import org.example.repository.FXDealRepository;
import org.example.service.FXDealService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SuppressWarnings("ALL")
@RunWith(MockitoJUnitRunner.class)
public class FXDealServiceTest {
    @Mock
    private FXDealRepository fxDealRepository;
    @Mock
    private LocalValidatorFactoryBean validator;
    @InjectMocks
    private FXDealService fxDealService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testValidateFxDealDto_ValidDto() {
        FxDealDto dealDto = new FxDealDto();

        when(validator.validate(dealDto)).thenReturn(Collections.emptySet());

        fxDealService.validateFxDealDto(dealDto);
    }


    @Test
    public void testSave_Success() {
        FxDealDto dealDto = new FxDealDto();

        when(fxDealRepository.save(any())).thenReturn(true);

        boolean result = fxDealService.save(dealDto);

        assertTrue(result);
    }

    @Test
    public void testSave_Failure() {
        FxDealDto dealDto = new FxDealDto();

        when(fxDealRepository.save(any())).thenThrow();

        boolean result = fxDealService.save(dealDto);

        assertFalse(result);
    }

    @Test
    public void testExists_True() {
        String uniqueId = "123";

        when(fxDealRepository.existsById(uniqueId)).thenReturn(false);
        boolean result = fxDealService.exists(uniqueId);

        assertTrue(result);
    }

    @Test
    public void testExists_False() {
        String uniqueId = "123";

        when(fxDealRepository.existsById(uniqueId)).thenReturn(false);

        boolean result = fxDealService.exists(uniqueId);

        assertFalse(result);
    }
}
