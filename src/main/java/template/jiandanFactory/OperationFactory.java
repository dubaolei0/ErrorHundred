package template.jiandanFactory;

public class OperationFactory {

    public static Operation4 createOperate(String operate){
        Operation4 oper = null;
        switch (operate) {
            case "+":
                oper = new Add();
                break;
            case "-":
                oper = new Sub();
                break;
            case "*":
                oper = new Mul();
                break;
            case "/":
                oper = new Div();
                break;
        }
        return oper;
    }
    
}
