package template.abstractFactory.three;

import template.abstractFactory.three.Department;
public class AccessDepartment implements template.abstractFactory.three.IDepartment {

    //新增一个部门
    public void insert(Department department){
        System.out.println("在Access中给Department表增加一条记录");     
    }

    //获取一个部门信息
    public Department getDepartment(int id){
        System.out.println("在Access中根据部门ID得到Department表一条记录");   
        return null;  
    }

}

