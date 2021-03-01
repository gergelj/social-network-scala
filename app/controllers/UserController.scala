package controllers

import config.DbConfiguration
import play.api.mvc.{AbstractController, AnyContent, BaseController, ControllerComponents, Request}
import repository.UsersRepository

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext

@Singleton
class UserController @Inject()(val cc: ControllerComponents)(implicit ec: ExecutionContext) extends AbstractController(cc) with DbConfiguration{

  private val userRepository = new UsersRepository(config)

  def createUser() = Action.async {implicit request: Request[AnyContent] =>
    userRepository.createUser("janos", "00000000", "Janos", "Janosi")
      .map {
        case Some(id) => Ok("User id = " + id)
        case None => Conflict("Username taken")
      }
  }

}
