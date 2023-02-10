package com.sunlife.vn.services;

import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import com.sunlife.vn.common.util.XmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

@Service
public class XmlListFormatService {

    private Logger logger = LoggerFactory.getLogger(XmlListFormatService.class);

    public String format(String xmlString) throws Exception {
        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            String xPathList = "/CobolData/L4927-DATA-INFO/L4927-CVG-INFO/L4927-CVG-CNT";
            String xPathItem = "/CobolData/L4927-DATA-INFO/L4927-CVG-INFO/L4927-CVG-G/L4927-CVG-T";
            XPath xPath = XPathFactory.newInstance().newXPath();
            NodeList countList = (NodeList) xPath.evaluate(xPathList, doc, XPathConstants.NODESET);
            int count = 0;
            if (!(countList.getLength() == 0 || countList.item(0) == null)) {
                for (int i = 0; i < countList.getLength(); i++) {
                    count = countList.item(i).getTextContent().equals("") ? 0 : Integer.valueOf(countList.item(i).getTextContent());
                    NodeList itemList = (NodeList) xPath.evaluate(xPathItem, countList.item(i).getParentNode(), XPathConstants.NODESET);
                    itemList.getLength();
                    for (int j = 0; j < itemList.getLength(); ++j) {
                        Element e = (Element) itemList.item(j);
                        if (j >= count && (e.getTextContent().equals("") || e.getTextContent().equals("00"))) {
                            e.getParentNode().removeChild(e);
                            continue;
                        }
                    }
                }

            }
            StreamResult result = new StreamResult(new StringWriter());
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);
            String xmlStringAfter = result.getWriter().toString();
            return XmlUtil.removeWhiteSpace(xmlStringAfter);
        } catch (Exception e) {
            logger.error(e.toString());
            throw e;
        }
    }
}