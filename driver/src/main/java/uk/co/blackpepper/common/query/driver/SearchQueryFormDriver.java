package uk.co.blackpepper.common.query.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import uk.co.blackpepper.common.driver.AbstractDriver;

import static uk.co.blackpepper.support.selenium.FormUtils.getControlValue;
import static uk.co.blackpepper.support.selenium.FormUtils.setControlValue;

public class SearchQueryFormDriver extends PagedQueryFormDriver {

	public SearchQueryFormDriver(AbstractDriver<? extends AbstractDriver<?>> page, WebElement element) {
		super(page, element);
	}
	
	public String getQueryString() {
		checkVisible();
		return getControlValue(element().findElement(byQueryString()));
	}
	
	public SearchQueryFormDriver setQueryString(String queryString) {
		checkVisible();
		setControlValue(element().findElement(byQueryString()), queryString);
		return this;
	}
	
	public void search() {
		checkVisible();
		element().findElement(By.className("search")).click();
	}
	
	private void checkVisible() {
		page().checkVisible();
	}

	private static By byQueryString() {
		return By.name("queryString");
	}
}
