package cc.mrbird.febs.shangpin.service;

import cc.mrbird.febs.shangpin.entity.ShangpinGys;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 供应商商品 Service接口
 *
 * @author MrBird
 * @date 2020-05-16 20:03:51
 */
public interface IShangpinGysService extends IService<ShangpinGys> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param shangpinGys shangpinGys
     * @return IPage<ShangpinGys>
     */
    IPage<ShangpinGys> findShangpinGyss(QueryRequest request, ShangpinGys shangpinGys);

    /**
     * 查询（所有）
     *
     * @param shangpinGys shangpinGys
     * @return List<ShangpinGys>
     */
    List<ShangpinGys> findShangpinGyss(ShangpinGys shangpinGys);

    /**
     * 新增
     *
     * @param shangpinGys shangpinGys
     */
    void createShangpinGys(ShangpinGys shangpinGys);

    /**
     * 修改
     *
     * @param shangpinGys shangpinGys
     */
    void updateShangpinGys(ShangpinGys shangpinGys);

    /**
     * 删除
     *
     * @param shangpinGys shangpinGys
     */
    void deleteShangpinGys(ShangpinGys shangpinGys);
}
