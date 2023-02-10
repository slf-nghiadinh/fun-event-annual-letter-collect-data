package com.sunlife.vn.repository.ing;

import com.sunlife.vn.models.CollectLetterModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface PaymentRequestCreationRepository {

    List<CollectLetterModel> queryCaseId();
    void UPDATE_TZPMS_FLAG_FAILED(@Param("coId") String coId, @Param("polId") String polId, @Param("tpDt") String tpDt, @Param("tsNum")  String tsNum );
    Integer UPDATE_TZPMS_FLAG(@Param("coId") String coId, @Param("polId") String polId, @Param("docId") String docId, @Param("tpDt") String tpDt,@Param("tsNum")  String tsNum);
}
