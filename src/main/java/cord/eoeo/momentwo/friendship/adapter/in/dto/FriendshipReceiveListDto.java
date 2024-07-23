package cord.eoeo.momentwo.friendship.adapter.in.dto;

import cord.eoeo.momentwo.friendship.domain.Friendship;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipReceiveListDto {
    private String nickname;
    public FriendshipReceiveListDto toDo(Friendship friendship) {
        return new FriendshipReceiveListDto(friendship.fromUser.getNickname());
    }
}
