package com.magicalyang.springcloud.account.dao;

import com.magicalyang.springcloud.account.entity.BizBookListDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * The interface Biz book list detail dao.
 *
 * @author Constanline
 * @since 2021/08/02
 */
@Mapper
public interface BizBookListDetailDao {
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
    int insert(BizBookListDetail record);

    /**
     * Insert selective int.
     *
     * @param record the record
     * @return the int
     */
    int insertSelective(BizBookListDetail record);

    /**
     * Select by primary key biz book list detail.
     *
     * @param id the id
     * @return the biz book list detail
     */
    BizBookListDetail selectByPrimaryKey(Integer id);

    /**
     * Update by primary key selective int.
     *
     * @param record the record
     * @return the int
     */
    int updateByPrimaryKeySelective(BizBookListDetail record);

    /**
     * Update by primary key int.
     *
     * @param record the record
     * @return the int
     */
    int updateByPrimaryKey(BizBookListDetail record);
}