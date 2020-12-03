package uarr.museum.incidents;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import uarr.museum.incidents.cache.SpreadsheetUtil;

@SpringBootApplication
public class AppTier {

	public static void main(String[] args) {
				
		SpreadsheetUtil.loadSpreadsheet(System.getProperty("input.spreadsheet"));
		
		SpringApplication app = new SpringApplication(AppTier.class);
		app.setDefaultProperties(Collections
		          .singletonMap("server.port", "8086"));
		app.run(args);
	}

}
