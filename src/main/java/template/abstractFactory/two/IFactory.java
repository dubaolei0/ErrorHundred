package template.abstractFactory.two;

/**
 * 定义一个创建访问User表对象的抽象的工厂接口
 */
//工厂接口
public interface IFactory {

    public IUser createUser();
    
}

