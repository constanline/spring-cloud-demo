package com.magicalyang.springcloud.account.service;

import com.magicalyang.springcloud.account.dto.RegisterDTO;
import com.magicalyang.springcloud.account.dto.SignInDTO;
import com.magicalyang.springcloud.account.vo.UserVO;
import com.magicalyang.springcloud.common.response.MessageResponse;

/**
 * The interface User service.
 *
 * @author Constanline
 * @since 2021 /07/30
 */
public interface UserService {

    /**
     * Gets user.
     *
     * @param userId the user id
     * @return the user
     */
    MessageResponse getUser(int userId);

    /**
     * Register base code enum.
     *
     * @param registerDTO the user dto
     * @return the base code enum
     */
    MessageResponse register(RegisterDTO registerDTO);

    /**
     * Cancel base code enum.
     *
     * @param userId the user id
     * @return the base code enum
     */
    MessageResponse cancel(int userId);

    /**
     * Search list.
     *
     * @param username      the username
     * @param displayName   the display name
     * @param lastLoginTime the last login time
     * @return the list
     */
    MessageResponse search(String username, String displayName, Long lastLoginTime);

    /**
     * Sign in base code enum.
     *
     * @param signInDTO the sign in dto
     * @return the base code enum
     */
    MessageResponse signIn(SignInDTO signInDTO, UserVO userVO);

    /**
     * Sign out message response.
     *
     * @param userId the user id
     * @return the message response
     */
    MessageResponse signOut(Integer userId);
}
