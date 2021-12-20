package kz.iitu.ead4.controllers;

import kz.iitu.ead4.models.User;
import kz.iitu.ead4.services.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    FriendService friendService;
    @Autowired
    private RestTemplate restTemplate;

    private final static String publicApi = "/public";
    private final static String privateApi = "/private";

    @GetMapping("addFriend")
    public ResponseEntity<?> addUser(@RequestParam("friendId")String friendId) throws NullPointerException{
        User currentUser = restTemplate.getForObject("http://user-api-app/user/public/" + friendId, User.class);
        friendService.saveFriend(currentUser,Integer.parseInt(friendId));
        return ResponseEntity.ok("Friend added successfully");
    }

    @GetMapping("listFriends/{userId}")
    public ResponseEntity<List<User>> getFriends(@PathVariable Long userId) {
        List<User> friends = friendService.getFriends(userId);
        return new ResponseEntity<List<User>>(friends, HttpStatus.OK);
    }
}
