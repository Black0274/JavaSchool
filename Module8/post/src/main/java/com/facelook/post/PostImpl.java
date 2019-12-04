package com.facelook.post;

import com.facelook.profile.Profile;
import content.Content;
import content.Image;

public class PostImpl implements Post {

    private Profile owner;
    private String text;
    private Content content;

    public PostImpl(Profile owner, String text, Content content){
        this.owner = owner;
        this.text = text;
        this.content = content;
    }

    @Override
    public String publish() {
        return "post from " + owner.getName() + " with message " + text +" was published";
    }

    @Override
    public Profile getOwner() {
        return owner;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void rewriteText(String text) {
        this.text = text;
    }

    @Override
    public Content getContent() {
        return content;
    }
}
