package com.hongmai.clonfer.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName cp_base_orginazation
 */
@TableName(value ="cp_base_orginazation")
@Data
public class CpBaseOrginazation implements Serializable {
    /**
     * 
     */
    @TableId
    private String uuid;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String category;

    /**
     * 
     */
    private String owner;

    /**
     * 
     */
    private String member;

    /**
     * 
     */
    private String gmtCreate;

    /**
     * 
     */
    private String plan;

    /**
     * 
     */
    private String status;

    /**
     * 
     */
    private String extInfo;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CpBaseOrginazation other = (CpBaseOrginazation) that;
        return (this.getUuid() == null ? other.getUuid() == null : this.getUuid().equals(other.getUuid()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getCategory() == null ? other.getCategory() == null : this.getCategory().equals(other.getCategory()))
            && (this.getOwner() == null ? other.getOwner() == null : this.getOwner().equals(other.getOwner()))
            && (this.getMember() == null ? other.getMember() == null : this.getMember().equals(other.getMember()))
            && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
            && (this.getPlan() == null ? other.getPlan() == null : this.getPlan().equals(other.getPlan()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getExtInfo() == null ? other.getExtInfo() == null : this.getExtInfo().equals(other.getExtInfo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUuid() == null) ? 0 : getUuid().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getCategory() == null) ? 0 : getCategory().hashCode());
        result = prime * result + ((getOwner() == null) ? 0 : getOwner().hashCode());
        result = prime * result + ((getMember() == null) ? 0 : getMember().hashCode());
        result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
        result = prime * result + ((getPlan() == null) ? 0 : getPlan().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getExtInfo() == null) ? 0 : getExtInfo().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uuid=").append(uuid);
        sb.append(", name=").append(name);
        sb.append(", category=").append(category);
        sb.append(", owner=").append(owner);
        sb.append(", member=").append(member);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", plan=").append(plan);
        sb.append(", status=").append(status);
        sb.append(", extInfo=").append(extInfo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}