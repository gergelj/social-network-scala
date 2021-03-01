package repository

import config.Db
import models.User
import models.tables.UsersTable
import org.mindrot.jbcrypt.BCrypt
import slick.basic.DatabaseConfig
import slick.dbio.DBIOAction
import slick.jdbc.JdbcProfile

import scala.concurrent.Future

class UsersRepository (val config: DatabaseConfig[JdbcProfile]) extends Db with UsersTable {
  import config.profile.api._
  import scala.concurrent.ExecutionContext.Implicits.global

  private val defaultAvatar = "images/avatar.png"

  def init(): Future[Unit] = db.run(DBIOAction.seq(users.schema.create))
  def drop(): Future[Unit] = db.run(DBIOAction.seq(users.schema.drop))

  def getUser(username: String): Future[Option[User]] =
    db.run(users.filter(_.username === username).result.headOption)

  def getUser(id: Int): Future[Option[User]] =
    db.run(users.filter(_.id === id).result.headOption)

  def createUser(username: String, password: String, firstName: String, lastName: String): Future[Option[Int]] = {
    val matches = db.run(users.filter(_.username === username).result)
    matches.flatMap{userRows =>
      if(userRows.isEmpty){
        db.run(users += User(-1, username, BCrypt.hashpw(password, BCrypt.gensalt()), firstName, lastName, defaultAvatar))
          .flatMap(addCount =>
            if(addCount > 0)
              db.run(users.filter(_.username === username).result)
                .map(_.headOption.map(_.id))
            else Future.successful(None))
      } else
        Future.successful(None)
    }
  }



}
