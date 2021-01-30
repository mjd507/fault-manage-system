package com.github.mjd507.faultmanagesystem.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_fault
 * @author 
 */
public class TbFault implements Serializable {
    private Integer id;

    private Date when;

    private Integer where;

    private String content;

    private Integer what;

    private String whatExtra;

    private Integer status;

    private String why;

    private String get;

    private Date createAt;

    private Date updateAt;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }

    public Integer getWhere() {
        return where;
    }

    public void setWhere(Integer where) {
        this.where = where;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getWhat() {
        return what;
    }

    public void setWhat(Integer what) {
        this.what = what;
    }

    public String getWhatExtra() {
        return whatExtra;
    }

    public void setWhatExtra(String whatExtra) {
        this.whatExtra = whatExtra;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getWhy() {
        return why;
    }

    public void setWhy(String why) {
        this.why = why;
    }

    public String getGet() {
        return get;
    }

    public void setGet(String get) {
        this.get = get;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}