package com.tensquare.qa.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Title: Pl
 * Description: 问题标签中间实体表
 * author: wenbiao
 * create: 2018-07-05 22:25
 */
@Entity
@Table(name = "tb_pl")
public class Pl implements Serializable {

    @Id
    private String problemid;// 问题主键

    @Id
    private String labelid;// 标签主键

    public String getProblemid() {
        return problemid;
    }

    public void setProblemid(String problemid) {
        this.problemid = problemid;
    }

    public String getLabelid() {
        return labelid;
    }

    public void setLabelid(String labelid) {
        this.labelid = labelid;
    }
}
