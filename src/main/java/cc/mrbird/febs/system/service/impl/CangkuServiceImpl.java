package cc.mrbird.febs.system.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.system.entity.Cangku;
import cc.mrbird.febs.system.mapper.CangkuMapper;
import cc.mrbird.febs.system.service.ICangkuService;
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
 * 仓库管理 Service实现
 *
 * @author lbx
 * @date 2020-05-16 19:57:45
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CangkuServiceImpl extends ServiceImpl<CangkuMapper, Cangku> implements ICangkuService {

    @Autowired
    private CangkuMapper cangkuMapper;

    @Override
    public IPage<Cangku> findCangkus(QueryRequest request, Cangku cangku) {
        LambdaQueryWrapper<Cangku> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<Cangku> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Cangku> findCangkus(Cangku cangku) {
	    LambdaQueryWrapper<Cangku> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createCangku(Cangku cangku) {
        this.save(cangku);
    }

    @Override
    @Transactional
    public void updateCangku(Cangku cangku) {
        this.saveOrUpdate(cangku);
    }

    @Override
    @Transactional
    public void deleteCangku(Cangku cangku) {
        LambdaQueryWrapper<Cangku> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}
