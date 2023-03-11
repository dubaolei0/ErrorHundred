package template.abstractFactory.two;

/**
 * 用于客户端访问，解除与具体数据库访问的耦合
 */
//用户类接口
public interface IUser {

    public void insert(User user);

    public User getUser(int id);
}

