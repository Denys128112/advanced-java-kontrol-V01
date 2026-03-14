package PaymentOperations;

import DomainEntities.Money;
import Exceptions.PaymentException;

public class PayPalPayment implements PaymentMethod{
    @Override
    public void pay(Money sumOfMoney){
        if(sumOfMoney.getCents()<100)
            throw new PaymentException("");
        System.out.println("Payed by paypal"+sale(sumOfMoney));
    }

}
