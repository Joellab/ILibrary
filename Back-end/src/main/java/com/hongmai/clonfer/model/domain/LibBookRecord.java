package com.hongmai.clonfer.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName lib_book_record
 */
@TableName(value ="lib_book_record")
@Data
public class LibBookRecord implements Serializable {
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
    private String status;

    /**
     * 
     */
    private String lastScan;

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
        LibBookRecord other = (LibBookRecord) that;
        return (this.getUuid() == null ? other.getUuid() == null : this.getUuid().equals(other.getUuid()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getCategory() == null ? other.getCategory() == null : this.getCategory().equals(other.getCategory()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getLastScan() == null ? other.getLastScan() == null : this.getLastScan().equals(other.getLastScan()))
            && (this.getExtInfo() == null ? other.getExtInfo() == null : this.getExtInfo().equals(other.getExtInfo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUuid() == null) ? 0 : getUuid().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getCategory() == null) ? 0 : getCategory().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getLastScan() == null) ? 0 : getLastScan().hashCode());
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
        sb.append(", status=").append(status);
        sb.append(", lastScan=").append(lastScan);
        sb.append(", extInfo=").append(extInfo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}