package com.skafandria.pocgraphUITA;

import com.skafandria.pocgraphUITA.configurations.PocProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PocgraphUitaApplication {


	public static void main(String[] args) throws Exception {
		ApplicationContext context = SpringApplication.run(PocgraphUitaApplication.class, args);
		PocProperties properties = context.getBean(PocProperties.class);
		System.out.println(properties.getGeckodriverPath());
		System.setProperty("webdriver.gecko.driver",properties.getGeckodriverPath());
		WebDriver driver = new FirefoxDriver();
		String expectedTitleAuth = "Please sign in";
		String expectedTitlePOC = "POC-GRAPH";

		System.out.println(properties.getBaseUrl());
		driver.get(properties.getBaseUrl());

		if(!expectedTitleAuth.equals(driver.getTitle())){
			throw new Exception("Titre Page Authentification non valide : currentTitle->"+driver.getTitle());
		}

		driver.findElement(By.id("username")).sendKeys(properties.getUsernameAuth());
		driver.findElement(By.id("password")).sendKeys(properties.getPasswordAuth());

		driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();

		if(!expectedTitlePOC.equals(driver.getTitle())){
			throw new Exception("Titre Page POC non valide : currentTitle->"+driver.getTitle());
		}

		driver.findElement(By.xpath("//button[@class=\"btn btn-danger\"]")).click();

		if(!"ok".equals(driver.getTitle())){
			throw new Exception("Titre Page ResultPOC non valide : currentTitle->"+driver.getTitle());
		}
        System.out.println("DAZ!!");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
        String currentValue = driver.findElements(By.xpath("//h1")).get(0).getText();

		if(!"OK".equals(currentValue)){
			throw new Exception("ResultPOC non valide : currentValue->"+currentValue);
		}
	}
}
