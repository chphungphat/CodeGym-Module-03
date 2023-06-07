package model.product;

import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Game {
    private int game_id;
    private String gameName;
    private long price;
    private Date releaseDate;
    private List<Integer> categories_id;
    private int developer_id;
    private int gameDetail_id;
    private int gameRequire_id;
}
