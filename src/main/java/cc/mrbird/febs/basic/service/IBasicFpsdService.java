package cc.mrbird.febs.basic.service;

import cc.mrbird.febs.basic.entity.BasicFpsd;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 发票税点 Service接口
 *
 * @author MrBird
 * @date 2020-05-16 20:10:40
 */
public interface IBasicFpsdService extends IService<BasicFpsd> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param basicFpsd basicFpsd
     * @return IPage<BasicFpsd>
     */
    IPage<BasicFpsd> findBasicFpsds(QueryRequest request, BasicFpsd basicFpsd);

    /**
     * 查询（所有）
     *
     * @param basicFpsd basicFpsd
     * @return List<BasicFpsd>
     */
    List<BasicFpsd> findBasicFpsds(BasicFpsd basicFpsd);

    /**
     * 新增
     *
     * @param basicFpsd basicFpsd
     */
    void createBasicFpsd(BasicFpsd basicFpsd);

    /**
     * 修改
     *
     * @param basicFpsd basicFpsd
     */
    void updateBasicFpsd(BasicFpsd basicFpsd);

    /**
     * 删除
     *
     * @param basicFpsd basicFpsd
     */
    void deleteBasicFpsd(BasicFpsd basicFpsd);
}
