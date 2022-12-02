package com.hongmai.clonfer.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @TableName cp_inspect_pipeline
 */
@TableName(value ="cp_inspect_pipeline")
@Data
public class CpInspectPipeline implements Serializable {
    /**
     *
     */
    @TableId
    private String uuid;

    /**
     *
     */
    private String inspectRecordUuid;

    /**
     *
     */
    private Integer sequence;

    /**
     *
     */
    private String action;

    /**
     *
     */
    private String owner;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private String status;

    /**
     *
     */
    private String coordinate;

    /**
     *
     */
    private String nextNodeUuid;

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
        CpInspectPipeline other = (CpInspectPipeline) that;
        return (this.getUuid() == null ? other.getUuid() == null : this.getUuid().equals(other.getUuid()))
                && (this.getInspectRecordUuid() == null ? other.getInspectRecordUuid() == null : this.getInspectRecordUuid().equals(other.getInspectRecordUuid()))
                && (this.getSequence() == null ? other.getSequence() == null : this.getSequence().equals(other.getSequence()))
                && (this.getAction() == null ? other.getAction() == null : this.getAction().equals(other.getAction()))
                && (this.getOwner() == null ? other.getOwner() == null : this.getOwner().equals(other.getOwner()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getCoordinate() == null ? other.getCoordinate() == null : this.getCoordinate().equals(other.getCoordinate()))
                && (this.getNextNodeUuid() == null ? other.getNextNodeUuid() == null : this.getNextNodeUuid().equals(other.getNextNodeUuid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUuid() == null) ? 0 : getUuid().hashCode());
        result = prime * result + ((getInspectRecordUuid() == null) ? 0 : getInspectRecordUuid().hashCode());
        result = prime * result + ((getSequence() == null) ? 0 : getSequence().hashCode());
        result = prime * result + ((getAction() == null) ? 0 : getAction().hashCode());
        result = prime * result + ((getOwner() == null) ? 0 : getOwner().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCoordinate() == null) ? 0 : getCoordinate().hashCode());
        result = prime * result + ((getNextNodeUuid() == null) ? 0 : getNextNodeUuid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uuid=").append(uuid);
        sb.append(", inspectRecordUuid=").append(inspectRecordUuid);
        sb.append(", sequence=").append(sequence);
        sb.append(", action=").append(action);
        sb.append(", owner=").append(owner);
        sb.append(", name=").append(name);
        sb.append(", status=").append(status);
        sb.append(", coordinate=").append(coordinate);
        sb.append(", nextNodeUuid=").append(nextNodeUuid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}