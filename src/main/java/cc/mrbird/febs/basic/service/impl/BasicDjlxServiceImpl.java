package cc.mrbird.febs.basic.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.basic.entity.BasicDjlx;
import cc.mrbird.febs.basic.mapper.BasicDjlxMapper;
import cc.mrbird.febs.basic.service.IBasicDjlxService;
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
 * 单据类型 Service实现
 *
 * @author MrBird
 * @date 2020-05-16 20:10:31
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BasicDjlxServiceImpl extends ServiceImpl<BasicDjlxMapper, BasicDjlx> implements IBasicDjlxService {

    @Autowired
    private BasicDjlxMapper basicDjlxMapper;

    @Override
    public IPage<BasicDjlx> findBasicDjlxs(QueryRequest request, BasicDjlx basicDjlx) {
        LambdaQueryWrapper<BasicDjlx> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<BasicDjlx> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<BasicDjlx> findBasicDjlxs(BasicDjlx basicDjlx) {
	    LambdaQueryWrapper<BasicDjlx> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createBasicDjlx(BasicDjlx basicDjlx) {
        this.save(basicDjlx);
    }

    @Override
    @Transactional
    public void updateBasicDjlx(BasicDjlx basicDjlx) {
        this.saveOrUpdate(basicDjlx);
    }

    @Override
    @Transactional
    public void deleteBasicDjlx(BasicDjlx basicDjlx) {
        LambdaQueryWrapper<BasicDjlx> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}
