package template.factoryMethod.jisuanqi01;

//加法工厂
public class AddFactory implements IFactory {

    public Operation createOperation(){
        return new Add();
    }
    
}
