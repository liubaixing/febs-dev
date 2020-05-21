package cc.mrbird.febs.basic.entity;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 计量单位 Entity
 *
 * @author MrBird
 * @date 2020-05-16 20:10:45
 */
@Data
@TableName("basic_jldw")
public class BasicJldw {

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 计量单位代码
     */
    @TableField("jldwdm")
    private String jldwdm;

    /**
     * 计量单位名称
     */
    @TableField("jldwmc")
    private String jldwmc;

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
