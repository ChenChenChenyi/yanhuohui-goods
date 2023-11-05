package com.chenyi.yanhuohui.goods.elastic.jdgoods;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;

@Data
@Document(indexName = "jdesgoodsinfo",createIndex = true)
public class JdEsGoodsInfo {

    @Id
    @Field(type = FieldType.Keyword)
    private String goodsInfoId;

    @Field(searchAnalyzer = "ik_smart",analyzer="ik_smart",type=FieldType.Text)
    private String goodsInfoName;

    @Field(type = FieldType.Double)
    private BigDecimal price;

    @Field(type = FieldType.Long)
    private Long cateId;

    @Field(searchAnalyzer = "ik_max_word",analyzer="ik_max_word",type=FieldType.Text)
    private String brandName;

    @Field(type=FieldType.Keyword)
    private String brandPinyin;

//    @Field(type = FieldType.Date,format = DateFormat.custom,pattern = "yyyy-MM-dd HH:mm:ss.SSS")
//    @JSONField(format="yyyy-MM-dd HH:mm:ss.SSS")
//    private LocalDateTime createTime;
//    @Field(type = FieldType.Date,format = DateFormat.basic_date_time)
//    private LocalDateTime updateTime;
}
