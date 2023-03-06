package com.powernode.owner.domain;

import com.powernode.common.annotation.Excel;
import com.powernode.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 业主信息对象 owner_user
 * 
 * @author mingyun
 * @date 2023-03-06
 */
public class OwnerUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private Long id;

    /** 微信unionId */
    private String unionId;

    /** 微信openId */
    private String openId;

    /** 登录标识 */
    private String token;

    /** 公司名 */
    @Excel(name = "公司名")
    private String company;

    /** 真实姓名 */
    @Excel(name = "真实姓名")
    private String username;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String identity;

    /** 用户昵称 */
    @Excel(name = "用户昵称")
    private String nickName;

    /** 用户类型（00系统用户） */
    private String userType;

    /** 用户邮箱 */
    @Excel(name = "用户邮箱")
    private String email;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phone;

    /** 用户性别（0男 1女 2未知） */
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /** 头像地址 */
    @Excel(name = "头像地址")
    private String avatar;

    /** 帐号状态（0正常 1停用） */
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 在租的数量 */
    @Excel(name = "在租的数量")
    private Long rentedCount;

    /** 拥有的数量 */
    @Excel(name = "拥有的数量")
    private Long ownCount;

    /** 0未绑定，1已绑定 */
    private String isBind;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUnionId(String unionId) 
    {
        this.unionId = unionId;
    }

    public String getUnionId() 
    {
        return unionId;
    }
    public void setOpenId(String openId) 
    {
        this.openId = openId;
    }

    public String getOpenId() 
    {
        return openId;
    }
    public void setToken(String token) 
    {
        this.token = token;
    }

    public String getToken() 
    {
        return token;
    }
    public void setCompany(String company) 
    {
        this.company = company;
    }

    public String getCompany() 
    {
        return company;
    }
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }
    public void setIdentity(String identity) 
    {
        this.identity = identity;
    }

    public String getIdentity() 
    {
        return identity;
    }
    public void setNickName(String nickName) 
    {
        this.nickName = nickName;
    }

    public String getNickName() 
    {
        return nickName;
    }
    public void setUserType(String userType) 
    {
        this.userType = userType;
    }

    public String getUserType() 
    {
        return userType;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setRentedCount(Long rentedCount) 
    {
        this.rentedCount = rentedCount;
    }

    public Long getRentedCount() 
    {
        return rentedCount;
    }
    public void setOwnCount(Long ownCount) 
    {
        this.ownCount = ownCount;
    }

    public Long getOwnCount() 
    {
        return ownCount;
    }
    public void setIsBind(String isBind) 
    {
        this.isBind = isBind;
    }

    public String getIsBind() 
    {
        return isBind;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("unionId", getUnionId())
            .append("openId", getOpenId())
            .append("token", getToken())
            .append("company", getCompany())
            .append("username", getUsername())
            .append("identity", getIdentity())
            .append("nickName", getNickName())
            .append("userType", getUserType())
            .append("email", getEmail())
            .append("phone", getPhone())
            .append("sex", getSex())
            .append("avatar", getAvatar())
            .append("status", getStatus())
            .append("rentedCount", getRentedCount())
            .append("ownCount", getOwnCount())
            .append("isBind", getIsBind())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
