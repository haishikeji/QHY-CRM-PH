package com.px.modules.aa.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 用户管理
 * @@author 品讯科技
 * @Date:   2022-12-16
 * @Version: V1.0
 */
@Data
@TableName("admin_user")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="admin_user对象", description="用户管理")
public class AdminUser implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
    private String name;
	/**手机号*/
	@Excel(name = "手机号", width = 15)
    @ApiModelProperty(value = "手机号")
    private String phone;

    @Excel(name = "联系方式", width = 15)
    @ApiModelProperty(value = "联系方式")
    private String contactInformation;
	/**头像*/
    @ApiModelProperty(value = "头像")
    private String avatar;
	/**密码*/
    @ApiModelProperty(value = "密码")
    private String password;
	/**md5密码盐*/
    @ApiModelProperty(value = "md5密码盐")
    private String salt;

	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建时间*/
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新时间*/
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
