package uk.co.blackpepper.common.query.sql;

import uk.co.blackpepper.common.query.PagedQuery;
import uk.co.blackpepper.common.query.SortOrder;

public final class QuerySqlBuilder {
	
	private QuerySqlBuilder() {
		throw new AssertionError();
	}

	public static String toSortOrder(SortOrder order) {
		String sqlOrder;
		
		if (order == SortOrder.ASCENDING) {
			sqlOrder = "ASC";
		}
		else if (order == SortOrder.DESCENDING) {
			sqlOrder = "DESC";
		}
		else {
			throw new IllegalArgumentException(String.format("Unknown sort order: %s", order));
		}
		
		return sqlOrder;
	}
	
	public static int toLimit(PagedQuery query) {
		return (query.getRows() > 0) ? query.getRows() : Integer.MAX_VALUE;
	}

	public static int toOffset(PagedQuery query) {
		return (query.getRows() > 0) ? query.getStart() : 0;
	}
}
