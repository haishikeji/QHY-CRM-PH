package com.px.modules.aa.service.impl;

import com.px.modules.aa.entity.AdminKhgl;
import com.px.modules.aa.entity.AdminKhglXqmx;
import com.px.modules.aa.entity.AdminKhglSbmx;
import com.px.modules.aa.mapper.AdminKhglXqmxMapper;
import com.px.modules.aa.mapper.AdminKhglSbmxMapper;
import com.px.modules.aa.mapper.AdminKhglMapper;
import com.px.modules.aa.service.IAdminKhglService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 客户管理
 * @@author 品讯科技
 * @Date:   2022-11-25
 * @Version: V1.0
 */
@Service
public class AdminKhglServiceImpl extends ServiceImpl<AdminKhglMapper, AdminKhgl> implements IAdminKhglService {

	@Autowired
	private AdminKhglMapper adminKhglMapper;
	@Autowired
	private AdminKhglXqmxMapper adminKhglXqmxMapper;
	@Autowired
	private AdminKhglSbmxMapper adminKhglSbmxMapper;
	
	@Override
	@Transactional
	public void saveMain(AdminKhgl adminKhgl, List<AdminKhglXqmx> adminKhglXqmxList,List<AdminKhglSbmx> adminKhglSbmxList) {
		adminKhglMapper.insert(adminKhgl);
		if(adminKhglXqmxList!=null && adminKhglXqmxList.size()>0) {
			for(AdminKhglXqmx entity:adminKhglXqmxList) {
				//外键设置
				entity.setKhglId(adminKhgl.getId());
				adminKhglXqmxMapper.insert(entity);
			}
		}
		if(adminKhglSbmxList!=null && adminKhglSbmxList.size()>0) {
			for(AdminKhglSbmx entity:adminKhglSbmxList) {
				//外键设置
				entity.setKhglId(adminKhgl.getId());
				adminKhglSbmxMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(AdminKhgl adminKhgl,List<AdminKhglXqmx> adminKhglXqmxList,List<AdminKhglSbmx> adminKhglSbmxList) {
		adminKhglMapper.updateById(adminKhgl);
		
		//1.先删除子表数据
		adminKhglXqmxMapper.deleteByMainId(adminKhgl.getId());
		adminKhglSbmxMapper.deleteByMainId(adminKhgl.getId());
		
		//2.子表数据重新插入
		if(adminKhglXqmxList!=null && adminKhglXqmxList.size()>0) {
			for(AdminKhglXqmx entity:adminKhglXqmxList) {
				//外键设置
				entity.setKhglId(adminKhgl.getId());
				entity.setCreateTime(adminKhgl.getCreateTime());
				entity.setUpdateTime(new Date(  ) );
				adminKhglXqmxMapper.insert(entity);
			}
		}
		if(adminKhglSbmxList!=null && adminKhglSbmxList.size()>0) {
			for(AdminKhglSbmx entity:adminKhglSbmxList) {
				//外键设置
				entity.setKhglId(adminKhgl.getId());
				entity.setCreateTime(adminKhgl.getCreateTime());
				entity.setUpdateTime(new Date(  ) );
				adminKhglSbmxMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		adminKhglXqmxMapper.deleteByMainId(id);
		adminKhglSbmxMapper.deleteByMainId(id);
		adminKhglMapper.deleteById(id);
	}
	@Override
	@Transactional
	public void delMainKhgl(String id) {
		adminKhglMapper.deleteById(id);
	}
	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			adminKhglXqmxMapper.deleteByMainId(id.toString());
			adminKhglSbmxMapper.deleteByMainId(id.toString());
			adminKhglMapper.deleteById(id);
		}
	}
	
}
