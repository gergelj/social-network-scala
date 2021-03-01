package models.tables

import config.Db
import models.User

trait UsersTable {
  this: Db =>

  import config.profile.api._

  class Users(tag: Tag) extends Table[User](tag, "user") {
    def id = column[Int]("id", O.AutoInc, O.PrimaryKey)

    def username = column[String]("username", O.Length(50, varying = true))

    def password = column[String]("password", O.Length(100, varying = true))

    def firstName = column[String]("firstName", O.Length(50, varying = true))

    def lastName = column[String]("lastName", O.Length(100, varying = true))

    def profilePicture = column[String]("profilePicture", O.Length(150, varying = true))

    def * = (id, username, password, firstName, lastName, profilePicture) <> (User.tupled, User.unapply)

    val index1 = index("username_UNIQUE", username, unique = true)
  }

  val users = TableQuery[Users]
}
