package com.facelook.post;

import content.Content;

public interface Post {

    String publish();

    String getOwnerName();

    String getText();

    void rewriteText(String text);

    Content getContent();

}
