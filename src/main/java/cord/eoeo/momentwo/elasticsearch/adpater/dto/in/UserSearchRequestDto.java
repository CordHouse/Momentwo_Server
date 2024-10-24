package cord.eoeo.momentwo.elasticsearch.adpater.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchRequestDto {
    private String nickname;
    private int page;
    private int size;
}
