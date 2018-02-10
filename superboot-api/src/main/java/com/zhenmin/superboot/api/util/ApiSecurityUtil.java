package com.zhenmin.superboot.api.util;

import com.zhenmin.superboot.api.exception.SuperBootApiException;
import com.zhenmin.superboot.api.req.ApiReq;
import com.zhenmin.superboot.api.req.ApiReqHeader;
import com.zhenmin.superboot.constant.RespCode;
import com.zhenmin.superboot.util.Md5Util;
import com.zhenmin.superboot.util.SuperBootUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by xuzhenmin on 17-6-5.
 */
public abstract class ApiSecurityUtil {

	public static void check(HttpServletRequest httpServletReq, ApiReq apiReq) throws Exception {
		String appId = apiReq.getHeader().getAppId();
		String method = apiReq.getHeader().getMethod();

		ApiSecurityConfig config = ApiSecurityUtil.getSecurityConfigByAppId(appId);
		List<String> ipList = config.getIpList();
		List<String> methodList = config.getMethodList();
		String key = getAppKey(config);

		// 校验IP
		String ip = ApiUtil.getIpAddr(httpServletReq);
		checkIp(ip, ipList, method);

		// 校验方法
		checkMethod(method, methodList);

		// 校验签名
		checkSign(apiReq, key);
	}

	public static String genSign(String appId, String body, String key) {
		StringBuffer sb = new StringBuffer();
		sb.append(appId);
		sb.append(body);
		sb.append(key);
		return Md5Util.getMD5(sb.toString()).toUpperCase();
	}

	private static void checkSign(ApiReq apiReq, String key) throws Exception {
		ApiReqHeader header = apiReq.getHeader();

		String signComp = ApiSecurityUtil.genSign(header.getAppId(), apiReq.getBody(), key);
		String sign = header.getSign();
		if (!StringUtils.equals(sign, signComp)) {
			throw new SuperBootApiException(RespCode.API_COMMON_ERROR_CHECK_SIGN, "request check sign failed");
		}
	}

	private static void checkIp(String ip, List<String> ipList, String method) throws Exception {
		if (ApiAllowIP.ALLOW_METHOD_LIST.contains(method)) {
			return;
		} else {
			if (SuperBootUtil.isOnlineMode() && !ApiUtil.hasIPMatch(ip, ipList)) {
				throw new SuperBootApiException(RespCode.API_COMMON_ERROR_IP_ILLEGAL, "request ip illegal");
			}
		}
	}

	private static void checkMethod(String method, List<String> methodList) throws Exception {
		if (!methodList.contains(method)) {
			throw new SuperBootApiException(RespCode.API_COMMON_ERROR_METHOD_ILLEGAL, "request method illegal");
		}
	}

	private static ApiSecurityConfig getSecurityConfigByAppId(String appId) throws SuperBootApiException {
		ApiSecurityConfig config = ApiSecurityConfig.getByAppId(appId);
		if (config == null) {
			throw new SuperBootApiException(RespCode.API_COMMON_ERROR_SEC_CONF_NOT_FOUND, "api security config not found");
		}
		return config;
	}

	private static String getAppKey(ApiSecurityConfig config) {
		return config.getAppKey();
	}
}
