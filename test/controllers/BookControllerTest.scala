package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test.Helpers._
import play.api.test._


class BookControllerTest extends  PlaySpec with GuiceOneAppPerTest with Injecting {

val controller=new BookController(stubControllerComponents())

  "getBookByTitle of scala" should {
    "return Book not found" in {
      val result= controller.getBookByTitle("scala").apply(FakeRequest(GET, "/"))
      status(result) mustBe OK
      contentAsString(result) must include ("Book not found of Title:scala")
    }
  }
  "getBookByTitle of Play WebSockets" should {
    "should return book details of book title as java text/html" in {
      val result= controller.getBookByTitle("Play WebSockets").apply(FakeRequest(GET, "/"))
      status(result) mustBe OK
      contentType(result) mustBe Some("text/html")
    }
  }
  "getBookByAuthor of Durga" should {
    "return Book not found" in {
      val result= controller.getBookByAuthor("Durga").apply(FakeRequest(GET, "/"))
      status(result) mustBe OK
      contentAsString(result) must include ("Book not found of Author:Durga")
    }
  }
  "getBookByAuthor of Rachana" should {
    "return book details of book author as durga text/html" in {
      val result= controller.getBookByAuthor("Rachana").apply(FakeRequest(GET, "/"))
      status(result) mustBe OK
      contentType(result) mustBe Some("text/html")
    }
  }
  "displayAllBooks" should {
    "display book detail in text/html format" in {
      val result= controller.displayAllBooks().apply(FakeRequest(GET, "/"))
      status(result) mustBe OK
      contentType(result) mustBe Some("text/html")

    }
  }
}