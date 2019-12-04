package com.facelook.post;

import com.facelook.profile.Profile;
import content.Content;

public interface Post {

    String publish();

    Profile getOwner();

    String getText();

    void rewriteText(String text);

    Content getContent();

}
