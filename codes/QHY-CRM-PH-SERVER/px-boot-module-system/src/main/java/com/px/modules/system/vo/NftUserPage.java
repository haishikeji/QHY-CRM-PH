package com.px.modules.system.vo;

import java.util.List;

import lombok.Data;
import com.px.modules.system.entity.NftOrderInfo;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import com.px.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: NFT用户信息
 * @@author 品讯科技
 * @Date:   2022-06-18
 * @Version: V1.0
 */
@Data
@ApiModel(value="nft_userPage对象", description="NFT用户信息")
public class NftUserPage {

	/**主键*/
	@ApiModelProperty(value = "主键")
    private String id;
	/**用户ID*/
	@Excel(name = "用户ID", width = 15)
	@ApiModelProperty(value = "用户ID")
    private String userId;
	/**注册时间*/
	@ApiModelProperty(value = "注册时间")
    private Date createTime;
	/**创建人*/
	@ApiModelProperty(value = "创建人")
    private String createBy;
	/**更新人*/
	@ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新日期*/
	@ApiModelProperty(value = "更新日期")
    private Date updateTime;
	/**头像*/
	@Excel(name = "头像", width = 15)
	@ApiModelProperty(value = "头像")
    private String headimg;
	/**openid支付用*/
	@Excel(name = "openid支付用", width = 15)
	@ApiModelProperty(value = "openid支付用")
    private String openid;
	/**名称*/
	@Excel(name = "名称", width = 15)
	@ApiModelProperty(value = "名称")
    private String name;
	/**手机*/
	@Excel(name = "手机", width = 15)
	@ApiModelProperty(value = "手机")
    private String phone;
	/**推荐人ID*/
	@Excel(name = "推荐人ID", width = 15)
	@ApiModelProperty(value = "推荐人ID")
    private Integer pid;
	/**登录密码*/
	@Excel(name = "登录密码", width = 15)
	@ApiModelProperty(value = "登录密码")
    private String password;
	/**二级密码*/
	@Excel(name = "二级密码", width = 15)
	@ApiModelProperty(value = "二级密码")
    private String secondPassword;
	/**状态*/
	@Excel(name = "状态", width = 15, dicCode = "vipstatus")
    @Dict(dicCode = "vipstatus")
	@ApiModelProperty(value = "状态")
    private Integer status;
	/**创建钱包的账号*/
	@Excel(name = "创建钱包的账号", width = 15)
	@ApiModelProperty(value = "创建钱包的账号")
    private String accountname;
	/**钱包地址*/
	@Excel(name = "钱包地址", width = 15)
	@ApiModelProperty(value = "钱包地址")
    private String walletAddress;
	/**钱包地址是否开通官方ddc*/
	@Excel(name = "钱包地址是否开通官方ddc", width = 15)
	@ApiModelProperty(value = "钱包地址是否开通官方ddc")
    private Integer isOpenOfficialDdc;
	/**转赠次数*/
	@Excel(name = "转赠次数", width = 15)
	@ApiModelProperty(value = "转赠次数")
    private Integer examplesNumber;
	/**余额*/
	@Excel(name = "余额", width = 15)
	@ApiModelProperty(value = "余额")
    private java.math.BigDecimal money;
	/**身份1藏友2大师*/
	@Excel(name = "身份1藏友2大师", width = 15)
	@ApiModelProperty(value = "身份1藏友2大师")
    private Integer vip;
	/**积分*/
	@Excel(name = "积分", width = 15)
	@ApiModelProperty(value = "积分")
    private Integer integral;
	/**真实姓名*/
	@Excel(name = "真实姓名", width = 15)
	@ApiModelProperty(value = "真实姓名")
    private String trueName;
	/**身份证号*/
	@Excel(name = "身份证号", width = 15)
	@ApiModelProperty(value = "身份证号")
    private String idCard;
	/**是否认证*/
	@Excel(name = "是否认证", width = 15, dicCode = "is_auth")
    @Dict(dicCode = "is_auth")
	@ApiModelProperty(value = "是否认证")
    private Integer isAuth;
	/**认证时间*/
	@Excel(name = "认证时间", width = 15)
	@ApiModelProperty(value = "认证时间")
    private Date authAt;
	/**邀请二维码*/
	@Excel(name = "邀请二维码", width = 15)
	@ApiModelProperty(value = "邀请二维码")
    private String inviteImg;
	/**邀请链接*/
	@Excel(name = "邀请链接", width = 15)
	@ApiModelProperty(value = "邀请链接")
    private String inviteAddress;
	/**抽奖次数*/
	@Excel(name = "抽奖次数", width = 15)
	@ApiModelProperty(value = "抽奖次数")
    private Integer lotteryNumber;
	/**支付宝账号*/
	@Excel(name = "支付宝账号", width = 15)
	@ApiModelProperty(value = "支付宝账号")
    private String zfbAccount;
	/**支付宝实名的姓名*/
	@Excel(name = "支付宝实名的姓名", width = 15)
	@ApiModelProperty(value = "支付宝实名的姓名")
    private String zfbRealName;
	/**邀请码*/
	@Excel(name = "邀请码", width = 15)
	@ApiModelProperty(value = "邀请码")
    private String inviteCode;
	
	@ExcelCollection(name="收藏明细")
	@ApiModelProperty(value = "收藏明细")
	private List<NftOrderInfo> nftOrderInfoList;
	
}
