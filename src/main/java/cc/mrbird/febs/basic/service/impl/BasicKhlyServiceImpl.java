package cc.mrbird.febs.basic.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.basic.entity.BasicKhly;
import cc.mrbird.febs.basic.mapper.BasicKhlyMapper;
import cc.mrbird.febs.basic.service.IBasicKhlyService;
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
 * 客户来源 Service实现
 *
 * @author MrBird
 * @date 2020-05-16 20:10:51
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BasicKhlyServiceImpl extends ServiceImpl<BasicKhlyMapper, BasicKhly> implements IBasicKhlyService {

    @Autowired
    private BasicKhlyMapper basicKhlyMapper;

    @Override
    public IPage<BasicKhly> findBasicKhlys(QueryRequest request, BasicKhly basicKhly) {
        LambdaQueryWrapper<BasicKhly> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<BasicKhly> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<BasicKhly> findBasicKhlys(BasicKhly basicKhly) {
	    LambdaQueryWrapper<BasicKhly> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createBasicKhly(BasicKhly basicKhly) {
        this.save(basicKhly);
    }

    @Override
    @Transactional
    public void updateBasicKhly(BasicKhly basicKhly) {
        this.saveOrUpdate(basicKhly);
    }

    @Override
    @Transactional
    public void deleteBasicKhly(BasicKhly basicKhly) {
        LambdaQueryWrapper<BasicKhly> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}
