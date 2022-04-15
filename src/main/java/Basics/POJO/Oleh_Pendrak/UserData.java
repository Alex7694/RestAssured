package Basics.POJO.Oleh_Pendrak;
import lombok.*;

@Data
public class UserData {
    private String lastName;
    private int id;
    private String avatar;
    private String firstName;
    private String email;


    public UserData(String lastName, int id, String avatar, String firstName, String email) {
        this.lastName = lastName;
        this.id = id;
        this.avatar = avatar;
        this.firstName = firstName;
        this.email = email;
    }

}
