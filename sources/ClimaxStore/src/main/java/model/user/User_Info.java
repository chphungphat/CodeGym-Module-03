package model;

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
    private String lastName;
    private String firstName;
    private Date birthday;
    private int address_id;
}
