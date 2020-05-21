package cc.mrbird.febs.shangpin.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.shangpin.entity.ShangpinZl;
import cc.mrbird.febs.shangpin.mapper.ShangpinZlMapper;
import cc.mrbird.febs.shangpin.service.IShangpinZlService;
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
 * 总类 Service实现
 *
 * @author MrBird
 * @date 2020-05-16 20:04:00
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ShangpinZlServiceImpl extends ServiceImpl<ShangpinZlMapper, ShangpinZl> implements IShangpinZlService {

    @Autowired
    private ShangpinZlMapper shangpinZlMapper;

    @Override
    public IPage<ShangpinZl> findShangpinZls(QueryRequest request, ShangpinZl shangpinZl) {
        LambdaQueryWrapper<ShangpinZl> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<ShangpinZl> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<ShangpinZl> findShangpinZls(ShangpinZl shangpinZl) {
	    LambdaQueryWrapper<ShangpinZl> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createShangpinZl(ShangpinZl shangpinZl) {
        this.save(shangpinZl);
    }

    @Override
    @Transactional
    public void updateShangpinZl(ShangpinZl shangpinZl) {
        this.saveOrUpdate(shangpinZl);
    }

    @Override
    @Transactional
    public void deleteShangpinZl(ShangpinZl shangpinZl) {
        LambdaQueryWrapper<ShangpinZl> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}
