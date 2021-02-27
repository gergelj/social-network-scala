package models

import slick.jdbc.MySQLProfile.api._
import scala.concurrent.ExecutionContext
import tables.Tables._
import org.mindrot.jbcrypt.BCrypt
import scala.concurrent.Future
import dto.UserDTO

class UserModel(db: Database)(implicit ec: ExecutionContext) {

    private val defaultAvatar = "images/avatar.png"

    def createUser(username: String, password: String, firstName: String, lastName: String): Future[Option[Int]] = {
        val matches = db.run(User.filter(userRow => userRow.username === username).result)
        matches.flatMap { userRows =>
            if(userRows.isEmpty){
                db.run(User += UserRow(-1, username, BCrypt.hashpw(password, BCrypt.gensalt()), Option(firstName), Option(lastName), Option(null)))
                .flatMap {addCount =>
                    if(addCount > 0) db.run(User.filter(userRow => userRow.username === username).result)
                        .map(_.headOption.map(_.id))
                    else Future.successful(None) 
                }
            } else Future.successful(None) 
        }
    }

    def getFriends(userId: Int): Future[Seq[UserDTO]] = {
        db.run(
            (for{
            userFriends <- Friends if userFriends.userId1 === userId
            friend <- User if friend.id === userFriends.userId2
            } yield {
                friend
            }).result
        ).map(friends => friends.map(friend => UserDTO(friend.username, friend.firstname.getOrElse(""), friend.lastname.getOrElse(""), friend.profilepicture.getOrElse(defaultAvatar))))
    }
}
