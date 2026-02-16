package com.example.backend.customers.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import com.example.backend.customers.application.CreateCustomerCommand;
import com.example.backend.customers.application.CreateCustomerResult;
import com.example.backend.customers.application.CreateCustomerUseCase;
import com.example.backend.customers.application.CustomerView;
import com.example.backend.customers.application.ListCustomersUseCase;
import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

/**
 * Pruebas unitarias para los endpoints expuestos por CustomersController.
 *
 * - Se mockean las dependencias CreateCustomerUseCase y ListCustomersUseCase
 * - Se comprueba la respuesta del controlador sin arrancar el contexto de Spring
 *
 * Nota: estos tests representan pruebas unitarias puras (llamadas directas a los métodos del controlador)
 * y utilizan JUnit 5 + Mockito conforme a lo discutido.
 */
@ExtendWith(MockitoExtension.class)
class CustomersControllerTest {

    @Mock
    private CreateCustomerUseCase createCustomer;

    @Mock
    private ListCustomersUseCase listCustomers;

    @InjectMocks
    private CustomersController controller;

    @BeforeEach
    void setUp() {
        // no-op (Mockito injecta los mocks)
    }

    @Test
    void newCustomer_shouldReturnCreatedResponse_andCallCreateUseCase() {
        // Arrange
        CreateCustomerRequest req = new CreateCustomerRequest();
        req.setName("Pedro");
        req.setEmail("pedro@example.com");

        CustomerView created = CustomerView.builder()
                .id("abc-123")
                .name("Pedro")
                .email("pedro@example.com")
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        when(createCustomer.handle(any(CreateCustomerCommand.class))).thenReturn(
            CreateCustomerResult.created(created));


        // Act
        ResponseEntity<CustomerResponse> response = (ResponseEntity<CustomerResponse>) controller.create(req,null);

        // Assert - Status/headers/body
        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
        assertTrue(response.getHeaders().getLocation().toString().contains("/customers/abc-123"));
        assertNotNull(response.getBody());
        assertEquals("abc-123", response.getBody().getId());
        assertEquals("Pedro", response.getBody().getName());

        // Verify interacción con el use case y capturar el comando enviado
        ArgumentCaptor<CreateCustomerCommand> captor = ArgumentCaptor.forClass(CreateCustomerCommand.class);
        verify(createCustomer, times(1)).handle(captor.capture());
        CreateCustomerCommand sent = captor.getValue();
        assertEquals("Pedro", sent.getName());
        assertEquals("pedro@example.com", sent.getEmail());
    }

    @Test
    void listCustomers_shouldReturnListMappedToResponse() {
        // Arrange
        CustomerView a = CustomerView.builder().id("1").name("A").email("a@e.com").createdAt(Instant.now()).updatedAt(Instant.now()).build();
        CustomerView b = CustomerView.builder().id("2").name("B").email("b@e.com").createdAt(Instant.now()).updatedAt(Instant.now()).build();

        when(listCustomers.handle()).thenReturn(List.of(a, b));

        // Act
        List<CustomerResponse> result = controller.list();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("1", result.get(0).getId());
        assertEquals("A", result.get(0).getName());
        assertEquals("2", result.get(1).getId());
        assertEquals("B", result.get(1).getName());

        verify(listCustomers, times(1)).handle();
    }
}
