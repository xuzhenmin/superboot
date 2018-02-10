package com.zhenmin.superboot.common.util;

import com.zhenmin.superboot.Exception.SuperBootException;
import com.zhenmin.superboot.common.request.InterReq;
import com.zhenmin.superboot.common.request.InterReqHeader;
import com.zhenmin.superboot.common.response.InterResp;
import com.zhenmin.superboot.common.response.InterRespHeader;
import com.zhenmin.superboot.util.ExceptionUtil;
import com.zhenmin.superboot.util.JsonUtil;
import com.zhenmin.superboot.util.Md5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Base64Utils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public abstract class InterUtil {


    public static String getIpAddr(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }

    public static InterResp genInterResp(Integer respCode, String desc, Object body) {
        InterResp interResp = new InterResp();
        InterRespHeader interRespHeader = new InterRespHeader();
        interRespHeader.setCode(respCode).setDesc(desc);
        interResp.setHeader(interRespHeader).setBody(body);
        return interResp;
    }

    public static InterResp genInterResp(SuperBootException e) {
        return InterUtil.genInterResp(e.getCode(), e.getMessage(), StringUtils.EMPTY);
    }

    public static InterResp genInterResp(Integer respCode, String desc) {
        return InterUtil.genInterResp(respCode, desc, StringUtils.EMPTY);
    }

    public static InterResp genInterResp(Integer respCode, String desc, Exception e) {
        return InterUtil.genInterResp(respCode, desc, ExceptionUtil.getStackTraceInfo(e));
    }

    public static String genSign(String appId, String body, String key) {
        String sb = appId + body + key;
        return Md5Util.getMD5(sb).toUpperCase();
    }


    private static InterReq getInterReq(String encodeReq) throws IOException {
        String decodeReq = new String(Base64Utils.decodeFromString(encodeReq));
        return JsonUtil.parse(decodeReq, InterReq.class);
    }

    /**
     *
     * @param appId
     * @param key
     * @param method
     * @param body 任意对象
     * @return
     */
    public static InterReq genInterReq(String appId, String key, String method, Object body) {
        String bodyStr = JsonUtil.writeValueQuite(body);

        InterReqHeader interReqHeader = new InterReqHeader();
        interReqHeader.setAppId(appId).setMethod(method).setOperatorId(StringUtils.EMPTY);
        interReqHeader.setOperatorName(StringUtils.EMPTY).setSign(genSign(appId, bodyStr, key));
        InterReq interReq = new InterReq();
        interReq.setHeader(interReqHeader).setBody(bodyStr);
        return interReq;
    }

}
