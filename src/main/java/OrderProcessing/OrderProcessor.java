package OrderProcessing;

import DomainEntities.Order;
import Exceptions.FullOrdereException;
import PaymentOperations.PaymentMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderProcessor extends OrderProcessorTemplate {
    private static final Logger logger = LoggerFactory.getLogger(OrderProcessor.class);
    private final PaymentMethod paymentMethod;
    private final Order order;
    private MofinalAmount;

    public OrderProcessor(PaymentMethod paymentMethod, Order order) {
        this.paymentMethod = paymentMethod;
        this.order = order;
    }

    @Override
    void validation() {
        logger.info("Validating order {}", order.getId());
        if (order.getItems().length > 10) {
            logger.warn("Validation failed: too many items");
            throw new RuntimeException("R1: Max 10 items allowed");
        }
    }

    @Override
    void rahunok() {
        double total = 0;
        for (var item : order.getItems()) total += item.getPrice();

        // Знижка 5% від 10_000
        if (total >= 10000) {
            total *= 0.95;
            logger.info("Discount applied. Final amount: {}", total);
        }
        finalAmount = total;
    }

    @Override
    void reserveStock() {
        logger.info("Reserving stock for order {}", order.getId());

    }

    @Override
    void pay() {
        try {
            paymentMethod.pay(finalAmount);
            order.setStatus("PAID");
            logger.info("Payment successful for order {}", order.getId());
        } catch (Exception e) {
            logger.error("Payment failed: {}", e.getMessage());
            throw new FullOrdereException("Payment error");
        }
    }

    @Override
    void finishing() {
        order.setStatus("SHIPPED");
        logger.info("Order {} finished and SHIPPED", order.getId());
    }
}