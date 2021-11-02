package com.flower.demo.common.response;

public enum CommonResponseCodeEnum implements ResponseCode {
    /**
     *
     */
    SUCCESS("200", "操作成功", 0),
    FINISH("201", "已完成所有题目", 0),
    CAN_NOT_FOUND_QUESTION("202", "该试卷无题目", 0),
    SERVER_ERROR("500", "服务器错误", 0),
    SERVER_SHUTDOWN("404", "服务器关闭", 0),
    SERVER_BUSY("503", "服务器忙", 0),
    PARAM_ERROR("416", "参数错误：%s", 1),
    NO_PERMISSION("502", "无权限", 0),
    TOKEN_DISABLED("504", "token失效", 0),
    PARAM_EMPTY("401", "传入参数错误", 0),
    VERIFICATION_CODE_EMPTY("402", "验证码失效", 0),
    VERIFICATION_CODE_ERROR("403", "验证码错误", 0),
    FAIL_REGISTER("406", "用户注册失败", 0),
    LOGIN_REGISTER("407", "用户登录失败", 0),
    USER_NOT_EXIST("408", "用户不存在", 0),
    ACCOUNT_PASSWORD_ERROR("409", "账号密码错误", 0),
    UPDATE_FAIL("410", "更新失败", 0),
    ACCOUNT_EXIST("411","账户已存在",0),
    FAIL_ERROR("417","分数记录失败",0),
    UNFINISH("417","未完成所有题目", 0),
    NOT_FOUND_OPTION("418","没有发现此项",0),
    CAN_NOT_DELETE("418","不能删除该选项",0),
    SYSTEM_EXCEPTION("-1", "系统异常", 0),
    /*NEED_LOGIN("999900005", "用户需要登录", 0),
    NOT_FOUND("999900011", "不存在", 0),
    ALREADY_EXISTS("999900012", "已存在", 0),
    ALREADY_LOCKED("999900021", "已锁定", 0),
    LOCK_FAILURE("999900022", "锁定异常", 0),
    DB_FAILURE("999900031", "数据库异常", 0),
    NETWORK_FAILURE("999900032", "网络异常", 0),
    ADAPTOR_FAILURE("999900033", "外部接口异常", 0),
    UPLOAD_FAIL("999900034", "上传失败", 0),
    GET_DISTRIBUTED_LOCK_FAILURE("999900035", "获取分布式锁异常", 0)*/;


    /**
     * code码
     */
    private String code;
    /**
     * 信息
     */
    private String message;
    /**
     * 是否有参数
     */
    private int args;

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public int getArgs() {
        return this.args;
    }

    private CommonResponseCodeEnum(String code, String message, int args) {
        this.code = code;
        this.message = message;
        this.args = args;
    }
}
