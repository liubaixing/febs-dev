package cc.mrbird.febs.system.entity;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 仓库管理 Entity
 *
 * @author lbx
 * @date 2020-05-16 19:57:45
 */
@Data
@TableName("system_cangku")
public class Cangku {

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 仓库代码
     */
    @TableField("ckdm")
    private String ckdm;

    /**
     * 仓库名称
     */
    @TableField("ckmc")
    private String ckmc;

    /**
     * 仓库类别 Basic_cklb表id
     */
    @TableField("cklx_id")
    private Integer cklxId;

    /**
     * 仓库性质 0:自发 1:直发
     */
    @TableField("ckxz")
    private Integer ckxz;

    /**
     * 联系人电话
     */
    @TableField("tel")
    private String tel;

    /**
     * 联系人
     */
    @TableField("contact")
    private String contact;

    /**
     * 发货地址
     */
    @TableField("address")
    private String address;

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
