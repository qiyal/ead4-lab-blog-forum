package kz.iitu.ead4.services;

import kz.iitu.ead4.models.Friend;
import kz.iitu.ead4.models.User;
import kz.iitu.ead4.repositories.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FriendService {
    @Autowired
    FriendRepository friendRepository;
    @Autowired
    private RestTemplate restTemplate;

    public void saveFriend(User user1, int id) throws NullPointerException{
//        User user = userRepository.getOne(id);
//        UserDto userDto2 = modelMapper.map(user,UserDto.class);

        Friend friend = new Friend();
        User user2 = restTemplate.getForObject("http://user-api-app/user/public/" + id, User.class); // by id

        User firstuser = user1;
        User seconduser = user2;

        if(user1.getId() > user2.getId()){
            firstuser = user2;
            seconduser = user1;
        }
        if( !(friendRepository.existsByFirstUserAndSecondUser(firstuser,seconduser)) ){
            friend.setCreatedDate(new
                      Date());
            friend.setFirstUser(firstuser);
            friend.setSecondUser(seconduser);
            friendRepository.save(friend);
        }
    }

    public List<User> getFriends(Long userId){
        User currentUser = restTemplate.getForObject("http://user-api-app/user/public/" + userId, User.class); // by id
//        User currentUser = userRepository.findUserByEmail(currentUserDto.getEmail());
        List<Friend> friendsByFirstUser = friendRepository.findByFirstUser(currentUser);
        List<Friend> friendsBySecondUser = friendRepository.findBySecondUser(currentUser);
        List<User> friendUsers = new ArrayList<>();

        for (Friend friend : friendsByFirstUser) {
            friendUsers.add(restTemplate.getForObject("http://user-api-app/user/public/" + friend.getSecondUser().getId(), User.class));
        }
        for (Friend friend : friendsBySecondUser) {
            friendUsers.add(restTemplate.getForObject("http://user-api-app/user/public/" + friend.getSecondUser().getId(), User.class));
//            friendUsers.add(userRepository.findUserById(friend.getFirstUser().getId()));
        }
        return friendUsers;
    }

}
