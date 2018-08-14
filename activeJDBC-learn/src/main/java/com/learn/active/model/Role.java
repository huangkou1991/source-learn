package com.learn.active.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

/**
 * @author :lwy
 * @date 2018/6/3 10:41
 */
@Table(value = "EMP_ROLES")
public class Role extends Model {
    public Role(){

    }

    public Role(String role,String createdBy){
        set("role_name",role);
        set("created_by",createdBy);
    }
}
