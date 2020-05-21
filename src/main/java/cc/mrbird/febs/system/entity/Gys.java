package cc.mrbird.febs.system.entity;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 供应商 Entity
 *
 * @author lbx
 * @date 2020-05-16 19:58:42
 */
@Data
@TableName("system_gys")
public class Gys {

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 供应商代码
     */
    @TableField("gysdm")
    private String gysdm;

    /**
     * 供应商名称
     */
    @TableField("gysmc")
    private String gysmc;

    /**
     * 联系人
     */
    @TableField("lsr")
    private String lsr;

    /**
     * 联系人电话
     */
    @TableField("lsrdh")
    private String lsrdh;

    /**
     * 联系人微信
     */
    @TableField("wx")
    private String wx;

    /**
     * 联系人QQ
     */
    @TableField("qq")
    private String qq;

    /**
     * 行业
     */
    @TableField("hy")
    private String hy;

    /**
     * 采购负责人 用户表id
     */
    @TableField("cgfzr")
    private String cgfzr;

    /**
     * 下单负责人 用户表id
     */
    @TableField("xdfzr")
    private String xdfzr;

    /**
     * 发票类型ID
     */
    @TableField("fplx_id")
    private Integer fplxId;

    /**
     * 发票税点ID
     */
    @TableField("fpsd_id")
    private Integer fpsdId;

    /**
     * 是否月结
     */
    @TableField("sfyj")
    private String sfyj;

    /**
     * 备注
     */
    @TableField("memo")
    private String memo;

    /**
     * 备用1
     */
    @TableField("ex1")
    private String ex1;

    /**
     * 备用2
     */
    @TableField("ex2")
    private String ex2;

    /**
     * 备用3
     */
    @TableField("ex3")
    private String ex3;

    /**
     * 是否启用 0:启用 1:停用
     */
    @TableField("status")
    private Integer status;

}
