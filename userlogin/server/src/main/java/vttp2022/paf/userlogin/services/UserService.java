package vttp2022.paf.userlogin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.paf.userlogin.models.User;
import vttp2022.paf.userlogin.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository uRepo;

    public boolean authenticate(User user) {
        return uRepo.countUsersMatchingNameAndPassword(user) == 1;
    }
    
}
