package exp.Forms;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@Configuration
@ComponentScan("exp.Forms")
@EnableAutoConfiguration
public class FileUploadBeanConfig {

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory mcf = new MultipartConfigFactory();
        mcf.setMaxFileSize(DataSize.ofKilobytes(128));
        mcf.setMaxRequestSize(DataSize.ofKilobytes(128));
        return mcf.createMultipartConfig();
    }

}
