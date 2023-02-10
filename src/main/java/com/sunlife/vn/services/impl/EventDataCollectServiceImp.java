package com.sunlife.vn.services.impl;

import com.ctc.wstx.exc.WstxIOException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sunlife.vn.actionframework.model.input.SqsInput;
import com.sunlife.vn.actionframework.utils.CommonUtils;
import com.sunlife.vn.engine.S3Engine;
import com.sunlife.vn.common.exception.ErrorCode;
import com.sunlife.vn.models.CollectLetterModel;
import com.sunlife.vn.common.constant.AppConstant;
import com.sunlife.vn.models.entity.dynamodb.EvtMstr;
import com.sunlife.vn.repository.EvtMastrRepository;
import com.sunlife.vn.services.EventDataCollectService;
import com.sunlife.vn.services.XmlListFormatService;
import com.sunlife.vn.services.engine.AmazonS3Engine;
import com.sunlife.vn.services.engine.AmazonSqsEngine;
import com.sunlife.vn.common.util.DateTimeUtil;
import com.sunlife.vn.common.util.JacksonUtil;
import com.sunlife.vn.common.util.JsonUtil;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import com.sunlife.vn.common.exception.TerminateException;
import com.sunlife.vn.common.util.XmlUtil;

import java.io.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import com.sunlife.vn.repository.ing.PaymentRequestCreationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.apache.commons.collections4.map.HashedMap;
import com.sunlife.vn.common.constant.AppConstant.ANNUAL_LETTER;
@Service
public class EventDataCollectServiceImp implements EventDataCollectService {

    @Autowired
    private XmlListFormatService xmlListFormatService;

    @Autowired
    private AmazonSqsEngine amazonSqsEngine;


    @Value("${application.aws.sqs.url}")
    private String urlSqs;

    @Value("${application.aws.s3.bucket}")
    private String bucket;

    @Value("${application.aws.s3.key}")
    private String key;

    @Autowired
    private AmazonS3Engine amazonS3Engine;
    @Autowired
    private EvtMastrRepository evtMastrRepository;

    @Autowired
    private PaymentRequestCreationRepository requestCreationRepository;

    @Autowired
    private S3Engine s3Engine;


