package machine

fun main() {

    val default = CoffeeMachine()

    while (true) {

        println("Write action (buy, fill, take): ")
        val input = readLine()!!.toString()

        if (input == "buy") {

            println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")

            val coffeeType = readLine()!!

            if (coffeeType == "1") {

                if (default.water >= 250 && default.beans >= 16 && default.cups >= 1) {
                    println("I have enough resources, making you a coffee!")
                    espresso(default)
                    continue
                } else {
                    println("Sorry, not enough resources")
                    continue
                }
            } else if (coffeeType == "2") {

                if (default.water >= 350 && default.milk >= 75 && default.beans >= 20 && default.cups >= 1) {
                    println("I have enough resources, making you a coffee!")
                    latte(default)
                    continue
                } else {
                    println("Sorry, not enough resources")
                    continue
                }
            } else if (coffeeType == "3") {

                if (default.water >= 200 && default.milk >= 100 && default.beans >= 12 && default.cups >= 1) {
                    println("I have enough resources, making you a coffee!")
                    cappuccino(default)
                    continue
                } else {
                    println("Sorry, not enough resources")
                    continue
                }
            } else if (coffeeType == "back") {
                continue
            }
        } else if (input == "fill") {
            fill(default)
            continue
        } else if (input == "take") {
            take(default)
            continue
        } else if (input == "remaining") {
            remaining(default)
            continue
        } else if (input == "exit") {
            break
        }
    }
}

fun remaining(default: CoffeeMachine) {
    println(default.state())
}

fun take(default: CoffeeMachine): CoffeeMachine {
    println("I gave you ${default.cash}")
    default.cash = 0
    return default
}

fun fill(default: CoffeeMachine): CoffeeMachine {
    println("Write how many ml of water do you want to add: ")
    val inputWater = readLine()!!.toInt()
    println("Write how many ml of milk do you want to add: ")
    val inputMilk = readLine()!!.toInt()
    println("Write how many grams of coffee beans do you want to add: ")
    val inputBeans = readLine()!!.toInt()
    println("Write how many disposable cups of coffee do you want to add: ")
    val inputCups = readLine()!!.toInt()

    default.water += inputWater
    default.milk += inputMilk
    default.beans += inputBeans
    default.cups += inputCups

    return default
}

fun buy(default: CoffeeMachine): CoffeeMachine {
    println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ")

    val newMachine: CoffeeMachine = when(readLine()!!.toString()) {
        "1" -> espresso(default)
        "2" -> latte(default)
        "3" -> cappuccino(default)
        else -> default
    }

    return newMachine
}

fun cappuccino(default: CoffeeMachine): CoffeeMachine {
    default.water -= 200
    default.milk -= 100
    default.beans -= 12
    default.cash += 6
    default.cups -= 1
    return default
}

fun latte(default: CoffeeMachine): CoffeeMachine {
    default.water -= 350
    default.milk -= 75
    default.beans -= 20
    default.cash += 7
    default.cups -= 1
    return default
}

fun espresso(default: CoffeeMachine): CoffeeMachine {
    default.water -= 250
    default.beans -= 16
    default.cash += 4
    default.cups -= 1
    return default
}

class CoffeeMachine(var cash:Int = 550,
                    var water:Int = 400,
                    var milk:Int = 540,
                    var beans:Int = 120,
                    var cups:Int = 9) {

    fun state() {
        println(
            """
        The coffee machine has:
        $water ml of water
        $milk ml of milk
        $beans g of coffee beans
        $cups disposable cups
        $$cash of money
    """.trimIndent()
        )
    }
}
