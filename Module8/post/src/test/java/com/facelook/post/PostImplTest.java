package com.facelook.post;

import org.junit.Test;

import static org.junit.Assert.*;

public class PostImplTest {

    @Test
    public void publish() {
        String me = "Dmitry Losev";
        Post mypost = new PostImpl(me, "Азовское море самое лучшее!", null);
        assertEquals(mypost.publish(), "post from Dmitry Losev with message Азовское море самое лучшее! was published");
    }
}