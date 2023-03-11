package template.bridge.basic;

public class Test {

    public static void main(String[] args) {
        Abstraction ab = new RefinedAbstraction();

        ab.setImplementor(new ConcreteImplementorA());
        ab.operation();

        ab.setImplementor(new ConcreteImplementorB());
        ab.operation();
    }
}

abstract class Implementor{
    public abstract void operation();
}


class ConcreteImplementorA extends Implementor{
    public void operation(){
        System.out.println("具体实现A的方法执行");
    }
}

class ConcreteImplementorB extends Implementor{
    public void operation(){
        System.out.println("具体实现B的方法执行");
    }
}


abstract class Abstraction{
    protected Implementor implementor;

    public void setImplementor(Implementor implementor){
        this.implementor = implementor;
    }

    public abstract void operation();
}

class RefinedAbstraction extends Abstraction{
    public void operation(){
        System.out.print("具体的Abstraction");
        implementor.operation();
    }
}




