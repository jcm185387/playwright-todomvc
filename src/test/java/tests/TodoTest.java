package tests;

import com.microsoft.playwright.*;
import pages.TodoPage;
import org.junit.jupiter.api.*;
import io.qameta.allure.Description;
import io.qameta.allure.Attachment;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    static Playwright playwright;
    static Browser browser;
    Page page;
    TodoPage todoPage;

    @BeforeAll
    static void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
            new BrowserType.LaunchOptions().setHeadless(false) // headed: abre la ventana
        );
    }

    @BeforeEach
    void createPage() {
        page = browser.newPage();
        todoPage = new TodoPage(page);
    }

    @Test
    @Description("Valida la creación de una tarea en TodoMVC")
    void agregarTarea() {
        todoPage.navigate();
        todoPage.addTask("Aprender POM con Playwright Java");
        assertEquals("Aprender POM con Playwright Java", todoPage.getTaskByIndex(0));
        attachScreenshot("agregarTarea");
    }

    @Test
    @Description("Valida la creación de dos tareas consecutivas en TodoMVC")
    void agregarDosTareas() {
        todoPage.navigate();
        todoPage.addTask("Primera tarea");
        todoPage.addTask("Segunda tarea");

        assertEquals("Primera tarea", todoPage.getTaskByIndex(0));
        assertEquals("Segunda tarea", todoPage.getTaskByIndex(1));
        attachScreenshot("agregarDosTareas");
    }

    @Test
    @Description("Valida la eliminación de una tarea en TodoMVC")
    void eliminarTarea() {
        todoPage.navigate();
        todoPage.addTask("Tarea a eliminar");
        todoPage.deleteTask(0);

        assertEquals(0, page.locator(".todo-list li").count());
        attachScreenshot("eliminarTarea");
    }

    @Attachment(value = "Screenshot {0}", type = "image/png")
    byte[] attachScreenshot(String name) {
        return page.screenshot(new Page.ScreenshotOptions()
            .setPath(Paths.get("target/screenshots/" + name + ".png")));
    }

    @AfterEach
    void closePage() {
        if (page != null && !page.isClosed()) {
            page.close();
        }
    }

    @AfterAll
    static void teardown() {
        browser.close();
        playwright.close();
    }
}
