package com.chenyi.yanhuohui.goods.jdgoods;

import com.chenyi.yanhuohui.common.base.exception.YhhRuntimeException;
import com.chenyi.yanhuohui.common.domain.CommonErrorCode;
import com.chenyi.yanhuohui.goods.bean.dto.JDGetSellPriceDTO;
import com.chenyi.yanhuohui.goods.goods.*;
import com.chenyi.yanhuohui.goods.goodscate.GoodsCategoryService;
import com.chenyi.yanhuohui.goods.goodsimage.GoodsImage;
import com.chenyi.yanhuohui.goods.goodsimage.GoodsImageService;
import com.chenyi.yanhuohui.goods.goodsprice.GoodsPrice;
import com.chenyi.yanhuohui.goods.goodsprice.GoodsPriceService;
import com.chenyi.yanhuohui.goods.goodsspecparam.GoodsSpecParam;
import com.chenyi.yanhuohui.goods.goodsspecparam.GoodsSpecParamService;
import com.chenyi.yanhuohui.goods.jd.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class JDGoodsImportService {

    @Autowired
    private JDGoodsService jdGoodsService;

    @Autowired
    private GoodsImageService goodsImageService;

    @Autowired
    private GoodsCategoryService goodsCategoryService;

    @Autowired
    private GoodsSpecParamService goodsSpecParamService;

    @Autowired
    private GoodsPriceService goodsPriceService;

    @Autowired
    private GoodsSkuService goodsSkuService;

    @Autowired
    private GoodsSpuService goodsSpuService;

    /**
     * 通过SKUID导入整组SKU和对应的这个SPU
     * 按理来说这里应该入参是SPUID，但是京东不一样，是通过某个SKU查询出同类商品的
     * @param spuID
     * @return
     */
    public boolean importSku(String sku){
        //第一步：通过/getSimilarSku接口查出一批SKU
        List<SimilarSkuResponseDTO> similarSkuResponseDTOS = jdGoodsService.getSimilarSku(sku);
        //获取所有SKUID
        HashSet<Long> skuIDSet = new HashSet<>();
        similarSkuResponseDTOS.get(0).getSaleAttrList().forEach(SaleAttrDTO->{
            skuIDSet.addAll(SaleAttrDTO.getSkuIds());
        });
        List<String> skuIDList = new ArrayList<>();
        skuIDSet.forEach(id->skuIDList.add(id.toString()));

        //第二步：导入SPU
        //如果不存在，需要新建SPU
        List<GoodsSku> oldGoodsSkus = goodsSkuService.findBySkuIds(skuIDList);
        JDGoodsDetailResponseDTO jdGoodsDetailResponseDTO = jdGoodsService.getGoodsDetail(sku);
        Long spuId;
        if(oldGoodsSkus.size()>0){
            spuId = oldGoodsSkus.get(0).getSpuId();
        }else {
            //生成新的SPUID
            spuId = 123L;
        }
        //导入SPU
        GoodsSpuDTO goodsSpuDTO = new GoodsSpuDTO();
        goodsSpuDTO.setSpuId(spuId);
        List<String> cateIds = Arrays.asList(jdGoodsDetailResponseDTO.getCategory().split(";"));
        goodsSpuDTO.setCateId(Long.valueOf(cateIds.get(2)));
        goodsSpuDTO.setSpuName(jdGoodsDetailResponseDTO.getName());
        this.importGoodsSpu(spuId,goodsSpuDTO);

        //第三步：导入SKU
        this.importGoodsSkus(spuId,skuIDList);



        //第二步：导入商品信息
        skuIDList.forEach(skuId->{

        });
       // jdGoodsService.getGoodsDetail()

        //第三步：导入图片
        this.importGoodsImage(skuIDList);

        //第四步：导入价格


        return true;
    }

    public void importGoodsSpu(Long spuId,GoodsSpuDTO goodsSpuDTO){
        GoodsSpu goodsSpu = new GoodsSpu();
        goodsSpu.setSpuId(goodsSpuDTO.getSpuId());
        goodsSpu.setSaleable(1);
        goodsSpu.setSpuName(goodsSpuDTO.getSpuName());
        goodsSpu.setDelFlag(0);
        goodsSpu.setCid3(goodsSpuDTO.getCateId());
        GoodsSpuDetail goodsSpuDetail = new GoodsSpuDetail();
        goodsSpuDetail.setSpuId(spuId);
        goodsSpuDetail.setDelFlag(0);
        goodsSpuService.saveGoodsSpu(goodsSpu);
        goodsSpuService.saveGoodsSpuDetail(goodsSpuDetail);
    }

    public void importGoodsSkus(Long spuId, List<String> skus){
        skus.forEach(skuId->{
            JDGoodsDetailResponseDTO jdGoodsDetailResponseDTO = jdGoodsService.getGoodsDetail(skuId);
            List<String> cateIds = Arrays.asList(jdGoodsDetailResponseDTO.getCategory().split(";"));
            //构建通用属性表目录
            this.importGeneralParam(Long.valueOf(cateIds.get(2)),jdGoodsDetailResponseDTO.getCategoryAttrs());

            GoodsSku goodsSku = new GoodsSku();
            goodsSku.setSkuId(Long.valueOf(jdGoodsDetailResponseDTO.getSku()));
            goodsSku.setSkuName(jdGoodsDetailResponseDTO.getName());
            goodsSku.setSaleable(1);
            goodsSku.setType(0);
            goodsSku.setSpuId(spuId);
            goodsSku.setDelFlag(0);
            goodsSkuService.saveGoodsSku(goodsSku);
            GoodsSkuDetail goodsSkuDetail = new GoodsSkuDetail();
            goodsSkuDetail.setSkuId(Long.valueOf(jdGoodsDetailResponseDTO.getSku()));
            goodsSkuDetail.setPackingList(jdGoodsDetailResponseDTO.getWareQD());
            goodsSkuDetail.setDelFlag(0);
            goodsSkuDetail.setGeneralParam(jdGoodsDetailResponseDTO.getParam());
            goodsSkuService.saveGoodsSkuDetail(goodsSkuDetail);

            //导入通用属性值
            List<GoodsSpecParam> paramsListByCate = goodsSpecParamService.findByCategoryId(Long.valueOf(cateIds.get(2)));
            //Map<String,GoodsSpecParam> name2ParamMap = paramsListByCate.stream().collect(Collectors.toMap(GoodsSpecParam::getName,GoodsSpecParam ))
        });
    }


    /**
     * sku个数不要超过100
     * @param skuList
     */
    public void importGoodsImage(List<String> skuList){
        Map<String, List<JDSkuImgResponseDTO>> skuID2Imgs = jdGoodsService.getJdSkuImg(skuList);
        skuID2Imgs.forEach((key,value)->{
            List<GoodsImage> goodsImages = new ArrayList<>();
            value.forEach(JDSkuImgResponseDTO->{
                GoodsImage goodsImage = new GoodsImage();
                goodsImage.setSkuId(JDSkuImgResponseDTO.getSkuId());
                if(JDSkuImgResponseDTO.getIsPrimary()==1){
                    goodsImage.setImageType(1);
                }else {
                    goodsImage.setImageType(0);
                }
                //上传到本系统的文件存储，并生成地址
                String localUrl = "";
                goodsImage.setLocalUrl(localUrl);
                goodsImage.setExternalUrl(JDSkuImgResponseDTO.getPath());
                goodsImage.setDelFlag(0);
                goodsImage.setCreateTime(LocalDateTime.now());
                goodsImage.setUpdateTime(LocalDateTime.now());
                goodsImages.add(goodsImage);
            });
            log.info("商品{}共{}张图片，开始存入数据库。",key,goodsImages.size());
            goodsImageService.saveGoodsImages(goodsImages);
        });
    }

    @Async
    public void importGoodsCategories(){
        log.info("==============开始进行京东类目同步==============");
        int pageNo = 1;
        int pageSize = 1000;
        JDCategoriesResponseDTO jdCategoriesResponseDTO = null;
        try {
            jdCategoriesResponseDTO = jdGoodsService.getCategores(pageNo,pageSize);
        } catch (Exception e) {
            log.error("京东接口查询商品类目失败，PageNo={},PageSize={}",pageNo,pageSize);
            throw new YhhRuntimeException(CommonErrorCode.FAILED);
        }
        int totalPageNo = jdCategoriesResponseDTO.getTotalRows()/pageSize+1;
        goodsCategoryService.saveJDCategoryVO(jdCategoriesResponseDTO.getCategorys());
        for(int i = 2; i<=totalPageNo; i++){
            JDCategoriesResponseDTO responseDTO = null;
            try {
                responseDTO = jdGoodsService.getCategores(i,pageSize);
            } catch (Exception e) {
                log.error("京东接口查询商品类目失败，PageNo={},PageSize={}，跳过该页。",i,pageSize);
            }
            goodsCategoryService.saveJDCategoryVO(responseDTO.getCategorys());
        }
        log.info("==============京东类目同步结束！==============");
    }

    /**
     * 入参是skuID和对应的categoryAttrs属性列表
     */
    public List<GoodsSpecParam> importGeneralParam(Long cateId,List<CategoryAttrDTO> categoryAttrDTOS){
        //存储本次需要导入的属性
        List<GoodsSpecParam> goodsSpecParams = new ArrayList<>();
        categoryAttrDTOS.forEach(categoryAttrDTO -> {
            //首先检查该三级类目下面这个属性值是否已存在
            List<GoodsSpecParam> oldGoodsSpecParams = goodsSpecParamService.findByCategoryIdAndName(cateId,categoryAttrDTO.getAttrName());
            if(oldGoodsSpecParams.size()>0){
                log.info("{}类目下面的{}属性已存在，本次属性为{}略过。",cateId,categoryAttrDTO.getAttrName(),categoryAttrDTO.toString());
            }else {
                //现在的代码是简化的
                GoodsSpecParam goodsSpecParam = new GoodsSpecParam();
                goodsSpecParam.setCategoryId(cateId);
                goodsSpecParam.setName(categoryAttrDTO.getAttrName());
                goodsSpecParam.setNumerical(0);
                goodsSpecParam.setGeneric(1);
                goodsSpecParam.setSearching(0);
                goodsSpecParam.setSort(9);
                goodsSpecParams.add(goodsSpecParam);
            }
        });
        return goodsSpecParamService.saveSpecParams(goodsSpecParams);
    }

    public void importGoodsPrice(List<String> skuList){
        List<JDGetSellPriceDTO> priceDTOS = jdGoodsService.getSellPrice(skuList);
        List<GoodsPrice> goodsPriceList = new ArrayList<>();
        priceDTOS.forEach(jdGetSellPriceDTO -> {
            GoodsPrice goodsPrice = new GoodsPrice();
            goodsPrice.setSkuId(Long.valueOf(jdGetSellPriceDTO.getSkuId()));
            goodsPrice.setMarketPrice(jdGetSellPriceDTO.getJdPrice());
            goodsPrice.setSettlePrice(jdGetSellPriceDTO.getPrice());
            goodsPrice.setLinePrice(jdGetSellPriceDTO.getPrice());
            goodsPrice.setVipPrice(jdGetSellPriceDTO.getPrice().multiply(BigDecimal.valueOf(0.9)));
            goodsPriceList.add(goodsPrice);
        });
        goodsPriceService.saveAll(goodsPriceList);
    }
}
