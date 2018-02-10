package com.zhenmin.superboot.service.logic.impl;

import com.zhenmin.superboot.dao.master.repository.SuperBootMapper;
import com.zhenmin.superboot.service.logic.SuperBootService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xuzhenmin on 17-5-31.
 */
@Service
@Slf4j
public class SuperBootServiceImpl implements SuperBootService {

    @Autowired
    private SuperBootMapper superBootMapper;
}
