package cz.utb.meteodenik.cv04.prehled

class MestoJ(
    var nazev: String? = null,
    var teplota: Double? = null
) {
    fun popis(): String {
        val n = nazev
        val t = teplota
        return if (n != null && t != null) "$n: $t C" else "neznámé"
    }
}

class Prehled {
    var nacita: Boolean = false
    var chyba: Boolean = false
    var prazdno: Boolean = false
    var mesta: MutableList<MestoJ> = mutableListOf()

    fun textStavu(): String = when {
        nacita -> "Načítám…"
        chyba -> "Chyba"
        prazdno -> "Nic tu není"
        else -> "Měst: ${mesta.size}"
    }

    fun nejteplejsi(): MestoJ? = mesta.maxByOrNull { it.teplota ?: Double.NEGATIVE_INFINITY }

    fun vypis() {
        mesta.forEach { println(it.popis()) }
        println(textStavu())
        nejteplejsi()?.let { nej ->
            println("Nejtepleji: ${nej.nazev} (${nej.teplota} C)")
        }
    }
}

fun main() {
    val p = Prehled()
    p.mesta.add(MestoJ("Zlín", 21.4))
    p.mesta.add(MestoJ("Brno", 23.0))
    p.mesta.add(MestoJ("Otrokovice", 20.9))
    p.vypis()
}