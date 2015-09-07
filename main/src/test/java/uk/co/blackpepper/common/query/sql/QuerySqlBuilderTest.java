package uk.co.blackpepper.common.query.sql;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import uk.co.blackpepper.common.query.PagedQuery;
import uk.co.blackpepper.common.query.SortOrder;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class QuerySqlBuilderTest {

	private ExpectedException thrown = ExpectedException.none();
	
	@Rule
	public ExpectedException getThrown() {
		return thrown;
	}
	
	@Test
	public void toSortOrderWithAscendingReturnsAsc() {
		assertThat(QuerySqlBuilder.toSortOrder(SortOrder.ASCENDING), is("ASC"));
	}
	
	@Test
	public void toSortOrderWithDescendingReturnsDesc() {
		assertThat(QuerySqlBuilder.toSortOrder(SortOrder.DESCENDING), is("DESC"));
	}
	
	@Test
	public void toSortOrderWithNullThrowsException() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Unknown sort order: null");
		
		QuerySqlBuilder.toSortOrder(null);
	}
	
	@Test
	public void toLimitWithRowsReturnsRows() {
		assertThat(QuerySqlBuilder.toLimit(new PagedQuery(0, 1)), is(1));
	}
	
	@Test
	public void toLimitWithZeroRowsReturnsMaxInt() {
		assertThat(QuerySqlBuilder.toLimit(new PagedQuery(0, 0)), is(Integer.MAX_VALUE));
	}
	
	@Test
	public void toOffsetWithStartReturnsStart() {
		assertThat(QuerySqlBuilder.toOffset(new PagedQuery(1, 10)), is(1));
	}
	
	@Test
	public void toOffsetWithZeroRowsReturnsZero() {
		assertThat(QuerySqlBuilder.toOffset(new PagedQuery(1, 0)), is(0));
	}
}
