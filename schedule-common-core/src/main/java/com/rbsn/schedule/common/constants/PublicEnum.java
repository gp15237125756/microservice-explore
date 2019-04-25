package com.rbsn.schedule.common.constants;

/**
 * 公共属性枚举
 *
 * @author Jiang
 * @date 2018/1/13
 */
public enum PublicEnum {

    YES("1", true, "是"),NO("0", false, "否");

    private String code;
    private boolean flag;
    private String desc;

    PublicEnum(String code, boolean flag, String desc) {
        this.code = code;
        this.flag = flag;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
