/**
 * ExpressionSolver Class A GUI calculator Program to evaluate simple
 * expressions. simple expression may include +,-,*,/, ., and parenthesis. Artin
 * Malekian Dr. Hoang CSC 201 - Assignment#4 12+1 December 2016
 */
// import javax.swing.JTextField;

public class ExpressionSolver {

	private static String numWhol;
	DivideByZeroException dvz = new DivideByZeroException();

	public static double solve(String e) {
		double n1 = 0, n2, n3;

		if (e.length() == 0)
			n1 = 0;
		// else if (operSetter(e) == 100){
		// n1 = Double.parseDouble(e);
		// }
		else {

			try {
				String numWhol2 = e.trim(); // get rid of space
				numWhol = numWhol2;
				numWhol = numWhol.replace('(', 'o'); // The method
														// "replaceFirst" gives
														// a different result
														// with some characters
														// like "*+()"
				numWhol = numWhol.replace(')', 'c');
				numWhol = numWhol.replace('+', 'p');
				numWhol = numWhol.replace('*', 'm');
				char o1, o2;

				if (operSetter(numWhol) == 100) {
					throw new Exception("invalid input ");
				}

				n1 = Double.parseDouble(ParenChech(numWhol.substring(0, operSetter(numWhol))));
				if (operSetter(numWhol) != 100) {
					o1 = numWhol.charAt(operSetter(numWhol));
					numWhol = numWhol.substring(operSetter(numWhol) + 1); // cut
																			// original
																			// number
					if (operSetter(numWhol) == 100) {
						n2 = Double.parseDouble(numWhol.substring(0));
						n1 = calcNumb(o1, n1, n2);
						n2 = 0;
						o1 = '.';
					} else {
						n2 = Double.parseDouble(ParenChech(numWhol.substring(0, operSetter(numWhol))));
						if (operSetter(numWhol) != 100) {
							o2 = numWhol.charAt(operSetter(numWhol));
							numWhol = numWhol.substring(operSetter(numWhol) + 1);// cut
																					// original
																					// number
							if (operSetter(numWhol) == 100) {
								n3 = Double.parseDouble(ParenChech(numWhol.substring(0)));
								numWhol = "";
							} else
								n3 = Double.parseDouble(ParenChech(numWhol.substring(0, operSetter(numWhol))));
							if (numWhol.length() != 0)
								numWhol = numWhol.substring(operSetter(numWhol));// cut
																					// original
																					// number
							for (int r = 0; r <= numWhol2.length() / 2; r++) {
								if ((((int) operFinder(o1, o2)) == (int) o1)) {
									n1 = calcNumb(o1, n1, n2);
									n2 = n3;
									o1 = o2;
									if (operSetter(numWhol) == 100) {
										n3 = 0;
										o2 = '.';
									} else {
										o2 = numWhol.charAt(operSetter(numWhol));
										numWhol = numWhol.substring((operSetter(numWhol) + 1));// cut
																								// original
																								// number
										if (operSetter(numWhol) == 100)
											n3 = Double.parseDouble(ParenChech(numWhol.substring(0)));
										else
											n3 = Double
													.parseDouble(ParenChech(numWhol.substring(0, operSetter(numWhol))));
									}
								} else if ((((int) operFinder(o1, o2)) == (int) o2)) {
									n2 = calcNumb(o2, n2, n3);
									if (operSetter(numWhol) == 100) {
										n3 = 0;
										o2 = '.';
									} else {
										o2 = numWhol.charAt(operSetter(numWhol));
										numWhol = numWhol.substring((operSetter(numWhol) + 1));// cut
																								// original
																								// number
										if (operSetter(numWhol) == 100)
											n3 = Double.parseDouble(ParenChech(numWhol.substring(0)));
										else
											n3 = Double
													.parseDouble(ParenChech(numWhol.substring(0, operSetter(numWhol))));
									}
								}
							}
						}
						n1 = calcNumb(o1, n1, n2);
					}
				}
			} catch (ArithmeticException t) {
				System.out.println(t.getMessage());
			} catch (DivideByZeroException t) {
				System.out.println(t.getMessage());
			} catch (NumberFormatException t) {
				System.out.println("invalid input " + t.getMessage());
			} catch (Exception t) {
				e = ParenCutter(numWhol);
				n1 = Double.parseDouble(e);
				System.out.println("no calculation ; " + t.getMessage() + e);
			}

		}
		return (n1);
	}

	/*
	 * Precondition: get a peace of string. Postcondition: Return the position
	 * of the fist operator in there.
	 */
	public static int operSetter(String q) {
		int result = 100;
		char operat[] = { 'p', '-', '/', 'm' };
		for (int i = 0; i < q.length(); i++) { // find position of operators
			char myChar = q.charAt(i);
			for (int j = 0; j <= 3; j++) {
				if (myChar == operat[j]) {
					result = i;
					i = q.length();
				}
			}
		}
		return result;
	}

