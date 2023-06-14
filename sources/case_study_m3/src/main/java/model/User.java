package model;

import lombok.*;

import java.util.Date;

@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    private int user_id;
    private String user_name;
    private String password;
    private String email;
    private String phone;
    private Date birthDay;
    private String position;
    private Date created_date;
}
