package com.example.demo.pojo;

import com.example.demo.consts.ResultPacketCode;
import org.apache.poi.ss.formula.functions.T;

public class ResultGenerator {


    /**
     * 成功不含参
     * @return
     */
    public static APIResult<T> getSuccessResult() {
        APIResult<T> result = new APIResult<>();
        result.setCode(ResultPacketCode.APIResultCode.Success.getCode());
        return result;
    }

    /**
     * 成功含参
     * @param data
     * @return
     */
    public static APIResult getSuccessResult(Object data) {
        APIResult result = new APIResult<>();
        result.setCode(ResultPacketCode.APIResultCode.Success.getCode());
        result.setResult(data);
        return result;
    }

    /**
     * 成功不含参含成功信息
     * @param message
     * @return
     */
    public static APIResult getSuccessMessage(String message) {
        APIResult result = new APIResult<>();
        result.setCode(ResultPacketCode.APIResultCode.Success.getCode());
        result.setMessage(message);
        return result;
    }

    /**
     * 成功包含分页信息
     * @param data
     * @param recordSize
     * @param isEnd
     * @return
     */
    public static APIResult getSuccessPagerResult(Object data,int recordSize,Boolean isEnd) {
        APIResult result = new APIResult<>();
        result.setPager(new Pager(recordSize,isEnd));
        result.setResult(data);
        result.setCode(ResultPacketCode.APIResultCode.Success.getCode());
        return result;
    }

    /**
     * 失败不含参
     * @return
     */
    public static APIResult<T> getFailResult() {
        APIResult<T> result = new APIResult<>();
        result.setCode(ResultPacketCode.APIResultCode.Fail.getCode());
        return result;
    }

    /**
     * 失败含报错信息
     * @return
     */
    public static APIResult<T> getFailResult(String message) {
        APIResult result = new APIResult<>();
        result.setMessage(message);
        result.setCode(ResultPacketCode.APIResultCode.Fail.getCode());
        return result;
    }

    /**
     * 失败含[param Message]
     * @return
     */
    public static APIResult<T> getFailResult(Object data, String message) {
        APIResult result = new APIResult();
        result.setResult(data);
        result.setMessage(message);
        result.setCode(ResultPacketCode.APIResultCode.Fail.getCode());
        return result;
    }


}
