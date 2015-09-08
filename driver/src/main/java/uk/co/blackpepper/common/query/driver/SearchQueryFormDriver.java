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
