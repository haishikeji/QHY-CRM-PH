package com.px.modules.aa.service;

import com.px.modules.aa.entity.AdminKhglXqmx;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 需求明细
 * @@author 品讯科技
 * @Date:   2022-11-25
 * @Version: V1.0
 */
public interface IAdminKhglXqmxService extends IService<AdminKhglXqmx> {

	public List<AdminKhglXqmx> selectByMainId(String mainId);
}
