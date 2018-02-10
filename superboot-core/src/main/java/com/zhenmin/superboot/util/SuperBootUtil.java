package com.zhenmin.superboot.util;

import com.google.common.collect.ImmutableSet;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by xuzhenmin on 17-6-5.
 */
@Component
public class SuperBootUtil implements InitializingBean {

	private static final String PRODUCT = "product";
	private static Set<String> CURR_PROFILES;
	@Autowired
	private Environment environment;


	/**
	 * 是否线上环境
	 *
	 * @return boolean
	 */
	public static boolean isOnlineMode() {
		return false;
//		return CURR_PROFILES.contains(PRODUCT);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		String[] tags = environment.getActiveProfiles();
		CURR_PROFILES = ImmutableSet.copyOf(tags);
	}
}
