package com.roden.mybatis.generator;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

public class MyBatisGeneratorUtil {
	public static void main(String[] args)
			throws IOException, XMLParserException, SQLException, InterruptedException, InvalidConfigurationException {
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		File configFile = new File(getPath());
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
	}

	public static String getPath() {
		String CONFIG_PATH = null;
		File file = new File(MyBatisGeneratorUtil.class.getResource("/").getFile());
		String CONFIG_FILE = "generatorConfig.xml";
		try {
			CONFIG_PATH = file.getCanonicalPath();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		CONFIG_PATH = CONFIG_PATH + File.separator + CONFIG_FILE;
		return CONFIG_PATH;
	}
}
