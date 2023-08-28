package com.chenyi.yanhuohui.goods.jdgoods;

import com.chenyi.yanhuohui.common.base.exception.SbcRuntimeException;
import com.chenyi.yanhuohui.goods.goodscate.GoodsCategory;
import com.chenyi.yanhuohui.goods.goodscate.GoodsCategoryService;
import com.chenyi.yanhuohui.goods.goodsimage.GoodsImage;
import com.chenyi.yanhuohui.goods.goodsimage.GoodsImageService;
import com.chenyi.yanhuohui.goods.jd.JDCategoriesResponseDTO;
import com.chenyi.yanhuohui.goods.jd.JDSkuImgResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class JDGoodsImportService {

    @Autowired
    private JDGoodsService jdGoodsService;

    @Autowired
    private GoodsImageService goodsImageService;

    @Autowired
    private GoodsCategoryService goodsCategoryService;

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
            throw new SbcRuntimeException(e);
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
}
