package com.facelook.profile;

import com.facelook.post.Post;

import java.util.Set;

public interface Profile {

    String getName();

    Set<Profile> showFriends();

    Set<Post> showPosts();
}
