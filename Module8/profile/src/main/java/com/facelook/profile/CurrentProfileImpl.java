package com.facelook.profile;

import com.facelook.post.Post;
import java.util.HashSet;
import java.util.Set;

public class CurrentProfileImpl implements CurrentProfile {

    private String name;
    private Set<Profile> friends = new HashSet<>();
    private Set<Post> posts = new HashSet<>();

    private CurrentProfileImpl(String name){
        this.name = name;
    }

    public static CurrentProfileImpl register(String name){
        return new CurrentProfileImpl(name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void addPost(Post post) {
        posts.add(post);
    }

    @Override
    public void deletePost(Post post) {
        posts.remove(post);
    }

    @Override
    public void addFriend(Profile friend) {
        friends.add(friend);
    }

    @Override
    public void deleteFriend(Profile friend) {
        friends.remove(friend);
    }

    @Override
    public Set<Profile> showFriends() {
        return friends;
    }

    @Override
    public Set<Post> showPosts() {
        return posts;
    }
}
