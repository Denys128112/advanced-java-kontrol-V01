
import OrderProcessing.OrderProcessor;
import DomainEntities.*;
import Exceptions.*;
import PaymentOperations.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class OrderProcessingTest {

    private PaymentMethod cardPayment;
    private PaymentMethod paypalPayment;

    @BeforeEach
    void setUp() {
        cardPayment = new CardPayment();
        paypalPayment = new PayPalPayment();
    }

    @Test
    void testProcessSuccessfulCardPayment() {
        Order order = new Order(cardPayment);
        order.setId("ORD-001");
        order.addOrderItem(new OrderItem("Smartphone", "ID-1", new Money(15000.0)));

        OrderProcessor processor = new OrderProcessor(cardPayment, order);
        assertEquals("SHIPPED", order.getStatus());
    }

    @Test
    void testDiscountFivePercentOverTenThousand() {
        Order order = new Order(cardPayment);
        order.addOrderItem(new OrderItem("Laptop", "ID-2", new Money(12000.0)));

        OrderProcessor processor = new OrderProcessor(cardPayment, order);
        processor.process();

        assertEquals("SHIPPED", order.getStatus());
    }

    @Test
    void testOrderLimitValidation() {
        Order order = new Order(cardPayment);
        for (int i = 0; i < 9; i++) {
            order.addOrderItem(new OrderItem("Item" + i, "SN" + i, new Money(100.0)));
        }

        assertThrows(IllegalArgumentException.class, () -> {
            order.addOrderItem(new OrderItem("Extra", "SN-99", new Money(100.0)));
        });
    }

    @Test
    void testCardPaymentLimitExceeded() {
        Order order = new Order(cardPayment);
        order.addOrderItem(new OrderItem("HighEndPC", "ID-3", new Money(25000.0)));

        OrderProcessor processor = new OrderProcessor(cardPayment, order);
        assertThrows(AppException.class, processor::process);
    }

    @Test
    void testPayPalMinimumAmountViolation() {
        Order order = new Order(paypalPayment);
        order.addOrderItem(new OrderItem("Cable", "ID-4", new Money(50.0)));

        OrderProcessor processor = new OrderProcessor(paypalPayment, order);
        assertThrows(AppException.class, processor::process);
    }

    @Test
    void testInvalidEmailFormat() {
        assertThrows(IllegalArgumentException.class, () -> new Email("wrong_email.com"));
    }

    @Test
    void testNegativeMoneyAmount() {
        assertThrows(IllegalArgumentException.class, () -> new Money(-50.0));
    }

    @Test
    void testOrderDefensiveCopy() {
        Order order = new Order(cardPayment);
        OrderItem item = new OrderItem("Original", "1", new Money(100.0));
        order.addOrderItem(item);

        OrderItem[] items = order.getItems();
        items[0] = null;

        assertNotNull(order.getItems()[0]);
    }

    @ParameterizedTest
    @ValueSource(doubles = {200.0, 1000.0, 5000.0})
    void testPayPalValidRange(double amount) {
        Order order = new Order(paypalPayment);
        order.addOrderItem(new OrderItem("Product", "P-ID", new Money(amount)));

        OrderProcessor processor = new OrderProcessor(paypalPayment, order);
        assertEquals("SHIPPED", order.getStatus());
    }

}