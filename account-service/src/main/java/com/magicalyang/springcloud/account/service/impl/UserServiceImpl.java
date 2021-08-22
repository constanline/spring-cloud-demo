package com.magicalyang.springcloud.account.service.impl;

import com.magicalyang.springcloud.account.dao.BizUserDao;
import com.magicalyang.springcloud.account.dto.RegisterDTO;
import com.magicalyang.springcloud.account.dto.SignInDTO;
import com.magicalyang.springcloud.account.entity.BizUser;
import com.magicalyang.springcloud.account.enums.UserCommonCodeEnum;
import com.magicalyang.springcloud.account.enums.UserRegisterCodeEnum;
import com.magicalyang.springcloud.account.service.UserService;
import com.magicalyang.springcloud.account.vo.UserVO;
import com.magicalyang.springcloud.common.response.DataResponse;
import com.magicalyang.springcloud.common.response.MessageResponse;
import com.magicalyang.springcloud.common.util.BeanUtil;
import com.magicalyang.springcloud.common.util.EncryptUtil;
import com.magicalyang.springcloud.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author Constanline
 * @since 2021/07/30
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    BizUserDao userDao;

    public UserServiceImpl(BizUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public MessageResponse getUser(int userId) {
        val user = userDao.selectByPrimaryKey(userId);
        if (user == null) {
            return new MessageResponse(UserCommonCodeEnum.USER_NOT_EXIST);
        }
        UserVO userVO = BeanUtil.copyProperties(user, UserVO::new);
        return new DataResponse<>(userVO);
    }

    @Override
    public MessageResponse register(RegisterDTO registerDTO) {
        BizUser user = userDao.selectByUsername(registerDTO.getUsername());
        if (user != null) {
            return new MessageResponse(UserRegisterCodeEnum.USERNAME_EXIST);
        }
        user = BeanUtil.copyProperties(registerDTO, BizUser::new);
        user.setHalt(StringUtil.getRandomStr(6));
        try {
            user.setPassword(EncryptUtil.md5(EncryptUtil.md5(user.getPassword() + user.getHalt())));
        } catch (Throwable th) {
            log.error("UserServiceImpl.register", th);
            return new MessageResponse(UserRegisterCodeEnum.REGISTER_FAILURE);
        }
        user.setCreateTime(LocalDateTime.now());
        user.setLastLoginTime(new Timestamp(0L).toLocalDateTime());
        int userId = userDao.insert(user);
        return new DataResponse<>(UserRegisterCodeEnum.REGISTER_SUCCESS, userId);
    }

    @Override
    public MessageResponse cancel(int userId) {
        val user = userDao.selectByPrimaryKey(userId);
        if (user == null) {
            return new MessageResponse(UserCommonCodeEnum.USER_NOT_EXIST);
        }
        userDao.deleteByPrimaryKey(userId);
        return new MessageResponse(UserCommonCodeEnum.OPERATION_SUCCESS);
    }

    @Override
    public MessageResponse search(String username, String displayName, Long lastLoginTime) {
        val list = userDao.listByUsernameAndDisplayName(username, displayName,
                new Timestamp(lastLoginTime).toLocalDateTime());
        return new DataResponse<>(BeanUtil.copyProperties(list, UserVO::new));
    }

    @Override
    public MessageResponse signIn(SignInDTO signInDTO, UserVO userVO) {
        val user = userDao.selectByUsername(signInDTO.getUsername());
        if (user == null) {
            return new MessageResponse(UserCommonCodeEnum.USER_NOT_EXIST);
        }
        try {
            String password = EncryptUtil.md5(EncryptUtil.md5(signInDTO.getPassword() + user.getHalt()));
            if (!user.getPassword().equals(password)) {
                return new MessageResponse(UserCommonCodeEnum.USERNAME_OR_PASSWORD_ERROR);
            }
        } catch (Throwable th) {
            log.error("UserServiceImpl.signIn", th);
            return new MessageResponse(UserCommonCodeEnum.SIGN_IN_FAILURE);
        }
        BeanUtil.copyProperties(user, userVO);
        return new DataResponse<>(UserCommonCodeEnum.SIGN_IN_SUCCESS, userVO);
    }

    @Override
    public MessageResponse signOut(Integer userId) {
        if (userId == 0) {
            return new MessageResponse(UserCommonCodeEnum.USER_NOT_EXIST);
        }
        return new MessageResponse(0, "登出成功");
    }
}
