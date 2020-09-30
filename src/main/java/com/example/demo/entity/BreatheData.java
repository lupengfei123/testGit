package com.example.demo.entity;

import java.util.Date;

public class BreatheData {
    private Long id;

    private String personCode;

    /**
     * 协议版本 ,V1.0
     * 2020-08-19
     */
    private String codeVersion;

    /**
     * 终端设备编号，例如: A116100001
     * 2020-08-19
     */
    private String deviceNo;

    /**
     * 渠道商编号,例如 : 72010000
     * 2020-08-19
     */
    private String saleChannel;

    /**
     * 检测时间,格式:yyyy-MM-dd HH:mm:ss
     * 2020-08-19
     */
    private String detectedTime;

    /**
     * PEF值,单位 L/min ，数据值如:395
     * 2020-08-19
     */
    private Integer pefValue;

    /**
     * FEV1值,单位 L ，数据值如:3.92
     * 2020-08-19
     */
    private Double fev1Value;

    /**
     * FVC值,单位 L ，数据值如:4.53
     * 2020-08-19
     */
    private Double fvcValue;

    /**
     * fef25_75值,单位 L/s ，数据值如:4.71
     * 2020-08-19
     */
    private Double fef2575Value;

    /**
     * mef75值,单位 L/s ，数据值如:7.94
     * 2020-08-19
     */
    private Double mef75Value;

    /**
     * mef50值,单位 L/s ，数据值如:5.17
     * 2020-08-19
     */
    private Double mef50Value;

    /**
     * Mef25值,单位 L/s ，数据值如:2.31
     * 2020-08-19
     */
    private Double mef25Value;

    /**
     * 设备已绑定检测人，根据检测人信息各 指标的预计值、实际检测值分析出检测 提示; 如果开机时判断未有绑定检测 人，则为空
     * 2020-08-19
     */
    private String tips;

    /**
     * 检测人 pef 指标预计值
     * 2020-08-19
     */
    private String param1;

    /**
     * 检测人 fev1 指标预计值
     * 2020-08-19
     */
    private String param2;

    /**
     * 检测人 fvc 指标预计值
     * 2020-08-19
     */
    private String param3;

    /**
     * 已定义为数据图表数据 (内容比较多， 请求方式为POST 请求才会推送数据) 注:这个值使用base64进行编码压缩， 接收到后需要base64解码才能获取到 原始值
     * 2020-08-19
     */
    private String param4;

    /**
     * 检测人 fef25_75指标预计值
     * 2020-08-19
     */
    private Double prefef2575Value;

    /**
     * 检测人 mef75指标预计值
     * 2020-08-19
     */
    private Double premef75Value;

    /**
     * 检测人 mef50指标预计值
     * 2020-08-19
     */
    private Double premef50Value;

    /**
     * 检测人 mef25指标预计值
     * 2020-08-19
     */
    private Double premef25Value;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public String getCodeVersion() {
        return codeVersion;
    }

    public void setCodeVersion(String codeVersion) {
        this.codeVersion = codeVersion;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getSaleChannel() {
        return saleChannel;
    }

    public void setSaleChannel(String saleChannel) {
        this.saleChannel = saleChannel;
    }

    public String getDetectedTime() {
        return detectedTime;
    }

    public void setDetectedTime(String detectedTime) {
        this.detectedTime = detectedTime;
    }

    public Integer getPefValue() {
        return pefValue;
    }

    public void setPefValue(Integer pefValue) {
        this.pefValue = pefValue;
    }

    public Double getFev1Value() {
        return fev1Value;
    }

    public void setFev1Value(Double fev1Value) {
        this.fev1Value = fev1Value;
    }

    public Double getFvcValue() {
        return fvcValue;
    }

    public void setFvcValue(Double fvcValue) {
        this.fvcValue = fvcValue;
    }

    public Double getFef2575Value() {
        return fef2575Value;
    }

    public void setFef2575Value(Double fef2575Value) {
        this.fef2575Value = fef2575Value;
    }

    public Double getMef75Value() {
        return mef75Value;
    }

    public void setMef75Value(Double mef75Value) {
        this.mef75Value = mef75Value;
    }

    public Double getMef50Value() {
        return mef50Value;
    }

    public void setMef50Value(Double mef50Value) {
        this.mef50Value = mef50Value;
    }

    public Double getMef25Value() {
        return mef25Value;
    }

    public void setMef25Value(Double mef25Value) {
        this.mef25Value = mef25Value;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public String getParam3() {
        return param3;
    }

    public void setParam3(String param3) {
        this.param3 = param3;
    }

    public String getParam4() {
        return param4;
    }

    public void setParam4(String param4) {
        this.param4 = param4;
    }

    public Double getPrefef2575Value() {
        return prefef2575Value;
    }

    public void setPrefef2575Value(Double prefef2575Value) {
        this.prefef2575Value = prefef2575Value;
    }

    public Double getPremef75Value() {
        return premef75Value;
    }

    public void setPremef75Value(Double premef75Value) {
        this.premef75Value = premef75Value;
    }

    public Double getPremef50Value() {
        return premef50Value;
    }

    public void setPremef50Value(Double premef50Value) {
        this.premef50Value = premef50Value;
    }

    public Double getPremef25Value() {
        return premef25Value;
    }

    public void setPremef25Value(Double premef25Value) {
        this.premef25Value = premef25Value;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}