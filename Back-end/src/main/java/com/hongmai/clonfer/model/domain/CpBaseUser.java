package com.hongmai.clonfer.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 * @TableName cp_base_user
 */
@TableName(value ="cp_base_user")
@Accessors(chain = true)
@Data
public class CpBaseUser implements Serializable {
    /**
     * 
     */
    @TableId
    private String uuid;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private String fullname;

    /**
     * 
     */
    private String avatar;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private Integer role;

    /**
     * 
     */
    private String orginazationUuid;

    /**
     * 
     */
    private String ability;

    /**
     * 
     */
    private String gmtReg;

    /**
     * 
     */
    private String ipReg;

    /**
     * 
     */
    private String gmtLastLogin;

    /**
     * 
     */
    private String ipLastLogin;

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
        CpBaseUser other = (CpBaseUser) that;
        return (this.getUuid() == null ? other.getUuid() == null : this.getUuid().equals(other.getUuid()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getFullname() == null ? other.getFullname() == null : this.getFullname().equals(other.getFullname()))
            && (this.getAvatar() == null ? other.getAvatar() == null : this.getAvatar().equals(other.getAvatar()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getRole() == null ? other.getRole() == null : this.getRole().equals(other.getRole()))
            && (this.getOrginazationUuid() == null ? other.getOrginazationUuid() == null : this.getOrginazationUuid().equals(other.getOrginazationUuid()))
            && (this.getAbility() == null ? other.getAbility() == null : this.getAbility().equals(other.getAbility()))
            && (this.getGmtReg() == null ? other.getGmtReg() == null : this.getGmtReg().equals(other.getGmtReg()))
            && (this.getIpReg() == null ? other.getIpReg() == null : this.getIpReg().equals(other.getIpReg()))
            && (this.getGmtLastLogin() == null ? other.getGmtLastLogin() == null : this.getGmtLastLogin().equals(other.getGmtLastLogin()))
            && (this.getIpLastLogin() == null ? other.getIpLastLogin() == null : this.getIpLastLogin().equals(other.getIpLastLogin()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getExtInfo() == null ? other.getExtInfo() == null : this.getExtInfo().equals(other.getExtInfo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUuid() == null) ? 0 : getUuid().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getFullname() == null) ? 0 : getFullname().hashCode());
        result = prime * result + ((getAvatar() == null) ? 0 : getAvatar().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getRole() == null) ? 0 : getRole().hashCode());
        result = prime * result + ((getOrginazationUuid() == null) ? 0 : getOrginazationUuid().hashCode());
        result = prime * result + ((getAbility() == null) ? 0 : getAbility().hashCode());
        result = prime * result + ((getGmtReg() == null) ? 0 : getGmtReg().hashCode());
        result = prime * result + ((getIpReg() == null) ? 0 : getIpReg().hashCode());
        result = prime * result + ((getGmtLastLogin() == null) ? 0 : getGmtLastLogin().hashCode());
        result = prime * result + ((getIpLastLogin() == null) ? 0 : getIpLastLogin().hashCode());
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
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", fullname=").append(fullname);
        sb.append(", avatar=").append(avatar);
        sb.append(", email=").append(email);
        sb.append(", role=").append(role);
        sb.append(", orginazationUuid=").append(orginazationUuid);
        sb.append(", ability=").append(ability);
        sb.append(", gmtReg=").append(gmtReg);
        sb.append(", ipReg=").append(ipReg);
        sb.append(", gmtLastLogin=").append(gmtLastLogin);
        sb.append(", ipLastLogin=").append(ipLastLogin);
        sb.append(", status=").append(status);
        sb.append(", extInfo=").append(extInfo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}