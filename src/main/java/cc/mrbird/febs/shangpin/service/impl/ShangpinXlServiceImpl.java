package cc.mrbird.febs.shangpin.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.shangpin.entity.ShangpinXl;
import cc.mrbird.febs.shangpin.mapper.ShangpinXlMapper;
import cc.mrbird.febs.shangpin.service.IShangpinXlService;
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
 * 小类 Service实现
 *
 * @author MrBird
 * @date 2020-05-16 20:03:58
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ShangpinXlServiceImpl extends ServiceImpl<ShangpinXlMapper, ShangpinXl> implements IShangpinXlService {

    @Autowired
    private ShangpinXlMapper shangpinXlMapper;

    @Override
    public IPage<ShangpinXl> findShangpinXls(QueryRequest request, ShangpinXl shangpinXl) {
        LambdaQueryWrapper<ShangpinXl> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<ShangpinXl> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<ShangpinXl> findShangpinXls(ShangpinXl shangpinXl) {
	    LambdaQueryWrapper<ShangpinXl> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createShangpinXl(ShangpinXl shangpinXl) {
        this.save(shangpinXl);
    }

    @Override
    @Transactional
    public void updateShangpinXl(ShangpinXl shangpinXl) {
        this.saveOrUpdate(shangpinXl);
    }

    @Override
    @Transactional
    public void deleteShangpinXl(ShangpinXl shangpinXl) {
        LambdaQueryWrapper<ShangpinXl> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}
