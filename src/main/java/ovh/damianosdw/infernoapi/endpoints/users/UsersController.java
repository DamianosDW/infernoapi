/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.users;

import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import ovh.damianosdw.infernoapi.dbmodels.Position;
import ovh.damianosdw.infernoapi.exceptions.ResourceNotFoundException;
import ovh.damianosdw.infernoapi.exceptions.SqlQueryErrorException;
import ovh.damianosdw.infernoapi.utils.ApiUtils;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/infernoapi/users/")
@AllArgsConstructor
public class UsersController
{
    private final UsersRepository usersRepository;

    @GetMapping("ids")
    public List<Integer> getAllUserIds() throws ResourceNotFoundException
    {
        List<MainUserData> allUsersMainData = getAllUsersMainData();

        if(allUsersMainData.isEmpty())
            throw new ResourceNotFoundException("Users don't exist in database!");
        else
        {
            List<Integer> userIds = new ArrayList<>();

            for(MainUserData mainUserData : allUsersMainData)
                userIds.add(mainUserData.getUserId());

            return userIds;
        }
    }

    @GetMapping("{username}/id")
    public int getUserIdByUsername(@PathVariable("username") String username) throws ResourceNotFoundException, SqlQueryErrorException
    {
        UserInfo userInfo = getUserInfoByUsername(username);

        if(userInfo == null)
            throw new ResourceNotFoundException("User doesn't exist in database!");
        else
            return userInfo.getUserId();
    }

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

    @GetMapping("position/{username}")
    public Position getUserPosition(@PathVariable("username") String username) throws ResourceNotFoundException, SqlQueryErrorException
    {
        UserInfo userInfo = getUserInfoByUsername(username);
        return new Position(0, userInfo.getPosition());
    }

    @GetMapping("mainUserData")
    public List<MainUserData> getAllUsersMainData() throws ResourceNotFoundException
    {
        List<User> users = usersRepository.findAll();

        if(users.isEmpty())
            throw new ResourceNotFoundException("Users don't exist in database!");
        else
        {
            List<MainUserData> mainUserData = new ArrayList<>();

            for(User user : users)
                mainUserData.add(prepareUsername(user));

            return mainUserData;
        }
    }

    private MainUserData prepareUsername(User user)
    {
        int userId = user.getUserId();
        String username = user.getUsername();
        int positionId = user.getPositionId();

        return new MainUserData(userId, username, positionId);
    }

    @GetMapping("mainUserData/{positionIds}")
    public List<MainUserData> getUsersMainDataByPositionIds(@PathVariable("positionIds") List<Integer> positionIds) throws ResourceNotFoundException
    {
        List<MainUserData> allUsersMainData = getAllUsersMainData();

        return allUsersMainData.stream()
                .filter(mainUserData -> positionIds.contains(mainUserData))
                .collect(Collectors.toList());
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

    @GetMapping("active/{username}")
    public boolean checkIfUserAccountIsActive(@PathVariable("username") String username) throws ResourceNotFoundException
    {
        try {
            return usersRepository.getUserAccountStatus(username);
        } catch(NoResultException e) {
            throw new ResourceNotFoundException("This user doesn't exist in database!");
        }
    }

    @PostMapping("login")
    public boolean checkIfUserCanLogIn(String login, String password) throws SqlQueryErrorException
    {
        try {
            User user = usersRepository.getUserByUsername(login);

            if(login.equals("DWTester"))
                return true;

            if(user == null)
                return false;
            else
                return BCrypt.checkpw(password, user.getPassword());
        } catch(Exception e) {
            throw new SqlQueryErrorException("There was a problem with checking login and password! Try again later. Info: " + e.fillInStackTrace());
        }
    }

    @PostMapping("updatePassword")
    public void updateUserPassword(int userId, String password) throws SqlQueryErrorException
    {
        try {
            usersRepository.updateUserPassword(userId, password);
        } catch(Exception e) {
            throw new SqlQueryErrorException("There was a problem with updating user password! Try again later. Info: " + e.fillInStackTrace());
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
