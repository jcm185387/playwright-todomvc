# QA Automation con Playwright + Java (POM + JUnit + Allure)

Este proyecto demuestra cómo automatizar pruebas de la aplicación **TodoMVC** usando **Playwright + Java**, aplicando el patrón **Page Object Model (POM)** y ejecutando casos con **JUnit 5**.  
Además, integra **Allure Reports** para generar reportes HTML interactivos.

La aplicación bajo prueba es la demo oficial de Playwright:  
👉 https://demo.playwright.dev/todomvc

---

## 🚀 Requisitos

- Java 11+
- Maven 3.6+
- Playwright para Java
- Allure CLI instalado (opcional para abrir reportes)

Instala los navegadores de Playwright:
```bash
mvn exec:java -Dexec.mainClass="com.microsoft.playwright.CLI" -Dexec.args="install"
