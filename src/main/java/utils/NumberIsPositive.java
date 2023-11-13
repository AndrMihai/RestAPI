package utils;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class NumberIsPositive extends TypeSafeMatcher<Integer> {

	@Override
	public void describeTo(Description description) {
		description.appendText(" positive number ");
		
	}

	@Override
	protected boolean matchesSafely(Integer item) {
		
		return item > 0;
	}
	public static Matcher<Integer> positiveNumber(){
		return new NumberIsPositive();
	}

}
