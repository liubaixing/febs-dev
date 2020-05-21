package cc.mrbird.febs.basic.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.basic.entity.BasicJldw;
import cc.mrbird.febs.basic.mapper.BasicJldwMapper;
import cc.mrbird.febs.basic.service.IBasicJldwService;
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
 * 计量单位 Service实现
 *
 * @author MrBird
 * @date 2020-05-16 20:10:45
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BasicJldwServiceImpl extends ServiceImpl<BasicJldwMapper, BasicJldw> implements IBasicJldwService {

    @Autowired
    private BasicJldwMapper basicJldwMapper;

    @Override
    public IPage<BasicJldw> findBasicJldws(QueryRequest request, BasicJldw basicJldw) {
        LambdaQueryWrapper<BasicJldw> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<BasicJldw> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<BasicJldw> findBasicJldws(BasicJldw basicJldw) {
	    LambdaQueryWrapper<BasicJldw> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createBasicJldw(BasicJldw basicJldw) {
        this.save(basicJldw);
    }

    @Override
    @Transactional
    public void updateBasicJldw(BasicJldw basicJldw) {
        this.saveOrUpdate(basicJldw);
    }

    @Override
    @Transactional
    public void deleteBasicJldw(BasicJldw basicJldw) {
        LambdaQueryWrapper<BasicJldw> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}
