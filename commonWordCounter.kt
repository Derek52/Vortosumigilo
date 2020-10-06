data class Item(val word : String, val count : Int)

fun main() {

	val list = listOf("Spanish", "Italian", "Esperanto", "Esperanto", "German", "French")
	val frequencyMap: MutableMap<String, Int> = HashMap()
	
	for (s in list) {
		var count = frequencyMap[s]
		if (count == null) count = 0
		frequencyMap[s] = count + 1
	}

	val items = ArrayList<Item>()

	for (s in frequencyMap) {
		items.add(Item(s.key, s.value))
	}

	val sortedList = items.sortedBy { it.count }.reversed()

	println(sortedList)
}
