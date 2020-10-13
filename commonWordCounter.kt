import java.io.File
import java.io.FileWriter

data class Item(val word : String, val count : Int)

fun main(args : Array<String>) {

	for (arg in args) {
		val inputFile = File(arg)
		val outputFileName = arg.substring(0, arg.length - 4)//remove file extension from input filename, e.g. "harry.potter.txt" to "harry.potter"

		val frequencyMap: MutableMap<String, Int> = createFrequencyMap(inputFile)
		
		val items = ArrayList<Item>()

		for (s in frequencyMap) {
			items.add(Item(s.key, s.value))
		}
	
		val sortedList = items.sortedBy { it.count }.reversed()
	
		println(sortedList)
		println(frequencyMap.size)
	
		writeToFile(sortedList, frequencyMap.size, outputFileName)
	
	}
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

fun writeToFile(list : List<Item>, individualWordCount : Int, outputFileName : String) {
	val writer = FileWriter("$outputFileName.counted.words.txt");
	writer.write("Number Of Unique Words: $individualWordCount\n")
	for (item in list) {
		writer.write("${item.word}=${item.count}\n")
	}

	writer.close()
}
