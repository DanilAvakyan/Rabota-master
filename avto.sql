-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Фев 04 2023 г., 15:18
-- Версия сервера: 5.6.51
-- Версия PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `UchPR`
--

-- --------------------------------------------------------

--
-- Структура таблицы `avto`
--

CREATE TABLE `avto` (
  `id` bigint(20) NOT NULL,
  `carbrand` varchar(255) DEFAULT NULL,
  `carsmodel` varchar(255) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `year_release` varchar(255) DEFAULT NULL,
  `engine_id` bigint(20) DEFAULT NULL,
  `color_id` bigint(20) DEFAULT NULL,
  `kpp_id` bigint(20) DEFAULT NULL,
  `vin` varchar(17) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `avto`
--

INSERT INTO `avto` (`id`, `carbrand`, `carsmodel`, `color`, `year_release`, `engine_id`, `color_id`, `kpp_id`, `vin`) VALUES
(28, 'BMW', 'm5', NULL, '2019', 27, 26, 25, 'WAUZZZ44ZEN096063'),
(38, 'Audi', 'A6', NULL, '2020', 27, 26, 25, 'WAUZZZ44ZEN096063'),
(67, 'Audi', 'rs3', NULL, '2019', 27, 29, 25, 'WAUZZZ44ZEN096063');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `avto`
--
ALTER TABLE `avto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4ghdgjib6ax1ldibuy8k00i5x` (`engine_id`),
  ADD KEY `FKo36schix2pbcfawkpch2ppbgj` (`color_id`),
  ADD KEY `FK6022k6bv1psusrgx35k9vambf` (`kpp_id`);

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `avto`
--
ALTER TABLE `avto`
  ADD CONSTRAINT `FK4ghdgjib6ax1ldibuy8k00i5x` FOREIGN KEY (`engine_id`) REFERENCES `engine` (`id`),
  ADD CONSTRAINT `FK6022k6bv1psusrgx35k9vambf` FOREIGN KEY (`kpp_id`) REFERENCES `kpp` (`id`),
  ADD CONSTRAINT `FKo36schix2pbcfawkpch2ppbgj` FOREIGN KEY (`color_id`) REFERENCES `color` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
