package template.abstractFactory.three;
import template.abstractFactory.three.User;
//用户类接口
public interface IUser {

    public void insert(User user);

    public User getUser(int id);
}
