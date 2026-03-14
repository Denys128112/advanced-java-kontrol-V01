package DomainEntities;

import PaymentOperations.PaymentMethod;

import java.util.*;

public class Order {
   private OrderItem[] items=new OrderItem[10];
   private final PaymentMethod paymentMethod;
   private int itemsInOrder=0;
   private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public Order(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public void addOrderItem(OrderItem item){
        if (item==null)
            throw new IllegalArgumentException("");
        if (itemsInOrder==items.length-1) {
            throw new IllegalArgumentException("Кошик повнй");
        }
        items[itemsInOrder]=item;
        itemsInOrder++;
    }

    public OrderItem[] getItems() {
        return items.clone();
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    @Override
    public String toString() {
        return "Order{" +
                "items=" + Arrays.toString(items) +
                ", paymentMethod=" + paymentMethod +
                ", itemsInOrder=" + itemsInOrder +
                '}';
    }
}
