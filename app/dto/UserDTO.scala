package dto

import play.api.libs.json.{Json, OFormat}

case class UserDTO(username: String, firstName: String, lastName: String, profilePicture: String)

object UserDTO{
    implicit val format: OFormat[UserDTO] = Json.format[UserDTO]
}