package demo

class Calculator {
    fun add(first: Int, second: Int) = (first + second)

    fun subtract(first: Int, second: Int) = (first - second)

    fun multiply(first: Int, second: Int) = (first * second)

    fun divide(first: Int, second: Int) = (first / second)

    fun calculate(expression: String): Int {
        val tokens = expression.split(" ")

        val multipleResult = calculateMultiply(tokens)

        val lastResult = calculateRest(multipleResult)

        return lastResult
    }

    private fun calculateMultiply(tokens: List<String>, index: Int = 0):List<String> {
        if (!tokens.contains("*") && !tokens.contains("/")) return tokens

        val (first, operator, second) = tokens.drop(index)
        val (firstNum, secondNum) = listOf(first, second).map { it.toInt() }

        return when (operator) {
            "+", "-" -> calculateMultiply(tokens, index + 2)
            "*" -> calculateMultiply(
                tokens.take(index)
                        + listOf(multiply(firstNum, secondNum).toString())
                        + tokens.drop(index + 3)

            )
            "/" -> calculateMultiply(
                tokens.take(index)
                        + listOf(divide(firstNum, secondNum).toString())
                        + tokens.drop(index + 3)

            )
            else -> listOf()
        }
    }

    private fun calculateRest(tokens: List<String>): Int {
        if (tokens.size == 1) return  tokens[0].toInt()

        val (first, operator, second) = tokens
        val (firstNum, secondNum) = listOf(first, second).map { it.toInt() }

        val result = when (operator) {
            "+" -> add(firstNum, secondNum).toString()
            "-" -> subtract(firstNum, secondNum).toString()
            else -> "1"
        }

        val rest = tokens.drop(3).toMutableList()

        rest.addFirst(result)

        return calculateRest(rest)
    }


}