    ObjectMapper mapper = new ObjectMapper();
    public String collectAnualLeeter() throws SQLException, IOException {
      requestCreationRepository.queryCaseId().forEach(requestCreation->{
            try {
                collectEpolicy(requestCreation);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
       return "OK";
    }

    public void collectEpolicy(CollectLetterModel event) throws Exception, IOException {
        boolean isUpdate = true;
        EvtMstr evtMstr = new EvtMstr();
        try {
            String xmlData = XmlUtil.cobol2Xml(event.getCobolType(), event.getData());
            try {
                int affectedRow  = requestCreationRepository.UPDATE_TZPMS_FLAG(event.getCoId(),event.getPolId(),event.getDocId(),event.getBatchDate(),event.getSeqNum());
                if (affectedRow == 0) {
                    isUpdate = false;
                    throw new TerminateException(ErrorCode.EVT_DATA_NOT_UPDATED);
                }
                String token_id = CommonUtils.getLongUUID();
                evtMstr.setEvt_mastr_id(token_id);
                String  caseId = CommonUtils.getLongUUID();
                evtMstr.setCase_id(caseId);
                ObjectNode eventMetaData = (ObjectNode) JacksonUtil.objectToJsonNode("{\t\t\"mailType\":\"alert-annual-letter\",\t\t\"smsType\":\"ANNUAL_LETTER_SMS\",\t\t\"notiType\":\"ANNUAL_LETTER_NOTI\",\t\t\"typeEE\":\"/CobolData/L4927-DATA-INFO/L4927-LETTER-TYP/L4927-TYP-EE\",\t\t\"typeFE\":\"/CobolData/L4927-DATA-INFO/L4927-LETTER-TYP/L4927-TYP-FE\",\t\t\"typeUL\":\"/CobolData/L4927-DATA-INFO/L4927-LETTER-TYP/L4927-TYP-UL\",\t\t\"typeILP\":\"/CobolData/L4927-DATA-INFO/L4927-LETTER-TYP/L4927-TYP-ILP\",\t\t\"typePaTerm\":\"/CobolData/L4927-DATA-INFO/L4927-LETTER-TYP/L4927-TYP-PA-TERM\",\t\t\"typeMapping\":{\t\t\t\"typeEE\":\"AL_EE\",\t\t\t\"typeFE\":\"AL_FE\",\t\t\t\"typeUL\":\"AL_UL\",\t\t\t\"typeILP\":\"AL_ILP\",\t\t\t\"typePaTerm\":\"AL_PA_TERM\"\t\t},\t\t\"polId\":\"/CobolData/L4927-DATA-INFO/L4927-POL-INFO/L4927-POL-ID\",\t\t\"cusEmail\":\"/CobolData/L4927-DATA-INFO/L4927-OWNER-INFO/L4927-OW-EMAIL-ID\",\t\t\"cusPhone\":\"/CobolData/L4927-DATA-INFO/L4927-OWNER-INFO/L4927-OW-CEL-PHON-NUM\",\t\t\"clientId\":\"/CobolData/L4927-DATA-INFO/L4927-OWNER-INFO/L4927-OW-CLI-ID\",\t\t\"clientName\":\"/CobolData/L4927-DATA-INFO/L4927-OWNER-INFO/L4927-OW-NM\",\t\t\"expiryDate\":\"/CobolData/L4927-DATA-INFO/L4927-LETTER-TYP/L4927-TYP-PA-TERM\",\t\t\"currentDate\":\"/CobolData/L4927-DATA-INFO/L4927-DATE-INFO/L4927-CURRENT-DT\"\t}");

                ObjectNode annualLetterJson = generateAnnualLetterJson(xmlData,event,evtMstr, eventMetaData);
                List<String> letterTypeList = new ArrayList<String>();
                Map<String, String> letterMap = new HashedMap<>();
               letterMap.put(ANNUAL_LETTER.TYPE_EE.value, getXmlTextWithJsonNode(xmlData, eventMetaData, ANNUAL_LETTER.TYPE_EE.value, "N"));
                letterMap.put(ANNUAL_LETTER.TYPE_FE.value, getXmlTextWithJsonNode(xmlData, eventMetaData, ANNUAL_LETTER.TYPE_FE.value, "N"));
                letterMap.put(ANNUAL_LETTER.TYPE_UL.value, getXmlTextWithJsonNode(xmlData, eventMetaData, ANNUAL_LETTER.TYPE_UL.value, "N"));
                letterMap.put(ANNUAL_LETTER.TYPE_ILP.value, getXmlTextWithJsonNode(xmlData, eventMetaData, ANNUAL_LETTER.TYPE_ILP.value, "N"));
                letterMap.put(ANNUAL_LETTER.TYPE_PA_TERM.value, getXmlTextWithJsonNode(xmlData, eventMetaData, ANNUAL_LETTER.TYPE_PA_TERM.value, "N"));

                letterTypeList = letterMap.entrySet().stream().filter(l -> l.getValue().equals("Y")).map(l -> l.getKey()).collect(Collectors.toList());


                evtMstr.setStat("in-progress");
                evtMstr.setUpdt_dt(new Date().toString());
                evtMastrRepository.saveSms(evtMstr);
                Date date = new Date();
                LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                annualLetterJson.put("s3FilePath",key + localDate + "/" + token_id + "/");
                for (String letterType : letterTypeList) {
                    annualLetterJson.put("docTyp", letterType);
                    sendMessageSQS(annualLetterJson, evtMstr);
                }
                uploadJsonFile(annualLetterJson, token_id,  xmlData);

            } catch (Exception ex) {
                throw ex;
            }

        } catch(WstxIOException we) {
            if(isUpdate) {
                evtMstr.setStat("failed");
                evtMstr.setUpdt_dt(new Date().toString());
                evtMastrRepository.saveSms(evtMstr);
                requestCreationRepository.UPDATE_TZPMS_FLAG_FAILED(event.getCoId(),event.getPolId(),event.getBatchDate(),event.getSeqNum());
            }
            throw we;
        } catch (Exception e) {
            throw e;
        }

    }

    public ObjectNode generateAnnualLetterJson(String xmlData,CollectLetterModel event, EvtMstr evtMstr, ObjectNode eventMetaData) throws Exception {

        try {

            xmlData = xmlListFormatService.format(xmlData);
            ObjectNode enrichdata = enrichData(xmlData, event, eventMetaData, evtMstr.getEvt_mastr_id());
            String polId = getXmlTextWithJsonNode(xmlData, eventMetaData, ANNUAL_LETTER.POLID.value, "");
            Timestamp anniversaryDate = new Timestamp(DateTimeUtil.parseDate(XmlUtil.getSingleValueText(xmlData, "/CobolData/L4927-DATA-INFO/L4927-POL-INFO/L4927-POL-ANNV-DT"), "dd/MM/yyyy").getTime());
            enrichdata.put("anniversaryDate",anniversaryDate.toString());
            return enrichdata;
        } catch (Exception e) {
            throw e;
        }
    }

    private String getXmlTextWithJsonNode(String xmlData, ObjectNode json, String node, String defaultValue) throws UnsupportedEncodingException, DocumentException {
        return XmlUtil.getSingleValue(xmlData,
                JsonUtil.extractJsonString(json.get(node))) == null ? defaultValue
                : XmlUtil.getSingleValue(xmlData, JsonUtil.extractJsonString(json.get(node)))
                .getText();
    }

    public ObjectNode enrichData(String xmlData, CollectLetterModel event, ObjectNode eventMetaData, String token_id) throws JsonProcessingException, UnsupportedEncodingException, DocumentException, SQLException {
        ObjectNode resultNode = JacksonUtil.getBlankObjectNode();
        Map<String, String> dataMap = new HashedMap<String, String>();

        dataMap.put(ANNUAL_LETTER.POLID.value, getXmlTextWithJsonNode(xmlData, eventMetaData, ANNUAL_LETTER.POLID.value, ""));
        dataMap.put(ANNUAL_LETTER.CUS_EMAIL.value, getXmlTextWithJsonNode(xmlData, eventMetaData, ANNUAL_LETTER.CUS_EMAIL.value, ""));
        dataMap.put(ANNUAL_LETTER.CUS_PHONE.value, getXmlTextWithJsonNode(xmlData, eventMetaData, ANNUAL_LETTER.CUS_PHONE.value, ""));
        dataMap.put(ANNUAL_LETTER.CUS_PHONE.value, getXmlTextWithJsonNode(xmlData, eventMetaData, ANNUAL_LETTER.CUS_PHONE.value, ""));
        dataMap.put(ANNUAL_LETTER.EXPIRY_DATE.value, getXmlTextWithJsonNode(xmlData, eventMetaData, ANNUAL_LETTER.EXPIRY_DATE.value, ""));
        dataMap.put(ANNUAL_LETTER.CLIENT_NAME.value, getXmlTextWithJsonNode(xmlData, eventMetaData, ANNUAL_LETTER.CLIENT_NAME.value, ""));
        dataMap.put(ANNUAL_LETTER.CLIENT_ID.value, getXmlTextWithJsonNode(xmlData, eventMetaData, ANNUAL_LETTER.CLIENT_ID.value, ""));
        dataMap.put(ANNUAL_LETTER.TOKEN_ID.value, token_id);
//		Date currentDate = DateTimeUtil.parseDate(
//				getXmlTextWithJsonNode(xmlData, eventMetaData, ANNUAL_LETTER.CURRENT_DATE.value, ""), "dd/MM/yyyy");
        Date currentDate = new Date();
        String currentDateStr = DateTimeUtil.longToDateString(currentDate.getTime(), AppConstant.DATETIME_YYYYMMDD_HHMMSS);
        dataMap.put(ANNUAL_LETTER.CURRENT_DATE.value, currentDateStr);

        resultNode.put(AppConstant.DOCUMENT_GENERATE.USER_ID.value, dataMap.get(ANNUAL_LETTER.CLIENT_ID.value));
        resultNode.put(AppConstant.DOCUMENT_GENERATE.TO.value, dataMap.get(ANNUAL_LETTER.CUS_EMAIL.value));
        resultNode.put(AppConstant.DOCUMENT_GENERATE.SMS_TO.value, dataMap.get(ANNUAL_LETTER.CUS_PHONE.value));
        for (Map.Entry<String, String> e : dataMap.entrySet()) {
            resultNode.put(e.getKey(), e.getValue());
        }

        return resultNode;
    }
    public void sendMessageSQS(ObjectNode objectNode, EvtMstr evtMstr) {
        SqsInput input = new SqsInput();
        input.setQueueUrl(urlSqs);
        evtMstr.setQueue_nm("AnnualLetter");
        SqsInput.MessageData messageData = new SqsInput.MessageData();
        messageData.setId(evtMstr.getCase_id());
        messageData.setDeduplicationId("AnnualLetter");
        messageData.setDeduplicationId(evtMstr.getCase_id());
        messageData.setGroupId("AnnualLetter");
        messageData.setDelaySecond(30);
        messageData.setMessageBody(objectNode.toString());
        List<SqsInput.MessageData> messageDataList = new ArrayList<SqsInput.MessageData>();
        messageDataList.add(messageData);
        input.setMessageData(messageDataList);
        amazonSqsEngine.sendMessage(input);
    }

    public String  uploadJsonFile(ObjectNode enrichdata, String fileName, String data) {
        try {
            enrichdata.put("data", data);
            byte[] fileContext =  mapper.writeValueAsBytes(enrichdata);
            Calendar cal = Calendar.getInstance();
            System.out.println(cal);
            Date date = new Date();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String s3StorageKey = key + localDate + "/" + fileName + "/" +fileName +".json";
            s3Engine.putObjectByteArray(bucket, s3StorageKey, fileContext);
            return key + localDate + "/" + fileName + "/";
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    private static byte[] getObjectFile(File file) {
        FileInputStream fileInputStream = null;
        byte[] bytesArray = null;
        try {
            bytesArray = new byte[(int) file.length()];
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytesArray);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytesArray;
    }
}
