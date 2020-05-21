package cc.mrbird.febs.shangpin.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.shangpin.entity.ShangpinQtlb;
import cc.mrbird.febs.shangpin.mapper.ShangpinQtlbMapper;
import cc.mrbird.febs.shangpin.service.IShangpinQtlbService;
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
 * 其他类别 Service实现
 *
 * @author MrBird
 * @date 2020-05-16 20:03:57
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ShangpinQtlbServiceImpl extends ServiceImpl<ShangpinQtlbMapper, ShangpinQtlb> implements IShangpinQtlbService {

    @Autowired
    private ShangpinQtlbMapper shangpinQtlbMapper;

    @Override
    public IPage<ShangpinQtlb> findShangpinQtlbs(QueryRequest request, ShangpinQtlb shangpinQtlb) {
        LambdaQueryWrapper<ShangpinQtlb> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<ShangpinQtlb> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<ShangpinQtlb> findShangpinQtlbs(ShangpinQtlb shangpinQtlb) {
	    LambdaQueryWrapper<ShangpinQtlb> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createShangpinQtlb(ShangpinQtlb shangpinQtlb) {
        this.save(shangpinQtlb);
    }

    @Override
    @Transactional
    public void updateShangpinQtlb(ShangpinQtlb shangpinQtlb) {
        this.saveOrUpdate(shangpinQtlb);
    }

    @Override
    @Transactional
    public void deleteShangpinQtlb(ShangpinQtlb shangpinQtlb) {
        LambdaQueryWrapper<ShangpinQtlb> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}
