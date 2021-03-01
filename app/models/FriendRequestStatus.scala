package models

object FriendRequestStatus extends Enumeration{
  val pending: FriendRequestStatus.Value = Value("PENDING")
  val accepted: FriendRequestStatus.Value = Value("ACCEPTED")
  val rejected: FriendRequestStatus.Value = Value("REJECTED")
}
