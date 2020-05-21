package cc.mrbird.febs.basic.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.basic.entity.BasicXsfs;
import cc.mrbird.febs.basic.mapper.BasicXsfsMapper;
import cc.mrbird.febs.basic.service.IBasicXsfsService;
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
 * 销售方式 Service实现
 *
 * @author MrBird
 * @date 2020-05-16 20:11:01
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BasicXsfsServiceImpl extends ServiceImpl<BasicXsfsMapper, BasicXsfs> implements IBasicXsfsService {

    @Autowired
    private BasicXsfsMapper basicXsfsMapper;

    @Override
    public IPage<BasicXsfs> findBasicXsfss(QueryRequest request, BasicXsfs basicXsfs) {
        LambdaQueryWrapper<BasicXsfs> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<BasicXsfs> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<BasicXsfs> findBasicXsfss(BasicXsfs basicXsfs) {
	    LambdaQueryWrapper<BasicXsfs> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createBasicXsfs(BasicXsfs basicXsfs) {
        this.save(basicXsfs);
    }

    @Override
    @Transactional
    public void updateBasicXsfs(BasicXsfs basicXsfs) {
        this.saveOrUpdate(basicXsfs);
    }

    @Override
    @Transactional
    public void deleteBasicXsfs(BasicXsfs basicXsfs) {
        LambdaQueryWrapper<BasicXsfs> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}
