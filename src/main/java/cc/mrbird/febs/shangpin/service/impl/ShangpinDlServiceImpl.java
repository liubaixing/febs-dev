package cc.mrbird.febs.shangpin.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.shangpin.entity.ShangpinDl;
import cc.mrbird.febs.shangpin.mapper.ShangpinDlMapper;
import cc.mrbird.febs.shangpin.service.IShangpinDlService;
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
 * 大类 Service实现
 *
 * @author MrBird
 * @date 2020-05-16 20:03:49
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ShangpinDlServiceImpl extends ServiceImpl<ShangpinDlMapper, ShangpinDl> implements IShangpinDlService {

    @Autowired
    private ShangpinDlMapper shangpinDlMapper;

    @Override
    public IPage<ShangpinDl> findShangpinDls(QueryRequest request, ShangpinDl shangpinDl) {
        LambdaQueryWrapper<ShangpinDl> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<ShangpinDl> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<ShangpinDl> findShangpinDls(ShangpinDl shangpinDl) {
	    LambdaQueryWrapper<ShangpinDl> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createShangpinDl(ShangpinDl shangpinDl) {
        this.save(shangpinDl);
    }

    @Override
    @Transactional
    public void updateShangpinDl(ShangpinDl shangpinDl) {
        this.saveOrUpdate(shangpinDl);
    }

    @Override
    @Transactional
    public void deleteShangpinDl(ShangpinDl shangpinDl) {
        LambdaQueryWrapper<ShangpinDl> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}
