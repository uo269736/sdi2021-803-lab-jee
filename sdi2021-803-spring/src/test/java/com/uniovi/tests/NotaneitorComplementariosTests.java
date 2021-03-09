package com.uniovi.tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_ProfessorView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class NotaneitorComplementariosTests {
	// En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioens
	// automáticas)):
	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "C:\\Users\\MiguelUni\\Desktop\\TrabajoUniversidadMiguel\\Tercero\\SDI\\Sesion 5\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";

	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Antes de cada prueba se navega al URL home de la aplicación
	@Before
	public void setUp() {
		driver.navigate().to(URL);
	}

	// Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {
	}

	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}

	// Prueba1 Registro de profesores con datos válidos 
	@Test
	public void Prueba1() {
		//Hacemos login como administrador
		PO_PrivateView.loginAndCheck(driver, "99999988F", "123456", "Notas del usuario");
		//Seleccionamos gestión de profesores
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'professor-menu')]/a");
        elementos.get(0).click();
        //Seleccionamos la opción agregar profesores
        elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'professor/add')]");
        elementos.get(0).click();
        //Rellenamos el cuestionario
		PO_ProfessorView.fillForm(driver, "12345678G", "Maria", "Fernandez Alvarez", "Ciencias");
		//Comprobamos que lo rellenamos con exito y que cambiamos de pantalla
		PO_View.checkElement(driver, "text", "Profesores");
	}

	// Prueba2 Registro de profesores con datos inválidos (nombre y categoría inválidos)
	@Test
	public void Prueba2() {
		//Hacemos login como administrador
		PO_PrivateView.loginAndCheck(driver, "99999988F", "123456", "Notas del usuario");
		//Seleccionamos gestión de profesores
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'professor-menu')]/a");
        elementos.get(0).click();
        //Seleccionamos la opción agregar profesores
        elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'professor/add')]");
        elementos.get(0).click();
        //Rellenamos el cuestionario con un nombre demasiado corto
		PO_ProfessorView.fillForm(driver, "12345678G", "M", "Fernandez Alvarez", "Ciencias");
		//Comprobamos que nos da error de nombre corto
		PO_RegisterView.checkKey(driver, "Error.signup.name.length", PO_Properties.getSPANISH());
		//Rellenamos el cuestionario con una categoria demasiado corta
		PO_ProfessorView.fillForm(driver, "22222222G", "Maria", "Fernandez Alvarez", "C");
		//Comprobamos que nos da error de categoria corta
		PO_RegisterView.checkKey(driver, "Error.profesor.agregar.categoria", PO_Properties.getSPANISH());
		
	}

	// Prueba3 Verificar que solo los usuarios autorizados pueden dar de alta un 
	//profesor.
	@Test
	public void Prueba3() {
		//Hacemos login como alumno
		PO_PrivateView.loginAndCheck(driver, "99999990A", "123456", "Notas del usuario");
		//Seleccionamos gestión de profesores
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'professor-menu')]/a");
        elementos.get(0).click();
        //Comprobamos que no nos sale agregar profesores
        SeleniumUtils.textoNoPresentePagina(driver, "Agregar Profesor");
        //Intentamos acceder a agregar profesores directamente
		driver.navigate().to("http://localhost:8090/professor/add");
		//Comprobamos que aparece el texto que nos prohibe entrar
		SeleniumUtils.textoPresentePagina(driver, "HTTP Status 403 – Forbidden");
	}

}