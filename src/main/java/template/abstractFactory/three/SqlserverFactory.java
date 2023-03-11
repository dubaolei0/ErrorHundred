package template.abstractFactory.three;

import template.abstractFactory.three.IDepartment;
import template.abstractFactory.three.SqlserverDepartment;

//Sqlserver工厂
public class SqlserverFactory implements IFactory {

    public IUser createUser(){
        return new SqlserverUser();
    }
    
    public IDepartment createDepartment(){
        return new SqlserverDepartment();
    }
    
}

