package cz.utb.meteodenik.cv04.prehled


data class Mesto(
    val nazev: String,
    val teplota: Double
)


fun Double.jakoCelsia(): String = "$this C"

fun Mesto.popis(): String = "$nazev: ${teplota.jakoCelsia()}"


sealed interface StavPrehledu {
    object Nacita : StavPrehledu
    object Chyba : StavPrehledu
    object Prazdno : StavPrehledu
    data class Hotovo(val pocet: Int) : StavPrehledu
}


class Prehled(private val mesta: List<Mesto>) {


    private val stav: StavPrehledu = when {
        mesta.isEmpty() -> StavPrehledu.Prazdno
        else -> StavPrehledu.Hotovo(mesta.size)
    }


    fun textStavu(): String = when (val s = stav) {
        is StavPrehledu.Nacita -> "Načítám…"
        is StavPrehledu.Chyba -> "Chyba"
        is StavPrehledu.Prazdno -> "Nic tu není"
        is StavPrehledu.Hotovo -> "Měst: ${s.pocet}"
    }


    fun nejteplejsi(): Mesto? = mesta.maxByOrNull { it.teplota }

    fun vypis() {

        for (m in mesta) {
            println(m.popis())
        }
        println(textStavu())

        val nej = nejteplejsi()
        if (nej != null) {
            println("Nejtepleji: ${nej.nazev} (${nej.teplota.jakoCelsia()})")
        }
    }
}

fun main() {

    val p = Prehled(
        listOf(
            Mesto("Zlín", 21.4),
            Mesto("Brno", 23.0),
            Mesto("Otrokovice", 20.9)
        )
    )
    p.vypis()
}