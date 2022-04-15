package Basics.POJO.Oleh_Pendrak;
import lombok.*;

@Data
public class UserData {
    private String last_name;
    private Integer id;
    private String avatar;
    private String first_name;
    private String email;


    public UserData(String lastName, int id, String avatar, String firstName, String email) {
        this.last_name = lastName;
        this.id = id;
        this.avatar = avatar;
        this.first_name = firstName;
        this.email = email;
    }
}
