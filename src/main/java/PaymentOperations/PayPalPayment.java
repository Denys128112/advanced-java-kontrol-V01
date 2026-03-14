package PaymentOperations;

import DomainEntities.Money;

public class PayPalPayment implements PaymentMethod{
    @Override
    public void pay(Money sumOfMoney){
        if(sumOfMoney.getCents()<100)
            throw new IllegalArgumentException();
        System.out.println("Payed by paypal"+sale(sumOfMoney));
    }

}
