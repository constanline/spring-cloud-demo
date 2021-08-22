package com.magicalyang.springcloud.account.dao;

import com.magicalyang.springcloud.account.entity.BizBookList;
import org.apache.ibatis.annotations.Mapper;

/**
 * The interface Biz book list dao.
 *
 * @author Constanline
 * @since 2021/08/02
 */
@Mapper
public interface BizBookListDao {
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
    int insert(BizBookList record);

    /**
     * Insert selective int.
     *
     * @param record the record
     * @return the int
     */
    int insertSelective(BizBookList record);

    /**
     * Select by primary key biz book list.
     *
     * @param id the id
     * @return the biz book list
     */
    BizBookList selectByPrimaryKey(Integer id);

    /**
     * Update by primary key selective int.
     *
     * @param record the record
     * @return the int
     */
    int updateByPrimaryKeySelective(BizBookList record);

    /**
     * Update by primary key int.
     *
     * @param record the record
     * @return the int
     */
    int updateByPrimaryKey(BizBookList record);
}