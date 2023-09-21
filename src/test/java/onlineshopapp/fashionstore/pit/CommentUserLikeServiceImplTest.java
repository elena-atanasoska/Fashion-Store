package onlineshopapp.fashionstore.pit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import onlineshopapp.fashionstore.model.ClothesComment;
import onlineshopapp.fashionstore.model.CommentUserLike;
import onlineshopapp.fashionstore.model.User;
import onlineshopapp.fashionstore.repository.CommentUserLikeRepository;
import onlineshopapp.fashionstore.service.impl.CommentUserLikeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CommentUserLikeServiceImplTest {

    @Mock
    private CommentUserLikeRepository commentUserLikeRepository;

    @InjectMocks
    private CommentUserLikeServiceImpl commentUserLikeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateOrUpdate() {
        User user = new User();
        ClothesComment clothesComment = new ClothesComment();
        Optional<CommentUserLike> commentUserLike = Optional.of(new CommentUserLike());
        int like = 1;
        int dislike = 0;

        CommentUserLike result = commentUserLikeService.createOrUpdate(like, dislike, user, clothesComment, commentUserLike);

        assertNotNull(result);
        verify(commentUserLikeRepository).save(result);
    }

    @Test
    public void testGetTotalLikesFromComment() {
        ClothesComment clothesComment = new ClothesComment();
        CommentUserLike like1 = new CommentUserLike(1, 0, new User(), clothesComment);
        CommentUserLike like0 = new CommentUserLike(0, 0, new User(), clothesComment);
        List<CommentUserLike> commentUserLikes = List.of(like1, like0);

        when(commentUserLikeRepository.findCommentUserLikesByClothesComment(clothesComment)).thenReturn(commentUserLikes);

        int result = commentUserLikeService.getTotalLikesFromComment(clothesComment);

        assertEquals(1, result);
    }

    @Test
    public void testGetTotalDislikesFromComment() {
        ClothesComment clothesComment = new ClothesComment();
        CommentUserLike dislike1 = new CommentUserLike(0, 1, new User(), clothesComment);
        CommentUserLike dislike0 = new CommentUserLike(0, 0, new User(), clothesComment);
        List<CommentUserLike> commentUserLikes = List.of(dislike1, dislike0);

        when(commentUserLikeRepository.findCommentUserLikesByClothesComment(clothesComment)).thenReturn(commentUserLikes);

        int result = commentUserLikeService.getTotalDislikesFromComment(clothesComment);

        assertEquals(1, result);
    }
}
