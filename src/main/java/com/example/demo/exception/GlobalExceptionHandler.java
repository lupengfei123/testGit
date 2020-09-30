package com.example.demo.exception;

import com.example.demo.consts.ResultPacketCode;
import com.example.demo.pojo.APIResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.util.StringUtil;

import javax.servlet.http.HttpServletRequest;

/**
 *          类说明
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public APIResult operateExp(RuntimeException ex, HttpServletRequest request) {
        APIResult result = new APIResult();
        if (ex instanceof AdminPermissionDeniedException
                || ex instanceof CustomParamCheckException
                || ex instanceof TooFrequentException
                || ex instanceof PermissionDeniedException) {
            result.setCode( ((CustomException) ex).getCode());
            result.setMessage(ex.getMessage());
            return result;
        }
        result.setCode(ResultPacketCode.APIResultCode.Fail.getCode());
        if(StringUtil.isNotEmpty(ex.getMessage())){
            result.setMessage(ex.getMessage());
        }else{
            result.setMessage("操作失败");
        }
        return result;
    }
}
