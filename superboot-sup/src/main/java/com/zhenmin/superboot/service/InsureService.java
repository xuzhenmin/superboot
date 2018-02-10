package com.zhenmin.superboot.service;

import com.zhenmin.superboot.base.exception.MinException;
import com.zhenmin.superboot.base.vo.resp.InsureVO;

/**
 * Created by xuzhenmin on 17-5-31.
 */
public interface InsureService {

	InsureVO queryInsure(Long orderId, String sn, String imei, Integer bizTime) throws MinException;

}
