package DomainEntities;

import PaymentOperations.PaymentMethod;

import java.util.Objects;

public class OrderItem {
    private final String id;
    private final String name;
    private Money costOfItem;

    public OrderItem(String name, String id,Money costOfItem) {
        if(name==null||name.isEmpty())
            throw new IllegalArgumentException();
        else {
        this.name= name;
        }
        if(id==null||id.isEmpty())
            throw new IllegalArgumentException();
        else
        this.id = id;
        this.costOfItem=costOfItem;
    }

    public String getId() {
        return id;
    }

    public Money getCostOfItem() {
        return costOfItem;
    }

    public String getName() {
        return name;
    }

    public void setCostOfItem(Money costOfItem) {
        this.costOfItem = costOfItem;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (this==o) return true;
        OrderItem orderItem = (OrderItem) o;

        return Objects.equals(id, orderItem.id) && Objects.equals(name, orderItem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", costOfItem=" + costOfItem +
                '}';
    }
}
