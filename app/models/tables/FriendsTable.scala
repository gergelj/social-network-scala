package models.tables

import config.Db
import models.Friend

trait FriendsTable extends UsersTable {
  this: Db =>

  import config.profile.api._

  class Friends(tag: Tag) extends Table[Friend](tag, "friends") {
    def * = (userId1, userId2) <> (Friend.tupled, Friend.unapply)

    def userId1 = column[Int]("user_id1")

    def userId2 = column[Int]("user_id2")

    def pk = primaryKey("friends_PK", (userId1, userId2))

    def userFk1 = foreignKey("fk_User_has_User_User1", userId1, users)(_.id, onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Cascade)

    def userFk2 = foreignKey("fk_User_has_User_User2", userId2, users)(_.id, onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Cascade)
  }

  val friends = TableQuery[Friends]

}
