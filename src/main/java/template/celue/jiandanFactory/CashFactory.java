package template.celue.jiandanFactory;

public class CashFactory {

    //收费工厂
    public static CashSuper createCashAccept(int cashType){
        CashSuper cs = null;
        switch (cashType) {
            case 1:
                cs = new CashNormal();      //正常收费
                break;
            case 2:
                cs = new CashRebate(0.8d);  //打八折
                break;
            case 3:
                cs = new CashRebate(0.7d);  //打七折
                break;
            case 4:
                cs = new CashReturn(300d,100d);//满300返100
                break;

        }
        return cs;
    }
    
}
