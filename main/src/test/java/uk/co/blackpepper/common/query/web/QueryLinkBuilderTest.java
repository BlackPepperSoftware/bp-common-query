package uk.co.blackpepper.common.query.web;

import org.junit.Test;

import uk.co.blackpepper.common.query.PagedQuery;
import uk.co.blackpepper.common.query.SearchQuery;
import uk.co.blackpepper.common.query.SortOrder;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class QueryLinkBuilderTest {

	@Test
	public void getLinkWithPagedQueryAddsStartAndRows() {
		PagedQuery query = new PagedQuery(1, 2);
		
		String actual = QueryLinkBuilder.getLink(query);
		
		assertThat(actual, allOf(containsString("start=1"), containsString("rows=2")));
	}
	
	@Test
	public void getLinkWithPagedQueryAndOrderAddsOrder() {
		PagedQuery query = new PagedQuery();
		query.setOrderBy("x");
		query.setOrder(SortOrder.ASCENDING);
		
		String actual = QueryLinkBuilder.getLink(query);
		
		assertThat(actual, allOf(containsString("orderBy=x"), containsString("order=ASCENDING")));
	}
	
	@Test
	public void getLinkWithPagedQueryAndNoOrderDoesNotAddOrder() {
		PagedQuery query = new PagedQuery();
		query.setOrderBy(null);
		
		String actual = QueryLinkBuilder.getLink(query);
		
		assertThat(actual, allOf(not(containsString("orderBy=")), not(containsString("order="))));
	}
	
	@Test
	public void getLinkWithPagedQueryReturnsLink() {
		PagedQuery query = new PagedQuery(1, 2);
		query.setOrderBy("x");
		query.setOrder(SortOrder.DESCENDING);
		
		String actual = QueryLinkBuilder.getLink(query);
		
		assertThat(actual, is("?start=1&rows=2&orderBy=x&order=DESCENDING"));
	}
	
	@Test
	public void getLinkWithSearchQueryAddsQueryString() {
		PagedQuery query = new SearchQuery("x", 1, 2);
		
		String actual = QueryLinkBuilder.getLink(query);
		
		assertThat(actual, containsString("queryString=x"));
	}

	@Test
	public void getLinkWithSearchQueryEncodesQueryString() {
		PagedQuery query = new SearchQuery("/", 1, 2);
		
		String actual = QueryLinkBuilder.getLink(query);
		
		assertThat(actual, containsString("queryString=%2F"));
	}
	
	@Test
	public void getLinkWithSearchQueryAndEmptyQueryStringDoesNotAddQueryString() {
		SearchQuery query = newSearchQuery();
		query.setQueryString("");
		
		String actual = QueryLinkBuilder.getLink(query);
		
		assertThat(actual, not(containsString("queryString=")));
	}

	@Test
	public void getLinkWithSearchQueryReturnsLink() {
		SearchQuery query = new SearchQuery("x", 1, 2);
		query.setOrderBy("y");
		query.setOrder(SortOrder.ASCENDING);
		
		String actual = QueryLinkBuilder.getLink(query);
		
		assertThat(actual, is("?start=1&rows=2&orderBy=y&order=ASCENDING&queryString=x"));
	}
	
	@Test
	public void getPreviousLinkWithPagedQuerySetsStartToPreviousPage() {
		PagedQuery query = new PagedQuery(1, 1);
		
		String actual = QueryLinkBuilder.getPreviousLink(query);
		
		assertThat(actual, containsString("start=0"));
	}
	
	@Test
	public void getPreviousLinkWithPagedQueryReturnsLink() {
		PagedQuery query = new PagedQuery(1, 1);
		
		String actual = QueryLinkBuilder.getPreviousLink(query);
		
		assertThat(actual, is("?start=0&rows=1"));
	}
	
	@Test
	public void getNextLinkWithPagedQuerySetsStartToNextPage() {
		PagedQuery query = new PagedQuery(0, 1);
		
		String actual = QueryLinkBuilder.getNextLink(query);
		
		assertThat(actual, containsString("start=1"));
	}
	
	@Test
	public void getNextLinkWithPagedQueryReturnsLink() {
		PagedQuery query = new PagedQuery(0, 1);
		
		String actual = QueryLinkBuilder.getNextLink(query);
		
		assertThat(actual, is("?start=1&rows=1"));
	}

	private static SearchQuery newSearchQuery() {
		return new SearchQuery("_queryString", 1000, 2000, "_orderBy", SortOrder.DESCENDING);
	}
}
