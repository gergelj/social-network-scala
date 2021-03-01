package models

import java.time.LocalDateTime

case class FriendRequest(id: Int, date: Option[java.sql.Timestamp] = Option(java.sql.Timestamp.valueOf(LocalDateTime.now())), status: String = FriendRequestStatus.pending.toString, senderId: Int, receiverId: Int)
