package DomainEntities;

public class Money {
    private Double cents;

    public Money(Double cents) {
        this.cents = cents;
    }

    public Double getCents() {
        return cents;
    }

    public void setCents(Double cents) {
        if(cents>0)
        this.cents = cents;
        else throw new IllegalArgumentException("cant Add minus Balance");
    }
}
