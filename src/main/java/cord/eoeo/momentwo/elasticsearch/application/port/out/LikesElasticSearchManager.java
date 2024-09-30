package cord.eoeo.momentwo.elasticsearch.application.port.out;

import cord.eoeo.momentwo.elasticsearch.domain.LikesDocument;
import cord.eoeo.momentwo.user.domain.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Configuration
public interface LikesElasticSearchManager {
    void save(User user, long photoId);
    Page<LikesDocument> getPhotoLikesPaging(long photoId, Pageable pageable);
    void deleteByLikes(long id, String nickname);
    boolean isLikes(User user, long photoId);
}