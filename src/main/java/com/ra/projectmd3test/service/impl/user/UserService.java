package com.ra.projectmd3test.service.impl.user;


import com.ra.projectmd3test.model.dto.user.UserLogin;
import com.ra.projectmd3test.model.dto.user.UserRegister;
import com.ra.projectmd3test.model.entity.User;
import com.ra.projectmd3test.repository.design.user.IUserRepository;
import com.ra.projectmd3test.service.design.user.IUserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private HttpSession session;
    @Override
    public List<User> findAll(Integer page, Integer size) {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(UserRegister userRegister) {
        User user = null;
        user = User.builder()
                .userName(userRegister.getUserName())
                .password(BCrypt.hashpw(userRegister.getPassword(), BCrypt.gensalt(5)))
                .email(userRegister.getEmail())
                .fullName(userRegister.getFullName())
                .created_At(new Date())
                .updated_At(new Date())
                .avatar("https://inkythuatso.com/uploads/thumbnails/800/2023/03/9-anh-dai-dien-trang-inkythuatso-03-15-27-03.jpg")
                .build();
        userRepository.save(user);
    }
    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public Boolean login(UserLogin userLogin) {
        try{
            User user = userRepository.findByName(userLogin.getUserName());
            if(user != null){
                if(BCrypt.checkpw(userLogin.getPassword(), user.getPassword())){
                    // luu thong tin nguoi dung vao session
                    session.setAttribute("adminLogin", user);
                    return true;
                }
            }
            return false;
        }catch (NoResultException e){
            return false;
        }
    }
}
