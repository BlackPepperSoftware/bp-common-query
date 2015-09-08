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
