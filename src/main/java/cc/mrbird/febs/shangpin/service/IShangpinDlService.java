package cc.mrbird.febs.shangpin.service;

import cc.mrbird.febs.shangpin.entity.ShangpinDl;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 大类 Service接口
 *
 * @author MrBird
 * @date 2020-05-16 20:03:49
 */
public interface IShangpinDlService extends IService<ShangpinDl> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param shangpinDl shangpinDl
     * @return IPage<ShangpinDl>
     */
    IPage<ShangpinDl> findShangpinDls(QueryRequest request, ShangpinDl shangpinDl);

    /**
     * 查询（所有）
     *
     * @param shangpinDl shangpinDl
     * @return List<ShangpinDl>
     */
    List<ShangpinDl> findShangpinDls(ShangpinDl shangpinDl);

    /**
     * 新增
     *
     * @param shangpinDl shangpinDl
     */
    void createShangpinDl(ShangpinDl shangpinDl);

    /**
     * 修改
     *
     * @param shangpinDl shangpinDl
     */
    void updateShangpinDl(ShangpinDl shangpinDl);

    /**
     * 删除
     *
     * @param shangpinDl shangpinDl
     */
    void deleteShangpinDl(ShangpinDl shangpinDl);
}
