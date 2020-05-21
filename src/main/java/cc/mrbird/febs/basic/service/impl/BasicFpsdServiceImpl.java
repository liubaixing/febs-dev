package cc.mrbird.febs.basic.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.basic.entity.BasicFpsd;
import cc.mrbird.febs.basic.mapper.BasicFpsdMapper;
import cc.mrbird.febs.basic.service.IBasicFpsdService;
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
 * 发票税点 Service实现
 *
 * @author MrBird
 * @date 2020-05-16 20:10:40
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BasicFpsdServiceImpl extends ServiceImpl<BasicFpsdMapper, BasicFpsd> implements IBasicFpsdService {

    @Autowired
    private BasicFpsdMapper basicFpsdMapper;

    @Override
    public IPage<BasicFpsd> findBasicFpsds(QueryRequest request, BasicFpsd basicFpsd) {
        LambdaQueryWrapper<BasicFpsd> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<BasicFpsd> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<BasicFpsd> findBasicFpsds(BasicFpsd basicFpsd) {
	    LambdaQueryWrapper<BasicFpsd> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createBasicFpsd(BasicFpsd basicFpsd) {
        this.save(basicFpsd);
    }

    @Override
    @Transactional
    public void updateBasicFpsd(BasicFpsd basicFpsd) {
        this.saveOrUpdate(basicFpsd);
    }

    @Override
    @Transactional
    public void deleteBasicFpsd(BasicFpsd basicFpsd) {
        LambdaQueryWrapper<BasicFpsd> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}
