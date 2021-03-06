/*
 * Copyright 2014 Black Pepper Software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.co.blackpepper.common.query.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import uk.co.blackpepper.common.driver.AbstractComponentDriver;
import uk.co.blackpepper.common.driver.AbstractDriver;
import uk.co.blackpepper.common.query.SortOrder;

import static com.google.common.base.Preconditions.checkNotNull;

import static uk.co.blackpepper.support.selenium.FormUtils.getRadioValue;
import static uk.co.blackpepper.support.selenium.FormUtils.setRadioValue;

public class PagedQueryFormDriver extends AbstractComponentDriver {
	
	/**
	 * Rows value that represents all rows.
	 */
	public static final int ALL = 0;
	
	private static final String ALL_TEXT = "All";

	public PagedQueryFormDriver(AbstractDriver<? extends AbstractDriver<?>> page, WebElement element) {
		super(page, element);
	}
	
	public int getRows() {
		checkVisible();
		String rowsText = new Select(element().findElement(byRows())).getFirstSelectedOption().getText();
		return rowsText.equals(ALL_TEXT) ? ALL : Integer.valueOf(rowsText);
	}
	
	public void setRows(int rows) {
		checkVisible();
		String rowsText = (rows == ALL) ? ALL_TEXT : String.valueOf(rows);
		new Select(element().findElement(byRows())).selectByVisibleText(rowsText);
	}
	
	public String getOrderBy() {
		checkVisible();
		return new Select(element().findElement(byOrderBy())).getFirstSelectedOption().getText();
	}

	public void setOrderBy(String orderBy) {
		checkVisible();
		new Select(element().findElement(byOrderBy())).selectByVisibleText(orderBy);
	}
	
	public SortOrder getOrder() {
		checkVisible();
		String orderText = getRadioValue(element().findElements(byOrder()));
		return SortOrder.valueOf(orderText);
	}
		
	public void setOrder(SortOrder order) {
		checkNotNull(order, "order");
		checkVisible();
		setRadioValue(element().findElements(byOrder()), order.name());
	}

	private void checkVisible() {
		page().checkVisible();
	}

	private static By byRows() {
		return By.name("rows");
	}
	
	private static By byOrderBy() {
		return By.name("orderBy");
	}
	
	private static By byOrder() {
		return By.name("order");
	}
}
