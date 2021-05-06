package com.tsmc.prequal;

import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.tsmc.prequal.config.DataSourceConfiguration;

@EnableScheduling
@ComponentScan(basePackages = "com.tsmc.prequal")
@EnableTransactionManagement
@Import({DataSourceConfiguration.class})
@SpringBootApplication(exclude 
		= { DataSourceAutoConfiguration.class, 
			HibernateJpaAutoConfiguration.class,
			DataSourceTransactionManagerAutoConfiguration.class })
//@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class PreqKepManagerApplication {

	private Logger LOG;
	public String VERSION = "1.0.1"; 
	
	public static void main(String[] args) {
		SpringApplication.run(PreqKepManagerApplication.class, args);
	}

}
