package PaymentOperations;

import DomainEntities.Money;

public class CardPayment implements PaymentMethod{
    @Override
    public void pay(Money sumOfMoney){
        if(sumOfMoney.getCents()>20000||sumOfMoney.getCents()<=0)
            throw new IllegalArgumentException();
        System.out.println("Payed by card"+sale(sumOfMoney));
    }

}
