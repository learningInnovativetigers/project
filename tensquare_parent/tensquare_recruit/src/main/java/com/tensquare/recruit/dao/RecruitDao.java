package com.tensquare.recruit.dao;

import com.tensquare.recruit.pojo.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{

    /**
     * 查询推荐职位列表(按照推荐状态,创建日期倒序取前四条)
     * @param state
     * @return
     */
    public List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String state);

    /**
     * 查询最新职位列表
     * @param state
     * @return
     */
    public List<Recruit> findTop12ByStateNotOrderByCreatetimeDesc(String state);
}
