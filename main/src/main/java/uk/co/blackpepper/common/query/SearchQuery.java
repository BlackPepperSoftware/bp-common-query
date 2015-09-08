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

import static com.google.common.base.Preconditions.checkNotNull;

public class SearchQuery extends PagedQuery {
	
	private String queryString;
	
	public SearchQuery() {
		this("");
	}

	public SearchQuery(String queryString) {
		super();
		setQueryString(queryString);
	}

	public SearchQuery(String queryString, int start, int rows) {
		super(start, rows);
		setQueryString(queryString);
	}

	public SearchQuery(String queryString, int start, int rows, String orderBy, SortOrder order) {
		super(start, rows, orderBy, order);
		setQueryString(queryString);
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = checkNotNull(queryString, "queryString");
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), queryString);
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof SearchQuery)) {
			return false;
		}

		SearchQuery request = (SearchQuery) object;

		return super.equals(object)
			&& queryString.equals(request.getQueryString());
	}

	@Override
	protected String propertiesString() {
		return String.format("%s, queryString=%s", super.propertiesString(), queryString);
	}
}
