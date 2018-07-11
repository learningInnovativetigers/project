package com.tensquare.qa.dao;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 *
 * @author Administrator
 */
public interface ProblemDao extends JpaRepository<Problem, String>, JpaSpecificationExecutor<Problem> {

    /**
     * 根据标签id查询最新回复的问题列表
     *
     * @param labelId
     * @return
     */
    @Query("select p from Problem p where id in (select problemid from Pl where labelid = ?1) order by p.replytime desc")
    public Page<Problem> findNewProListByLabelId(String labelId, Pageable pageable);

    /**
     * 根据标签查询热门问题
     * 何以热门:回复数比较多
     *
     * @param labelId
     * @param pageable
     * @return
     */
    @Query("select p from Problem p where p.id in (select problemid from Pl where labelid = ?1) order by p.reply desc")
    public Page<Problem> findHotListByLabelId(String labelId, Pageable pageable);

    /**
     * 根据标签查询等待回答问题列表
     *
     * @param labelId
     * @param pageable
     * @return
     */
    @Query("select p from Problem p where p.id in (select problemid from Pl where labelid = ?1) and p.reply = 0 order by p.createtime desc")
    public Page<Problem> findWaitListByLabelId(String labelId, Pageable pageable);
}
