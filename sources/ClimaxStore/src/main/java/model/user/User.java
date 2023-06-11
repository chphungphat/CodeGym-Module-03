package model.user;

import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private int user_id;
    private String user_name;
    private String phone;
    private String email;
    private String password;
    private Date created_date;
    private int position_id = 2;
}
