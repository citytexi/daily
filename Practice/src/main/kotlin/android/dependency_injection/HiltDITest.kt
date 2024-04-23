package android.dependency_injection


//@Module
//@InstallIn(SingletonComponent::class)
//class CalModule {
//    @Provides
//    fun getNumberA(): Int = 100
//
//    @Provides
//    fun sumOperator(a: Int): Operator = SumOperator(a)
//}
//
//interface Operator {
//    fun calculation(): Int
//}
//
//class SumOperator(
//    private val a: Int
//) : Operator {
//    override fun calculation(): Int = a + 200
//
//}
//
//class Calculator
//@Inject
//constructor(
//    private val operator: Operator
//) {
//    fun cal() {
//        operator.calculation()
//    }
//}