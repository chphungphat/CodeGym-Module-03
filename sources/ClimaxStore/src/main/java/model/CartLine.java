package model;

import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CartLine {
    private int cart_id;
    private int user_id;
    private int game_id;
    private long price;
    private Date check_out_date;
}
