package com.facelook.feed;

import com.facelook.post.Post;
import com.facelook.profile.Profile;

import java.util.Set;

public interface Feed {

    Set<Post> show(Profile owner);
}
