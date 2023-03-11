package template.zhuangshi.basic;

/**
 * Component是定义一个对象接口，可以给这些对象动态地添加职责。ConcreteComponent是定义了一个具体的对象，也可以给这个对象添加一些职责。
 * Decorator，装饰抽象类，继承了Component，从外类来扩展Component类的功能，但对于Component来说，
 * 是无须知道Decorator的存在的。至于ConcreteDecorator就是具体的装饰对象，起到给Component添加职责的功能
 */
public class Test {

	public static void main(String[] args){

		System.out.println("**********************************************");		
		System.out.println("《大话设计模式》代码样例");
		System.out.println();		

		ConcreteComponent c = new ConcreteComponent();
    	ConcreteDecoratorA d1 = new ConcreteDecoratorA();
    	ConcreteDecoratorB d2 = new ConcreteDecoratorB();

    	d1.SetComponent(c);	//首先用d1来包装c
    	d2.SetComponent(d1);//再用有来包装d1
    	d2.Operation();   	//最终执行d2的Operation()


		System.out.println();
		System.out.println("**********************************************");

	}
}

//Component类
abstract class Component {
    public abstract void Operation();
}


//ConcreteComponent类
class ConcreteComponent extends Component {
    
    public void Operation() {
        System.out.println("具体对象的实际操作");
    }

}

//Decorator类
abstract class Decorator extends Component {

    protected Component component;

    //装饰一个Component对象
    public void SetComponent(Component component) {
        this.component = component;
    }

    //重写Operation()，实际调用component的Operation方法
    public void Operation() {
        if (component != null) {
            component.Operation();
        }
    }
}

//ConcreteDecoratorA类
class ConcreteDecoratorA extends Decorator {
    
    private String addedState;//本类独有子段，以区别于ConcreteDecoratorB类

    public void Operation() {
        super.Operation();//首先运行了原有Component的Operation()

        this.addedState = "具体装饰对象A的独有操作";//再执行本类独有功能
        System.out.println(this.addedState);

    }
}

//ConcreteDecoratorB类
class ConcreteDecoratorB extends Decorator {

    public void Operation() {
        super.Operation();//首先运行了原有Component的Operation()
        this.AddedBehavior();//再执行本类独有功能
    }

    //本类独有方法，以区别于ConcreteDecoratorA类
    private void AddedBehavior() { 
    	System.out.println("具体装饰对象B的独有操作");
    }
}




