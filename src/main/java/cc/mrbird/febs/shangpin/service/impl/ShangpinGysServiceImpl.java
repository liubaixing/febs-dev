package cc.mrbird.febs.shangpin.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.shangpin.entity.ShangpinGys;
import cc.mrbird.febs.shangpin.mapper.ShangpinGysMapper;
import cc.mrbird.febs.shangpin.service.IShangpinGysService;
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
 * 供应商商品 Service实现
 *
 * @author MrBird
 * @date 2020-05-16 20:03:51
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ShangpinGysServiceImpl extends ServiceImpl<ShangpinGysMapper, ShangpinGys> implements IShangpinGysService {

    @Autowired
    private ShangpinGysMapper shangpinGysMapper;

    @Override
    public IPage<ShangpinGys> findShangpinGyss(QueryRequest request, ShangpinGys shangpinGys) {
        LambdaQueryWrapper<ShangpinGys> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<ShangpinGys> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<ShangpinGys> findShangpinGyss(ShangpinGys shangpinGys) {
	    LambdaQueryWrapper<ShangpinGys> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createShangpinGys(ShangpinGys shangpinGys) {
        this.save(shangpinGys);
    }

    @Override
    @Transactional
    public void updateShangpinGys(ShangpinGys shangpinGys) {
        this.saveOrUpdate(shangpinGys);
    }

    @Override
    @Transactional
    public void deleteShangpinGys(ShangpinGys shangpinGys) {
        LambdaQueryWrapper<ShangpinGys> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}