	/*
	 * Precondition: get a peace of string. Postcondition: check possible
	 * parentheses and calculate it and replace into the original number line
	 */
	public static String ParenChech(String n) {
		String result = n;
		int o = 0, c = 0;
		char parentOpen = 'o';
		char parentClose = 'c';
		int[] parPosO = new int[numWhol.length() / 4];
		int parPosC = 0;
		for (int t = 0; t <= n.length() - 1; t++) {
			char myChar = n.charAt(t);
			if (myChar == parentOpen) {
				n = numWhol;
				parPosO[o] = t;
				o++;
			} else if (myChar == parentClose) {
				parPosC = t;
				c++;
			}
			if ((o == 1) && (c == 1)) { // if there is on parenthesis
				n = numWhol.substring(parPosO[o - 1], parPosC + 1);
				result = ParenHand(numWhol.substring(parPosO[o - 1] + 1, parPosC));
				numWhol = numWhol.replaceFirst(n, result); // Replace the
															// calculated number
															// into the original
															// instead of
															// parentheses.
				t = n.length() - 1;
			} else if ((o > 1) && (c == 1)) { // if there is more than one
												// parentheses
				result = ParenHand(numWhol.substring(parPosO[o - 1] + 1, parPosC));
				numWhol = numWhol.replaceFirst(numWhol.substring(parPosO[o - 1], parPosC + 1), result);// Replace
																										// the
																										// calculated
																										// number
																										// into
																										// the
																										// original
																										// instead
																										// of
																										// parentheses.
				n = numWhol;
				t = t - (numWhol.substring(parPosO[o - 1], parPosC + 1).length());
				o--;
				c = 0;
			}
		}
		return result;
	}

	/*
	 * Precondition: get a peace of string. Postcondition: Return a calculated
	 * number
	 */
	public static String ParenHand(String k) {
		double n1, n2, n3;
		String result = k;
		char o1 = ' ', o2 = ' ';
		if (operSetter(k) == 100)
			result = k;
		else {
			n1 = Double.parseDouble(k.substring(0, operSetter(k)));
			o1 = k.charAt(operSetter(k));
			k = k.substring((operSetter(k) + 1));
			if (operSetter(k) == 100) {
				n2 = Double.parseDouble(k.substring(0));
				result = Double.toString(calcNumb(o1, n1, n2));
			} else {
				n2 = Double.parseDouble(k.substring(0, operSetter(k)));
				if (operSetter(k) == 100)
					result = Double.toString(calcNumb(o1, n1, n2));
				o2 = k.charAt(operSetter(k));
				k = k.substring((operSetter(k) + 1));
				if (operSetter(k) == 100) {
					n3 = Double.parseDouble(k.substring(0));
					k = "";
				} else {
					n3 = Double.parseDouble(k.substring(0, operSetter(k)));
					k = k.substring((operSetter(k)));
				}
				int n = k.length() / 2;
				for (int r = 0; r <= n + 2; r++) {
					result = Double.toString(n1);
					if ((((int) operFinder(o1, o2)) == (int) o1)) {
						n1 = calcNumb(o1, n1, n2);
						n2 = n3;
						o1 = o2;
						if (k.length() == 0) {
							n3 = 0;
							o2 = '.';
						} else {
							o2 = k.charAt(operSetter(k));
							k = k.substring((operSetter(k) + 1));
							if (operSetter(k) == 100) {
								n3 = Double.parseDouble(k.substring(0));
								k = "";
							} else {
								n3 = Double.parseDouble(k.substring(0, operSetter(k)));
								k = k.substring(operSetter(k));
							}
						}
					} else if ((((int) operFinder(o1, o2)) == (int) o2)) {
						n2 = calcNumb(o2, n2, n3);
						if (k.length() == 0) {
							n3 = 0;
							o2 = '.';
						} else {
							o2 = k.charAt(operSetter(k));
							k = k.substring((operSetter(k) + 1));
							if (operSetter(k) == 100) {
								n3 = Double.parseDouble(k.substring(0));
								k = "";
							} else {
								n3 = Double.parseDouble(k.substring(0, operSetter(k)));
								k = k.substring(operSetter(k));
							}
						}
					}
				}
			}
		}
		return result;
	}

	/*
	 * Precondition: get the numbers and operator. Postcondition: Return a
	 * calculated number
	 */
	public static double calcNumb(char o, double a, double b) {
		double result = 0;

		switch (o) {
		case 'p':
			result = a + b;
			break;
		case '-':
			result = a - b;
			break;
		case '/':
			result = a / b;
			break;
		case 'm':
			result = a * b;
			break;
		default:
			result = a;
			break;
		}
		return result;
	}

	/*
	 * Precondition: get two operator. Postcondition: Return the appropriate
	 * operator
	 */
	public static char operFinder(char a, char b) {
		char result = '.';
		if (((int) a == 109) || ((int) a == 47))
			result = a;
		else if (((int) b == 109) || ((int) b == 47))
			result = b;
		else
			result = a;
		return result;
	}

	/*
	 * Precondition: check the input string with parenthesis . Postcondition:
	 * Return the out put without parenthesis.
	 */
	public static String ParenCutter(String n) {
		String b = " ";
		char parentOpen = 'o';
		char parentClose = 'c';

		for (int t = 0; t < n.length(); t++) {
			char myChar = n.charAt(t);
			if (myChar == parentOpen) {
				n = n.replaceFirst(n.substring(t, t + 1), b);
			} else if (myChar == parentClose) {
				n = n.replaceFirst(n.substring(t), b);
			}
		}
		return n.trim();
	}
}