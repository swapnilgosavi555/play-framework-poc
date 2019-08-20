package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._


class BookControllerTest extends  PlaySpec with GuiceOneAppPerTest with Injecting {

val controller=new BookController(stubControllerComponents())
  "insertBook" should {
    "return insertBook form in text/html format" in {
      val result= controller.insertBook().apply(FakeRequest(GET, "/"))
      status(result) mustBe OK
      contentType(result) mustBe Some("text/html")

    }
  }
  "getBookById of 1" should {
    "return  book of id 1 in text/html format" in {
      val result= controller.getBookById(1).apply(FakeRequest(GET, "/"))
      status(result) mustBe OK
      contentType(result) mustBe Some("text/html")

    }
  }
  "getBookById of 2" should {
    "return Book not found" in {
      val result= controller.getBookById(2).apply(FakeRequest(GET, "/"))
      status(result) mustBe OK
      contentAsString(result) must include ("Book not found of Id:2")
    }
  }
  "getBookByTitle of scala" should {
    "return Book not found" in {
      val result= controller.getBookByTitle("scala").apply(FakeRequest(GET, "/"))
      status(result) mustBe OK
      contentAsString(result) must include ("Book not found of Title:scala")
    }
  }
  "getBookByTitle of java" should {
    "should return book details of book title as java text/html" in {
      val result= controller.getBookByTitle("java").apply(FakeRequest(GET, "/"))
      status(result) mustBe OK
      contentType(result) mustBe Some("text/html")
    }
  }
  "getBookByAuthor of swapnil" should {
    "return Book not found" in {
      val result= controller.getBookByAuthor("rachana").apply(FakeRequest(GET, "/"))
      status(result) mustBe OK
      contentAsString(result) must include ("Book not found of Author:rachana")
    }
  }
  "getBookByAuthor of durga" should {
    "return book details of book author as durga text/html" in {
      val result= controller.getBookByAuthor("durga").apply(FakeRequest(GET, "/"))
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