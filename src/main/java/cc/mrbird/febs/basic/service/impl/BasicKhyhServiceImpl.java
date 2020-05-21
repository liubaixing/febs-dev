package cc.mrbird.febs.basic.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.basic.entity.BasicKhyh;
import cc.mrbird.febs.basic.mapper.BasicKhyhMapper;
import cc.mrbird.febs.basic.service.IBasicKhyhService;
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
 * 开户银行 Service实现
 *
 * @author MrBird
 * @date 2020-05-16 20:10:54
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BasicKhyhServiceImpl extends ServiceImpl<BasicKhyhMapper, BasicKhyh> implements IBasicKhyhService {

    @Autowired
    private BasicKhyhMapper basicKhyhMapper;

    @Override
    public IPage<BasicKhyh> findBasicKhyhs(QueryRequest request, BasicKhyh basicKhyh) {
        LambdaQueryWrapper<BasicKhyh> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<BasicKhyh> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<BasicKhyh> findBasicKhyhs(BasicKhyh basicKhyh) {
	    LambdaQueryWrapper<BasicKhyh> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createBasicKhyh(BasicKhyh basicKhyh) {
        this.save(basicKhyh);
    }

    @Override
    @Transactional
    public void updateBasicKhyh(BasicKhyh basicKhyh) {
        this.saveOrUpdate(basicKhyh);
    }

    @Override
    @Transactional
    public void deleteBasicKhyh(BasicKhyh basicKhyh) {
        LambdaQueryWrapper<BasicKhyh> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}
