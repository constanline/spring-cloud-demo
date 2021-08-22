package com.magicalyang.springcloud.account.dao;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.magicalyang.springcloud.account.entity.BizUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * The interface Biz user dao.
 *
 * @author Constanline
 * @since 2021 /08/02
 */
@Mapper
public interface BizUserDao {
    /**
     * Delete by primary key int.
     *
     * @param id the id
     * @return the int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * Insert int.
     *
     * @param record the record
     * @return the int
     */
    int insert(BizUser record);

    /**
     * Insert selective int.
     *
     * @param record the record
     * @return the int
     */
    int insertSelective(BizUser record);

    /**
     * Select by primary key biz user.
     *
     * @param id the id
     * @return the biz user
     */
    BizUser selectByPrimaryKey(Integer id);

    /**
     * Update by primary key selective int.
     *
     * @param record the record
     * @return the int
     */
    int updateByPrimaryKeySelective(BizUser record);

    /**
     * Update by primary key int.
     *
     * @param record the record
     * @return the int
     */
    int updateByPrimaryKey(BizUser record);

    /**
     * List by username and display name list.
     *
     * @param username      the username
     * @param displayName   the display name
     * @param lastLoginTime the last login time
     * @return the list
     */
    List<BizUser> listByUsernameAndDisplayName(@Param("username")String username,
                                               @Param("displayName")String displayName,
                                               @Param("lastLoginTime")LocalDateTime lastLoginTime);

    /**
     * Select by username biz user.
     *
     * @param username the username
     * @return the biz user
     */
    BizUser selectByUsername(@Param("username")String username);
}