package core;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class ExpressionEvaluator {
	Deque<Node> operatorStack;
	Queue<Node> outputQueue;
	Deque<Node> evaluationStack;

	public ExpressionEvaluator(String expression) {
		operatorStack = new ArrayDeque<>();
		outputQueue = new LinkedList<>();
		convertToPostfix(expression);
	}

	public void postFixToString() {
		for (Node n : outputQueue) {
			System.out.print(n.getValue() + " ");
		}
		System.out.println("");
	}

	public boolean evaluateExpression() {
		evaluationStack = new ArrayDeque<>();
		for (Node n : outputQueue) {
			if (n instanceof Operand) {
				evaluationStack.push(n);
			} else if (n instanceof Operator) {
				Node nodeLast = evaluationStack.pop();
				Node nodeFirst = evaluationStack.pop();
				Node result = operate(nodeFirst, nodeLast, n);
				evaluationStack.push(result);
			}
		}
		Node node = evaluationStack.pop();
		return Boolean.valueOf(node.getValue());
	}

	private Node operate(Node nodeFirst, Node nodeLast, Node operation) {
		boolean evaluation = false;
		if (operation.getValue().equals(OperatorEnum.AND.value())) {
			evaluation = Boolean.valueOf(nodeFirst.getValue()) && Boolean.valueOf(nodeLast.getValue());
		} else if (operation.getValue().equals(OperatorEnum.EQUAL.value())) {
			evaluation = nodeFirst.getValue().equals(nodeLast.getValue());
		} else if (operation.getValue().equals(OperatorEnum.OR.value())) {
			evaluation = Boolean.valueOf(nodeFirst.getValue()) || Boolean.valueOf(nodeLast.getValue());
		}
		return new Operand(String.valueOf(evaluation));
	}

	/**
	 * Shunting-yard algorithm to convert infix expression to postfix
	 * 
	 * @param expression
	 */
	private void convertToPostfix(String expression) {
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
							&& !operatorStack.peek().getValue().equals(OperatorEnum.RIGHT_P.value())
							&& isHigerPrec(expression.substring(cursor, cursor + 1), operatorStack.peek().getValue())) {
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

	private boolean isHigerPrec(String op, String sub) {
		return OperatorEnum.findByValue(sub).precedence() >= OperatorEnum.findByValue(op).precedence();

	}

}
