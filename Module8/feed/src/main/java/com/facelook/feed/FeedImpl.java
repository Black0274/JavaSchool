package com.facelook.feed;

import com.facelook.post.Post;
import com.facelook.profile.Profile;

import java.util.HashSet;
import java.util.Set;

public class FeedImpl implements Feed {

    @Override
    public Set<Post> show(Profile owner) {
        Set<Post> feed = new HashSet<>();
        for (Profile user : owner.showFriends()){
            feed.addAll(user.showPosts());
        }
        return feed;
    }
}
