package com.zhenmin.superboot.api.controller;

import com.zhenmin.superboot.api.req.ApiReq;
import com.zhenmin.superboot.api.resp.ApiResp;
import com.zhenmin.superboot.api.util.ApiSecurityUtil;
import com.zhenmin.superboot.api.util.ApiUtil;
import com.zhenmin.superboot.constant.ModuleType;
import com.zhenmin.superboot.constant.RespCode;
import com.zhenmin.superboot.log.LogService;
import com.zhenmin.superboot.util.JsonUtil;
import jdk.net.SocketFlow;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * Created by xuzhenmin on 17-6-5.
 */
@RestController
@RequestMapping("/superBoot")
public class SuperBootController {

	private static final Class LOG_CLASS = SuperBootController.class;
	@Autowired
	private LogService logService;
	@Autowired
	private ApplicationContext appContext;


	/**
	 * @param service 业务类
	 * @param method  目标方法
	 * @return "ok" 默认全部返回ok，异常抛出
	 * @throws
	 */
	@RequestMapping(value = "/{service}/{method}", method = RequestMethod.POST)
	public String consumerProxy(HttpServletRequest request, @PathVariable("service") String service, @PathVariable("method") String method) throws Throwable {
		long startTime = System.currentTimeMillis();
		String encodeRequest = IOUtils.toString(request.getReader());
		String body = this.process(request, encodeRequest, null);
		Object serviceBean = appContext.getBean(service);
		boolean noParams = checkBodyEmpty(body);
		ApiReq apiReq = ApiUtil.checkParam(encodeRequest);
		Method callMethod = noParams ? serviceBean.getClass().getMethod(method) : serviceBean.getClass().getMethod(method, String.class);
		long costTime = System.currentTimeMillis() - startTime;
		ApiResp apiResp = ApiUtil.genApiResp(RespCode.API_COMMON_SUCCESS, SocketFlow.Status.OK.name());
		return SocketFlow.Status.OK.name();
	}

	private boolean checkBodyEmpty(String reqBody) throws Exception {
		if (StringUtils.isBlank(reqBody))
			return true;
		return reqBody.length() <= 2;
	}

	private String process(final HttpServletRequest request, final String encodeRequest, final String reqMethod)
			throws Throwable {
		ApiReq apiReq = ApiUtil.checkParam(encodeRequest);
		ApiSecurityUtil.check(request, apiReq);
		String reqBody = apiReq.getBody();
		logService.info(LOG_CLASS, ModuleType.API_COMMON, reqMethod, reqBody);
		return reqBody;
	}

	private void afterProcess(final long startTime, final ApiReq apiReq, final ApiResp apiResp,
							  final HttpServletRequest request, final String reqMethod) {

		long endTime = System.currentTimeMillis();
		long costTime = endTime - startTime;
		ApiUtil.logApim(apiReq, apiResp, costTime, request, null);
		String logMsg = String.format("maf api success, cost time:%sms, req:%s, resp:%s", costTime, apiReq
				.getBody(), JsonUtil.writeValueQuite(apiResp));
		logService.info(LOG_CLASS, ModuleType.API_COMMON, RespCode.API_COMMON_SUCCESS, reqMethod, logMsg);
	}
}
