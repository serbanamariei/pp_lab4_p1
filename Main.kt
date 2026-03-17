interface RawPrinter {
    fun printRaw(books: Set<Book>)
}

interface HtmlPrinter {
    fun printHTML(books: Set<Book>)
}

interface JsonPrinter {
    fun printJSON(books: Set<Book>)
}

class LibraryPrinter : RawPrinter, HtmlPrinter, JsonPrinter {
    override fun printRaw(books: Set<Book>) {
        println("Format RAW:")
        books.forEach { println(it.toString()) }
    }

    override fun printHTML(books: Set<Book>) {
        println("<html><body>")
        books.forEach { println("<p>${it.getName()} de ${it.getAuthor()}</p>") }
        println("</body></html>")
    }

    override fun printJSON(books: Set<Book>) {
        println("[")
        books.forEach { println("  { \"name\": \"${it.getName()}\" },") }
        println("]")
    }
}

open class Content(
    private var author: String,
    private var text: String,
    private var name: String,
    private var publisher: String
) {
    fun getAuthor(): String {
        return author
    }

    fun setAuthor(a: String) {
        author = a
    }

    fun getText(): String {
        return text
    }

    fun setText(t: String) {
        text = t
    }

    fun getName(): String {
        return name
    }

    fun setName(n: String) {
        name = n
    }

    fun getPublisher(): String {
        return publisher
    }

    fun setPublisher(p: String) {
        publisher = p
    }

    override fun toString(): String
    {
        return "Author: $author, Title: $name, Publisher: $publisher"
    }

}

class PricedContent(
    author: String,
    text: String,
    name: String,
    publisher: String,
    private var price: Double
): Content(author,text,name,publisher)
{
    fun getPrice(): Double
    {
        return price
    }

    fun setPrice(p: Double)
    {
        price=p
    }

    override fun toString(): String
    {
        return "${super.toString()}, Price: $price"
    }
}


open class Book(
    private val data: Content
)
{
    override fun toString(): String {
        return data.toString()
    }

    fun getName(): String {
        return data.getName()
    }

    fun getAuthor(): String {
        return data.getAuthor()
    }

    fun getPublisher(): String {
        return data.getPublisher()
    }

    fun getText(): String {
        return data.getText()
    }

    fun hasAuthor(a: String): Boolean {
        return a == getAuthor()
    }

    fun hasTitle(t: String): Boolean {
        return t == getName()
    }

    fun isPublishedBy(p: String): Boolean {
        return p == getPublisher()
    }
}


class Library(
    private var books: MutableSet<Book>
)
{
    fun getBooks(): MutableSet<Book> {
        return books
    }

    fun addBook(b: Book) {
        books.add(b)
    }

    fun findAllByAuthor(a: String): MutableSet<Book> {
        return books.filter { it.getAuthor() == a }.toMutableSet()
    }

    fun findAllByName(n: String): MutableSet<Book> {
        return books.filter { it.getName() == n }.toMutableSet()
    }

    fun findAllByPublisher(p: String): MutableSet<Book> {
        return books.filter { it.getPublisher() == p }.toMutableSet()
    }
}


fun main()
{
    val myLibrary = Library(mutableSetOf())

    val classicContent = Content("Mihai Eminescu", "Poezii", "Luceafarul", "Junimea")
    myLibrary.addBook(Book(classicContent))

    val pricedContent = PricedContent("Ion Creanga", "Povesti", "Amintiri", "Polirom", 45.0)
    myLibrary.addBook(Book(pricedContent))

    val printer: RawPrinter = LibraryPrinter()

    println("Carti: ")
    printer.printRaw(myLibrary.getBooks())
}