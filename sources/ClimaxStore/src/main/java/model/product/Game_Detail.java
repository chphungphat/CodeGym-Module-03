package model.product;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Game_Detail {
    private int gameDetail_id;
    private String gameDescription;
    private String gameImage;
}
