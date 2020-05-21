package cc.mrbird.febs.system.service;

import cc.mrbird.febs.system.entity.Gys;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 供应商 Service接口
 *
 * @author lbx
 * @date 2020-05-16 19:58:42
 */
public interface IGysService extends IService<Gys> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param gys gys
     * @return IPage<Gys>
     */
    IPage<Gys> findGyss(QueryRequest request, Gys gys);

    /**
     * 查询（所有）
     *
     * @param gys gys
     * @return List<Gys>
     */
    List<Gys> findGyss(Gys gys);

    /**
     * 新增
     *
     * @param gys gys
     */
    void createGys(Gys gys);

    /**
     * 修改
     *
     * @param gys gys
     */
    void updateGys(Gys gys);

    /**
     * 删除
     *
     * @param gys gys
     */
    void deleteGys(String[] ids);
}
