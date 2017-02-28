INSERT INTO `Lyubin`.`role` (`id`, `name`) VALUES ('1', 'ADMIN');
INSERT INTO `Lyubin`.`role` (`id`, `name`) VALUES ('2', 'USER');
INSERT INTO `Lyubin`.`role` (`id`, `name`) VALUES ('3', 'MODERATOR');

INSERT INTO `Lyubin`.`theme` (`id`, `name`, `code` ) VALUES ('1', 'theme.light.name', 'light');
INSERT INTO `Lyubin`.`theme` (`id`, `name`, `code` ) VALUES ('2', 'theme.dark.name', 'dark');

INSERT INTO `Lyubin`.`language` (`id`, `name`, `code` ) VALUES ('1', 'English', 'en');
INSERT INTO `Lyubin`.`language` (`id`, `name`, `code` ) VALUES ('2', 'Русский', 'ru');

CREATE TABLE persistent_logins (
    username varchar(64) not null,
    series varchar(64) not null,
    token varchar(64) not null,
    last_used timestamp not null,
    PRIMARY KEY (series)
);
