package cc.mrbird.febs.system.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.system.entity.Bumeng;
import cc.mrbird.febs.system.mapper.BumengMapper;
import cc.mrbird.febs.system.service.IBumengService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 部门管理 Service实现
 *
 * @author lbx
 * @date 2020-05-16 19:50:02
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BumengServiceImpl extends ServiceImpl<BumengMapper, Bumeng> implements IBumengService {

    @Autowired
    private BumengMapper bumengMapper;

    @Override
    public IPage<Bumeng> findBumengs(QueryRequest request, Bumeng bumeng) {
        LambdaQueryWrapper<Bumeng> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<Bumeng> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Bumeng> findBumengs(Bumeng bumeng) {
	    LambdaQueryWrapper<Bumeng> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createBumeng(Bumeng bumeng) {
        this.save(bumeng);
    }

    @Override
    @Transactional
    public void updateBumeng(Bumeng bumeng) {
        this.saveOrUpdate(bumeng);
    }

    @Override
    @Transactional
    public void deleteBumeng(Bumeng bumeng) {
        LambdaQueryWrapper<Bumeng> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}
