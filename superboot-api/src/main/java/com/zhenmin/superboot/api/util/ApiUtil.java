package com.zhenmin.superboot.api.util;

import com.zhenmin.superboot.Exception.SuperBootException;
import com.zhenmin.superboot.api.exception.SuperBootApiException;
import com.zhenmin.superboot.api.req.ApiReq;
import com.zhenmin.superboot.api.req.ApiReqHeader;
import com.zhenmin.superboot.api.resp.ApiResp;
import com.zhenmin.superboot.api.resp.ApiRespHeader;
import com.zhenmin.superboot.api.vo.ApimLogVO;
import com.zhenmin.superboot.constant.RespCode;
import com.zhenmin.superboot.util.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by xuzhenmin on 17-6-5.
 */
public abstract class ApiUtil {

	private static final Logger loggerApim = LoggerFactory.getLogger("com.mi.maf.apim.logger");

	public static ApiReq checkParam(String encodeReq) throws SuperBootApiException, IOException {
		if (StringUtils.isEmpty(encodeReq)) {
			throw new SuperBootApiException(RespCode.API_COMMON_ERROR_PARAM, "param data not exist");
		}

		ApiReq apiReq = getApiReq(encodeReq);
		if (apiReq == null || apiReq.getHeader() == null) {
			throw new SuperBootApiException(RespCode.API_COMMON_ERROR_PARAM, "param basic format error");
		}
		ApiReqHeader apiReqHeader = apiReq.getHeader();
		if (StringUtils.isEmpty(apiReqHeader.getAppId()) || StringUtils.isEmpty(apiReqHeader.getSign()) || StringUtils.isEmpty(apiReqHeader.getMethod())) {
			throw new SuperBootApiException(RespCode.API_COMMON_ERROR_PARAM, "param header format error");
		}

		return apiReq;
	}

	public static <E> void checkParamByValidator(List<E> list) throws Exception {
		if (list != null) {
			for (E obj : list) {
				checkParamByValidator(obj);
			}
		}
	}

	public static <E> void checkParamByValidator(E obj) throws Exception {
		ValidatorUtil.checkParam(obj, SuperBootApiException.class, RespCode.API_COMMON_ERROR_PARAM);
	}

	public static String getIpAddr(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}

	public static boolean hasIPMatch(String clientAddr, List<String> ipExps) {
		for (String ip : ipExps) {
			//如果是内网IP地址，匹配前两个段
			if (isInterIp(clientAddr, ip)) return true;
			//IP段匹配 中间使用"-"分割
			if (isRangeIp(clientAddr, ip)) return true;
			if (clientAddr.matches(ip)) return true;
		}
		return false;
	}

	public static ApiResp genApiResp(Integer respCode, String desc, Object body) {
		ApiResp apiResp = new ApiResp();
		ApiRespHeader apiRespHeader = new ApiRespHeader();
		apiRespHeader.setCode(respCode);
		apiRespHeader.setDesc(desc);
		apiResp.setHeader(apiRespHeader);
		apiResp.setBody(body);
		return apiResp;
	}

	public static ApiResp genApiResp(SuperBootException e) {
		return ApiUtil.genApiResp(e.getCode(), e.getMessage(), StringUtils.EMPTY);
	}

	public static ApiResp genApiResp(Integer respCode, String desc) {
		return ApiUtil.genApiResp(respCode, desc, StringUtils.EMPTY);
	}

	public static ApiResp genApiResp(Integer respCode, String desc, Exception e) {
		return ApiUtil.genApiResp(respCode, desc, ExceptionUtil.getStackTraceInfo(e));
	}

	public static ApiReq genApiReq(String appId, String key, String method, Object body) {
		String bodyStr = JsonUtil.writeValueQuite(body);

		ApiReqHeader apiReqHeader = new ApiReqHeader();
		apiReqHeader.setAppId(appId);
		apiReqHeader.setMethod(method);
		apiReqHeader.setOperatorId(StringUtils.EMPTY);
		apiReqHeader.setOperatorName(StringUtils.EMPTY);
		apiReqHeader.setSign(ApiSecurityUtil.genSign(appId, bodyStr, key));

		ApiReq apiReq = new ApiReq();
		apiReq.setHeader(apiReqHeader);
		apiReq.setBody(bodyStr);

		return apiReq;
	}

