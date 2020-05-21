package cc.mrbird.febs.basic.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.basic.entity.BasicHsxm;
import cc.mrbird.febs.basic.mapper.BasicHsxmMapper;
import cc.mrbird.febs.basic.service.IBasicHsxmService;
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
 * 核算项目 Service实现
 *
 * @author MrBird
 * @date 2020-05-16 20:10:43
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BasicHsxmServiceImpl extends ServiceImpl<BasicHsxmMapper, BasicHsxm> implements IBasicHsxmService {

    @Autowired
    private BasicHsxmMapper basicHsxmMapper;

    @Override
    public IPage<BasicHsxm> findBasicHsxms(QueryRequest request, BasicHsxm basicHsxm) {
        LambdaQueryWrapper<BasicHsxm> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<BasicHsxm> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<BasicHsxm> findBasicHsxms(BasicHsxm basicHsxm) {
	    LambdaQueryWrapper<BasicHsxm> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createBasicHsxm(BasicHsxm basicHsxm) {
        this.save(basicHsxm);
    }

    @Override
    @Transactional
    public void updateBasicHsxm(BasicHsxm basicHsxm) {
        this.saveOrUpdate(basicHsxm);
    }

    @Override
    @Transactional
    public void deleteBasicHsxm(BasicHsxm basicHsxm) {
        LambdaQueryWrapper<BasicHsxm> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}
