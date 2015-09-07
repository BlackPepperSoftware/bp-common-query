package uk.co.blackpepper.common.query.test;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.junit.Test;

import uk.co.blackpepper.common.query.PagedResults;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import static uk.co.blackpepper.common.query.test.PagedResultsMatcher.pagedResultsEqualTo;

public class PagedResultsMatcherTest {

	@Test
	public void pagedResultsEqualToReturnsMatcherWithProperties() {
		Matcher<Object> expectedResult1 = mock(Matcher.class);
		Matcher<Object> expectedResult2 = mock(Matcher.class);
		
		PagedResultsMatcher<Object> actual = pagedResultsEqualTo(1, 2, expectedResult1, expectedResult2);
		
		assertThat("expectedStart", actual.getExpectedStart(), is(1));
		assertThat("expectedTotalCount", actual.getExpectedTotalCount(), is(2));
		assertThat("expectedResults", actual.getExpectedResults(),
			is(arrayContaining((Object) expectedResult1, expectedResult2)));
	}
	
	@Test
	public void describeToAppendsDescription() {
		PagedResultsMatcher<Object> matcher = pagedResultsEqualTo(1, 2, anything("x"), anything("y"));
		Description description = new StringDescription();
		
		matcher.describeTo(description);
		
		assertThat(description.toString(), is("start <1>, total count <2>, results [x, y]"));
	}
	
	@Test
	public void matchesSafelyWithMatchingResultsReturnsTrue() {
		PagedResultsMatcher<String> matcher = pagedResultsEqualTo(0, 2, is("x"), is("y"));
		
		boolean actual = matcher.matchesSafely(new PagedResults<>(asList("x", "y")));
		
		assertThat(actual, is(true));
	}

	@Test
	public void matchesSafelyWithDifferentResultsReturnsFalse() {
		PagedResultsMatcher<String> matcher = pagedResultsEqualTo(0, 1, is("x"));
		
		boolean actual = matcher.matchesSafely(new PagedResults<>(asList("y")));
		
		assertThat(actual, is(false));
	}

	@Test
	public void matchesSafelyWhenEmptyResultMatchersReturnsTrue() {
		PagedResultsMatcher<Object> matcher = pagedResultsEqualTo(0, 0);
		
		boolean actual = matcher.matchesSafely(new PagedResults<>(emptyList()));
		
		assertThat(actual, is(true));
	}
	
	@Test
	public void matchesSafelyWithEmptyResultsReturnsFalse() {
		PagedResultsMatcher<Object> matcher = pagedResultsEqualTo(0, 1, anything());
		
		boolean actual = matcher.matchesSafely(new PagedResults<>(emptyList()));
		
		assertThat(actual, is(false));
	}

	@Test
	public void matchesSafelyWithDifferentStartReturnsFalse() {
		PagedResultsMatcher<String> matcher = pagedResultsEqualTo(1, 1, any(String.class));
		
		boolean actual = matcher.matchesSafely(new PagedResults<>(asList("x"), 0, 1));
		
		assertThat(actual, is(false));
	}
	
	@Test
	public void matchesSafelyWithDifferentTotalResultReturnsFalse() {
		PagedResultsMatcher<String> matcher = pagedResultsEqualTo(0, 1, any(String.class));
		
		boolean actual = matcher.matchesSafely(new PagedResults<>(asList("x"), 0, 2));
		
		assertThat(actual, is(false));
	}
}
