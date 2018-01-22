package core;

import org.junit.Test;

public class ExpressionTest {

	@Test
	public void Test() {
		String exp = "q1=a & (q2=b|(q3=c))";
		new ExpressionTree(exp);
	}

}
