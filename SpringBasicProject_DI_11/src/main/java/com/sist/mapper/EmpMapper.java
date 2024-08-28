package com.sist.mapper;
import java.util.*;
/*
 *   "select    a.voc_no "
	+", a.voc_rcp_type_cd
    , b.dtl_cd_nm as voc_rcp_type_cd_nm
    , a.voc_rcp_mthd_cd
    , g.dtl_cd_nm as voc_rcp_mthd_cd_nm
	, a.voc_type_cd
    , h.voc_type_cd_nm as voc_type_cd_nm
	, a.voc_sub_type_cd 
    , i.voc_sub_type_cd_nm as voc_sub_type_cd_nm
	, a.cust_cd
	, a.cont_no
	, a.contr_nm
	, a.cust_div_cd
    , c.dtl_cd_nm as cust_div_cd_nm
	, a.cust_nm
	<!--, SM.FN_MASKING(a.cust_nm, 'NAME', #{userId}, #{pgmId}) as cust_nm-->
	, a.apyr_nm
	<!--, SM.FN_MASKING(apyr_nm, 'NAME', #{userId}, #{pgmId}) as apyr_nm-->
	, a.apyr_tel_no
	, a.apyr_cord_tel_no
	<!--, SM.FN_MASKING(apyr_tel_no, 'PHONE_NUM', #{userId}, #{pgmId}) as apyr_tel_no-->
    , a.rl_trt_cust_nm
    <!--, SM.FN_MASKING(rl_trt_cust_nm, 'NAME', #{userId}, #{pgmId}) as rl_trt_cust_nm-->
    , a.rl_trt_cust_tel_no
    <!--, SM.FN_MASKING(rl_trt_cust_tel_no, 'PHONE_NUM', #{userId}, #{pgmId}) as rl_trt_cust_tel_no-->
	, a.cust_indv_info_agree_yn
	, a.sms_trm_agree_yn
	, a.voc_lctg_cd
    , j.voc_lctg_cd_nm as voc_lctg_cd_nm
	, a.voc_mctg_cd
    , k.voc_mctg_cd_nm as voc_mctg_cd_nm
	, a.voc_sctg_cd
	, l.voc_sctg_cd_nm as voc_sctg_cd_nm
	, decode(l.voc_sctg_cd_nm, null, decode(k.voc_mctg_cd_nm, null, j.voc_lctg_cd_nm, j.voc_lctg_cd_nm || ' > ' || k.voc_mctg_cd_nm), j.voc_lctg_cd_nm || ' > ' || k.voc_mctg_cd_nm || ' > ' || l.voc_sctg_cd_nm ) as voc_type_ctg_nm
	, decode(l.voc_sctg_cd_nm, null, decode(k.voc_mctg_cd_nm, null, j.voc_lctg_cd_nm, j.voc_lctg_cd_nm || ' > ' || k.voc_mctg_cd_nm), j.voc_lctg_cd_nm || ' > ' || k.voc_mctg_cd_nm || ' > ' || l.voc_sctg_cd_nm ) as voc_type_ctg_nm_2
    , a.fclt_cd
    , a.fclt_nm
    , a.std_fac_cd
    , a.fac_nm
    , a.equip_cd
    , a.equip_nm
    , a.dtl_item_cd
    , a.dtl_item_cd as dtl_item_nm
	, to_char(a.rcp_dt,'YYYY-MM-DD HH24:MI:SS') as rcp_dt
	, a.rcpr_id
	, a.rcpr_nm
	, a.rcpr_tel_no
	, a.rcpr_dept_cd
	, a.rcpr_dept_nm
	, a.rcp_sbst
	, a.bldg_dsrt_prov
	, a.bldg_dsrt_city
	, a.bldg_adr
	, a.bldg_cd
	, a.bldg_nm
	, a.voc_rcp_flr
	, a.voc_rcp_dtl_lo
	, a.cmpn_cd
	, a.hq_cd
	, a.rgn_cd
	, a.centr_cd
	, a.group_asset_cd
	, a.group_asset_nm
	, a.fm_coorp_vndr_cd
	, a.cust_dsrt_aptbd
	, a.cust_dsrt_flr
	, a.cust_dsrt_hroom
	, a.pclr_mtr
	, to_char(a.trt_cmplt_dt,'YYYY-MM-DD HH24:MI:SS') as trt_cmplt_dt
	, a.trtr_id
	, a.trtr_nm
	, a.trtr_tel_no
	, a.trtr_dept_cd
	, a.trtr_dept_nm
	, a.trt_sttus_cd
    , d.dtl_cd_nm as trt_sttus_cd_nm
	, a.trt_sbst
	, a.trt_lead_time
	, a.voc_occ_cause_cd
    , e.dtl_cd_nm as voc_occ_cause_cd_nm
	, a.voc_occ_cause_sbst
	, a.hcall_need_yn
	, a.hcall_rqt_sbst
	, a.hcall_trt_sbst
	, a.hcall_trt_cd
	, a.hcall_sdeg_cd
    , f.dtl_cd_nm as hcall_sdeg_cd_nm
	, a.hcall_trt_sbst
	, a.hcall_trt_dt
	, a.hcall_trtr_id
	, a.hcall_trtr_nm
	, a.hcall_trtr_tel_no
	, a.hcall_trtr_dept_cd 
	, a.hcall_trtr_dept_nm
	, a.impt_voc_yn
	, a.dup_voc_yn
	, a.intrct_no
	, a.resid_yn
	, a.atc_file_id
	, a.cons_rqt_no
	, a.cons_nm
	, a.cont_cd
	, a.cont_nm
	, a.vendor_cd
	, a.vendor_nm
from vc.vc_voc_txn a
	left join sm.sm_cd_dtl b on b.sys_id = 'VC' and b.cd_group_id = 'VOC_RCP_TYPE_CD' and a.voc_rcp_type_cd = b.dtl_cd
    left join sm.sm_cd_dtl c on c.sys_id = 'VC' and c.cd_group_id = 'CUST_DIV_CD' and a.cust_div_cd = c.dtl_cd
    left join sm.sm_cd_dtl d on d.sys_id = 'VC' and d.cd_group_id = 'TRT_STTUS_CD' and a.trt_sttus_cd = d.dtl_cd
    left join sm.sm_cd_dtl e on e.sys_id = 'VC' and e.cd_group_id = 'VOC_OCC_CASUE_CD' and a.voc_occ_cause_cd = e.dtl_cd
    left join sm.sm_cd_dtl f on f.sys_id = 'VC' and f.cd_group_id = 'HCALL_SDEG_CD' and a.hcall_sdeg_cd = f.dtl_cd
    left join sm.sm_cd_dtl g on g.sys_id = 'VC' and g.cd_group_id = 'VOC_RCP_MTHD_CD' and a.voc_rcp_mthd_cd = g.dtl_cd
    left join vc.vc_voc_type_ctg h on h.voc_type_cd = a.voc_type_cd and h.voc_sub_type_cd = '9999'
    left join vc.vc_voc_type_ctg i on i.voc_type_cd = a.voc_type_cd and i.voc_sub_type_cd = a.voc_sub_type_cd and i.voc_lctg_cd = '9999999999'
    left join vc.vc_voc_type_ctg j on j.voc_type_cd = a.voc_type_cd and j.voc_sub_type_cd = a.voc_sub_type_cd and j.voc_lctg_cd = a.voc_lctg_cd and j.voc_mctg_cd = '9999999999'
    left join vc.vc_voc_type_ctg k on a.voc_type_cd = k.voc_type_cd and k.voc_sub_type_cd = a.voc_sub_type_cd and k.voc_lctg_cd = a.voc_lctg_cd and k.voc_mctg_cd = a.voc_mctg_cd and k.voc_sctg_cd = '9999999999'
    left join vc.vc_voc_type_ctg l on a.voc_type_cd = l.voc_type_cd and l.voc_sub_type_cd = a.voc_sub_type_cd and l.voc_lctg_cd = a.voc_lctg_cd and l.voc_mctg_cd = a.voc_mctg_cd and l.voc_sctg_cd = a.voc_sctg_cd
	<where>
		<if test = "'data18 != null and data18 != ''">
			and a.voc_no 			LIKE NVL('%'||#{data18}||'%','%') 
		</if>
		<if test = "data18 == null or data18 == ''">	
			and a.cmpn_cd       	= nvl(#{data1}, a.cmpn_cd)
		    and a.hq_cd 			= nvl(#{data2}, a.hq_cd)
		    and a.rgn_cd 			= nvl(#{data3}, a.rgn_cd)
		    and a.centr_cd 			= nvl(#{data4}, a.centr_cd)
		    and (a.group_asset_cd 	isnull or a.group_asset_cd = nvl(#{data5}, a.group_asset_cd))
		    and (a.bldg_cd 			isnull or a.bldg_cd = nvl(#{data6}, a.bldg_cd))
			and a.rcp_dt between #{data7} and #{data8}
			and a.voc_type_cd 		= nvl(#{data9}, a.voc_type_cd)
			<if test = "data10 == null or data10 == ''">
			and (a.voc_sub_type_cd 	isnull or a.voc_sub_type_cd = nvl(#{data10}, a.voc_sub_type_cd))
			</if>
			<if test = "data10 != null and data10 != ''">
			and a.voc_sub_type_cd 	= nvl(#{data10}, a.voc_sub_type_cd)
			</if>
			<if test = "data11 == null or data11 == ''">
			and (a.voc_lctg_cd 		isnull or a.voc_lctg_cd = nvl(#{data11}, a.voc_lctg_cd))
			</if>
			<if test = "data11 != null and data11 != ''">
			and a.voc_lctg_cd 		= nvl(#{data11}, a.voc_lctg_cd)
			</if>
			<if test = "data12 == null or data12 == ''">
			and (a.voc_mctg_cd 		isnull or a.voc_mctg_cd = nvl(#{data12}, a.voc_mctg_cd))
			</if>
			<if test = "data12 != null and data12 != ''">
			and a.voc_mctg_cd 		= nvl(#{data12}, a.voc_mctg_cd)
			</if>
			<if test = "data13 == null or data13 == ''">
			and (a.fclt_cd 			isnull or a.fclt_cd = nvl(#{data13}, a.fclt_cd))
			</if>
			<if test = "data13 != null and data13 != ''">
			and a.fclt_cd 			= nvl(#{data13}, a.fclt_cd)
			</if>
			<if test = "data14 == null or data14 == ''">
			and (a.fac_nm 		isnull or a.fac_nm = nvl(#{data14}, a.fac_nm))
			</if>
			<if test = "data14 != null and data14 != ''">
			and a.fac_nm 		= nvl(#{data14}, a.fac_nm)
			</if>
			and a.rcpr_id 			= nvl(#{data15}, a.rcpr_id)
			and a.trtr_id 			= nvl(#{data16}, a.trtr_id)
			and a.trt_sttus_cd 	    = nvl(#{data17}, a.trt_sttus_cd)
		</if>
	</where>
 order by a.rcp_dt DESC

 */
import com.sist.vo.EmpVO;
public interface EmpMapper {
  /*
   *     <select id="empListData" resultType="EmpVO">
		   SELECT empno,ename,job,TO_CHAR(hiredate,'YYYY-MM-DD') as dbday,sal
		   FROM emp ORDER BY empno ASC
		  </select>
   */
   public List<EmpVO> empListData();
}
