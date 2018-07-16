/**
 * Copyright &copy; 2015-2020 <a href="http://www.xiaostartTar.com/">XSS</a> All rights reserved.
 */
package com.bzz.cloud.entity;

import java.util.Date;

import lombok.*;

/**
 * 企业核心数据Entity
 * @author admin
 * @version 2018-03-03
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CCustSale  {
	
	private static final long serialVersionUID = 1L;
	private String gongchang;		// 工厂
	private String daqu;		// 大区
	private String chengshi;		// 城市群
	private String yewuyuan;		// 业务员
	private String custNo;		// 客户代码
	private String custName;		// 客户名称
	private String dapinleimiaoshu;		// 大品类描述
	private String yijipinleimiaoshu;		// 一级品类描述
	private String erjipinleimiaoshu;		// 二级品类描述
	private String sanjipinleimiaoshu;		// 三级品类描述
	private String chanpinxianmiaoshu;		// 产品线描述
	private String wuliaobianma;		// 物料编码
	private String wuliaomiaoshu;		// 物料描述
	private String xiang;		// 箱
	private String dun;		// 吨
	private String xiaoshoushouru;		// 销售收入
	private String jingzhi;		// 净值
	private String shuie;		// 税额
	private String zhanlvjine;		// 战略价金额
	private String zhekoujine;		// 折扣金额
	private String zhekoubili;		// 折扣百分比
	private String shoudafangjiancheng;		// 售达方简称
	private Date fapiaoshiqi;		// 出具发票日期
	private String dingdanbianhao;		// 订单号码
	private Date danjuriqi;		// 单据日期
	private String kucundidian;		// 库存地点
	private String caigoubianhao;		// 采购订单号码
	private Date beginFapiaoshiqi;		// 开始 出具发票日期
	private Date endFapiaoshiqi;		// 结束 出具发票日期
	
	
}