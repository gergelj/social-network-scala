package models.tables

import config.Db
import models.Like

trait LikesTable extends PostsTable {
  this: Db =>

  import config.profile.api._

  class Likes(tag: Tag) extends Table[Like](tag, "likes") {
    def * = (postId, userId) <> (Like.tupled, Like.unapply)

    def postId = column[Int]("post_id")

    def userId = column[Int]("user_id")

    def pk = primaryKey("likes_PK", (postId, userId))

    def postFk = foreignKey("fk_Post_has_User_Post1", postId, posts)(_.id, onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Cascade)

    def userFk = foreignKey("fk_Post_has_User_User1", userId, users)(_.id, onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Cascade)
  }

  val likes = TableQuery[Likes]

}
