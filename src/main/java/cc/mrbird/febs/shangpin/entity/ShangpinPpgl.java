package cc.mrbird.febs.shangpin.entity;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 品牌管理 Entity
 *
 * @author MrBird
 * @date 2020-05-16 20:03:55
 */
@Data
@TableName("shangpin_ppgl")
public class ShangpinPpgl {

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 品牌代码
     */
    @TableField("ppgldm")
    private String ppgldm;

    /**
     * 品牌名称
     */
    @TableField("ppglmc")
    private String ppglmc;

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
