package vip.zihen.spice.workspace.admin.entity;

import lombok.Data;
import vip.zihen.spice.common.entity.BaseEntity;

/**
 * (Admin)实体类
 *
 * @author wangjie
 * @since 2021-01-05 18:10:11
 */
@Data
public class Admin extends BaseEntity {
    private static final long serialVersionUID = -41147043843689422L;
    
    private String username;
    
    private String password;
    
    private String name;


}