package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_ProfessorView extends PO_NavView{
	
	static public void fillForm(WebDriver driver, String dnip, String namep, String lastnamep, String categoriap) {
		WebElement dni = driver.findElement(By.name("dni"));
		dni.click();
		dni.clear();
		dni.sendKeys(dnip);
		WebElement name = driver.findElement(By.name("nombre"));
		name.click();
		name.clear();
		name.sendKeys(namep);
		WebElement lastname = driver.findElement(By.name("apellidos"));
		lastname.click();
		lastname.clear();
		lastname.sendKeys(lastnamep);
		WebElement categoria = driver.findElement(By.name("categoria"));
		categoria.click();
		categoria.clear();
		categoria.sendKeys(categoriap);
		// Pulsar el boton de Alta.
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}

}
