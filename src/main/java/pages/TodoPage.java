package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class TodoPage {
    private final Page page;

    public TodoPage(Page page) {
        this.page = page;
    }

    public void navigate() {
        page.navigate("https://demo.playwright.dev/todomvc");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void addTask(String task) {
        page.locator(".new-todo").type(task);
        page.locator(".new-todo").press("Enter");
    }

    public String getTaskByIndex(int index) {
        return page.locator(".todo-list li").nth(index).textContent();
    }

    public void deleteTask(int index) {
        page.locator(".todo-list li").nth(index).hover();
        page.locator(".todo-list li").nth(index).locator(".destroy").click();
    }
}
