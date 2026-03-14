package PaymentOperations;

import DomainEntities.Money;

public interface PaymentMethod {

    void pay(Money sumOfMoney);
    default double sale(Money sumOfmoney){
        if(sumOfmoney.getCents()>10000)
            return sumOfmoney.getCents()*0.95;
        else
            return sumOfmoney.getCents();
    }

}
