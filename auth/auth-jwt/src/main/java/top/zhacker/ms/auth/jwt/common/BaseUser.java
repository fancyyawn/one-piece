package top.zhacker.ms.auth.jwt.common;

import java.io.Serializable;

public interface BaseUser extends Serializable {

    /**
     * 获取用户id
     * @return id
     */
    String getId();

    /**
     * 获取用户名 username
     * @return username
     */
    String getName();

    /**
     * 获取用户类型 整形 含义由子类自行定义
     * @return 用户类型
     */
    Integer getType();

    /**
     * 获取用户的规则列表
     * @return 规则列表
     */
    String getRoles();
}