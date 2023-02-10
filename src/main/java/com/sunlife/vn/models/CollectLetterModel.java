package com.sunlife.vn.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CollectLetterModel {
    String Data;
    String cobolType;
    String coId;
    String polId;
    String docId;
    String batchDate;
    String seqNum;
    String evtTyp;
}
