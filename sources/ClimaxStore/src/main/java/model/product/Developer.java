package model.product;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Developer {
    private int developer_id;
    private String developerName;
}
