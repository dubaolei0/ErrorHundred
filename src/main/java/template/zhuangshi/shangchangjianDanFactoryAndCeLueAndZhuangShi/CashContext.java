package template.zhuangshi.shangchangjianDanFactoryAndCeLueAndZhuangShi;

public class CashContext {

    private ISale cs;   //声明一个ISale接口对象

    //通过构造方法，传入具体的收费策略
    public CashContext(int cashType){
        switch(cashType){
            case 1:
                this.cs = new CashNormal();
                break;
            case 2:
                this.cs = new CashRebate(0.8d);
                break;
            case 3:
                this.cs = new CashRebate(0.7d);
                break;
            case 4:
                this.cs = new CashReturn(300d,100d);
                break;
            case 5:
                //先打8折,再满300返100
                CashNormal cn = new CashNormal();
                CashReturn cr1 = new CashReturn(300d,100d); 
                CashRebate cr2 = new CashRebate(0.8d);
                cr1.decorate(cn);   //用满300返100算法包装基本的原价算法
                cr2.decorate(cr1);  //打8折算法装饰满300返100算法
                this.cs = cr2;      //将包装好的算法组合引用传递给cs对象
                break;
            case 6:
                //先满200返50，再打7折
                CashNormal cn2 = new CashNormal();
                CashRebate cr3 = new CashRebate(0.7d);
                CashReturn cr4 = new CashReturn(200d,50d); 
                cr3.decorate(cn2);  //用打7折算法包装基本的原价算法
                cr4.decorate(cr3);  //满200返50算法装饰打7折算法
                this.cs = cr4;      //将包装好的算法组合引用传递给cs对象
                break;
        }
    }

    public double getResult(double price,int num){
        //根据收费策略的不同，获得计算结果
        return this.cs.acceptCash(price,num);
    }    
}
