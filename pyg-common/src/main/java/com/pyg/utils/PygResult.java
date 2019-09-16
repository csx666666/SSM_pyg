package com.pyg.utils;/**
 * @author DingZhenYun
 * @create 2019-09-15 下午 14:35
 */

import java.io.Serializable;

/**
 * @program: SSM_pyg->PygResult
 * @description:
 **/
public class PygResult implements Serializable {
    private boolean success;

    private String message;

    public PygResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
