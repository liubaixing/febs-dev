package cc.mrbird.febs.basic.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.basic.entity.BasicPtda;
import cc.mrbird.febs.basic.mapper.BasicPtdaMapper;
import cc.mrbird.febs.basic.service.IBasicPtdaService;
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
 * 平台档案 Service实现
 *
 * @author MrBird
 * @date 2020-05-16 20:10:58
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BasicPtdaServiceImpl extends ServiceImpl<BasicPtdaMapper, BasicPtda> implements IBasicPtdaService {

    @Autowired
    private BasicPtdaMapper basicPtdaMapper;

    @Override
    public IPage<BasicPtda> findBasicPtdas(QueryRequest request, BasicPtda basicPtda) {
        LambdaQueryWrapper<BasicPtda> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<BasicPtda> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<BasicPtda> findBasicPtdas(BasicPtda basicPtda) {
	    LambdaQueryWrapper<BasicPtda> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createBasicPtda(BasicPtda basicPtda) {
        this.save(basicPtda);
    }

    @Override
    @Transactional
    public void updateBasicPtda(BasicPtda basicPtda) {
        this.saveOrUpdate(basicPtda);
    }

    @Override
    @Transactional
    public void deleteBasicPtda(BasicPtda basicPtda) {
        LambdaQueryWrapper<BasicPtda> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}
