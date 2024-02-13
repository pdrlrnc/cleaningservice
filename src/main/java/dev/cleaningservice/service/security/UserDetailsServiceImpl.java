package dev.cleaningservice.service.security;

import dev.cleaningservice.dao.RoleDAO;
import dev.cleaningservice.dao.UserEntityDAO;
import dev.cleaningservice.dao.UserInfoDAO;
import dev.cleaningservice.dto.RegistrationDTO;
import dev.cleaningservice.entity.Role;
import dev.cleaningservice.entity.UserEntity;
import dev.cleaningservice.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserSecService {

    private UserEntityDAO userEntityDAO;
    private RoleDAO roleDAO;

    private UserInfoDAO userInfoDAO;

    @Autowired
    public UserDetailsServiceImpl(UserEntityDAO userEntityDAO, RoleDAO roleDAO, UserInfoDAO userInfoDAO){
        this.userEntityDAO = userEntityDAO;
        this.roleDAO = roleDAO;
        this.userInfoDAO = userInfoDAO;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userEntityDAO.findByUsername(username);

        if(userEntity != null){
            System.out.println(userEntity.getUsername() + "\n\n\n\n\n");

            User authenticatedUser = new User(userEntity.getUsername(), userEntity.getPassword(),
                    userEntity.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList())
            );
            return authenticatedUser;
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }



    @Override
    public UserEntity findByUseName(String username) {
        return userEntityDAO.findByUsername(username);
    }

    @Override
    @Transactional
    public UserInfo save(RegistrationDTO registrationDTO) {


        //creating userEntity
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(registrationDTO.getPassword());
        userEntity.setUsername(registrationDTO.getUsername());
        Role role = roleDAO.findByName("ROLE_CUSTOMER");
        userEntity.addRole(role);

        //saving userEntity
        userEntityDAO.save(userEntity);

        //creating userInfo
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(registrationDTO.getUsername());
        userInfo.setEmail(registrationDTO.getEmail());
        userInfo.setFirstName(registrationDTO.getFirstName());

        System.out.println("\n\n\n\n" + userInfo + "\n\n\n\n");

        //saving userInfo
        userInfoDAO.save(userInfo);
        return userInfo;

    }
}
