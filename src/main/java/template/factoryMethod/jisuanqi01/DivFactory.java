package template.factoryMethod.jisuanqi01;

//除法工厂
public class DivFactory implements IFactory {

    public Operation createOperation(){
        return new Div();
    }
    
}
