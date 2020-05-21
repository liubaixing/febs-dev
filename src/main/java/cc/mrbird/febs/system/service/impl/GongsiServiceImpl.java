package cc.mrbird.febs.system.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.system.entity.Gongsi;
import cc.mrbird.febs.system.mapper.GongsiMapper;
import cc.mrbird.febs.system.service.IGongsiService;
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
 * 公司管理 Service实现
 *
 * @author lbx
 * @date 2020-05-16 19:58:39
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class GongsiServiceImpl extends ServiceImpl<GongsiMapper, Gongsi> implements IGongsiService {

    @Autowired
    private GongsiMapper gongsiMapper;

    @Override
    public IPage<Gongsi> findGongsis(QueryRequest request, Gongsi gongsi) {
        LambdaQueryWrapper<Gongsi> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<Gongsi> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Gongsi> findGongsis(Gongsi gongsi) {
	    LambdaQueryWrapper<Gongsi> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createGongsi(Gongsi gongsi) {
        this.save(gongsi);
    }

    @Override
    @Transactional
    public void updateGongsi(Gongsi gongsi) {
        this.saveOrUpdate(gongsi);
    }

    @Override
    @Transactional
    public void deleteGongsi(Gongsi gongsi) {
        LambdaQueryWrapper<Gongsi> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}
