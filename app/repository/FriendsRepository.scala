package repository

import config.Db
import models.{Friend, User}
import models.tables.FriendsTable
import slick.basic.DatabaseConfig
import slick.dbio.DBIOAction
import slick.jdbc.JdbcProfile

import scala.concurrent.Future

class FriendsRepository (val config: DatabaseConfig[JdbcProfile]) extends Db with FriendsTable{
  import config.profile.api._
  import scala.concurrent.ExecutionContext.Implicits.global

  def init(): Future[Unit] = db.run(DBIOAction.seq(friends.schema.create))
  def drop(): Future[Unit] = db.run(DBIOAction.seq(friends.schema.drop))

  def getFriends(userId: Int): Future[Seq[User]] = {
    db.run(
      (for{
        userFriends <- friends if userFriends.userId1 === userId
        friend <- users if friend.id === userFriends.userId2
      } yield{
        friend
      }).result
    )
  }

  private def addFriend(userId1: Int, userId2: Int): Future[Option[Boolean]] = {
    db.run(friends ++= Seq(Friend(userId1, userId2), Friend(userId2, userId1)))
      .map(result => result.map(r => r == 2))
  }

}
