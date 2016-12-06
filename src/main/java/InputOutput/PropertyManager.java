package InputOutput;

/**
 * (╯°□°）╯︵ ┻━┻
 * Created by Zhufyak V.V.
 * zhufyakvv@gmail.com
 * github.com/zhufyakvv
 * 16.11.2016
 **/
public class PropertyManager {
    String url;
    String user;
    String password;
    public PropertyManager(){
        url = "jdbc:mysql://localhost:3306/hospital_schema";
        user = "root";
        password = "1357";
    }

    public PropertyManager(String url, String user, String pass){
        this.url = url;
        this.user = user;
        this.password = pass;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
