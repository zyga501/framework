package framework.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class XMLParser {
    public static InputStream getStringStream(String sInputString) throws UnsupportedEncodingException {
        ByteArrayInputStream tInputStringStream = null;
        if (sInputString != null && !sInputString.trim().equals("")) {
            tInputStringStream = new ByteArrayInputStream(sInputString.getBytes("utf-8"));
        }
        return tInputStringStream;
    }

    public static Map<String, Object> convertMapFromXml(String xmlString) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream is =  getStringStream(xmlString);
        Document document = builder.parse(is);

        return convertMapFromXML(document.getFirstChild().getChildNodes());
    }

    public static Map<String, Object> convertMapFromXmlFile(String xmlPath) throws ParserConfigurationException, IOException, SAXException {
        File file = new File(xmlPath);
        if (file.exists()) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            StringBuilder stringBuilder = new StringBuilder();
            String lineBuffer;
            while ((lineBuffer = bufferedReader.readLine()) != null) {
                stringBuilder.append(lineBuffer);
            }
            bufferedReader.close();
            return convertMapFromXml(stringBuilder.toString());
        }
        return null;
    }

    private static Map<String, Object> convertMapFromXML(NodeList allNodes) {
        Node node;
        Map<String, Object> map = new HashMap<String, Object>();
        int i=0;
        while (i < allNodes.getLength()) {
            node = allNodes.item(i);
            if(node instanceof Element){
                if (node.getChildNodes().getLength() == 1) {
                    map.put(node.getNodeName(), node.getTextContent());
                }
                else {
                    map.put(node.getNodeName(), convertMapFromXML(node.getChildNodes()));
                }
            }
            i++;
        }
        return map;
    }
}
