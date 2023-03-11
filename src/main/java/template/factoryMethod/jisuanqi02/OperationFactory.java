package template.factoryMethod.jisuanqi02;

public class OperationFactory {

    public static Operation createOperate(String operate){
        Operation oper = null;
        IFactory factory = null;
        switch (operate) {
            case "+":
            case "-":
            case "*":
            case "/":
                //基础运算工厂实例
                factory=new FactoryBasic();
                break;
            case "pow":
            case "log":
                //高级运算工厂实例
                factory=new FactoryAdvanced();
                break;
        }  
        //利用多态返回实际的运算类实例
        oper = factory.createOperation(operate);
        return oper;
    }
}













