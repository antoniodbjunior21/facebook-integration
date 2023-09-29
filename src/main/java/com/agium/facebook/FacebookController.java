package com.agium.facebook;

import com.agium.facebook.data.FBWebhook;
import com.agium.facebook.data.UserBean;
import com.agium.facebook.services.FacebookService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@RestController
public class FacebookController {
    private final FacebookService facebookService;

    @Autowired
    public FacebookController(FacebookService facebookService) {
        this.facebookService = facebookService;
    }

    @GetMapping(value = "/facebook")
    public String validacaoFacebook(@RequestParam("hub.verify_token") String verifyToken, @RequestParam("hub.challenge") String challenge) {
        System.out.println(verifyToken);
        System.out.println(challenge);
        return challenge;
    }

    /**
     * https://developers.facebook.com/docs/graph-api/webhooks/getting-started/webhooks-for-leadgen
     * */
    @PostMapping(value = "/facebook")
    public void webhookFacebook(HttpServletRequest request) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(request.getInputStream());
        FBWebhook webhook = mapper.treeToValue(jsonNode, FBWebhook.class);
        System.out.println("received");
    }

    @GetMapping(value = "login")
    public RedirectView redirectWithUsingRedirectView() {
        String login =  this.facebookService.getLoginUrl();
        return new RedirectView(login);
    }

    @GetMapping(value = "user")
    public UserBean user(@RequestParam("token") String userToken) {
        return this.facebookService.getMe(userToken);
    }

    @GetMapping(value = "pages-tokens")
    public JsonNode pagesTokens(@RequestParam("accessToken") String userAccessToken, @RequestParam("userId") String userId) throws IOException {
        return this.facebookService.getPagesAccessTokens(userAccessToken, userId);
    }

    @GetMapping(value = "cadastrar-webhook")
    public JsonNode setWebhook(@RequestParam("pageId") String pageId, @RequestParam("pageToken") String pageToken) throws IOException {
        return this.facebookService.setWeebhookFor(pageId, pageToken);
    }

    @GetMapping(value = "apps-instalados")
    public JsonNode getPageInstaledApps(@RequestParam("pageId") String pageId, @RequestParam("pageToken") String pageToken) throws IOException {
        return this.facebookService.getPageInstaledApps(pageId, pageToken);
    }
}
