package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.pinyougou.entity.TbSeller;
import com.pinyougou.entity.TbSellerExample;
import com.pinyougou.mapper.TbSellerMapper;
import com.pinyougou.page.PageResult;
import com.pinyougou.sellergoods.service.ISellerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Author:       Caychen
 * Class:        com.pinyougou.sellergoods.service.impl.SellerServiceImpl
 * Date:         2018/10/27
 * Desc:
 */

@Service
@Component("sellerService")
public class SellerServiceImpl implements ISellerService {

    @Autowired
    private TbSellerMapper tbSellerMapper;

    @Override
    public List<TbSeller> findAll() {
        return tbSellerMapper.selectByExample(null);
    }

    @Override
    public PageResult search(TbSeller seller, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbSellerExample example = new TbSellerExample();

        if (seller != null) {
            TbSellerExample.Criteria criteria = example.createCriteria();

            if (StringUtils.isNotBlank(seller.getSellerId())) {
                criteria.andSellerIdLike("%" + seller.getSellerId() + "%");
            }
            if (StringUtils.isNotBlank(seller.getName())) {
                criteria.andNameLike("%" + seller.getName() + "%");
            }
            if (StringUtils.isNotBlank(seller.getNickName())) {
                criteria.andNickNameLike("%" + seller.getNickName() + "%");
            }
            if (StringUtils.isNotBlank(seller.getPassword())) {
                criteria.andPasswordLike("%" + seller.getPassword() + "%");
            }
            if (StringUtils.isNotBlank(seller.getEmail())) {
                criteria.andEmailLike("%" + seller.getEmail() + "%");
            }
            if (StringUtils.isNotBlank(seller.getMobile())) {
                criteria.andMobileLike("%" + seller.getMobile() + "%");
            }
            if (StringUtils.isNotBlank(seller.getTelephone())) {
                criteria.andTelephoneLike("%" + seller.getTelephone() + "%");
            }
            if (StringUtils.isNotBlank(seller.getStatus())) {
                criteria.andStatusLike("%" + seller.getStatus() + "%");
            }
            if (StringUtils.isNotBlank(seller.getAddressDetail())) {
                criteria.andAddressDetailLike("%" + seller.getAddressDetail() + "%");
            }
            if (StringUtils.isNotBlank(seller.getLinkmanName())) {
                criteria.andLinkmanNameLike("%" + seller.getLinkmanName() + "%");
            }
            if (StringUtils.isNotBlank(seller.getLinkmanQq())) {
                criteria.andLinkmanQqLike("%" + seller.getLinkmanQq() + "%");
            }
            if (StringUtils.isNotBlank(seller.getLinkmanMobile())) {
                criteria.andLinkmanMobileLike("%" + seller.getLinkmanMobile() + "%");
            }
            if (StringUtils.isNotBlank(seller.getLinkmanEmail())) {
                criteria.andLinkmanEmailLike("%" + seller.getLinkmanEmail() + "%");
            }
            if (StringUtils.isNotBlank(seller.getLicenseNumber())) {
                criteria.andLicenseNumberLike("%" + seller.getLicenseNumber() + "%");
            }
            if (StringUtils.isNotBlank(seller.getTaxNumber())) {
                criteria.andTaxNumberLike("%" + seller.getTaxNumber() + "%");
            }
            if (StringUtils.isNotBlank(seller.getOrgNumber())) {
                criteria.andOrgNumberLike("%" + seller.getOrgNumber() + "%");
            }
            if (StringUtils.isNotBlank(seller.getLogoPic())) {
                criteria.andLogoPicLike("%" + seller.getLogoPic() + "%");
            }
            if (StringUtils.isNotBlank(seller.getBrief())) {
                criteria.andBriefLike("%" + seller.getBrief() + "%");
            }
            if (StringUtils.isNotBlank(seller.getLegalPerson())) {
                criteria.andLegalPersonLike("%" + seller.getLegalPerson() + "%");
            }
            if (StringUtils.isNotBlank(seller.getLegalPersonCardId())) {
                criteria.andLegalPersonCardIdLike("%" + seller.getLegalPersonCardId() + "%");
            }
            if (StringUtils.isNotBlank(seller.getBankUser())) {
                criteria.andBankUserLike("%" + seller.getBankUser() + "%");
            }
            if (StringUtils.isNotBlank(seller.getBankName())) {
                criteria.andBankNameLike("%" + seller.getBankName() + "%");
            }

        }

        Page<TbSeller> page = (Page<TbSeller>) tbSellerMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void add(TbSeller seller) {
        seller.setStatus("0");//待审核
        seller.setCreateTime(new Date());//申请时间
        tbSellerMapper.insert(seller);
    }

    @Override
    public void update(TbSeller seller) {
        tbSellerMapper.updateByPrimaryKey(seller);
    }

    @Override
    public TbSeller findOne(String id) {
        return tbSellerMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(String[] ids) {
        Lists.newArrayList(ids).stream().forEach(id -> tbSellerMapper.deleteByPrimaryKey(id));
    }
}
