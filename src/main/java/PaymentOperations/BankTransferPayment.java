package PaymentOperations;

import DomainEntities.Money;

public class BankTransferPayment implements PaymentMethod{

    @Override
    public void pay(Money sumOfMoney) {
        if(sumOfMoney.getCents()<=0)
            throw new IllegalArgumentException();
        double sumAfterfee;
        sumAfterfee = sale(sumOfMoney)*0.99;
        System.out.println("Payed by bank"+sumAfterfee);
    }
}
