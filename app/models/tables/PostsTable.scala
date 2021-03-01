package models.tables

import config.Db
import models.Post

trait PostsTable extends UsersTable {
  this: Db =>

  import config.profile.api._

  class Posts(tag: Tag) extends Table[Post](tag, "post") {
    def * = (id, content, date, userId) <> (Post.tupled, Post.unapply)

    def id = column[Int]("id", O.AutoInc, O.PrimaryKey)

    def content = column[String]("content", O.Length(280, varying = true))

    def date = column[Option[java.sql.Timestamp]]("date")

    def userId = column[Int]("user_id")

    def userFk = foreignKey("fk_Post_User", userId, users)(_.id, onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Cascade)

  }

  val posts = TableQuery[Posts]

}
