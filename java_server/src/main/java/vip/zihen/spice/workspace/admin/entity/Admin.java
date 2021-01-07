package vip.zihen.spice.workspace.admin.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Admin)实体类
 *
 * @author wangjie
 * @since 2021-01-05 18:10:11
 */
@Data
public class Admin implements Serializable {
    private static final long serialVersionUID = -41147043843689422L;
    
    private Integer id;
    
    private String username;
    
    private String password;
    
    private String name;


}