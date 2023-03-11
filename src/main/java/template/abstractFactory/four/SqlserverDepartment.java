package template.abstractFactory.four;

public class SqlserverDepartment implements IDepartment {

    //新增一个部门
    public void insert(Department department){
        System.out.println("在SQL Server中给Department表增加一条记录");     
    }

    //获取一个部门信息
    public Department getDepartment(int id){
        System.out.println("在SQL Server中根据部门ID得到Department表一条记录");   
        return null;  
    }

}