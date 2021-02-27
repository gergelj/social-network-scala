package tables
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.MySQLProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Friendrequest.schema ++ Friends.schema ++ Likes.schema ++ Post.schema ++ User.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Friendrequest
   *  @param id Database column id SqlType(INT), AutoInc, PrimaryKey
   *  @param date Database column date SqlType(TIMESTAMP), Default(None)
   *  @param status Database column status SqlType(ENUM), Length(8,false), Default(None)
   *  @param senderId Database column sender_id SqlType(INT)
   *  @param receiverId Database column receiver_id SqlType(INT) */
  case class FriendrequestRow(id: Int, date: Option[java.sql.Timestamp] = None, status: Option[String] = None, senderId: Int, receiverId: Int)
  /** GetResult implicit for fetching FriendrequestRow objects using plain SQL queries */
  implicit def GetResultFriendrequestRow(implicit e0: GR[Int], e1: GR[Option[java.sql.Timestamp]], e2: GR[Option[String]]): GR[FriendrequestRow] = GR{
    prs => import prs._
    FriendrequestRow.tupled((<<[Int], <<?[java.sql.Timestamp], <<?[String], <<[Int], <<[Int]))
  }
  /** Table description of table friendrequest. Objects of this class serve as prototypes for rows in queries. */
  class Friendrequest(_tableTag: Tag) extends profile.api.Table[FriendrequestRow](_tableTag, Some("scalatest"), "friendrequest") {
    def * = (id, date, status, senderId, receiverId) <> (FriendrequestRow.tupled, FriendrequestRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), date, status, Rep.Some(senderId), Rep.Some(receiverId))).shaped.<>({r=>import r._; _1.map(_=> FriendrequestRow.tupled((_1.get, _2, _3, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column date SqlType(TIMESTAMP), Default(None) */
    val date: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date", O.Default(None))
    /** Database column status SqlType(ENUM), Length(8,false), Default(None) */
    val status: Rep[Option[String]] = column[Option[String]]("status", O.Length(8,varying=false), O.Default(None))
    /** Database column sender_id SqlType(INT) */
    val senderId: Rep[Int] = column[Int]("sender_id")
    /** Database column receiver_id SqlType(INT) */
    val receiverId: Rep[Int] = column[Int]("receiver_id")

    /** Foreign key referencing User (database name fk_FriendRequest_User1) */
    lazy val userFk1 = foreignKey("fk_FriendRequest_User1", senderId, User)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing User (database name fk_FriendRequest_User2) */
    lazy val userFk2 = foreignKey("fk_FriendRequest_User2", receiverId, User)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Friendrequest */
  lazy val Friendrequest = new TableQuery(tag => new Friendrequest(tag))

  /** Entity class storing rows of table Friends
   *  @param userId1 Database column user_id1 SqlType(INT)
   *  @param userId2 Database column user_id2 SqlType(INT) */
  case class FriendsRow(userId1: Int, userId2: Int)
  /** GetResult implicit for fetching FriendsRow objects using plain SQL queries */
  implicit def GetResultFriendsRow(implicit e0: GR[Int]): GR[FriendsRow] = GR{
    prs => import prs._
    FriendsRow.tupled((<<[Int], <<[Int]))
  }
  /** Table description of table friends. Objects of this class serve as prototypes for rows in queries. */
  class Friends(_tableTag: Tag) extends profile.api.Table[FriendsRow](_tableTag, Some("scalatest"), "friends") {
    def * = (userId1, userId2) <> (FriendsRow.tupled, FriendsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(userId1), Rep.Some(userId2))).shaped.<>({r=>import r._; _1.map(_=> FriendsRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column user_id1 SqlType(INT) */
    val userId1: Rep[Int] = column[Int]("user_id1")
    /** Database column user_id2 SqlType(INT) */
    val userId2: Rep[Int] = column[Int]("user_id2")

    /** Primary key of Friends (database name friends_PK) */
    val pk = primaryKey("friends_PK", (userId1, userId2))

    /** Foreign key referencing User (database name fk_User_has_User_User1) */
    lazy val userFk1 = foreignKey("fk_User_has_User_User1", userId1, User)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing User (database name fk_User_has_User_User2) */
    lazy val userFk2 = foreignKey("fk_User_has_User_User2", userId2, User)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Friends */
  lazy val Friends = new TableQuery(tag => new Friends(tag))

  /** Entity class storing rows of table Likes
   *  @param postId Database column post_id SqlType(INT)
   *  @param userId Database column user_id SqlType(INT) */
  case class LikesRow(postId: Int, userId: Int)
  /** GetResult implicit for fetching LikesRow objects using plain SQL queries */
  implicit def GetResultLikesRow(implicit e0: GR[Int]): GR[LikesRow] = GR{
    prs => import prs._
    LikesRow.tupled((<<[Int], <<[Int]))
  }
  /** Table description of table likes. Objects of this class serve as prototypes for rows in queries. */
  class Likes(_tableTag: Tag) extends profile.api.Table[LikesRow](_tableTag, Some("scalatest"), "likes") {
    def * = (postId, userId) <> (LikesRow.tupled, LikesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(postId), Rep.Some(userId))).shaped.<>({r=>import r._; _1.map(_=> LikesRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column post_id SqlType(INT) */
    val postId: Rep[Int] = column[Int]("post_id")
    /** Database column user_id SqlType(INT) */
    val userId: Rep[Int] = column[Int]("user_id")

    /** Primary key of Likes (database name likes_PK) */
    val pk = primaryKey("likes_PK", (postId, userId))

    /** Foreign key referencing Post (database name fk_Post_has_User_Post1) */
    lazy val postFk = foreignKey("fk_Post_has_User_Post1", postId, Post)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing User (database name fk_Post_has_User_User1) */
    lazy val userFk = foreignKey("fk_Post_has_User_User1", userId, User)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Likes */
  lazy val Likes = new TableQuery(tag => new Likes(tag))

  /** Entity class storing rows of table Post
   *  @param id Database column id SqlType(INT), AutoInc, PrimaryKey
   *  @param content Database column content SqlType(VARCHAR), Length(280,true)
   *  @param date Database column date SqlType(TIMESTAMP)
   *  @param likes Database column likes SqlType(INT), Default(None)
   *  @param userId Database column user_id SqlType(INT) */
  case class PostRow(id: Int, content: String, date: Option[java.sql.Timestamp], likes: Option[Int] = None, userId: Int)
  /** GetResult implicit for fetching PostRow objects using plain SQL queries */
  implicit def GetResultPostRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[java.sql.Timestamp]], e3: GR[Option[Int]]): GR[PostRow] = GR{
    prs => import prs._
    PostRow.tupled((<<[Int], <<[String], <<?[java.sql.Timestamp], <<?[Int], <<[Int]))
  }
  /** Table description of table post. Objects of this class serve as prototypes for rows in queries. */
  class Post(_tableTag: Tag) extends profile.api.Table[PostRow](_tableTag, Some("scalatest"), "post") {
    def * = (id, content, date, likes, userId) <> (PostRow.tupled, PostRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(content), date, likes, Rep.Some(userId))).shaped.<>({r=>import r._; _1.map(_=> PostRow.tupled((_1.get, _2.get, _3, _4, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column content SqlType(VARCHAR), Length(280,true) */
    val content: Rep[String] = column[String]("content", O.Length(280,varying=true))
    /** Database column date SqlType(TIMESTAMP) */
    val date: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date")
    /** Database column likes SqlType(INT), Default(None) */
    val likes: Rep[Option[Int]] = column[Option[Int]]("likes", O.Default(None))
    /** Database column user_id SqlType(INT) */
    val userId: Rep[Int] = column[Int]("user_id")

    /** Foreign key referencing User (database name fk_Post_User) */
    lazy val userFk = foreignKey("fk_Post_User", userId, User)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Post */
  lazy val Post = new TableQuery(tag => new Post(tag))

  /** Entity class storing rows of table User
   *  @param id Database column id SqlType(INT), AutoInc, PrimaryKey
   *  @param username Database column username SqlType(VARCHAR), Length(50,true)
   *  @param password Database column password SqlType(VARCHAR), Length(100,true)
   *  @param firstname Database column firstName SqlType(VARCHAR), Length(50,true), Default(None)
   *  @param lastname Database column lastName SqlType(VARCHAR), Length(100,true), Default(None)
   *  @param profilepicture Database column profilePicture SqlType(VARCHAR), Length(150,true), Default(None) */
  case class UserRow(id: Int, username: String, password: String, firstname: Option[String] = None, lastname: Option[String] = None, profilepicture: Option[String] = None)
  /** GetResult implicit for fetching UserRow objects using plain SQL queries */
  implicit def GetResultUserRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[String]]): GR[UserRow] = GR{
    prs => import prs._
    UserRow.tupled((<<[Int], <<[String], <<[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table user. Objects of this class serve as prototypes for rows in queries. */
  class User(_tableTag: Tag) extends profile.api.Table[UserRow](_tableTag, Some("scalatest"), "user") {
    def * = (id, username, password, firstname, lastname, profilepicture) <> (UserRow.tupled, UserRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(username), Rep.Some(password), firstname, lastname, profilepicture)).shaped.<>({r=>import r._; _1.map(_=> UserRow.tupled((_1.get, _2.get, _3.get, _4, _5, _6)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column username SqlType(VARCHAR), Length(50,true) */
    val username: Rep[String] = column[String]("username", O.Length(50,varying=true))
    /** Database column password SqlType(VARCHAR), Length(100,true) */
    val password: Rep[String] = column[String]("password", O.Length(100,varying=true))
    /** Database column firstName SqlType(VARCHAR), Length(50,true), Default(None) */
    val firstname: Rep[Option[String]] = column[Option[String]]("firstName", O.Length(50,varying=true), O.Default(None))
    /** Database column lastName SqlType(VARCHAR), Length(100,true), Default(None) */
    val lastname: Rep[Option[String]] = column[Option[String]]("lastName", O.Length(100,varying=true), O.Default(None))
    /** Database column profilePicture SqlType(VARCHAR), Length(150,true), Default(None) */
    val profilepicture: Rep[Option[String]] = column[Option[String]]("profilePicture", O.Length(150,varying=true), O.Default(None))

    /** Uniqueness Index over (username) (database name username_UNIQUE) */
    val index1 = index("username_UNIQUE", username, unique=true)
  }
  /** Collection-like TableQuery object for table User */
  lazy val User = new TableQuery(tag => new User(tag))
}
