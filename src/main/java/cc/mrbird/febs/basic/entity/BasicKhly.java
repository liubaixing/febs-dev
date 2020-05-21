package cc.mrbird.febs.basic.entity;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 客户来源 Entity
 *
 * @author MrBird
 * @date 2020-05-16 20:10:51
 */
@Data
@TableName("basic_khly")
public class BasicKhly {

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 客户来源代码
     */
    @TableField("khlydm")
    private String khlydm;

    /**
     * 客户来源名称
     */
    @TableField("khlymc")
    private String khlymc;

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
