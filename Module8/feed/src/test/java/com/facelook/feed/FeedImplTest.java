package com.facelook.feed;

import com.facelook.post.Post;
import com.facelook.post.PostImpl;
import com.facelook.profile.CurrentProfile;
import com.facelook.profile.CurrentProfileImpl;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class FeedImplTest {

    @Test
    public void show() {
        CurrentProfile me = CurrentProfileImpl.register("Dmitry Losev");
        CurrentProfile user1 = CurrentProfileImpl.register("Kirill Yakovlev");
        CurrentProfile user2 = CurrentProfileImpl.register("Pavel Bondarenko");
        me.addFriend(user1);
        me.addFriend(user2);
        Post mypost = new PostImpl(me.getName(), "Азовское море самое лучшее!", null);
        me.addPost(mypost);
        Post post1 = new PostImpl(user1.getName(), "Улочки Рима", null);
        Post post2 = new PostImpl(user1.getName(), "Статуя Христа-Искупителя", null);
        user1.addPost(post1);
        user1.addPost(post2);
        Post post3 = new PostImpl(user2.getName(), "Мост Золотые Ворота", null);
        Post post4 = new PostImpl(user2.getName(), "Ночной Токио", null);
        user2.addPost(post3);
        user2.addPost(post4);

        FeedImpl myfeed = new FeedImpl(me);
        Set<Post> feed = myfeed.show();

        assertEquals(feed.size(), 4);
        assertTrue(feed.contains(post1));
        assertTrue(feed.contains(post2));
        assertTrue(feed.contains(post3));
        assertTrue(feed.contains(post4));
        assertFalse(feed.contains(mypost));
    }
}