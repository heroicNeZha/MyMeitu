package ustc.sse.meitu.util;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyBatisGen {
    public static void main(String[] args) {
        try {
            List<String> warnings = new ArrayList<>();
            InputStream is = MyBatisGen.class.getClassLoader().getResource("generatorConfig.xml").openStream();
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(is);
            is.close();

            boolean overwrite = true;
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);

            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
        } catch (IOException | XMLParserException | InvalidConfigurationException | InterruptedException | SQLException e) {
            e.printStackTrace();
        }
    }
}
