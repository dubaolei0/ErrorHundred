package template.factoryMethod.cel_zhuangs_factory;

public class CashContext {
    private ISale cs;   //声明一个ISale接口对象
    //通过构造方法，传入具体的收费策略
    public CashContext(int cashType){
        IFactory fs=null;
        switch(cashType) {
            case 1://原价
                fs = new CashRebateReturnFactory(1d,0d,0d);
                break;
            case 2://打8折
                fs = new CashRebateReturnFactory(0.8d,0d,0d);
                break;
            case 3://打7折
                fs = new CashRebateReturnFactory(0.7d,0d,0d);
                break;
            case 4://满300返100
                fs = new CashRebateReturnFactory(1,300d,100d);
                break;
            case 5://先打8折,再满300返100
                fs = new CashRebateReturnFactory(0.8d,300d,100d);
                break;
            case 6://先满200返50，再打7折
                fs = new CashReturnRebateFactory(0.7d,200d,50d);
                break;
        }
        this.cs = fs.createSalesModel();
    }

    public double getResult(double price,int num){
        //根据收费策略的不同，获得计算结果
        return this.cs.acceptCash(price,num);
    }    
}


