package cc.mrbird.febs.basic.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.basic.entity.BasicCklb;
import cc.mrbird.febs.basic.mapper.BasicCklbMapper;
import cc.mrbird.febs.basic.service.IBasicCklbService;
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
 * 仓库类别 Service实现
 *
 * @author MrBird
 * @date 2020-05-16 20:10:27
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BasicCklbServiceImpl extends ServiceImpl<BasicCklbMapper, BasicCklb> implements IBasicCklbService {

    @Autowired
    private BasicCklbMapper basicCklbMapper;

    @Override
    public IPage<BasicCklb> findBasicCklbs(QueryRequest request, BasicCklb basicCklb) {
        LambdaQueryWrapper<BasicCklb> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<BasicCklb> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<BasicCklb> findBasicCklbs(BasicCklb basicCklb) {
	    LambdaQueryWrapper<BasicCklb> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createBasicCklb(BasicCklb basicCklb) {
        this.save(basicCklb);
    }

    @Override
    @Transactional
    public void updateBasicCklb(BasicCklb basicCklb) {
        this.saveOrUpdate(basicCklb);
    }

    @Override
    @Transactional
    public void deleteBasicCklb(BasicCklb basicCklb) {
        LambdaQueryWrapper<BasicCklb> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}
