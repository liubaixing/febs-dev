package cc.mrbird.febs.basic.entity;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 付款类型 Entity
 *
 * @author MrBird
 * @date 2020-05-16 20:10:33
 */
@Data
@TableName("basic_fklx")
public class BasicFklx {

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 付款类型代码
     */
    @TableField("fklxdm")
    private String fklxdm;

    /**
     * 付款类型名称
     */
    @TableField("fklxmc")
    private String fklxmc;

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
