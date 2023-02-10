package com.sunlife.vn.common.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import net.sf.JRecord.cbl2xml.Cobol2Xml;
import net.sf.JRecord.cbl2xml.def.ICobol2Xml;
import net.sf.JRecord.cbl2xml.def.Icb2xml2Xml;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;

import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



public class XmlUtil {

    public static Node getSingleValue(String xml, String path) throws DocumentException, UnsupportedEncodingException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));
        Element root = document.getRootElement();
        return root.selectSingleNode(path);
    }

    public static String getSingleValueText(String xml, String path)
            throws DocumentException, UnsupportedEncodingException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));
        Element root = document.getRootElement();
        Node node = root.selectSingleNode(path);
        if (Objects.nonNull(node)) {
            return node.getText();
        } else {
            return "";
        }
    }
    public static String removeWhiteSpace(String input) {
        return input.replaceAll(">[\\s\r\n]*<", "><");
    }

    public static String cobol2Xml(String cobolType, String data) throws IOException, XMLStreamException {
        // get copybook
        InputStream cp = new ClassPathResource("template/cobol/" + cobolType + ".cpy").getInputStream();
        // Build convert option
        ICobol2Xml cbl2xml = Cobol2Xml.newCobol2Xml(cp, cobolType).setDialect(1).setFont("UTF-8");
        setOpts(cbl2xml);

        InputStream targetStream = IOUtils.toInputStream(data, StandardCharsets.UTF_8);
        ByteArrayOutputStream xmlStream = new ByteArrayOutputStream();

        cbl2xml.cobol2xml(targetStream, xmlStream);
        // get xml string and return
        return xmlStream.toString("UTF-8");
    }

    public static void setOpts(Icb2xml2Xml cbl2xml) {
        cbl2xml.setFileOrganization(90).setDropCopybookNameFromFields(false).setXmlMainElement("CobolData")
                .setTagFormat(0).setSplitCopybook(0);
    }
}
