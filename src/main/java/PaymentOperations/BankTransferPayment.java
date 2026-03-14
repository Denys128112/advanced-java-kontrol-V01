package PaymentOperations;

import DomainEntities.Money;
import Exceptions.PaymentException;

public class BankTransferPayment implements PaymentMethod{

    @Override
    public void pay(Money sumOfMoney) {
        if(sumOfMoney.getCents()<=0)
            throw new PaymentException("");
        double sumAfterfee;
        sumAfterfee = sale(sumOfMoney)*0.99;
        System.out.println("Payed by bank"+sumAfterfee);
    }
}
