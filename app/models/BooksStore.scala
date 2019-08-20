package models

import play.api.data._
import play.api.data.Forms._

case class Book(id: Int, bookName: String, author: String)

object BooksStore {
  val list=List(Book(1,"java","durga"),Book(2,"scala","swapnil"))
  val bookform = Form(
    mapping(
      "id" -> number(min = 1, max = 10),
      "bookName" -> nonEmptyText,
      "author" -> nonEmptyText
    )(Book.apply)(Book.unapply))
def addBooks(book:Book)={
  list.:+(book)
}
  def checkBook(id:Int)={
   val list1= list.filter(_.id==id)
    if(list1.isEmpty) false
    else true
  }
  def update(id:Int,book:Book)={
    list.updated(id-1,book)
  }
  def delete(id:Int)= {
    val book=list.filter(_.id==id)
    list.diff(book)
  }
  def getBookById(id: Int) = {
    list.filter(_.id == id)
  }

  def getBookByTitle(bookName: String) = {
    list.filter(_.bookName == bookName)
  }

  def getBookByAuthor(author: String) = {
    list.filter(_.author == author)
  }

}
