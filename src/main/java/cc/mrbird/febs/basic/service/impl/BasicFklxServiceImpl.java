package cc.mrbird.febs.basic.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.basic.entity.BasicFklx;
import cc.mrbird.febs.basic.mapper.BasicFklxMapper;
import cc.mrbird.febs.basic.service.IBasicFklxService;
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
 * 付款类型 Service实现
 *
 * @author MrBird
 * @date 2020-05-16 20:10:33
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BasicFklxServiceImpl extends ServiceImpl<BasicFklxMapper, BasicFklx> implements IBasicFklxService {

    @Autowired
    private BasicFklxMapper basicFklxMapper;

    @Override
    public IPage<BasicFklx> findBasicFklxs(QueryRequest request, BasicFklx basicFklx) {
        LambdaQueryWrapper<BasicFklx> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<BasicFklx> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<BasicFklx> findBasicFklxs(BasicFklx basicFklx) {
	    LambdaQueryWrapper<BasicFklx> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createBasicFklx(BasicFklx basicFklx) {
        this.save(basicFklx);
    }

    @Override
    @Transactional
    public void updateBasicFklx(BasicFklx basicFklx) {
        this.saveOrUpdate(basicFklx);
    }

    @Override
    @Transactional
    public void deleteBasicFklx(BasicFklx basicFklx) {
        LambdaQueryWrapper<BasicFklx> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}
