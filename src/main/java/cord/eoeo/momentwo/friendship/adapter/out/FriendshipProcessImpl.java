package cord.eoeo.momentwo.friendship.adapter.out;

import cord.eoeo.momentwo.friendship.advice.exception.NotFoundFriendshipRequestException;
import cord.eoeo.momentwo.friendship.application.port.out.FriendshipProcess;
import cord.eoeo.momentwo.friendship.application.port.out.FriendshipRepository;
import cord.eoeo.momentwo.friendship.domain.Friendship;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FriendshipProcessImpl implements FriendshipProcess {
    private final FriendshipRepository friendshipRepository;

    @Override
    @Transactional
    public void friendsRequest(User from, User to, boolean type) {
        Friendship newFriendship = new Friendship(
                from,
                to,
                type
        );
        friendshipRepository.save(newFriendship);
    }

    @Override
    @Transactional
    public void responseProcess(User responseUser, User requestUser, Boolean accept) {
        Friendship friendship = friendshipRepository.findByToUserAndFromUserAndAccept(
                responseUser,
                requestUser,
                false
        ).orElseThrow(NotFoundFriendshipRequestException::new);

        if(!accept) {
            deleteFriendship(responseUser, requestUser);
            return;
        }

        friendship.setAccept(true);
        friendshipRepository.save(friendship);
    }

    @Transactional
    public void deleteFriendship(User responseUser, User requestUser) {
        friendshipRepository.deleteByFromUserAndToUser(requestUser, responseUser);
    }
}