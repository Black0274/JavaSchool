package com.facelook.profile;

import com.facelook.feed.Feed;
import com.facelook.post.Post;

public interface CurrentProfile extends Profile {

    void setName(String name);

    void addPost(Post post);

    void deletePost(Post post);

    void addFriend(Profile friend);

    void deleteFriend(Profile friend);

    Feed getFeed();
}
