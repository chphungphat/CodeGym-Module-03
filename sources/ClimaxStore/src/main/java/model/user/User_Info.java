package model.user;

import lombok.*;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User_Info {
    private int userInfo_id;
    private int user_id;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String gender;
}
