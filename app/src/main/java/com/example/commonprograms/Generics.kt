package com.example.commonprograms


open class Weapon
open class Rifle : Weapon()
class SniperRifle : Rifle()

fun main() {

    // With the out modifier, the subtyping is preserved, so the Case<SniperRifle> is a subtype
    // of Case<Rifle> when SniperRifle is a subtype of Rifle.
    // As a result, the useProducer() function can be called with Case<SniperRifle> too:

    useProducer(Case<SniperRifle>())               // OK
    useProducer(Case<Rifle>())                     // OK
    //useProducer(Case<Weapon>())                    // Error

    // With the in modifier, the subtyping is reversed, so now the Case<Weapon>
    // is a subtype of Case<Rifle> when Rifle is a subtype of Weapon.
    // As a result, the useConsumer() function can be called with Case<Weapon> too:

    //useConsumer(Case2<SniperRifle>())               // Error
    useConsumer(Case2<Rifle>())                     // OK
    useConsumer(Case2<Weapon>())                    // OK

    // Without the in or out modifier, the subtyping is disallowed, so now neither
    // Case<Weapon> nor Case<SniperRifle> is a subtype of Case<Rifle>.
    // As a result, the useProducerConsumer() function can only be called with Case<Rifle>:

    //useProducerConsumer(Case3<SniperRifle>())       // Error
    useProducerConsumer(Case3<Rifle>())             // OK
    //useProducerConsumer(Case3<Weapon>())            // Error
}

// When you declare a generic type with an out modifier, it's called covariant.
// A covariant is a producer of T, that means functions can return T but they can't take T as arguments:

class Case<out T> {
    private val contents = mutableListOf<T>()
    fun produce(): T = contents.last()         // Producer: OK
    //fun consume(item: T) = contents.add(item)  // Consumer: Error
}

//The Case declared with the out modifier produces T and its subtypes:

fun useProducer(case: Case<Rifle>) {
    // Produces Rifle and its subtypes
    val rifle = case.produce()
}

// When you declare a generic type with an in modifier, it's called contravariant. A contravariant is a
// consumer of T, that means functions can take T as arguments but they can't return T:

class Case2<in T> {
    private val contents = mutableListOf<T>()
    //fun produce(): T = contents.last()         // Producer: Error
    fun consume(item: T) = contents.add(item)  // Consumer: OK
}

// The Case declared with the in modifier consumes T and its subtypes:

fun useConsumer(case: Case2<Rifle>) {
    // Consumes Rifle and its subtypes
    case.consume(SniperRifle())
}

class Case3<T> {
    private val contents = mutableListOf<T>()
    fun produce(): T = contents.last()         // Producer: OK
    fun consume(item: T) = contents.add(item)  // Consumer: OK
}

// The Case declared without in or out modifier produces and consumes T and its subtypes:

fun useProducerConsumer(case: Case3<Rifle>) {
    // Produces Rifle and its subtypes
    case.produce()
    // Consumes Rifle and its subtypes
    case.consume(SniperRifle())
}

/*

Conclusion
The List in Kotlin is a producer only. Because it's declared using the out modifier: List<out T>.
This means you cannot add elements to it as the add(element: T) is a consumer function.
Whenever you want to be able to get() as well as add() elements, use the invariant version MutableList<T>.

That's it! Hopefully that helps understand the ins and outs of the variance!

*/