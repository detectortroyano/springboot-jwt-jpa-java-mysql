-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-06-2019 a las 05:52:02
-- Versión del servidor: 10.1.37-MariaDB
-- Versión de PHP: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `db_login`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `email_verification_token`
--

CREATE TABLE `email_verification_token` (
  `token_id` bigint(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `expiry_dt` datetime NOT NULL,
  `token` varchar(255) NOT NULL,
  `token_status` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1),
(1),
(1),
(1),
(1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `password_reset_token`
--

CREATE TABLE `password_reset_token` (
  `token_id` bigint(20) NOT NULL,
  `expiry_dt` datetime NOT NULL,
  `token_name` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `refresh_token`
--

CREATE TABLE `refresh_token` (
  `token_id` bigint(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `expiry_dt` datetime NOT NULL,
  `refresh_count` bigint(20) DEFAULT NULL,
  `token` varchar(255) NOT NULL,
  `user_device_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `role`
--

CREATE TABLE `role` (
  `role_id` bigint(20) NOT NULL,
  `role_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `role`
--

INSERT INTO `role` (`role_id`, `role_name`) VALUES
(2, 'ROLE_ADMIN'),
(1, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `is_active` bit(1) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `is_email_verified` bit(1) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_authority`
--

CREATE TABLE `user_authority` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_device`
--

CREATE TABLE `user_device` (
  `user_device_id` bigint(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `device_id` varchar(255) NOT NULL,
  `device_type` varchar(255) DEFAULT NULL,
  `is_refresh_active` bit(1) DEFAULT NULL,
  `notification_token` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `email_verification_token`
--
ALTER TABLE `email_verification_token`
  ADD PRIMARY KEY (`token_id`),
  ADD UNIQUE KEY `UK_idu2ippaks8bn6vcsq62khvdu` (`token`),
  ADD KEY `FKqmvt3qcly3hbvde97srchdo3x` (`user_id`);

--
-- Indices de la tabla `password_reset_token`
--
ALTER TABLE `password_reset_token`
  ADD PRIMARY KEY (`token_id`),
  ADD UNIQUE KEY `UK_p4ksw3dweftlkig5mtd3onr9a` (`token_name`),
  ADD KEY `FK5lwtbncug84d4ero33v3cfxvl` (`user_id`);

--
-- Indices de la tabla `refresh_token`
--
ALTER TABLE `refresh_token`
  ADD PRIMARY KEY (`token_id`),
  ADD UNIQUE KEY `UK_8ogx3ejsbfbf2xsgl4758otrm` (`user_device_id`),
  ADD UNIQUE KEY `UK_r4k4edos30bx9neoq81mdvwph` (`token`);

--
-- Indices de la tabla `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`),
  ADD UNIQUE KEY `UK_epk9im9l9q67xmwi4hbed25do` (`role_name`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  ADD UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`);

--
-- Indices de la tabla `user_authority`
--
ALTER TABLE `user_authority`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKash3fy9hdayq3o73fir11n3th` (`role_id`);

--
-- Indices de la tabla `user_device`
--
ALTER TABLE `user_device`
  ADD PRIMARY KEY (`user_device_id`),
  ADD KEY `FKd2lb0k09c4nnfpvku8r61g92n` (`user_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `role`
--
ALTER TABLE `role`
  MODIFY `role_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `email_verification_token`
--
ALTER TABLE `email_verification_token`
  ADD CONSTRAINT `FKqmvt3qcly3hbvde97srchdo3x` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Filtros para la tabla `password_reset_token`
--
ALTER TABLE `password_reset_token`
  ADD CONSTRAINT `FK5lwtbncug84d4ero33v3cfxvl` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Filtros para la tabla `refresh_token`
--
ALTER TABLE `refresh_token`
  ADD CONSTRAINT `FKr92opronarwe7pn1c41621grv` FOREIGN KEY (`user_device_id`) REFERENCES `user_device` (`user_device_id`);

--
-- Filtros para la tabla `user_authority`
--
ALTER TABLE `user_authority`
  ADD CONSTRAINT `FKash3fy9hdayq3o73fir11n3th` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  ADD CONSTRAINT `FKpqlsjpkybgos9w2svcri7j8xy` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Filtros para la tabla `user_device`
--
ALTER TABLE `user_device`
  ADD CONSTRAINT `FKd2lb0k09c4nnfpvku8r61g92n` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
