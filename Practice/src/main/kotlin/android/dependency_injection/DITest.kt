package android.dependency_injection

private class Car(
    val tire: Tire?,
    val engine: Engine?,
    val name: String = "TestCar"
) {

    fun start() {
        if (tire == null) {
            println("tire is not equip")
            return
        }

        if (engine == null) {
            println("engine is not equip")
            return
        }

        engine.start()
    }
}
private class Tire
private class Engine(
    val name: String
) {
    private var _temperature: Int = 30
    val temperature
        get() = _temperature

    private var _isStarted: Boolean = false
    val isStarted
        get() = _isStarted

    fun start() {
        println("engine $name is start")
        println("current temperature is $_temperature")

        _isStarted = true
        _temperature = 90

        println("current temperature is $_temperature")
    }
}

private fun main() {
    val nullCar = Car(
        tire = null,
        engine = null
    )
    nullCar.start()

    val tire = Tire()
    val engine = Engine("custom")
    val car = Car(tire, engine)
    car.start()
}