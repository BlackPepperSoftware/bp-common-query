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

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import static com.google.common.collect.Lists.newArrayList;

public class PagedResultsTest {

	@Test
	public void constructorWithResultsSetsProperties() {
		PagedResults<String> results = new PagedResults<>(asList("x", "y"));
		
		assertThat("results", newArrayList(results.iterator()), contains("x", "y"));
		assertThat("start", results.getStart(), is(0));
		assertThat("total count", results.getTotalCount(), is(2L));
	}
	
	@Test
	public void constructorWithAllSetsProperties() {
		PagedResults<String> results = new PagedResults<>(asList("x", "y"), 1, 3);
		
		assertThat("results", newArrayList(results.iterator()), contains("x", "y"));
		assertThat("start", results.getStart(), is(1));
		assertThat("total count", results.getTotalCount(), is(3L));
	}
	
	@Test
	public void getResultCountReturnsCount() {
		PagedResults<String> results = new PagedResults<>(asList("x", "y"), 0, 3);
		
		assertThat(results.getResultCount(), is(2));
	}
	
	@Test
	public void hasPreviousPageWhenPreviousPageReturnsTrue() {
		PagedResults<String> results = new PagedResults<>(asList("y"), 1, 2);
		
		assertThat(results.hasPreviousPage(), is(true));
	}
	
	@Test
	public void hasPreviousPageWhenNoPreviousPageReturnsFalse() {
		PagedResults<String> results = new PagedResults<>(asList("x"), 0, 2);
		
		assertThat(results.hasPreviousPage(), is(false));
	}
	
	@Test
	public void hasNextPageWhenNextPageReturnsTrue() {
		PagedResults<String> results = new PagedResults<>(asList("x"), 0, 2);
		
		assertThat(results.hasNextPage(), is(true));
	}
	
	@Test
	public void hasNextPageWhenNoNextPageReturnsFalse() {
		PagedResults<String> results = new PagedResults<>(asList("y"), 1, 2);
		
		assertThat(results.hasNextPage(), is(false));
	}
	
	@Test
	public void hashCodeWhenEqualReturnsEqual() {
		PagedResults<String> results1 = newPagedResults();
		PagedResults<String> results2 = newPagedResults();
		
		assertThat(results1.hashCode(), is(results2.hashCode()));
	}

	@Test
	public void equalsWithEqualReturnsTrue() {
		PagedResults<String> results1 = newPagedResults();
		PagedResults<String> results2 = newPagedResults();
		
		assertThat(results1.equals(results2), is(true));
	}
	
	@Test
	public void equalsWithUnequalResultsReturnsFalse() {
		PagedResults<String> results1 = new PagedResults<>(singletonList("x"), 1, 2);
		PagedResults<String> results2 = new PagedResults<>(singletonList("y"), 1, 2);
		
		assertThat(results1.equals(results2), is(false));
	}
	
	@Test
	public void equalsWithUnequalStartReturnsFalse() {
		PagedResults<String> results1 = new PagedResults<>(singletonList("x"), 1, 2);
		PagedResults<String> results2 = new PagedResults<>(singletonList("x"), 2, 2);
		
		assertThat(results1.equals(results2), is(false));
	}
	
	@Test
	public void equalsWithUnequalTotalCountReturnsFalse() {
		PagedResults<String> results1 = new PagedResults<>(singletonList("x"), 1, 2);
		PagedResults<String> results2 = new PagedResults<>(singletonList("x"), 1, 3);
		
		assertThat(results1.equals(results2), is(false));
	}
	
	@Test
	public void toStringReturnsString() {
		PagedResults<String> results = new PagedResults<>(singletonList("x"), 1, 2);
		
		assertThat(results.toString(), is("uk.co.blackpepper.common.query.PagedResults[results=[x], start=1, "
			+ "totalCount=2]"));
	}

	private static PagedResults<String> newPagedResults() {
		return new PagedResults<>(singletonList("_result"), 1000, 2000);
	}
}
