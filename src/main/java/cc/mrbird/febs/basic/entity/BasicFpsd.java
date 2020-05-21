package cc.mrbird.febs.basic.entity;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 发票税点 Entity
 *
 * @author MrBird
 * @date 2020-05-16 20:10:40
 */
@Data
@TableName("basic_fpsd")
public class BasicFpsd {

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 发票税点代码
     */
    @TableField("fpsddm")
    private String fpsddm;

    /**
     * 发票税点名称
     */
    @TableField("fpsdmc")
    private String fpsdmc;

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
