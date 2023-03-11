package template.factoryMethod.jisuanqi01;

//乘法工厂
public class MulFactory implements IFactory {

    public Operation createOperation(){
        return new Mul();
    }
    
}
