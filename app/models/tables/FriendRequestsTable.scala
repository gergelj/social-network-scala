package models.tables

import config.Db
import models.{FriendRequest, FriendRequestStatus}

import java.time.LocalDateTime

trait FriendRequestsTable extends UsersTable {
  this: Db =>

  import config.profile.api._

  class FriendRequests(tag: Tag) extends Table[FriendRequest](tag, "friendrequest") {
    def * = (id, date, status, senderId, receiverId) <> (FriendRequest.tupled, FriendRequest.unapply)

    def id = column[Int]("id", O.AutoInc, O.PrimaryKey)

    def date = column[Option[java.sql.Timestamp]]("date", O.Default(Option(java.sql.Timestamp.valueOf(LocalDateTime.now()))))

    def status = column[String]("status", O.Length(8, varying = false), O.Default(FriendRequestStatus.pending.toString))

    def senderId = column[Int]("sender_id")

    def receiverId = column[Int]("receiver_id")

    def userFk1 = foreignKey("fk_FriendRequest_User1", senderId, users)(_.id, onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.Cascade)

    def userFk2 = foreignKey("fk_FriendRequest_User2", receiverId, users)(_.id, onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.Cascade)
  }

  val friendRequests = TableQuery[FriendRequests]

}
