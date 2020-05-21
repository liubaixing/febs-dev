package cc.mrbird.febs.basic.service;

import cc.mrbird.febs.basic.entity.BasicFplx;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 发票类型 Service接口
 *
 * @author MrBird
 * @date 2020-05-16 20:10:37
 */
public interface IBasicFplxService extends IService<BasicFplx> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param basicFplx basicFplx
     * @return IPage<BasicFplx>
     */
    IPage<BasicFplx> findBasicFplxs(QueryRequest request, BasicFplx basicFplx);

    /**
     * 查询（所有）
     *
     * @param basicFplx basicFplx
     * @return List<BasicFplx>
     */
    List<BasicFplx> findBasicFplxs(BasicFplx basicFplx);

    /**
     * 新增
     *
     * @param basicFplx basicFplx
     */
    void createBasicFplx(BasicFplx basicFplx);

    /**
     * 修改
     *
     * @param basicFplx basicFplx
     */
    void updateBasicFplx(BasicFplx basicFplx);

    /**
     * 删除
     *
     * @param basicFplx basicFplx
     */
    void deleteBasicFplx(BasicFplx basicFplx);
}
