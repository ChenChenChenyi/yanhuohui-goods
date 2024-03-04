package com.chenyi.yanhuohui.goods.elastic.jdgoods;

import com.chenyi.yanhuohui.common.base.util.CustomLocalDateTimeDeserializer;
import com.chenyi.yanhuohui.common.base.util.CustomLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Document(indexName = "jdesgoodsinfo_backup", type = "_doc", replicas = 1, shards = 1, createIndex = true)
public class JdEsGoodsInfoBackup {

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

    @Field(type = FieldType.Date,format = DateFormat.custom,pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    @Field(type = FieldType.Integer)
    private int channelSource;


}
