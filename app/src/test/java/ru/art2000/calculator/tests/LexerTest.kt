package ru.art2000.calculator.tests

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import ru.art2000.calculator.model.calculator.ExpressionPart
import ru.art2000.calculator.view_model.calculator.CalculationClass

@RunWith(Parameterized::class)
class LexerTest {

    companion object {

        private inline val lexer get() = CalculationClass.lexer

        @JvmStatic
        @Parameterized.Parameters(name = "{0}")
        fun testCases(): Iterable<TestCase<Double>> = TestCases.cases

        private fun test(expressions: List<String>, expectedLexemes: List<ExpressionPart<Double>>?) {
            expressions.forEach {
                test(it, expectedLexemes)
            }
        }

        private fun test(expression: String, expectedLexemes: List<ExpressionPart<Double>>?) {
            println(expression)
            val actualLexemes = lexer.getLexemes(expression.toCharArray())

            assert(actualLexemes == expectedLexemes) {
                """ 
                Expression: $expression
                Expected lexemes: $expectedLexemes
                Actual lexemes:   $actualLexemes
            """.trimIndent()
            }
        }
    }

    @Parameterized.Parameter
    lateinit var testCase: TestCase<Double>

    @Test
    fun runTestCases() {
        test(testCase.expressions, testCase.expectedLexemes)
    }
}