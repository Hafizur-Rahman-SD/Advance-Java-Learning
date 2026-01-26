package bd.edu.seu.shopnopuribackend.modules.match.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchRes {
    private String career;
    private List<String> keys;
    private List<MatchItem> items;
}
