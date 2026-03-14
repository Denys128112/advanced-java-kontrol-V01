package PaymentOperations;

import DomainEntities.Money;
import Exceptions.PaymentException;

public class CardPayment implements PaymentMethod{
    @Override
    public void pay(Money sumOfMoney){
        if(sumOfMoney.getCents()>20000||sumOfMoney.getCents()<=0)
            throw new PaymentException("");
        System.out.println("Payed by card"+sale(sumOfMoney));
    }

}
