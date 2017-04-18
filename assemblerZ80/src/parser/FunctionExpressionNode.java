package parser;

//import MyParser.SequenceExpressionNode.Term;

public class FunctionExpressionNode implements ExpressionNode {
	private int function;
	private ExpressionNode argument;

	public FunctionExpressionNode(int function, ExpressionNode argument) {
		super();
		this.function = function;
		this.argument = argument;
	}// Constructor - FunctionExpressionNode()

	@Override
	public NodeType getType() {
		return NodeType.FUNCTION;
	}// getType

	@Override
	public Integer getValue() {
		switch (function) {
		case SIN:
			return (int) Math.sin(argument.getValue());
		case COS:
			return (int) Math.cos(argument.getValue());
		case TAN:
			return (int) Math.tan(argument.getValue());
		case ASIN:
			return (int) Math.asin(argument.getValue());
		case ACOS:
			return (int) Math.acos(argument.getValue());
		case ATAN:
			return (int) Math.atan(argument.getValue());
		case SQRT:
			return (int) Math.sqrt(argument.getValue());
		case EXP:
			return (int) Math.exp(argument.getValue());
		case LN:
			return (int) Math.log(argument.getValue());
		case LOG:
			return (int) (Math.log(argument.getValue()) * 0.43429448190325182765);
		case LOG2:
			return (int) (Math.cos(argument.getValue()) * 1.442695040888963407360);
		}// switch
		throw new EvaluationException("Invalid function id " + function);
	}// getValue

	public static int stringToFunction(String str) {
		if (str.equalsIgnoreCase("sin"))
			return FunctionExpressionNode.SIN;
		if (str.equalsIgnoreCase("cos"))
			return FunctionExpressionNode.COS;
		if (str.equalsIgnoreCase("tan"))
			return FunctionExpressionNode.TAN;
		if (str.equalsIgnoreCase("asin"))
			return FunctionExpressionNode.ASIN;
		if (str.equalsIgnoreCase("acos"))
			return FunctionExpressionNode.ACOS;
		if (str.equalsIgnoreCase("atan"))
			return FunctionExpressionNode.ATAN;
		if (str.equalsIgnoreCase("sqrt"))
			return FunctionExpressionNode.SQRT;
		if (str.equalsIgnoreCase("exp"))
			return FunctionExpressionNode.EXP;
		if (str.equalsIgnoreCase("ln"))
			return FunctionExpressionNode.LN;
		if (str.equalsIgnoreCase("log"))
			return FunctionExpressionNode.LOG;
		if (str.equalsIgnoreCase("log2"))
			return FunctionExpressionNode.LOG2;

		throw new EvaluationException("Unexpected function: " + str);
	}// stringToFunction

	public static String getAllFunctions() {
		return "sin|cos|tan|asin|acos|atan|sqrt|exp|ln|log|log2";
	}// getAllFunctions
	
	public void accept(ExpressionNodeVisitor visitor){
		visitor.visit(this);
		argument.accept(visitor);
	}//accept



	public static final int SIN = 1;
	public static final int COS = 2;
	public static final int TAN = 3;
	public static final int ASIN = 4;
	public static final int ACOS = 5;
	public static final int ATAN = 6;
	public static final int SQRT = 7;
	public static final int EXP = 8;
	public static final int LN = 9;
	public static final int LOG = 10;
	public static final int LOG2 = 11;

}// class FunctionExpressionNode
