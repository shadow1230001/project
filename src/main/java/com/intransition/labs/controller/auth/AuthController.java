package com.intransition.labs.controller.auth;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.intransition.labs.service.SecurityService;
import com.intransition.labs.service.UserService;
import com.intransition.labs.service.auth.AuthService;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Field;

@Controller
public class AuthController {

    @Autowired
    private Environment env;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Resource(name = "authServiceVK")
    private AuthService authServiceVK;

    @Resource(name = "authServiceFB")
    private AuthService authServiceFB;

    @Resource(name = "authServiceTwitter")
    private AuthService authServiceTwitter;

    @GetMapping(value = "/auth")
    public String auth(HttpServletRequest request, Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", env.getProperty("page.auth.login.failed"));

        if (logout != null)
            model.addAttribute("message", env.getProperty("page.auth.login.logout"));

        return "auth/auth";
    }

    @GetMapping(value = "/auth/vk")
    public String authViaVK(@RequestParam(name = "code", required = true) String code) throws ApiException, ClientException, IOException, JsonSyntaxException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        String appId = env.getProperty("spring.social.vk.app-id");
        String appSecret = env.getProperty("spring.social.vk.app-secret");
        String redirectURI = env.getProperty("spring.social.vk.redirect-uri");

        String oauth = "https://oauth.vk.com/access_token?client_id=" + appId + "&client_secret=" + appSecret + "&redirect_uri=" + redirectURI + "&code=" + code;

        TransportClient transportClient = new HttpTransportClient();
        String response = transportClient.post(oauth).getContent();

        VKResponse vkResponse = new Gson().fromJson(response, VKResponse.class);
        String email = vkResponse.email;

        VkApiClient vk = new VkApiClient(transportClient);
        UserAuthResponse authResponse = getAuthResponseFromJson(new Gson().fromJson(response, VKResponse.class));
        UserActor actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());

        if (email == null || email.isEmpty()) {
            return "redirect:/";
        }


        if (!userService.existsEmail(email)) {
            userService.registerByVK(email, vk, actor);
        }

        securityService.authorize(email, null);

        //TODO
        //authServiceVK.authorize(userName, password);

        return "redirect:/";
    }

    @GetMapping(value = "/auth/facebook")
    public String authViaFacebook(@RequestParam(name = "userName") String userName, @RequestParam(name = "password") String password) throws ApiException, ClientException, IOException, JsonSyntaxException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        authServiceFB.authorize(userName, password);
        return "redirect:/";
    }

    @GetMapping(value = "/auth/twitter")
    public String authViaTwitter(@RequestParam(name = "appp-id") String userName, @RequestParam(name = "app-secret") String password) throws ApiException, ClientException, IOException, JsonSyntaxException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {

//        authServiceTwitter.authorize(appId, appKey);
        return "redirect:https://twitter.com/vlyubin?lang=ru";
    }


    private UserAuthResponse getAuthResponseFromJson(VKResponse response) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        UserAuthResponse authResponse = new UserAuthResponse();

        Field accessToken = UserAuthResponse.class.getDeclaredField("accessToken");
        accessToken.setAccessible(true);
        accessToken.set(authResponse, response.access_token);

        Field userId = UserAuthResponse.class.getDeclaredField("userId");
        userId.setAccessible(true);
        userId.set(authResponse, response.user_id);

        Field expiresIn = UserAuthResponse.class.getDeclaredField("expiresIn");
        expiresIn.setAccessible(true);
        expiresIn.set(authResponse, response.expires_in);

        return authResponse;
    }

    private class VKResponse {

        public String access_token;

        public int expires_in;

        public int user_id;

        public String email;

    }

}
