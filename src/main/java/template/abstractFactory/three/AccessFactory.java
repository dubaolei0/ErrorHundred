package template.abstractFactory.three;
import template.abstractFactory.three.IDepartment;

import template.abstractFactory.three.AccessDepartment;
import template.abstractFactory.three.AccessUser;

//Access工厂
public class AccessFactory implements IFactory {

    public IUser createUser(){
        return new AccessUser();
    }

    public IDepartment createDepartment(){
        return new AccessDepartment();
    }

}


