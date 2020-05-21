package cc.mrbird.febs.shangpin.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.shangpin.entity.ShangpinPpgl;
import cc.mrbird.febs.shangpin.mapper.ShangpinPpglMapper;
import cc.mrbird.febs.shangpin.service.IShangpinPpglService;
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
 * 品牌管理 Service实现
 *
 * @author MrBird
 * @date 2020-05-16 20:03:55
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ShangpinPpglServiceImpl extends ServiceImpl<ShangpinPpglMapper, ShangpinPpgl> implements IShangpinPpglService {

    @Autowired
    private ShangpinPpglMapper shangpinPpglMapper;

    @Override
    public IPage<ShangpinPpgl> findShangpinPpgls(QueryRequest request, ShangpinPpgl shangpinPpgl) {
        LambdaQueryWrapper<ShangpinPpgl> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<ShangpinPpgl> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<ShangpinPpgl> findShangpinPpgls(ShangpinPpgl shangpinPpgl) {
	    LambdaQueryWrapper<ShangpinPpgl> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createShangpinPpgl(ShangpinPpgl shangpinPpgl) {
        this.save(shangpinPpgl);
    }

    @Override
    @Transactional
    public void updateShangpinPpgl(ShangpinPpgl shangpinPpgl) {
        this.saveOrUpdate(shangpinPpgl);
    }

    @Override
    @Transactional
    public void deleteShangpinPpgl(ShangpinPpgl shangpinPpgl) {
        LambdaQueryWrapper<ShangpinPpgl> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}
