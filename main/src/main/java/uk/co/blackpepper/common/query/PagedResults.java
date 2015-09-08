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

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

import com.google.common.collect.Iterables;

import static com.google.common.base.Preconditions.checkNotNull;

public class PagedResults<T> implements Iterable<T> {

	private final Collection<T> results;
	
	private final int start;
	
	private final long totalCount;
	
	public PagedResults(Collection<T> results) {
		this(results, 0, results.size());
	}
	
	public PagedResults(Collection<T> results, int start, long totalCount) {
		this.results = checkNotNull(results, "results");
		this.start = start;
		this.totalCount = totalCount;
	}

	@Override
	public Iterator<T> iterator() {
		return results.iterator();
	}

	public int getResultCount() {
		return results.size();
	}
	
	public int getStart() {
		return start;
	}
	
	public long getTotalCount() {
		return totalCount;
	}

	public boolean hasPreviousPage() {
		return start > 0;
	}
	
	public boolean hasNextPage() {
		return start + getResultCount() < totalCount;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(results, start, totalCount);
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof PagedResults)) {
			return false;
		}

		PagedResults<?> results = (PagedResults<?>) object;

		return Iterables.elementsEqual(this, results)
			&& start == results.getStart()
			&& totalCount == results.getTotalCount();
	}
	
	@Override
	public String toString() {
		String format = "%s[results=%s, start=%s, totalCount=%s]";
		
		return String.format(format, getClass().getName(), results.toString(), start, totalCount);
	}
}
