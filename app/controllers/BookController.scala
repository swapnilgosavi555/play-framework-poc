package controllers

import javax.inject._
import play.api.mvc._
import models._

class BookController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with play.api.i18n.I18nSupport {
  def displayAllBooks() = Action {
    Ok(views.html.displaybook(BooksStore.list))
  }
  def checkBook(id:Int)=Action{
    if(BooksStore.checkBook(id)){
      Ok("Book is present in BookStore")
    }
    else Ok(s"Book is not present in BookStore of id:$id")
  }
  def insertBook() = Action { implicit request =>
    Ok(views.html.insertbookform(BooksStore.bookform))
  }

  def save() = Action { implicit request =>
    val bookData: Book = BooksStore.bookform.bindFromRequest.get
    val list=BooksStore.addBooks(bookData)
    Ok(views.html.displaybook(list))
  }
  def update(id:Int)=Action{ implicit request =>
    if(BooksStore.checkBook(id)) {
      Ok(views.html.updateform(id, BooksStore.bookform))
    }
    else Ok(s"Book not found of id:$id")
  }
  def edit(id:Int)=Action{implicit request =>
    val book:Book = BooksStore.bookform.bindFromRequest.get
    val list=BooksStore.update(id,book)
    Ok(views.html.displaybook(list))
  }
  def delete(id:Int)= Action{implicit request =>
    if(BooksStore.checkBook(id)){
      val list1=BooksStore.delete(id)
      Ok(views.html.displaybook(list1))}
    else Ok(s"you can't delete book because book not found of id:$id")
  }
  def getBookById(id: Int) = Action { implicit request =>
    val list = BooksStore.getBookById(id)
    if (list.isEmpty) Ok(s"Book not found of Id:$id")
    else Ok(views.html.displaybook(list))
  }

  def getBookByTitle(bookName: String) = Action {
    val list = BooksStore.getBookByTitle(bookName)
    if (list.isEmpty) Ok(s"Book not found of Title:$bookName")
    else Ok(views.html.displaybook(list))
  }

  def getBookByAuthor(author: String) = Action {
    val list = BooksStore.getBookByAuthor(author)
    if (list.isEmpty) Ok(s"Book not found of Author:$author")
    else Ok(views.html.displaybook(list))
  }
}
