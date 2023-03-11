package template.zhuangshi.shangchangjianDanFactoryAndCeLue;

public class CashReturnRebate extends CashSuper {

    private double moneyRebate = 1d;
    private double moneyCondition = 0d; //返利条件
    private double moneyReturn = 0d;    //返利值

    //先折扣，再返利。初始化时需要折扣参数，再输入返利条件和返利值。
    //比如“先8折，再满300返100”，就是moneyRebate=0.8,moneyCondition=300,moneyReturn=100
    public CashReturnRebate(double moneyRebate,double moneyCondition,double moneyReturn){
        this.moneyRebate = moneyRebate;
        this.moneyCondition = moneyCondition;
        this.moneyReturn = moneyReturn;
    }

    //先折扣，再返利
    public double acceptCash(double price,int num){
        double result = price * num * this.moneyRebate;
        if (moneyCondition>0 && result >= moneyCondition)
            result = result - Math.floor(result / moneyCondition) * moneyReturn; 
        return result;
    }
    
}
