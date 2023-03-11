package template.abstractFactory.three;

import template.abstractFactory.three.Department;

//部门类接口
public interface IDepartment {

    public void insert(Department department);

    public Department getDepartment(int id);
}

