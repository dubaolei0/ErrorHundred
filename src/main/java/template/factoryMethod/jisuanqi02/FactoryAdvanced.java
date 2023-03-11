package template.factoryMethod.jisuanqi02;

//高级运算工厂
public class FactoryAdvanced implements IFactory {

    public Operation createOperation(String operType){
        Operation oper = null;
        switch (operType) {
            case "pow":
                oper = new Pow();//指数运算类实例
                break;
            case "log":
                oper = new Log();//对数运算类实例
                break;

            //此处可扩展其他高级运算类的实例化，但修改
            //当前工厂类不会影响到基础运算工厂类

        }
                
        return oper;
    }    
}





