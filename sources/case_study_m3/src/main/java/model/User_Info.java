package model;

import lombok.*;

@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User_Info {
    private int userInfo_id;
    private int user_id;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
}
