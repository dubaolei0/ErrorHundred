package template.adapter.basic;

public class Test {
	
	public static void main(String[] args){
        Target target = new Adapter();
        target.request();
	}
}

//客户期待的接口
class Target {
    public void request(){
        System.out.println("普通请求！");
    }
}

//需要适配的类
class Adaptee {
    public void specificRequest(){
        System.out.println("特殊请求！");
    }
}

//适配器类
class Adapter extends Target {
    //建立一个私有的Adaptee对象
    private Adaptee adaptee = new Adaptee();

    public void request(){          //这样就可以把表面上调用request()方法
        adaptee.specificRequest();  //变成实际调用specificRequest()
    }
}






