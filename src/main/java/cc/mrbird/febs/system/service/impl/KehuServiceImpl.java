package cc.mrbird.febs.system.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.system.entity.Kehu;
import cc.mrbird.febs.system.mapper.KehuMapper;
import cc.mrbird.febs.system.service.IKehuService;
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
 * 客户管理 Service实现
 *
 * @author lbx
 * @date 2020-05-16 19:44:24
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class KehuServiceImpl extends ServiceImpl<KehuMapper, Kehu> implements IKehuService {

    @Autowired
    private KehuMapper kehuMapper;

    @Override
    public IPage<Kehu> findKehus(QueryRequest request, Kehu kehu) {
        LambdaQueryWrapper<Kehu> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<Kehu> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Kehu> findKehus(Kehu kehu) {
	    LambdaQueryWrapper<Kehu> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createKehu(Kehu kehu) {
        this.save(kehu);
    }

    @Override
    @Transactional
    public void updateKehu(Kehu kehu) {
        this.saveOrUpdate(kehu);
    }

    @Override
    @Transactional
    public void deleteKehu(String[] ids) {
        List<String> list = Arrays.asList(ids);
        // 删除用户
        this.removeByIds(list);
	}
}
