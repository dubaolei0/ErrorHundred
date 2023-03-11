package template.factoryMethod.cel_zhuangs_factory;

//先打折再满减类
public class CashRebateReturnFactory implements IFactory {
    
    private double moneyRebate = 1d;
    private double moneyCondition = 0d;
    private double moneyReturn = 0d;

    public CashRebateReturnFactory(double moneyRebate,double moneyCondition,double moneyReturn){
      this.moneyRebate=moneyRebate;
      this.moneyCondition=moneyCondition;
      this.moneyReturn=moneyReturn;
    }

    //先打x折,再满m返n
    public ISale createSalesModel(){
        
        CashNormal cn = new CashNormal();
        CashReturn cr1 = new CashReturn(this.moneyCondition,this.moneyReturn); 
        CashRebate cr2 = new CashRebate(this.moneyRebate);
        
        cr1.decorate(cn);   //用满m返n算法包装基本的原价算法
        cr2.decorate(cr1);  //打x折算法装饰满m返n算法
        return cr2;         //将包装好的算法组合返回
    }    
}


