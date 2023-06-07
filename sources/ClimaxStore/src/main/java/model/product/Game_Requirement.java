package model.product;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Game_Requirement {
    private int gameRequire_id;
    private String os;
    private String ram;
    private String cpu;
    private String gpu;
    private String size;
}
