package cc.mrbird.febs.basic.service;

import cc.mrbird.febs.basic.entity.BasicHsxm;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 核算项目 Service接口
 *
 * @author MrBird
 * @date 2020-05-16 20:10:43
 */
public interface IBasicHsxmService extends IService<BasicHsxm> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param basicHsxm basicHsxm
     * @return IPage<BasicHsxm>
     */
    IPage<BasicHsxm> findBasicHsxms(QueryRequest request, BasicHsxm basicHsxm);

    /**
     * 查询（所有）
     *
     * @param basicHsxm basicHsxm
     * @return List<BasicHsxm>
     */
    List<BasicHsxm> findBasicHsxms(BasicHsxm basicHsxm);

    /**
     * 新增
     *
     * @param basicHsxm basicHsxm
     */
    void createBasicHsxm(BasicHsxm basicHsxm);

    /**
     * 修改
     *
     * @param basicHsxm basicHsxm
     */
    void updateBasicHsxm(BasicHsxm basicHsxm);

    /**
     * 删除
     *
     * @param basicHsxm basicHsxm
     */
    void deleteBasicHsxm(BasicHsxm basicHsxm);
}
