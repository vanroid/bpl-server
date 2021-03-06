/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bpl.tucao.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 吐槽热点Entity
 * @author yongdaicui
 * @version 2017-08-11
 */
public class BplHot extends DataEntity<BplHot> {
	
	private static final long serialVersionUID = 1L;
	private Integer tucaoCount;		// 支持次数
	private String content;		// 内容
	private Integer status;		// 状态
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间
	
	public BplHot() {
		super();
	}

	public BplHot(String id){
		super(id);
	}

	public Integer getTucaoCount() {
		return tucaoCount;
	}

	public void setTucaoCount(Integer tucaoCount) {
		this.tucaoCount = tucaoCount;
	}
	
	@Length(min=0, max=255, message="内容长度必须介于 0 和 255 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}