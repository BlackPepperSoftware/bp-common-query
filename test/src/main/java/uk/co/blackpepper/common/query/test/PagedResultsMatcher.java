package uk.co.blackpepper.common.query.test;

import java.util.Arrays;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import uk.co.blackpepper.common.query.PagedResults;

import static org.hamcrest.Matchers.contains;

public final class PagedResultsMatcher<T> extends TypeSafeMatcher<PagedResults<T>> {
	
	private final int expectedStart;
	
	private final int expectedTotalCount;
	
	private final Matcher<T>[] expectedResults;

	private PagedResultsMatcher(int expectedStart, int expectedTotalCount, Matcher<T>[] expectedResults) {
		this.expectedStart = expectedStart;
		this.expectedTotalCount = expectedTotalCount;
		this.expectedResults = Arrays.copyOf(expectedResults, expectedResults.length);
	}
	
	@SafeVarargs
	public static <T> PagedResultsMatcher<T> pagedResultsEqualTo(int expectedStart, int expectedTotalCount,
		Matcher<T>... expectedResults) {
		return new PagedResultsMatcher<>(expectedStart, expectedTotalCount, expectedResults);
	}
	
	@Override
	public void describeTo(Description description) {
		description.appendText("start ")
			.appendValue(expectedStart)
			.appendText(", total count ")
			.appendValue(expectedTotalCount)
			.appendText(", results ")
			.appendList("[", ", ", "]", Arrays.asList(expectedResults));
	}

	@Override
	protected boolean matchesSafely(PagedResults<T> actual) {
		return expectedStart == actual.getStart()
			&& expectedTotalCount == actual.getTotalCount()
			&& matchesResults(actual);
	}

	private boolean matchesResults(PagedResults<T> actual) {
		return expectedResults.length == 0
			? actual.getResultCount() == 0
			: contains(expectedResults).matches(actual);
	}

	public int getExpectedStart() {
		return expectedStart;
	}

	public int getExpectedTotalCount() {
		return expectedTotalCount;
	}

	public Matcher<T>[] getExpectedResults() {
		return expectedResults;
	}
}
