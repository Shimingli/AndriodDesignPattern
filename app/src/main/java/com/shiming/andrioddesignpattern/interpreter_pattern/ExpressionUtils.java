package com.shiming.andrioddesignpattern.interpreter_pattern;

import java.util.List;

import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.basic.OperatorManager;
import fr.expression4j.core.Catalog;
import fr.expression4j.core.Expression;
import fr.expression4j.core.ExpressionModel;
import fr.expression4j.core.Parameters;
import fr.expression4j.core.exception.EvalException;
import fr.expression4j.core.exception.ParametersException;
import fr.expression4j.core.predefine.AbstractFunction;
import fr.expression4j.factory.ExpressionFactory;
import fr.expression4j.factory.NumberFactory;
import fr.expression4j.util.ParameterUtil;

/**
 * author： Created by shiming on 2018/3/29 11:37
 * mailbox：lamshiming@sina.com
 */

public class ExpressionUtils {

        static class Factoriel extends AbstractFunction {
            private double fact(double x) {
                if (x == 0) {
                    return 1;
                }
                return x * fact(x-1);
            }

            /**
             * evaluation method called by the Expression4j when needed.
             *
             * @param parameters parameter given to the function for the evaluation.
             */
            public MathematicalElement evaluate(Parameters parameters)
                    throws EvalException {
                try {
                    MathematicalElement me = parameters.getParameter("x");
                    double tmpValue = me.getRealValue();
                    if (tmpValue < 0) {
                        throw new EvalException(
                                "Cannot evaluate fact of " +
                                        "negativ number.");
                    }
                    double result = fact(tmpValue);
                    return NumberFactory.createReal(result);
                }
                catch (ParametersException pe) {
                    throw new EvalException("Cannot evaluate fact(x). " + pe);
                }
            }

            public MathematicalElement evaluate(OperatorManager operatorManager,
                                                Parameters parameters) throws EvalException {
                return evaluate(parameters);
            }

            public Catalog getCatalog() {
                return ExpressionFactory.getCatalog();
            }

            /**
             * get the function name
             */
            public String getName() {
                return "fact";
            }

            public List getParameters() {
                return ParameterUtil.generateXParameters();
            }

            public ExpressionModel getExpressionModel() {
                return  null;
            }
        }
        public static void Test() {
//            test001();
//            test002();
//            test003();
//            test004();
//            test005();
            test006();
        }

        private static void test006() {
            try {
                //y=ax2+bx+c 一元二次方程
                Expression expression = ExpressionFactory.createExpression("y(a,b,c,x)=a*x^2+b*x+c");
                System.out.println("Expression name: " + expression.getName());

                System.out.println("Expression parameters: " + expression.getParameters());

                MathematicalElement element_a=NumberFactory.createReal(1);
                MathematicalElement element_b=NumberFactory.createReal(4);
                MathematicalElement element_c=NumberFactory.createReal(4);
                MathematicalElement element_x=NumberFactory.createReal(1);
                Parameters parameters=ExpressionFactory.createParameters();
                parameters.addParameter("a", element_a);
                parameters.addParameter("b", element_b);
                parameters.addParameter("c", element_c);
                parameters.addParameter("x", element_x);
                System.out.println("Value of expression:  y for x=1 a=1 b=4 c=4 :" +expression.evaluate(parameters).getRealValue());

                //y=x^2+6x+9=(x+3)^2
                element_a=NumberFactory.createReal(1);
                element_b=NumberFactory.createReal(6);
                element_c=NumberFactory.createReal(9);
                element_x=NumberFactory.createReal(1);
                parameters=ExpressionFactory.createParameters();
                parameters.addParameter("a", element_a);
                parameters.addParameter("b", element_b);
                parameters.addParameter("c", element_c);
                parameters.addParameter("x", element_x);
                System.out.println("Value of expression:  y for x=1 a=1 b=6 c=9 :" +expression.evaluate(parameters).getRealValue());


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private static void test005() {
            try {

                Catalog catalog = ExpressionFactory.createCatalog("catalog");
                catalog.addExpression(new Factoriel());
                Expression expression1 =
                        ExpressionFactory.createExpression("f(x)=fact(x+1)",catalog);
                System.out.println("Expression name: " + expression1.getName());
                System.out.println("Expression : " + expression1);
                System.out.println("Expression parameters: " +
                        expression1.getParameters());

                //compute a value
                MathematicalElement me1 = NumberFactory.createReal(5);
                Parameters parameters = ExpressionFactory.createParameters();
                parameters.addParameter("x",me1);
                System.out.println("Value of expression h :" +
                        expression1.evaluate(parameters));
            }
            catch (Exception e) {
                System.out.println("Error: " + e);
            }

        }

        private static void test004() {
            try {
                Expression expression = ExpressionFactory
                        .createExpression("k()=abs(-3)");
                System.out.println("Expression name: " + expression.getName());
                System.out.println("Value of expression:" +
                        expression.evaluate(null).getRealValue());
            }
            catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }

        private static void test003() {
            try {
                Expression expression1 = ExpressionFactory
                        .createExpression("f(x,y)=2.4e-2*x-3.2*y");
                Expression expression2 = ExpressionFactory
                        .createExpression("g(x,y)=4*f(x+3,f(y*2,x/2))+3");
                System.out.println("Expression name: " + expression2.getName());
                System.out.println("Expression : " + expression2);
                System.out.println("Expression parameters: " +
                        expression2.getParameters());

                MathematicalElement me1 = NumberFactory.createReal(2);
                MathematicalElement me2 = NumberFactory.createReal(4);
                Parameters parameters = ExpressionFactory.createParameters();
                parameters.addParameter("x",me1);
                parameters.addParameter("y",me2);
                System.out.println("Value of expression g for x=2 and y = 4:" +
                        expression2.evaluate(parameters).getRealValue());

                me1 = NumberFactory.createReal(3);
                me2 = NumberFactory.createReal(2);
                parameters.addParameter("x",me1);
                parameters.addParameter("y",me2);
                System.out.println("Value of expression g for x=3 and y = 2:" +
                        expression2.evaluate(parameters).getRealValue());
            }
            catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }

        private static void test002() {
            try {
                Expression expression = ExpressionFactory.createExpression("f()=2.4e-2");

                System.out.println("Expression name: " + expression.getName());

                System.out.println("Value of expression:" + expression.evaluate(null).getRealValue());
            }
            catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
        private static void test001() {
            try {
//                Expression expression1 = ExpressionFactory.createExpression("f(x,y)=2*x-3*y");
                Expression expression2 = ExpressionFactory.createExpression("g(x,y)=4*f(x,y)+3");

                System.out.println("Expression name: " + expression2.getName());
                System.out.println("Expression : " + expression2);
                System.out.println("Expression parameters: " + expression2.getParameters());

                MathematicalElement me1 = NumberFactory.createReal(2);
                MathematicalElement me2 = NumberFactory.createReal(4);
                Parameters parameters = ExpressionFactory.createParameters();
                parameters.addParameter("x",me1);
                parameters.addParameter("y",me2);

                System.out.println("Value of expression g for x=2 and y = 4:" +
                        expression2.evaluate(parameters).getRealValue());

                me1 = NumberFactory.createReal(3);
                me2 = NumberFactory.createReal(2);
                parameters.addParameter("x",me1);
                parameters.addParameter("y",me2);
                System.out.println("Value of expression g for x=3 and y = 2:" +
                        expression2.evaluate(parameters).getRealValue());
            }
            catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
}
