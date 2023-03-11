package template.abstractFactory.two;

/**
 * 实现IFactory接口，实例化SqlserverUser
 */
//Sqlserver工厂
public class SqlserverFactory implements IFactory {

    public IUser createUser(){
        return new SqlserverUser();
    }
    
}

