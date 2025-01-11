package com.example.Restaurant.Service;

import com.example.Restaurant.Repository.UserRepository;
import com.example.Restaurant.model.StoreUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;



    public StoreUser addUser(StoreUser storeUser){

        if (userRepository.existsById(storeUser.getUsernameId())){
            return new StoreUser();
        }

        System.out.println(storeUser);

        userRepository.saveAndFlush(storeUser);

        return userRepository.findById(storeUser.getUsernameId()).get();
    }


    public List<StoreUser> getAllUsers(){

        List<StoreUser> list = userRepository.findAll();

        return list;
    }


    public String validateUser(String user, String pass){

        if (userRepository.findById(user).isEmpty() && userRepository.findAllByEmail(user).isEmpty()){

            return "Empty";
        }
        else {
            StoreUser current = userRepository.findById(user).get();

            System.out.println(current);

            if (!pass.equals(current.getPassword())){
                return "Incorrect Password";
            }
            else {
                return "Match";
            }
        }
    }


}
