package cc.mrbird.febs.system.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.system.entity.Gys;
import cc.mrbird.febs.system.mapper.GysMapper;
import cc.mrbird.febs.system.service.IGysService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 供应商 Service实现
 *
 * @author lbx
 * @date 2020-05-16 19:58:42
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class GysServiceImpl extends ServiceImpl<GysMapper, Gys> implements IGysService {

    @Autowired
    private GysMapper gysMapper;

    @Override
    public IPage<Gys> findGyss(QueryRequest request, Gys gys) {
        LambdaQueryWrapper<Gys> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<Gys> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Gys> findGyss(Gys gys) {
	    LambdaQueryWrapper<Gys> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createGys(Gys gys) {
        this.save(gys);
    }

    @Override
    @Transactional
    public void updateGys(Gys gys) {
        this.saveOrUpdate(gys);
    }

    @Override
    @Transactional
    public void deleteGys(String[] ids) {
        List<String> list = Arrays.asList(ids);
        this.removeByIds(list);
    }
}
