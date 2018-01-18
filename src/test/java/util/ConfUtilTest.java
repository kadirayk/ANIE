package util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ConfUtilTest {

	@Test
	public void booleanValueTest() {
		assertTrue(ConfUtil.getValue(ConfUtil.DEBUG));
	}

}
