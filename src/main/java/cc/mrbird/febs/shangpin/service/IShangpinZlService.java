package cc.mrbird.febs.shangpin.service;

import cc.mrbird.febs.shangpin.entity.ShangpinZl;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 总类 Service接口
 *
 * @author MrBird
 * @date 2020-05-16 20:04:00
 */
public interface IShangpinZlService extends IService<ShangpinZl> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param shangpinZl shangpinZl
     * @return IPage<ShangpinZl>
     */
    IPage<ShangpinZl> findShangpinZls(QueryRequest request, ShangpinZl shangpinZl);

    /**
     * 查询（所有）
     *
     * @param shangpinZl shangpinZl
     * @return List<ShangpinZl>
     */
    List<ShangpinZl> findShangpinZls(ShangpinZl shangpinZl);

    /**
     * 新增
     *
     * @param shangpinZl shangpinZl
     */
    void createShangpinZl(ShangpinZl shangpinZl);

    /**
     * 修改
     *
     * @param shangpinZl shangpinZl
     */
    void updateShangpinZl(ShangpinZl shangpinZl);

    /**
     * 删除
     *
     * @param shangpinZl shangpinZl
     */
    void deleteShangpinZl(ShangpinZl shangpinZl);
}
