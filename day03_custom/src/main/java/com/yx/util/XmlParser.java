package com.yx.util;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class XmlParser {
    public static DbProfile parseXml(InputStream in) throws Exception {
        DbProfile dbProfile = new DbProfile();
        SAXReader saxReader = new SAXReader();
        saxReader.setEntityResolver(new EntityResolver() {
            @Override
            public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
                return new InputSource(new StringReader(""));
            }
        });

        saxReader.setValidation(false);

        Document document = saxReader.read(in);
        Element root = document.getRootElement();
        List<Element> props = root.selectNodes("//property").stream()
                .filter(node -> node instanceof Element)
                .map(node -> (Element) node)
                .collect(Collectors.toList());
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
        List<Element> mappers = root.selectNodes("//mappers/mapper").stream()
                .filter(node -> node instanceof Element)
                .map(node -> (Element) node)
                .collect(Collectors.toList());
        Map<String, Mapper> statementMap = new HashMap<>();
        for (Element mapper : mappers) {
            String path = mapper.attributeValue("resource");
            if (path != null && !path.trim().isEmpty()) {
                Map<String, Mapper> oneMapper = parseMapperXml(path);
                statementMap.putAll(oneMapper);
            }
        }
        dbProfile.setMappers(statementMap);
        return dbProfile;
    }

    private static Map<String, Mapper> parseMapperXml(String path) throws Exception {
        Map<String, Mapper> map = new HashMap<>();

        SAXReader reader = new SAXReader();
        reader.setEntityResolver(new EntityResolver() {
            @Override
            public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
                return new InputSource(new StringReader(""));
            }
        });

        // 禁用DTD验证
        reader.setValidation(false);

        // 尝试设置各种可能禁用DTD的特性
        try {
            reader.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        } catch (Exception e) {
            // 忽略不支持该特性的异常
        }

        try {
            reader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        } catch (Exception e) {
            // 忽略不支持该特性的异常
        }

        try {
            reader.setFeature("http://xml.org/sax/features/external-general-entities", false);
        } catch (Exception e) {
            // 忽略不支持该特性的异常
        }

        try {
            reader.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        } catch (Exception e) {
            // 忽略不支持该特性的异常
        }

        Document document = reader.read(Resources.getResourceAsStream(path));
        Element root = document.getRootElement();
        String namespace = root.attributeValue("namespace");
        List<Element> selects = root.selectNodes("//select").stream()
                .filter(node -> node instanceof Element)
                .map(node -> (Element) node)
                .collect(Collectors.toList());
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