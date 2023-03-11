package template.abstractFactory.three;
import template.abstractFactory.three.IDepartment;

//工厂接口
public interface IFactory {

    public IUser createUser();

    public IDepartment createDepartment();
    
}

