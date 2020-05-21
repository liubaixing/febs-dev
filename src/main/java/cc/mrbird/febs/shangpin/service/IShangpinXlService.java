package cc.mrbird.febs.shangpin.service;

import cc.mrbird.febs.shangpin.entity.ShangpinXl;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 小类 Service接口
 *
 * @author MrBird
 * @date 2020-05-16 20:03:58
 */
public interface IShangpinXlService extends IService<ShangpinXl> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param shangpinXl shangpinXl
     * @return IPage<ShangpinXl>
     */
    IPage<ShangpinXl> findShangpinXls(QueryRequest request, ShangpinXl shangpinXl);

    /**
     * 查询（所有）
     *
     * @param shangpinXl shangpinXl
     * @return List<ShangpinXl>
     */
    List<ShangpinXl> findShangpinXls(ShangpinXl shangpinXl);

    /**
     * 新增
     *
     * @param shangpinXl shangpinXl
     */
    void createShangpinXl(ShangpinXl shangpinXl);

    /**
     * 修改
     *
     * @param shangpinXl shangpinXl
     */
    void updateShangpinXl(ShangpinXl shangpinXl);

    /**
     * 删除
     *
     * @param shangpinXl shangpinXl
     */
    void deleteShangpinXl(ShangpinXl shangpinXl);
}
