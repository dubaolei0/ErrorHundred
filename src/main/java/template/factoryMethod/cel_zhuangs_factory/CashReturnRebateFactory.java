package template.factoryMethod.cel_zhuangs_factory;

//先满减再打折类
public class CashReturnRebateFactory implements IFactory {
    
    private double moneyRebate = 1d;
    private double moneyCondition = 0d;
    private double moneyReturn = 0d;

    public CashReturnRebateFactory(double moneyRebate,double moneyCondition,double moneyReturn){
      this.moneyRebate=moneyRebate;
      this.moneyCondition=moneyCondition;
      this.moneyReturn=moneyReturn;
    }

    //先满m返n,再打x折
    public ISale createSalesModel(){
        
        CashNormal cn2 = new CashNormal();
        CashRebate cr3 = new CashRebate(this.moneyRebate);
        CashReturn cr4 = new CashReturn(this.moneyCondition,this.moneyReturn); 

        cr3.decorate(cn2);  //用打x折算法包装基本的原价算法
        cr4.decorate(cr3);  //满m返n算法装饰打x折算法
        return cr4;         //将包装好的算法组合返回
    }    
}


