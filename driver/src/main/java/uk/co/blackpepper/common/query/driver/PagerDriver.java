package uk.co.blackpepper.common.query.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import uk.co.blackpepper.common.driver.AbstractComponentDriver;
import uk.co.blackpepper.common.driver.AbstractDriver;

public class PagerDriver extends AbstractComponentDriver {

	public PagerDriver(AbstractDriver<? extends AbstractDriver<?>> page, WebElement element) {
		super(page, element);
	}

	public boolean isPreviousEnabled() {
		checkVisible();
		return element().findElements(By.cssSelector(".previous.disabled")).isEmpty();
	}
	
	public void previous() {
		checkVisible();
		element().findElement(By.cssSelector(".previous a")).click();
	}
	
	public int getStart() {
		checkVisible();
		String startText = element().findElement(By.className("start")).getText();
		return Integer.parseInt(startText);
	}
	
	public int getEnd() {
		checkVisible();
		String endText = element().findElement(By.className("end")).getText();
		return Integer.parseInt(endText);
	}
	
	public int getTotalCount() {
		checkVisible();
		String totalCountText = element().findElement(By.className("total-count")).getText();
		return Integer.parseInt(totalCountText);
	}
	
	public boolean isNextEnabled() {
		checkVisible();
		return element().findElements(By.cssSelector(".next.disabled")).isEmpty();
	}

	public void next() {
		checkVisible();
		element().findElement(By.cssSelector(".next a")).click();
	}

	private void checkVisible() {
		page().checkVisible();
	}
}
