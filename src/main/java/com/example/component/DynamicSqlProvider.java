package com.example.component;

import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

@Component
public class DynamicSqlProvider {

    private final Map<String, String> sqlMap = new HashMap<>();
    private final Configuration configuration;

    public DynamicSqlProvider() throws Exception {
        this.configuration = new Configuration();
        loadXmlFiles();
    }

    private void loadXmlFiles() throws Exception {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:mappers/**/*.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        for (Resource resource : resources) {
        	
        	
            Document document = builder.parse(resource.getInputStream());
            Element root = document.getDocumentElement();
            String namespace = root.getAttribute("namespace");

            NodeList nodes = root.getElementsByTagName("sql");
            for (int i = 0; i < nodes.getLength(); i++) {
                Element element = (Element) nodes.item(i);
                String id = element.getAttribute("id");
                String fullId = namespace + "." + id;
                sqlMap.put(fullId, element.getTextContent().trim());
            }
        }
    }

    public String getSql(String id, Map<String, Object> params) {
        String xmlSql = sqlMap.get(id);
        if (xmlSql == null) {
            throw new RuntimeException("SQL not found for id: " + id);
        }

        SqlSourceBuilder sqlSourceBuilder = new SqlSourceBuilder(configuration);
        SqlSource sqlSource = sqlSourceBuilder.parse(xmlSql, Object.class, params);
        BoundSql boundSql = sqlSource.getBoundSql(params);

        return boundSql.getSql();
    }
}