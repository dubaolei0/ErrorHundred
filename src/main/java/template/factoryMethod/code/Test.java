package template.factoryMethod.code;

public class Test {

	public static void main(String[] args){

		System.out.println("**********************************************");		
		System.out.println("《大话设计模式》代码样例");
		System.out.println();		

		Creator[] creators = new Creator[2];
		creators[0] = new ConcreteCreatorA();
		creators[1] = new ConcreteCreatorB();

		for(Creator item : creators){
			Product product = item.factoryMethod();
			product.make();
		}

		System.out.println();
		System.out.println("**********************************************");

	}
}

//Product类
abstract class Product {
	public abstract void make();
}

//ConcreteProductA类
class ConcreteProductA extends Product {
	public void make(){
		System.out.println("产品A制造");
	}
}

//ConcreteProductB类
class ConcreteProductB extends Product {
	public void make(){
		System.out.println("产品B制造");
	}
}

//Creator类
abstract class Creator {
	public abstract Product factoryMethod();
}

class ConcreteCreatorA extends Creator{
	public Product factoryMethod(){
		return new ConcreteProductA();
	}
}

class ConcreteCreatorB extends Creator{
	public Product factoryMethod(){
		return new ConcreteProductB();
	}
}



