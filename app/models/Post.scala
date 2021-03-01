package models

case class Post(id: Int, content: String, date: Option[java.sql.Timestamp] = None, userId: Int)