	public static void logApim(ApiReq req, ApiResp resp, long costTime, HttpServletRequest httpReq, Exception ex) {
		try {
			String method = req.getHeader().getMethod();
			String appId = req.getHeader().getAppId();
			ApiMethod apiMethod = ApiMethod.get(method);

			ApimLogVO logVO = new ApimLogVO();
			if (apiMethod != null) {
				logVO.setLog_app_id(ApiConstant.APIM_LOG_APP_ID);
				logVO.setSC(apiMethod.getSign());
				logVO.setSS(apiMethod.getServiceSign());

				logVO.setLog_biz_id(apiMethod.getSign());
				logVO.setLog_other_id(appId);
				logVO.setT(DateUtil.date2str(new Date()));
				logVO.setF(req.getHeader().getMethod());
				if (resp != null) {
					logVO.setRL(JsonUtil.writeValueQuite(resp).length());
				}
				logVO.setS(ApiConstant.SERVER_IP);
				logVO.setC(getIpAddr(httpReq));
				if (ex != null) {
					if (ex instanceof SuperBootException) {
						logVO.setRC(String.valueOf(((SuperBootException) ex).getCode()));
					} else {
						logVO.setRC(String.valueOf(RespCode.EXP_OTHERS));
					}
					logVO.setEB(ex.getMessage());
					logVO.setED(ExceptionUtil.getStackTraceInfo(ex));
					;
				}
				BigDecimal costTimeB = new BigDecimal(costTime);
				BigDecimal divide = new BigDecimal(1000);
				logVO.setRT(String.valueOf(costTimeB.divide(divide, 3, BigDecimal.ROUND_HALF_UP)));
				loggerApim.info(JsonUtil.writeValueQuite(logVO));
			}
		} catch (Exception e) {
			loggerApim.error("log apim error", e);
		}
	}

	private static boolean isRangeIp(String checkIp, String ipRange) {
		if (StringUtils.isEmpty(checkIp) || StringUtils.isEmpty(ipRange)) {
			return false;
		}
		if (ipRange.indexOf("-") == 0) {
			return false;
		}

		String[] ipRangeArr = ipRange.split("-");
		if (ipRangeArr == null || ipRangeArr.length != 2) {
			return false;
		}
		String ipRangeFrom = ipRangeArr[0];
		String ipRangeTo = ipRangeArr[1];

		String[] checkIpArr = checkIp.split("\\.");
		String[] ipRangeFromArr = ipRangeFrom.split("\\.");
		String[] ipRangeToArr = ipRangeTo.split("\\.");

		for (int i = 0; i < 4; i++) {
			int tempIp = Integer.valueOf(checkIpArr[i]);
			int tempIpRangeFrom = Integer.valueOf(ipRangeFromArr[i]);
			int tempIpRangeTo = Integer.valueOf(ipRangeToArr[i]);
			if (!MathUtil.isIntInRange(tempIp, tempIpRangeFrom, tempIpRangeTo)) {
				return false;
			}
		}
		return true;
	}

	private static boolean isInterIp(String clientIP, String allowIP) {
		boolean isAllowIP = false;
		String[] interIPs = new String[]{"10", "172", "192"};// 内网IP地址开始段
		String[] clientIPs = clientIP.split("\\.");// 以点号分割客户端IP地址为数组
		if (clientIPs == null || clientIPs.length == 0) return isAllowIP;
		String startClientIP = clientIPs[0];// 获取客户端IP地址的第一位

		boolean isInterIP = Arrays.asList(interIPs).contains(startClientIP);// 判断客户端地址是否属于内网地址
		if (isInterIP) {
			String[] ip = allowIP.split("\\.");
			String regex = ip[0] + "." + ip[1];
			if (clientIP.matches("^(" + regex + ")(.*)$")) {
				isAllowIP = true;
			} else {
				isAllowIP = false;
			}
		}
		return isAllowIP;
	}

	private static ApiReq getApiReq(String encodeReq) throws IOException {
		String decodeReq = new String(Base64Utils.decodeFromString(encodeReq));
		ApiReq apiReq = JsonUtil.parse(decodeReq, ApiReq.class);
		return apiReq;
	}
}
