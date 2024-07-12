package com.example.util;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.springframework.core.io.Resource;
import org.apache.ibatis.scripting.xmltags.DynamicSqlSource;
import org.apache.ibatis.scripting.xmltags.SqlNode;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.example.db1.entity.User1;

public class Test {

	public static void main(String[] args) throws IOException {
		
		
		
		
		String resource = "com/example/util/UserQueries.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        Configuration configuration = new Configuration();
        XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(inputStream, configuration, resource, configuration.getSqlFragments());
        xmlMapperBuilder.parse();

        // Get the MappedStatement
        MappedStatement mappedStatement = configuration.getMappedStatement("com.example.queries.UserQueries.getUsersByName");

        // Parameters for the dynamic SQL
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("name", "John");
        parameterMap.put("age", 30);


        // Create the BoundSql
        BoundSql boundSql = mappedStatement.getBoundSql(parameterMap);

        // Get the dynamic SQL query
        String sqlQuery = boundSql.getSql();
        System.out.println("mmmmmmmmmmmmmmmmm.............."+sqlQuery);


		// TODO Auto-generated method stub
		
		
		FtMap a = new FtMap();
		
		a.put("name", "sdsa0");
		a.put("age", 30);
		
		
		User1 a1= new User1();
		
		try {
			ConvertUtil.mapToBean(a1, a);
			
			
			System.out.println(a1.getAge());
			
			FtMap ftmap=ConvertUtil.objectToMap(a1);
			
			System.out.println(ftmap.get("name"));
			System.out.println(ftmap.get("age"));
			
			if (ftmap.get("age") instanceof String) {
				System.out.println("String");
			}
			
			if (ftmap.get("age") instanceof Integer) {
				System.out.println("int");
			}
			
			a = ConvertUtil.convertMapToObjectFieldType(a, User1.class);
			
			System.out.println(a.get("age"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
