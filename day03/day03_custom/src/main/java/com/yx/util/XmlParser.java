package com.yx.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlParser {
    public static DbProfile parseXml(InputStream in) throws DocumentException {
        DbProfile dbProfile = new DbProfile();
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(in);
        Element root = document.getRootElement();
        List<Element> props = root.selectNodes("//property");
        for (Element prop : props) {
            String name = prop.attributeValue("name");
            String value = prop.attributeValue("value");
            if ("driver".equalsIgnoreCase(name)) {
                dbProfile.setDriver(value);
            } else if ("url".equalsIgnoreCase(name)) {
                dbProfile.setUrl(value);
            } else if ("username".equalsIgnoreCase(name)) {
                dbProfile.setUsername(value);
            } else if ("password".equalsIgnoreCase(name)) {
                dbProfile.setPassword(value);
            }
        }
        List<Element> mappers = root.selectNodes("//mappers/mapper");
        Map<String, Mapper> statementMap = new HashMap<>();
        for (Element mapper : mappers) {
            String path = mapper.attributeValue("resource");
            if (path != null && !path.trim().isEmpty()) {
                Map<String, Mapper> oneMapper = parseMapperXml(path);
                statementMap.putAll(oneMapper);
            }
        }

        // 5. 加入 profile 返回
        dbProfile.setMappers(statementMap);
        return dbProfile;
    }

    private static Map<String, Mapper> parseMapperXml(String path) throws Exception {
        Map<String, Mapper> map = new HashMap<>();

        SAXReader reader = new SAXReader();
        Document document = reader.read(Resources.getResourceAsStream(path));
        Element root = document.getRootElement();
        String namespace = root.attributeValue("namespace");
        List<Element> selects = root.selectNodes("//select");
        for (Element e : selects) {
            String id = e.attributeValue("id");
            String resultType = e.attributeValue("resultType");
            String sql = e.getText().trim();
            Mapper stmt = new Mapper();
            stmt.setResultType(resultType);
            stmt.setSqlStatement(sql);
            map.put(namespace + "." + id, stmt);
        }

        return map;
    }
}
