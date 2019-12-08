package com.facelook.profile;

import com.facelook.post.Post;
import com.facelook.post.PostImpl;
import content.Image;
import org.junit.Test;

import javafx.scene.paint.Color;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CurrentProfileImplTest {

    @Test
    public void register() {
        CurrentProfileImpl profile = CurrentProfileImpl.register("Dmitry Losev");
        assertEquals(profile.getName(), "Dmitry Losev");
    }

    @Test
    public void setName() {
        CurrentProfileImpl profile = CurrentProfileImpl.register("Dmitry Losev");
        String newName = "Vasily Pupkin";
        profile.setName(newName);
        assertEquals(profile.getName(), "Vasily Pupkin");
    }

    @Test
    public void addPost() {
        CurrentProfileImpl profile = CurrentProfileImpl.register("Dmitry Losev");
        Color[] colors = {Color.BLUE, Color.CYAN};
        Image photo = mock(Image.class);
        when(photo.getBitmap()).thenReturn(colors);
        PostImpl post = new PostImpl(profile.getName(), "Азовское море самое лучшее!", photo);
        Set<Post> posts = profile.showPosts();

        assertEquals(posts.size(), 0);
        profile.addPost(post);
        assertEquals(posts.size(), 1);
        assertTrue(posts.contains(post));
    }

    @Test
    public void deletePost() {
        CurrentProfileImpl profile = CurrentProfileImpl.register("Dmitry Losev");
        Color[] colors = {Color.BLUE, Color.CYAN};
        Image photo = mock(Image.class);
        when(photo.getBitmap()).thenReturn(colors);
        PostImpl post = new PostImpl(profile.getName(), "Азовское море самое лучшее!", photo);
        Set<Post> posts = profile.showPosts();

        assertEquals(posts.size(), 0);
        profile.addPost(post);
        assertEquals(posts.size(), 1);
        profile.deletePost(post);
        assertEquals(posts.size(), 0);
        assertFalse(posts.contains(post));
    }

    @Test
    public void addFriend() throws NoSuchFieldException, IllegalAccessException {
        CurrentProfileImpl me = CurrentProfileImpl.register("Dmitry Losev");
        CurrentProfileImpl friend = CurrentProfileImpl.register("Ivan Ostapenko");
        Field field = CurrentProfileImpl.class.getDeclaredField("friends");
        field.setAccessible(true);
        HashSet friends = (HashSet) field.get(me);

        assertEquals(friends.size(), 0);
        me.addFriend(friend);
        assertEquals(friends.size(), 1);
        assertTrue(friends.contains(friend));
    }

    @Test
    public void deleteFriend() throws NoSuchFieldException, IllegalAccessException {
        CurrentProfileImpl me = CurrentProfileImpl.register("Dmitry Losev");
        CurrentProfileImpl friend = CurrentProfileImpl.register("Ivan Ostapenko");
        Field field = CurrentProfileImpl.class.getDeclaredField("friends");
        field.setAccessible(true);
        HashSet friends = (HashSet) field.get(me);

        assertEquals(friends.size(), 0);
        me.addFriend(friend);
        assertEquals(friends.size(), 1);
        me.deleteFriend(friend);
        assertEquals(friends.size(), 0);
        assertFalse(friends.contains(friend));
    }

    @Test
    public void showFriends() {
        CurrentProfileImpl me = CurrentProfileImpl.register("Dmitry Losev");
        CurrentProfileImpl friend1 = CurrentProfileImpl.register("Ivan Ostapenko");
        CurrentProfileImpl friend2 = CurrentProfileImpl.register("Anna Platonova");
        CurrentProfileImpl friend3 = CurrentProfileImpl.register("Isaac Newton");

        me.addFriend(friend1);
        me.addFriend(friend2);
        me.addFriend(friend3);

        Set<Profile> friends = me.showFriends();
        assertEquals(friends.size(), 3);
        assertTrue(friends.contains(friend1));
        assertTrue(friends.contains(friend2));
        assertTrue(friends.contains(friend2));
    }

    @Test
    public void showPosts() {
        CurrentProfileImpl profile = CurrentProfileImpl.register("Dmitry Losev");
        Color[] colors = {Color.BLUE, Color.CYAN};
        Image photo = mock(Image.class);
        when(photo.getBitmap()).thenReturn(colors);
        PostImpl post1 = new PostImpl(profile.getName(), "Азовское море самое лучшее!", photo);
        PostImpl post2 = new PostImpl(profile.getName(), "Лучше Анапы курорта нет!", photo);

        profile.addPost(post1);
        profile.addPost(post2);

        Set<Post> posts = profile.showPosts();
        assertEquals(posts.size(), 2);
        assertTrue(posts.contains(post1));
        assertTrue(posts.contains(post2));
    }
}