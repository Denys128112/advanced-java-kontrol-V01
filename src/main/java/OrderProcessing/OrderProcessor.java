package OrderProcessing;

import DomainEntities.Money;
import DomainEntities.Order;
import DomainEntities.OrderItem;
import Exceptions.AppException;
import Exceptions.FullOrdereException;
import Exceptions.PaymentException;
import PaymentOperations.PaymentMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderProcessor extends OrderProcessorTemplate {
    private static final Logger logger = LoggerFactory.getLogger(OrderProcessor.class);
    private final PaymentMethod paymentMethod;
    private final Order order;
    private Money finalAmount;

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
        for (OrderItem item : order.getItems())
        total += item.getCostOfItem().getCents();
        finalAmount.setCents(total);
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
        } catch (PaymentException e) {
            logger.error("Payment failed: {}", e.getMessage());
            throw new AppException("Payment error",e);
        }
    }

    @Override
    void finishing() {
        order.setStatus("SHIPPED");
        logger.info("Order {} finished and SHIPPED", order.getId());
    }
}