package cc.mrbird.febs.basic.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.basic.entity.BasicKhqy;
import cc.mrbird.febs.basic.mapper.BasicKhqyMapper;
import cc.mrbird.febs.basic.service.IBasicKhqyService;
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
 * 客户区域 Service实现
 *
 * @author MrBird
 * @date 2020-05-16 20:10:52
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BasicKhqyServiceImpl extends ServiceImpl<BasicKhqyMapper, BasicKhqy> implements IBasicKhqyService {

    @Autowired
    private BasicKhqyMapper basicKhqyMapper;

    @Override
    public IPage<BasicKhqy> findBasicKhqys(QueryRequest request, BasicKhqy basicKhqy) {
        LambdaQueryWrapper<BasicKhqy> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<BasicKhqy> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<BasicKhqy> findBasicKhqys(BasicKhqy basicKhqy) {
	    LambdaQueryWrapper<BasicKhqy> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createBasicKhqy(BasicKhqy basicKhqy) {
        this.save(basicKhqy);
    }

    @Override
    @Transactional
    public void updateBasicKhqy(BasicKhqy basicKhqy) {
        this.saveOrUpdate(basicKhqy);
    }

    @Override
    @Transactional
    public void deleteBasicKhqy(BasicKhqy basicKhqy) {
        LambdaQueryWrapper<BasicKhqy> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}
