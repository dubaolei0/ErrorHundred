package template.abstractFactory.six;

//用户类
public class User {

    //用户ID
    private int _id;
    public int getId(){
        return this._id;
    }
    public void setId(int value){
        this._id=value;
    }

    //用户姓名
    private String _name;
    public String getName(){
        return this._name;
    }
    public void setName(String value){
        this._name=value;
    }
    
}
