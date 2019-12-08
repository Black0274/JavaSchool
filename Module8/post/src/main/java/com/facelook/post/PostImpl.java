package com.facelook.post;

import content.Content;

public class PostImpl implements Post {

    private String ownerName;
    private String text;
    private Content content;

    public PostImpl(String ownerName, String text, Content content){
        this.ownerName = ownerName;
        this.text = text;
        this.content = content;
    }

    @Override
    public String publish() {
        return "post from " + ownerName + " with message " + text +" was published";
    }

    @Override
    public String getOwnerName() {
        return ownerName;
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
