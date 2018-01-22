package core;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class ExpressionTree {
	Deque<Node> operatorStack;
	Queue<Node> outputQueue;

	public ExpressionTree(String expression) {
		operatorStack = new ArrayDeque<>();
		outputQueue = new LinkedList<>();
		parseExpression(expression);
		for (Node n : outputQueue) {
			System.out.print(n.getValue() + " ");
		}
	}

	/**
	 * Shunting-yard algorithm to convert infix expression to postfix
	 * 
	 * @param expression
	 */
	private void parseExpression(String expression) {
		// TODO: reduce Cognitive Complexity
		int cursor = 0;
		while (cursor < expression.length()) {
			StringBuilder str = new StringBuilder();
			boolean isOperand = false;
			while (!expression.substring(cursor, cursor + 1).equals(OperatorEnum.AND.value())
					&& !expression.substring(cursor, cursor + 1).equals(OperatorEnum.EQUAL.value())
					&& !expression.substring(cursor, cursor + 1).equals(OperatorEnum.OR.value())
					&& !expression.substring(cursor, cursor + 1).equals(OperatorEnum.LEFT_P.value())
					&& !expression.substring(cursor, cursor + 1).equals(OperatorEnum.RIGHT_P.value())) {
				str.append(expression.substring(cursor, cursor + 1));
				cursor++;
				isOperand = true;
			}
			if (isOperand) {
				String value = str.toString().trim();
				if (!value.isEmpty()) {
					Node operand1 = new Operand(value);
					outputQueue.add(operand1);
				}
			} else {
				if (!expression.substring(cursor, cursor + 1).equals(OperatorEnum.LEFT_P.value())
						&& !expression.substring(cursor, cursor + 1).equals(OperatorEnum.RIGHT_P.value())) {
					while (!operatorStack.isEmpty()
							&& !operatorStack.peek().getValue().equals(OperatorEnum.LEFT_P.value())
							&& !operatorStack.peek().getValue().equals(OperatorEnum.RIGHT_P.value())) {
						outputQueue.add(operatorStack.pop());
					}
					Node operator = new Operator(expression.substring(cursor, cursor + 1));
					operatorStack.push(operator);
				} else if (expression.substring(cursor, cursor + 1).equals(OperatorEnum.LEFT_P.value())) {
					Node operator = new Operator(expression.substring(cursor, cursor + 1));
					operatorStack.push(operator);
				} else if (expression.substring(cursor, cursor + 1).equals(OperatorEnum.RIGHT_P.value())) {
					while (!operatorStack.isEmpty()
							&& !operatorStack.peek().getValue().equals(OperatorEnum.LEFT_P.value())) {
						outputQueue.add(operatorStack.pop());
					}
					if (!operatorStack.isEmpty()) {
						operatorStack.pop();
					}

				}

				cursor++;
			}
		}

		while (!operatorStack.isEmpty())
			outputQueue.add(operatorStack.pop());
	}

}
