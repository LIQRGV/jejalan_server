SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";
CREATE DATABASE IF NOT EXISTS `jejalan` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `jejalan`;

DROP TABLE IF EXISTS `city_list`;
CREATE TABLE IF NOT EXISTS `city_list` (
`id` int(11) NOT NULL,
  `city_name` varchar(32) NOT NULL,
  `region` int(11) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT='daftar kota dan hubungannya terhadap wilayah' AUTO_INCREMENT=5 ;

DROP TABLE IF EXISTS `comment`;
CREATE TABLE IF NOT EXISTS `comment` (
`id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `content` varchar(256) NOT NULL,
  `revisionOf` int(11) DEFAULT NULL,
  `removed` tinyint(1) NOT NULL,
  `date_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

DROP TABLE IF EXISTS `post`;
CREATE TABLE IF NOT EXISTS `post` (
`id` int(11) NOT NULL,
  `title` varchar(32) NOT NULL,
  `creator` int(11) NOT NULL,
  `city` int(11) NOT NULL,
  `content` text NOT NULL,
  `hit` int(11) NOT NULL DEFAULT '0',
  `revision_Of` int(11) DEFAULT NULL,
  `removed` tinyint(1) NOT NULL,
  `date_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

DROP TABLE IF EXISTS `posts_tag`;
CREATE TABLE IF NOT EXISTS `posts_tag` (
  `post_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `region`;
CREATE TABLE IF NOT EXISTS `region` (
`id` int(11) NOT NULL,
  `nama_region` varchar(32) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

DROP TABLE IF EXISTS `socmed`;
CREATE TABLE IF NOT EXISTS `socmed` (
`id` int(11) NOT NULL,
  `nama_socmed` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

DROP TABLE IF EXISTS `tag`;
CREATE TABLE IF NOT EXISTS `tag` (
`id` int(11) NOT NULL,
  `title` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
`id` int(11) NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `complete_name` varchar(128) NOT NULL,
  `region` int(11) NOT NULL,
  `email` varchar(128) NOT NULL,
  `HP` varchar(16) NOT NULL,
  `profilePicture` varchar(64) NOT NULL,
  `isActive` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

DROP TABLE IF EXISTS `user_socmed`;
CREATE TABLE IF NOT EXISTS `user_socmed` (
  `id_user` int(11) NOT NULL,
  `id_socmed` int(11) NOT NULL,
  `value` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


ALTER TABLE `city_list`
 ADD PRIMARY KEY (`id`), ADD KEY `region` (`region`);

ALTER TABLE `comment`
 ADD PRIMARY KEY (`id`), ADD KEY `post_id` (`post_id`), ADD KEY `user_id` (`user_id`);

ALTER TABLE `post`
 ADD PRIMARY KEY (`id`), ADD KEY `creator` (`creator`), ADD KEY `city` (`city`);

ALTER TABLE `posts_tag`
 ADD KEY `post_id` (`post_id`), ADD KEY `tag_id` (`tag_id`);

ALTER TABLE `region`
 ADD PRIMARY KEY (`id`);

ALTER TABLE `socmed`
 ADD PRIMARY KEY (`id`);

ALTER TABLE `tag`
 ADD PRIMARY KEY (`id`);

ALTER TABLE `user`
 ADD PRIMARY KEY (`id`), ADD KEY `region` (`region`);

ALTER TABLE `user_socmed`
 ADD KEY `id_user` (`id_user`), ADD KEY `id_socmed` (`id_socmed`);


ALTER TABLE `city_list`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
ALTER TABLE `comment`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `post`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
ALTER TABLE `region`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
ALTER TABLE `socmed`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `tag`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `user`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;

ALTER TABLE `city_list`
ADD CONSTRAINT `city_list_ibfk_1` FOREIGN KEY (`region`) REFERENCES `region` (`id`);

ALTER TABLE `comment`
ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
ADD CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `post`
ADD CONSTRAINT `post_ibfk_1` FOREIGN KEY (`creator`) REFERENCES `user` (`id`),
ADD CONSTRAINT `post_ibfk_2` FOREIGN KEY (`city`) REFERENCES `city_list` (`id`);

ALTER TABLE `posts_tag`
ADD CONSTRAINT `posts_tag_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
ADD CONSTRAINT `posts_tag_ibfk_2` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`);

ALTER TABLE `user`
ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`region`) REFERENCES `region` (`id`);

ALTER TABLE `user_socmed`
ADD CONSTRAINT `user_socmed_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`),
ADD CONSTRAINT `user_socmed_ibfk_2` FOREIGN KEY (`id_socmed`) REFERENCES `socmed` (`id`);
