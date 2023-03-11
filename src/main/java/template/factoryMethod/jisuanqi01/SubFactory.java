package template.factoryMethod.jisuanqi01;

//减法工厂
public class SubFactory implements IFactory {

    public Operation createOperation(){
        return new Sub();
    }
    
}
