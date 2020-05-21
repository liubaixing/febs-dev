package cc.mrbird.febs.basic.entity;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 销售方式 Entity
 *
 * @author MrBird
 * @date 2020-05-16 20:11:01
 */
@Data
@TableName("basic_xsfs")
public class BasicXsfs {

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 销售方式代码
     */
    @TableField("xsfsdm")
    private String xsfsdm;

    /**
     * 销售方式名称
     */
    @TableField("xsfsmc")
    private String xsfsmc;

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
