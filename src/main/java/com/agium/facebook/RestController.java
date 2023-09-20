package com.agium.facebook;

import com.agium.facebook.data.ContaFacebook;
import com.agium.facebook.services.FacebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@org.springframework.web.bind.annotation.RestController
public class RestController
{
    private final FacebookService facebookServiceFacade;

    @Autowired
    public RestController(FacebookService facebookServiceFacade)
    {
        this.facebookServiceFacade = facebookServiceFacade;
    }

    @RequestMapping("/facebookData")
    public ContaFacebook getPlaces() throws IOException
    {
        return this.facebookServiceFacade.fetchFacebookData();
    }
}
