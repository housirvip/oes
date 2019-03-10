package vip.housir.store.service;

import com.github.pagehelper.Page;
import vip.housir.base.dto.PageDto;
import vip.housir.store.alipay.BizContent;
import vip.housir.store.entity.Recharge;

import java.util.Map;

/**
 * @author housirvip
 */
public interface RechargeService {

    /**
     * 获取 Recharge 记录
     *
     * @param id  Integer
     * @param uid Integer
     * @return Recharge
     */
    Recharge oneById(Integer id, Integer uid);

    /**
     * 根据参数查询，支持分页
     *
     * @param pageDto PageDto
     * @return Page
     */
    Page<Recharge> pageByParam(PageDto pageDto);

    /**
     * 更新订单
     *
     * @param recharge Recharge
     */
    void update(Recharge recharge);

    /**
     * 开始充值，返回主键 id
     *
     * @param bizContent BizContent
     * @param uid        Integer
     * @return Integer
     */
    Integer start(BizContent bizContent, Integer uid);

    /**
     * 结束充值，保存状态
     *
     * @param param Map<String, String>
     */
    void finish(Map<String, String> param);
}
