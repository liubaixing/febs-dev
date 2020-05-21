package cc.mrbird.febs.shangpin.service;

import cc.mrbird.febs.shangpin.entity.Shangpin;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 商品 Service接口
 *
 * @author MrBird
 * @date 2020-05-16 20:03:47
 */
public interface IShangpinService extends IService<Shangpin> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param shangpin shangpin
     * @return IPage<Shangpin>
     */
    IPage<Shangpin> findShangpins(QueryRequest request, Shangpin shangpin);

    /**
     * 查询（所有）
     *
     * @param shangpin shangpin
     * @return List<Shangpin>
     */
    List<Shangpin> findShangpins(Shangpin shangpin);

    /**
     * 新增
     *
     * @param shangpin shangpin
     */
    void createShangpin(Shangpin shangpin);

    /**
     * 修改
     *
     * @param shangpin shangpin
     */
    void updateShangpin(Shangpin shangpin);

    /**
     * 删除
     *
     * @param shangpin shangpin
     */
    void deleteShangpin(Shangpin shangpin);
}
