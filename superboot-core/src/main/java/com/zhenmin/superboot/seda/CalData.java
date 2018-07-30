/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.zhenmin.superboot.seda;

import java.io.Serializable;

/**
 * @author zhenmin
 * @version $Id: CalData.java, v 0.1 2018-07-30 下午5:16 zhenmin Exp $$
 */
public class CalData implements Serializable {

    private static final long serialVersionUID = 9094119450263826263L;

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}