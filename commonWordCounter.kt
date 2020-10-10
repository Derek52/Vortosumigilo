import java.io.File
import java.io.FileWriter

data class Item(val word : String, val count : Int)

fun main() {

	//this file tests a set of words that use special accented characters from languages like Catalan, French, and German
	val frequencyMap: MutableMap<String, Int> = createFrequencyMap(File("Accented.Letters.txt"))


	val items = ArrayList<Item>()

	for (s in frequencyMap) {
		items.add(Item(s.key, s.value))
	}

	val sortedList = items.sortedBy { it.count }.reversed()

	println(sortedList)
	println(frequencyMap.size)

	writeToFile(sortedList, frequencyMap.size)
}

fun createFrequencyMap(inputFile : File) : MutableMap<String, Int> {
	val frequencyMap: MutableMap<String, Int> = HashMap()
	val regex = "[^'a-zA-ZÀ-ÖØ-öø-ÿ]".toRegex()
	inputFile.forEachLine { 
		val words = it.split(" ")
		for (word in words) {
			val wordToCount = regex.replace(word.toLowerCase(), "")
			var count = frequencyMap[wordToCount]
			if (count == null) count = 0
			frequencyMap[wordToCount] = count + 1
		}	
	}
	return frequencyMap
}

fun writeToFile(list : List<Item>, individualWordCount: Int) {
	val writer = FileWriter("Accented.Letters.Test.Result.txt");
	writer.write("Number Of Unique Words: $individualWordCount\n")
	for (item in list) {
		writer.write("${item.word}=${item.count}\n")
	}

	writer.close()
}
