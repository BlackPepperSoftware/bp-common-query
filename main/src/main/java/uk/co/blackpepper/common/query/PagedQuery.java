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

import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class PagedQuery {

	private static final int DEFAULT_ROWS = 10;

	private static final SortOrder DEFAULT_SORT_ORDER = SortOrder.ASCENDING;
	
	private int start;
	
	private int rows;

	private String orderBy;

	private SortOrder order;
	
	public PagedQuery() {
		this(0, DEFAULT_ROWS);
	}

	public PagedQuery(int start, int rows) {
		this(start, rows, null, DEFAULT_SORT_ORDER);
	}

	public PagedQuery(int start, int rows, String orderBy, SortOrder order) {
		setStart(start);
		setRows(rows);
		setOrderBy(orderBy);
		setOrder(order);
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}
	
	public int getPreviousPageStart() {
		return Math.max(0, start - rows);
	}
	
	public int getNextPageStart() {
		return start + rows;
	}
	
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		checkArgument(rows >= 0, "Rows must be non-negative");
		this.rows = rows;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public SortOrder getOrder() {
		return order;
	}

	public void setOrder(SortOrder order) {
		this.order = checkNotNull(order, "order");
	}

	@Override
	public int hashCode() {
		return Objects.hash(start, rows, orderBy, order);
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof PagedQuery)) {
			return false;
		}

		PagedQuery query = (PagedQuery) object;

		return start == query.getStart()
			&& rows == query.getRows()
			&& Objects.equals(orderBy, query.getOrderBy())
			&& order == query.getOrder();
	}

	@Override
	public String toString() {
		return String.format("%s[%s]", getClass().getName(), propertiesString());
	}

	protected String propertiesString() {
		return String.format("start=%d, rows=%d, orderBy=%s, order=%s", start, rows, orderBy, order);
	}
}
