package controller;


import org.example.controller.FXDealController;
import org.example.dto.FxDealDto;
import org.example.service.FXDealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class FXDealControllerTest {

    @Mock
    private FXDealService fxDealService;
    @InjectMocks
    private FXDealController fxDealController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void SuccessfulDealImport_Test() {
        // Creating a valid deal
        FxDealDto deal = new FxDealDto(UUID.randomUUID().toString(),
                "USD",
                "UK",
                LocalDateTime.now(),
                100);

        // Mock the service methods
        when(fxDealService.exists(anyString())).thenReturn(false);
        when(fxDealService.save(deal)).thenReturn(true);

        // Call the controller method
        fxDealController.importDeal(deal);

        // Verify that the service methods were called
        verify(fxDealService).validateFxDealDto(deal);
        verify(fxDealService).exists(deal.getUniqueId());
        verify(fxDealService).save(deal);
    }

    @Test
    public void ValidationFailureDealImport_Test() {
        // Creating a valid deal
        FxDealDto deal = new FxDealDto("",
                "JOD",
                "UK",
                LocalDateTime.now(),
                99);

        doThrow(new IllegalArgumentException("Validation failed"))
                .when(fxDealService)
                .validateFxDealDto(deal);

        fxDealController.importDeal(deal);

        verify(fxDealService).validateFxDealDto(deal);

        verify(fxDealService, never()).exists(anyString());
        verify(fxDealService, never()).save(any());
    }

    @Test
    public void ExistingDealImport_Test() {
        FxDealDto deal = new FxDealDto(UUID.randomUUID().toString(),
                "USD",
                "JOD",
                LocalDateTime.now(),
                83);

        when(fxDealService.exists(deal.getUniqueId())).thenReturn(true);

        fxDealController.importDeal(deal);

        verify(fxDealService).validateFxDealDto(deal);
        verify(fxDealService).exists(deal.getUniqueId());

        verify(fxDealService, never()).save(any());
    }


}
