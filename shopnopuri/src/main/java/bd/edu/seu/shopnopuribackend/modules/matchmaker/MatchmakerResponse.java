package bd.edu.seu.shopnopuribackend.modules.matchmaker;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchmakerResponse {

    private String career;
    private List<String> matchedDepartmentKeywords;
    private List<Suggestion> suggestions;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Suggestion {
        private Long universityId;
        private String universityName;
        private Long departmentId;
        private String departmentName;
        private String category; // REACH / TARGET / SAFE
        private String note;
    }
}

