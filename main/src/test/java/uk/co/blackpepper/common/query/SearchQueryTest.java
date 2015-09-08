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
package uk.co.blackpepper.common.query;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class SearchQueryTest {

	@Test
	public void defaultConstructorSetsProperties() {
		SearchQuery query = new SearchQuery();
		
		assertThat("queryString", query.getQueryString(), is(""));
		assertThat("orderBy", query.getOrderBy(), is(nullValue()));
		assertThat("order", query.getOrder(), is(SortOrder.ASCENDING));
		assertThat("start", query.getStart(), is(0));
		assertThat("rows", query.getRows(), is(10));
	}
	
	@Test
	public void constructorWithQuerySetsProperties() {
		SearchQuery query = new SearchQuery("x");
		
		assertThat("queryString", query.getQueryString(), is("x"));
		assertThat("orderBy", query.getOrderBy(), is(nullValue()));
		assertThat("order", query.getOrder(), is(SortOrder.ASCENDING));
		assertThat("start", query.getStart(), is(0));
		assertThat("rows", query.getRows(), is(10));
	}
	
	@Test
	public void constructorWithQueryStartAndRowsSetsProperties() {
		SearchQuery query = new SearchQuery("x", 1, 2);
		
		assertThat("queryString", query.getQueryString(), is("x"));
		assertThat("orderBy", query.getOrderBy(), is(nullValue()));
		assertThat("order", query.getOrder(), is(SortOrder.ASCENDING));
		assertThat("start", query.getStart(), is(1));
		assertThat("rows", query.getRows(), is(2));
	}
	
	@Test
	public void constructorWithAllSetsProperties() {
		SearchQuery query = new SearchQuery("x", 1, 2, "y", SortOrder.DESCENDING);
		
		assertThat("queryString", query.getQueryString(), is("x"));
		assertThat("orderBy", query.getOrderBy(), is("y"));
		assertThat("order", query.getOrder(), is(SortOrder.DESCENDING));
		assertThat("start", query.getStart(), is(1));
		assertThat("rows", query.getRows(), is(2));
	}
	
	@Test(expected = NullPointerException.class)
	public void constructorWithNullQueryThrowsException() {
		new SearchQuery(null, 1, 2);
	}
	
	@Test(expected = NullPointerException.class)
	public void setQueryStringWithNullQueryThrowsException() {
		SearchQuery query = new SearchQuery();
		
		query.setQueryString(null);
	}
	
	@Test
	public void hashCodeWhenEqualReturnsEqual() {
		SearchQuery query1 = newSearchQuery();
		SearchQuery query2 = newSearchQuery();
		
		assertThat(query1.hashCode(), is(query2.hashCode()));
	}
	
	@Test
	public void equalsWithEqualReturnsTrue() {
		SearchQuery query1 = newSearchQuery();
		SearchQuery query2 = newSearchQuery();
		
		assertThat(query1.equals(query2), is(true));
	}
	
	@Test
	public void equalsWithDifferentStartReturnsFalse() {
		SearchQuery query1 = newSearchQuery();
		query1.setStart(1);
		SearchQuery query2 = newSearchQuery();
		query2.setStart(2);
		
		assertThat(query1.equals(query2), is(false));
	}
	
	@Test
	public void equalsWithDifferentRowsReturnsFalse() {
		SearchQuery query1 = newSearchQuery();
		query1.setRows(1);
		SearchQuery query2 = newSearchQuery();
		query2.setRows(2);
		
		assertThat(query1.equals(query2), is(false));
	}
	
	@Test
	public void equalsWithDifferentOrderByReturnsFalse() {
		SearchQuery query1 = newSearchQuery();
		query1.setOrderBy("x");
		SearchQuery query2 = newSearchQuery();
		query2.setOrderBy("y");
		
		assertThat(query1.equals(query2), is(false));
	}
	
	@Test
	public void equalsWithDifferentOrderReturnsFalse() {
		SearchQuery query1 = newSearchQuery();
		query1.setOrder(SortOrder.ASCENDING);
		SearchQuery query2 = newSearchQuery();
		query2.setOrder(SortOrder.DESCENDING);
		
		assertThat(query1.equals(query2), is(false));
	}
	
	@Test
	public void equalsWithDifferentQueryReturnsFalse() {
		SearchQuery query1 = newSearchQuery();
		query1.setQueryString("x");
		SearchQuery query2 = newSearchQuery();
		query2.setQueryString("y");
		
		assertThat(query1.equals(query2), is(false));
	}
	
	@Test
	public void toStringTest() {
		SearchQuery query = new SearchQuery("x", 1, 2, "y", SortOrder.ASCENDING);
		
		assertThat(query.toString(), is("uk.co.blackpepper.common.query.SearchQuery[start=1, rows=2, orderBy=y, "
			+ "order=ASCENDING, queryString=x]"));
	}

	private static SearchQuery newSearchQuery() {
		return new SearchQuery("_query", 1000, 2000, "_orderBy", SortOrder.DESCENDING);
	}
}
