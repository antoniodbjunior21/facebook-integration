package com.agium.facebook.services;

import com.agium.facebook.data.ContaFacebook;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FacebookService {

    public static final String FQL_ME_TAGGED_PLACES = "/me?fields=id,name";

    @Value("${access.token:test}")
    private String MY_ACCESS_TOKEN;

    public ContaFacebook fetchFacebookData() {
        FacebookClient facebookClient = new DefaultFacebookClient(MY_ACCESS_TOKEN, Version.LATEST);

        User user = facebookClient.fetchObject("me", User.class);

        /* Page page = facebookClient.fetchObject("cocacola", Page.class,
           Parameter.withFields("fan_count"));*/

        ContaFacebook contaFacebook = new ContaFacebook();
        contaFacebook.id = user.getId();
        contaFacebook.name = user.getName();
        return contaFacebook;
    }
}
