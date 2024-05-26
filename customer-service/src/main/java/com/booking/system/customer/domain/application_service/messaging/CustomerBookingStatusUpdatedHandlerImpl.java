package com.booking.system.customer.domain.application_service.messaging;


import com.booking.system.commons.domain.core.event.CustomerBookingFailureStatusUpdateEvent;
import com.booking.system.commons.domain.core.event.CustomerBookingInitiatedEvent;
import com.booking.system.commons.domain.core.event.CustomerBookingStatusUpdatedEvent;
import com.booking.system.customer.domain.ports.api.mapper.CustomerUseCaseMapper;
import com.booking.system.customer.domain.ports.api.messaging.CustomerBookingStatusUpdatedHandler;
import com.booking.system.customer.domain.ports.api.usecase.InitializeCustomerBookingUseCase;
import com.booking.system.customer.domain.ports.api.usecase.UpdateCustomerBookingFailureStatusUseCase;
import com.booking.system.customer.domain.ports.api.usecase.UpdateCustomerBookingStatusUseCase;

public class CustomerBookingStatusUpdatedHandlerImpl implements CustomerBookingStatusUpdatedHandler {

    private final InitializeCustomerBookingUseCase initializeCustomerBookingUseCase;
    private final UpdateCustomerBookingStatusUseCase updateCustomerBookingStatusUseCase;
    private final UpdateCustomerBookingFailureStatusUseCase updateCustomerBookingFailureStatusUseCase;
    private final CustomerUseCaseMapper customerUseCaseMapper;

    public CustomerBookingStatusUpdatedHandlerImpl(
            final InitializeCustomerBookingUseCase initializeCustomerBookingUseCase,
            final UpdateCustomerBookingStatusUseCase updateCustomerBookingStatusUseCase,
            final CustomerUseCaseMapper customerUseCaseMapper,
            final UpdateCustomerBookingFailureStatusUseCase updateCustomerBookingFailureStatusUseCase
    ) {
        this.initializeCustomerBookingUseCase = initializeCustomerBookingUseCase;
        this.updateCustomerBookingStatusUseCase = updateCustomerBookingStatusUseCase;
        this.customerUseCaseMapper = customerUseCaseMapper;
        this.updateCustomerBookingFailureStatusUseCase = updateCustomerBookingFailureStatusUseCase;
    }

    @Override
    public void handle(final CustomerBookingStatusUpdatedEvent event) {

        switch (event) {
            case final CustomerBookingInitiatedEvent e -> {
                final var input = this.customerUseCaseMapper.customerBookingInitiatedEventToInitializeCustomerBookingInput(e);
                this.initializeCustomerBookingUseCase.execute(input);
            }
            case final CustomerBookingFailureStatusUpdateEvent e -> {
                final var input = this.customerUseCaseMapper.customerBookingFailureStatusUpdateEventToUpdateCustomerBookingFailureStatusInput(e);
                this.updateCustomerBookingFailureStatusUseCase.execute(input);
            }
            case final CustomerBookingStatusUpdatedEvent e -> {
                final var input = this.customerUseCaseMapper.customerBookingStatusUpdateEventToUpdateCustomerBookingStatusInput(e);
                this.updateCustomerBookingStatusUseCase.execute(input);
            }
        }
    }
}
