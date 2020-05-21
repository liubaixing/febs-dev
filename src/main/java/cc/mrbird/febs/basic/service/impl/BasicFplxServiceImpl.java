package cc.mrbird.febs.basic.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.basic.entity.BasicFplx;
import cc.mrbird.febs.basic.mapper.BasicFplxMapper;
import cc.mrbird.febs.basic.service.IBasicFplxService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * 发票类型 Service实现
 *
 * @author MrBird
 * @date 2020-05-16 20:10:37
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BasicFplxServiceImpl extends ServiceImpl<BasicFplxMapper, BasicFplx> implements IBasicFplxService {

    @Autowired
    private BasicFplxMapper basicFplxMapper;

    @Override
    public IPage<BasicFplx> findBasicFplxs(QueryRequest request, BasicFplx basicFplx) {
        LambdaQueryWrapper<BasicFplx> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<BasicFplx> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<BasicFplx> findBasicFplxs(BasicFplx basicFplx) {
	    LambdaQueryWrapper<BasicFplx> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createBasicFplx(BasicFplx basicFplx) {
        this.save(basicFplx);
    }

    @Override
    @Transactional
    public void updateBasicFplx(BasicFplx basicFplx) {
        this.saveOrUpdate(basicFplx);
    }

    @Override
    @Transactional
    public void deleteBasicFplx(BasicFplx basicFplx) {
        LambdaQueryWrapper<BasicFplx> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}
