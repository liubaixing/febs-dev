package cc.mrbird.febs.shangpin.entity;

import java.math.BigDecimal;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 商品 Entity
 *
 * @author MrBird
 * @date 2020-05-16 20:03:47
 */
@Data
@TableName("shangpin")
public class Shangpin {

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品代码
     */
    @TableField("spdm")
    private String spdm;

    /**
     * 商品名称
     */
    @TableField("spmc")
    private String spmc;

    /**
     * 唯一码
     */
    @TableField("memo")
    private String memo;

    /**
     * 销售价
     */
    @TableField("xsj")
    private BigDecimal xsj;
    /**
     * 吊牌价
     */
    @TableField("dpj")
    private BigDecimal dpj;
    /**
     * 预售价
     */
    @TableField("ysj")
    private BigDecimal ysj;
    /**
     * 采购价
     */
    @TableField("cgj")
    private BigDecimal cgj;
    /**
     * 默认供应商 供应商表id
     */
    @TableField("gys_id")
    private Integer gysId;

    /**
     * 起订量
     */
    @TableField("qdl")
    private Integer qdl;

    /**
     * 计量单位 计量单位id
     */
    @TableField("jldw_id")
    private Integer jldwId;

    /**
     * 默认仓库 仓库id
     */
    @TableField("ck_id")
    private Integer ckId;

    /**
     * 默认供应商图片
     */
    @TableField("mrtp")
    private String mrtp;

    /**
     * 总类 Shangpin_zl总类id
     */
    @TableField("zl_id")
    private Integer zlId;

    /**
     * 大类 Shangpin_dl大类id
     */
    @TableField("dl_id")
    private Integer dlId;

    /**
     * 小类 Shangpin_xl小类id
     */
    @TableField("xl_id")
    private Integer xlId;

    /**
     * 其他类别 Shangpin_qtlb其他类别id
     */
    @TableField("qtlb_id")
    private Integer qtlbId;

    /**
     * 是否启用 0:启用 1:停用
     */
    @TableField("status")
    private Integer status;

}
