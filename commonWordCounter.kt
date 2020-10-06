import java.io.File

data class Item(val word : String, val count : Int)

fun main() {

	val frequencyMap: MutableMap<String, Int> = HashMap()

	val regex = "[^A-Za-z ]".toRegex()
	File("wordsToCount.txt").forEachLine { 
		val words = it.split(" ")
		for (word in words) {
			val wordToCount = regex.replace(word.toLowerCase(), "")
			var count = frequencyMap[wordToCount]
			if (count == null) count = 0
			frequencyMap[wordToCount] = count + 1
		}	
	}

//	val list = listOf("Spanish", "Italian", "Esperanto", "Esperanto", "German", "French")
	
/*	for (s in list) {
		var count = frequencyMap[s]
		if (count == null) count = 0
		frequencyMap[s] = count + 1
	}
*/
	val items = ArrayList<Item>()

	for (s in frequencyMap) {
		items.add(Item(s.key, s.value))
	}

	val sortedList = items.sortedBy { it.count }.reversed()

	println(sortedList)
	println(frequencyMap.size)
}
