package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author fundebug
 * @since 2021-06-27
 */
@Data
public class SysAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限标识
     */
    private String permission;

    @Override
    public String toString() {
        return "SysAuth{" +
            "id=" + id +
            ", name=" + name +
            ", permission=" + permission +
        "}";
    }
}
