package cc.mrbird.febs.basic.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.basic.entity.BasicLlly;
import cc.mrbird.febs.basic.mapper.BasicLllyMapper;
import cc.mrbird.febs.basic.service.IBasicLllyService;
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
 * 流量来源 Service实现
 *
 * @author MrBird
 * @date 2020-05-16 20:10:56
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BasicLllyServiceImpl extends ServiceImpl<BasicLllyMapper, BasicLlly> implements IBasicLllyService {

    @Autowired
    private BasicLllyMapper basicLllyMapper;

    @Override
    public IPage<BasicLlly> findBasicLllys(QueryRequest request, BasicLlly basicLlly) {
        LambdaQueryWrapper<BasicLlly> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<BasicLlly> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<BasicLlly> findBasicLllys(BasicLlly basicLlly) {
	    LambdaQueryWrapper<BasicLlly> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createBasicLlly(BasicLlly basicLlly) {
        this.save(basicLlly);
    }

    @Override
    @Transactional
    public void updateBasicLlly(BasicLlly basicLlly) {
        this.saveOrUpdate(basicLlly);
    }

    @Override
    @Transactional
    public void deleteBasicLlly(BasicLlly basicLlly) {
        LambdaQueryWrapper<BasicLlly> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}
