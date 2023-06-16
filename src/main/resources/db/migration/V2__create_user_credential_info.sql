
CREATE TABLE if not exists `user_credential`(
                                      `users_creden_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                      `user_name` CHAR(255) NOT NULL,
                                      `pass_word` CHAR(255) NOT NULL,
                                      `user_type_id` INT
);

ALTER TABLE
    `user_credential` ADD CONSTRAINT `fk_2_user_type_id` FOREIGN KEY(`user_type_id`) REFERENCES `user_type`(`user_type_id`);
