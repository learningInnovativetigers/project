package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 标签业务逻辑类
 */
@Service
public class LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部标签
     *
     * @return
     */
    public List<Label> findAll() {
        return labelDao.findAll();
    }

    /**
     * 根据id查询标签
     *
     * @param id
     * @return
     */
    public Label findById(String id) {
        return labelDao.findById(id).get();
    }

    /**
     * 条件查询
     *
     * @param searchMap
     * @return
     */
    public List<Label> findSearch(Map searchMap) {
        Specification specification = createSpectification(searchMap);
        return labelDao.findAll(specification);
    }

    /**
     * 分页条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public Page<Label> findSearch(Map searchMap,int page,int size){
        Specification spectification = createSpectification(searchMap);
        PageRequest pr = PageRequest.of(page-1, size);
        return labelDao.findAll(spectification,pr);
    }

    /**
     * 创建查询条件
     *
     * @param searchMap
     * @return
     */
    private Specification createSpectification(Map searchMap) {
        return new Specification() {
            @Nullable
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery cq, CriteriaBuilder cb) {
                List<Predicate> predicatelist = new ArrayList<>();
                if (searchMap != null) {
                    if (searchMap.get("labelname") != null && !"".equals(searchMap.get("labelname"))) {
                        predicatelist.add(cb.like(root.get("labelname").as(String.class), "%" + (String) searchMap.get("labelname") + "%"));
                    }
                    if (searchMap.get("state") != null && !"".equals(searchMap.get("state"))) {
                        predicatelist.add(cb.equal(root.get("state").as(String.class), searchMap.get("state")));
                    }
                    if (searchMap.get("recommend") != null && !"".equals(searchMap.get("recommend"))) {
                        predicatelist.add(cb.equal(root.get("recommend").as(String.class), searchMap.get("recommend")));
                    }

                }
                return cb.and(predicatelist.toArray(new Predicate[predicatelist.size()]));  // 将集合转换为数组
            }
        };
    }

    /**
     * 添加标签
     *
     * @param label
     */
    public void add(Label label) {
        label.setId(idWorker.nextId() + "");  // 设置ID
        labelDao.save(label);
    }

    /**
     * 修改标签
     *
     * @param label
     */
    public void update(Label label) {
        labelDao.save(label);
    }

    /**
     * 根据id删除标签
     *
     * @param id
     */
    public void deleteById(String id) {
        labelDao.deleteById(id);
    }
}
