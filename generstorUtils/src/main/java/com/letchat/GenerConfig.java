package com.letchat;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author alice
 */
public class GenerConfig {

    public void generator() throws Exception{

        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        //指定 逆向工程配置文件
        String path = GenerConfig.class.getResource("/generatorConfig.xml").getPath();
        System.out.println(path);
        File configFile = new File(path);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
                callback, warnings);
        myBatisGenerator.generate(null);

    }

    public static void main(String[] args) {
        try {
            GenerConfig generatorSqlMap = new GenerConfig();
            generatorSqlMap.generator();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
