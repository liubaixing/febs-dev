package cc.mrbird.febs.shangpin.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.shangpin.entity.Shangpin;
import cc.mrbird.febs.shangpin.mapper.ShangpinMapper;
import cc.mrbird.febs.shangpin.service.IShangpinService;
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
 * 商品 Service实现
 *
 * @author MrBird
 * @date 2020-05-16 20:03:47
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ShangpinServiceImpl extends ServiceImpl<ShangpinMapper, Shangpin> implements IShangpinService {

    @Autowired
    private ShangpinMapper shangpinMapper;

    @Override
    public IPage<Shangpin> findShangpins(QueryRequest request, Shangpin shangpin) {
        LambdaQueryWrapper<Shangpin> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<Shangpin> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Shangpin> findShangpins(Shangpin shangpin) {
	    LambdaQueryWrapper<Shangpin> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createShangpin(Shangpin shangpin) {
        this.save(shangpin);
    }

    @Override
    @Transactional
    public void updateShangpin(Shangpin shangpin) {
        this.saveOrUpdate(shangpin);
    }

    @Override
    @Transactional
    public void deleteShangpin(Shangpin shangpin) {
        LambdaQueryWrapper<Shangpin> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}
