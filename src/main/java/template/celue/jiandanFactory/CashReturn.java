package template.celue.jiandanFactory;

public class CashReturn extends CashSuper {

    private double moneyCondition = 0d; //返利条件
    private double moneyReturn = 0d;    //返利值

    //返利收费。初始化时需要输入返利条件和返利值。
    //比如“满300返100”，就是moneyCondition=300,moneyReturn=100
    public CashReturn(double moneyCondition,double moneyReturn){
        this.moneyCondition = moneyCondition;
        this.moneyReturn = moneyReturn;
    }

    //计算收费时，当达到返利条件，就原价减去返利值
    public double acceptCash(double price,int num){
        double result = price * num;
        if (moneyCondition>0 && result >= moneyCondition)
            result = result - Math.floor(result / moneyCondition) * moneyReturn; 
        return result;
    }
    
}
