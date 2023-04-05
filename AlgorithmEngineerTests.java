import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AlgorithmEngineerTests {

	/**
	 * tests getFirst method under ideal circumstances
	 */
	@Test
	void test1() {
		EnhancedRedBlackTreeAE<Integer> testTree = new EnhancedRedBlackTreeAE<Integer>();

		testTree.insert(1);
		testTree.insert(2);
		testTree.insert(3);
		Object[] testValue = testTree.getFirst(2);
		String returnValue = "";
		for (int i = 0; i < testValue.length; i++) {
			returnValue += testValue[i];
			returnValue += " ";
		}
		assertEquals(returnValue, "1 2 ");
	}

	/**
	 * tests getLast method under ideal circumstances
	 */
	@Test
	void test2() {
		EnhancedRedBlackTreeAE<Integer> testTree = new EnhancedRedBlackTreeAE<Integer>();

		testTree.insert(1);
		testTree.insert(2);
		testTree.insert(3);
		Integer[] testValue = testTree.getLast(2);
		String returnValue = "";
		for (int i = 0; i < testValue.length; i++) {
			returnValue += testValue[i];
			returnValue += " ";
		}
		assertEquals(returnValue, "3 2 ");
	}

	/**
	 * tests getRange method with min greater than max
	 */
	@Test
	void test3() {
		EnhancedRedBlackTreeAE<Integer> testTree = new EnhancedRedBlackTreeAE<Integer>();

		testTree.insert(1);
		testTree.insert(2);
		testTree.insert(3);
		try {
			Object[] testValue = testTree.getRange(3, 1);
			assertEquals(1, 2);
		} catch (IllegalArgumentException e) {
			assertEquals(1, 1);
		}
	}

	/**
	 * tests getRange method properly
	 */
	@Test
	void test4() {
		EnhancedRedBlackTreeAE<Integer> testTree = new EnhancedRedBlackTreeAE<Integer>();

		testTree.insert(1);
		testTree.insert(2);
		testTree.insert(3);
		Object[] testValue = testTree.getRange(1, 2);
		String returnValue = "";
		for (int i = 0; i < testValue.length; i++) {
			returnValue += testValue[i];
			returnValue += " ";
		}
		assertEquals(returnValue, "1 2 ");
	}
}
