package uk.co.blackpepper.common.query;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class PagedQueryTest {

	@Test
	public void defaultConstructorSetsAllProperties() {
		PagedQuery query = new PagedQuery();
		
		assertThat("start", query.getStart(), is(0));
		assertThat("rows", query.getRows(), is(10));
		assertThat("orderBy", query.getOrderBy(), is(nullValue()));
		assertThat("order", query.getOrder(), is(SortOrder.ASCENDING));
	}
	
	@Test
	public void constructorWithStartAndRowsSetsAllProperties() {
		PagedQuery query = new PagedQuery(1, 2);
		
		assertThat("start", query.getStart(), is(1));
		assertThat("rows", query.getRows(), is(2));
		assertThat("orderBy", query.getOrderBy(), is(nullValue()));
		assertThat("order", query.getOrder(), is(SortOrder.ASCENDING));
	}
	
	@Test
	public void constructorWithAllSetsAllProperties() {
		PagedQuery query = new PagedQuery(1, 2, "x", SortOrder.DESCENDING);
		
		assertThat("start", query.getStart(), is(1));
		assertThat("rows", query.getRows(), is(2));
		assertThat("orderBy", query.getOrderBy(), is("x"));
		assertThat("order", query.getOrder(), is(SortOrder.DESCENDING));
	}
	
	@Test
	public void setStartSetsProperty() {
		PagedQuery query = new PagedQuery();
		
		query.setStart(1);
		
		assertThat(query.getStart(), is(1));
	}
	
	@Test
	public void getPreviousPageStartWhenPreviousReturnsPreviousStart() {
		PagedQuery query = new PagedQuery(1, 1);
		
		assertThat(query.getPreviousPageStart(), is(0));
	}
	
	@Test
	public void getPreviousPageStartWhenNoPreviousReturnsZero() {
		PagedQuery query = new PagedQuery(0, 1);
		
		assertThat(query.getPreviousPageStart(), is(0));
	}
	
	@Test
	public void getNextPageStartReturnsNextStart() {
		PagedQuery query = new PagedQuery(0, 1);
		
		assertThat(query.getNextPageStart(), is(1));
	}

	@Test
	public void setRowsSetsProperty() {
		PagedQuery query = new PagedQuery();
		
		query.setRows(1);
		
		assertThat(query.getRows(), is(1));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setRowsWithNegativeThrowsException() {
		PagedQuery query = new PagedQuery();
		
		query.setRows(-1);
	}
	
	@Test
	public void setOrderBySetsProperty() {
		PagedQuery query = new PagedQuery();
		
		query.setOrderBy("x");
		
		assertThat(query.getOrderBy(), is("x"));
	}
	
	@Test
	public void setOrderByWithNullSetsProperty() {
		PagedQuery query = new PagedQuery();
		
		query.setOrderBy(null);
		
		assertThat(query.getOrderBy(), is(nullValue()));
	}
	
	@Test
	public void setOrderSetsProperty() {
		PagedQuery query = new PagedQuery();
		
		query.setOrder(SortOrder.DESCENDING);
		
		assertThat(query.getOrder(), is(SortOrder.DESCENDING));
	}
	
	@Test(expected = NullPointerException.class)
	public void setOrderWithNullThrowsException() {
		PagedQuery query = new PagedQuery();
		
		query.setOrder(null);
	}
	
	@Test
	public void hashCodeWhenEqualReturnsEqual() {
		PagedQuery query1 = newPagedQuery();
		PagedQuery query2 = newPagedQuery();
		
		assertThat(query1.hashCode(), is(query2.hashCode()));
	}
	
	@Test
	public void equalsWithEqualReturnsTrue() {
		PagedQuery query1 = newPagedQuery();
		PagedQuery query2 = newPagedQuery();
		
		assertThat(query1.equals(query2), is(true));
	}
	
	@Test
	public void equalsWithDifferentStartReturnsFalse() {
		PagedQuery query1 = newPagedQuery();
		query1.setStart(1);
		PagedQuery query2 = newPagedQuery();
		query2.setStart(2);
		
		assertThat(query1.equals(query2), is(false));
	}
	
	@Test
	public void equalsWithDifferentRowsReturnsFalse() {
		PagedQuery query1 = newPagedQuery();
		query1.setRows(1);
		PagedQuery query2 = newPagedQuery();
		query2.setRows(2);
		
		assertThat(query1.equals(query2), is(false));
	}
	
	@Test
	public void equalsWithDifferentOrderByReturnsFalse() {
		PagedQuery query1 = newPagedQuery();
		query1.setOrderBy("x");
		PagedQuery query2 = newPagedQuery();
		query2.setOrderBy("y");
		
		assertThat(query1.equals(query2), is(false));
	}
	
	@Test
	public void equalsWithDifferentOrderReturnsFalse() {
		PagedQuery query1 = newPagedQuery();
		query1.setOrder(SortOrder.ASCENDING);
		PagedQuery query2 = newPagedQuery();
		query2.setOrder(SortOrder.DESCENDING);
		
		assertThat(query1.equals(query2), is(false));
	}
		
	@Test
	public void toStringReturnsString() {
		PagedQuery query = new PagedQuery(1, 2, "x", SortOrder.ASCENDING);
		
		assertThat(query.toString(), is("uk.co.blackpepper.common.query.PagedQuery[start=1, rows=2, orderBy=x, "
			+ "order=ASCENDING]"));
	}

	private static PagedQuery newPagedQuery() {
		return new PagedQuery(1000, 2000, "_orderBy", SortOrder.DESCENDING);
	}
}
