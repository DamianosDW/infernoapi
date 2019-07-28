/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.users;

import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import ovh.damianosdw.infernoapi.exceptions.ResourceNotFoundException;
import ovh.damianosdw.infernoapi.exceptions.SqlQueryErrorException;
import ovh.damianosdw.infernoapi.utils.ApiUtils;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/infernoapi/users")
@AllArgsConstructor
public class UsersController
{
    private final UsersRepository usersRepository;

    @GetMapping("avatars")
    public List<UserAvatar> getAllUserAvatars() throws ResourceNotFoundException
    {
        List<User> users = usersRepository.findAll();

        if(users.isEmpty())
            throw new ResourceNotFoundException("Users don't exist in database!");
        else
        {
            List<UserAvatar> userAvatars = new ArrayList<>();

            for(User user : users)
                userAvatars.add(prepareUserAvatar(user));

            return userAvatars;
        }
    }

    private UserAvatar prepareUserAvatar(User user)
    {
        int userId = user.getUserId();
        String username = user.getUsername();
        String avatarUrl = user.getAvatarURL();

        return new UserAvatar(userId, username, avatarUrl);
    }

    @GetMapping("{username}/info")
    public UserInfo getUserInfoByUsername(@PathVariable("username") String username) throws SqlQueryErrorException, ResourceNotFoundException
    {
        UserInfo userInfo;
        try {
            userInfo = usersRepository.getUserInfoByUsername(username);
        } catch(Exception e) {
            throw new SqlQueryErrorException("There was a problem with getting user info from database! Try again later.");
        }

        if(ApiUtils.checkIfValueIsCorrect(userInfo))
            return userInfo;
        else
            throw new ResourceNotFoundException("This user doesn't exist in database!");
    }

    @GetMapping("{username}/active")
    public boolean checkIfUserAccountIsActive(@PathVariable("username") String username) throws ResourceNotFoundException, SqlQueryErrorException
    {
        try {
            return usersRepository.getUserAccountStatus(username);
        } catch(NoResultException e) {
            throw new ResourceNotFoundException("This user doesn't exist in database!");
        } catch(Exception e) {
            throw new SqlQueryErrorException("There was a problem with getting account status!");
        }
    }

    @PostMapping("login")
    public boolean checkIfUserCanLogIn(@RequestBody UserCredentials userCredentials) throws SqlQueryErrorException
    {
        try {
            User user = usersRepository.getUserByUsernameAndActiveIsTrue(userCredentials.getLogin());
            return BCrypt.checkpw(userCredentials.getPassword(), user.getPassword());
        } catch(Exception e) {
            throw new SqlQueryErrorException("There was a problem with checking login and password! Try again later.");
        }
    }

    @PutMapping("create")
    public void createUserAccount(@RequestBody User user) throws SqlQueryErrorException
    {
        try {
            usersRepository.save(user);
        } catch(Exception e) {
            throw new SqlQueryErrorException("There was a problem with creating user account! Try again later.");
        }
    }
}
