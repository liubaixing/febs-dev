package cc.mrbird.febs.basic.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.basic.entity.BasicKhlb;
import cc.mrbird.febs.basic.mapper.BasicKhlbMapper;
import cc.mrbird.febs.basic.service.IBasicKhlbService;
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
 * 客户类别 Service实现
 *
 * @author MrBird
 * @date 2020-05-16 20:10:49
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BasicKhlbServiceImpl extends ServiceImpl<BasicKhlbMapper, BasicKhlb> implements IBasicKhlbService {

    @Autowired
    private BasicKhlbMapper basicKhlbMapper;

    @Override
    public IPage<BasicKhlb> findBasicKhlbs(QueryRequest request, BasicKhlb basicKhlb) {
        LambdaQueryWrapper<BasicKhlb> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<BasicKhlb> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<BasicKhlb> findBasicKhlbs(BasicKhlb basicKhlb) {
	    LambdaQueryWrapper<BasicKhlb> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createBasicKhlb(BasicKhlb basicKhlb) {
        this.save(basicKhlb);
    }

    @Override
    @Transactional
    public void updateBasicKhlb(BasicKhlb basicKhlb) {
        this.saveOrUpdate(basicKhlb);
    }

    @Override
    @Transactional
    public void deleteBasicKhlb(BasicKhlb basicKhlb) {
        LambdaQueryWrapper<BasicKhlb> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}
