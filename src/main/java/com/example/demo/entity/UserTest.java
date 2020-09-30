package com.example.demo.entity;

public class UserTest {
    /**
     * 用户ID
     * 2020-08-19
     */
    private String userId;

    /**
     * 手机号码
     * 2020-08-19
     */
    private String userName;

    /**
     * 微信用户登录标志
     * 2020-08-19
     */
    private String openId;

    /**
     * 密码
     * 2020-08-19
     */
    private String password;

    /**
     * 真实姓名
     * 2020-08-19
     */
    private String realName;

    /**
     * 昵称
     * 2020-08-19
     */
    private String nickName;

    /**
     * 性别（0：女，1：男）
     * 2020-08-19
     */
    private Boolean sex;

    /**
     * 头像
     * 2020-08-19
     */
    private String avatar;

    /**
     * 会员积分
     * 2020-08-19
     */
    private Long points;

    /**
     * 会员卡号
     * 2020-08-19
     */
    private String memberCardNo;

    /**
     * 会员等级（user_grade外键）
     * 2020-08-19
     */
    private String userLevel;

    /**
     * 是否为新用户
     * 2020-08-19
     */
    private Boolean isNewer;

    /**
     * 删除标示 0未删除 1删除
     * 2020-08-19
     */
    private Boolean delFlag;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public String getMemberCardNo() {
        return memberCardNo;
    }

    public void setMemberCardNo(String memberCardNo) {
        this.memberCardNo = memberCardNo;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public Boolean getIsNewer() {
        return isNewer;
    }

    public void setIsNewer(Boolean isNewer) {
        this.isNewer = isNewer;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }
}