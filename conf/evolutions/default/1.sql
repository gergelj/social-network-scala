--- !Ups

CREATE TABLE IF NOT EXISTS `User` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(50) NOT NULL,
    `password` VARCHAR(100) NOT NULL,
    `firstName` VARCHAR(50) NULL,
    `lastName` VARCHAR(100) NULL,
    `profilePicture` VARCHAR(150) NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE)
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `Post` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `content` VARCHAR(280) NOT NULL,
    `date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `user_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_Post_User_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_Post_User`
    FOREIGN KEY (`user_id`)
    REFERENCES `User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `Likes` (
    `post_id` INT NOT NULL,
    `user_id` INT NOT NULL,
    PRIMARY KEY (`post_id`, `user_id`),
    INDEX `fk_Post_has_User_User1_idx` (`user_id` ASC) VISIBLE,
    INDEX `fk_Post_has_User_Post1_idx` (`post_id` ASC) VISIBLE,
    CONSTRAINT `fk_Post_has_User_Post1`
    FOREIGN KEY (`post_id`)
    REFERENCES `Post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_Post_has_User_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `Friends` (
    `user_id1` INT NOT NULL,
    `user_id2` INT NOT NULL,
    PRIMARY KEY (`user_id1`, `user_id2`),
    INDEX `fk_User_has_User_User2_idx` (`user_id2` ASC) VISIBLE,
    INDEX `fk_User_has_User_User1_idx` (`user_id1` ASC) VISIBLE,
    CONSTRAINT `fk_User_has_User_User1`
    FOREIGN KEY (`user_id1`)
    REFERENCES `User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_User_has_User_User2`
    FOREIGN KEY (`user_id2`)
    REFERENCES `User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `FriendRequest` (
   `id` INT NOT NULL AUTO_INCREMENT,
   `date` TIMESTAMP NULL,
   `status` ENUM('PENDING', 'ACCEPTED', 'REJECTED') NULL,
    `sender_id` INT NOT NULL,
    `receiver_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_FriendRequest_User1_idx` (`sender_id` ASC) VISIBLE,
    INDEX `fk_FriendRequest_User2_idx` (`receiver_id` ASC) VISIBLE,
    CONSTRAINT `fk_FriendRequest_User1`
    FOREIGN KEY (`sender_id`)
    REFERENCES `User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_FriendRequest_User2`
    FOREIGN KEY (`receiver_id`)
    REFERENCES `User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;

--- !Downs

DROP TABLE `FriendRequest`;
DROP TABLE `Friends`;
DROP TABLE `Likes`;
DROP TABLE `Post`;
DROP TABLE `User`;