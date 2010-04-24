-- phpMyAdmin SQL Dump
-- version 3.2.0.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 10, 2010 at 04:34 PM
-- Server version: 5.1.36
-- PHP Version: 5.3.0


--
-- Database: 'db_rest_fel'
--

-- --------------------------------------------------------

--
-- Table structure for table 'account'
--



--
-- Dumping data for table 'account'
--

INSERT INTO account (accountID, tableID, accountStatusTypeID, isDeleted,userId, name, discountTypeId) VALUES
(2, 2, 2, 0, 2, 'prvniUcet', NULL),
(3, 3, 2, 0, 2, 'druhyUcet', NULL),
(4, 3, 1, 0, 2, 'ucet na baru', NULL),
(5, NULL, 2, 0, NULL, 'bar', NULL),
(7, 1, 2, 0, 1, 'ucetUcet', 1),
(17, NULL, 3, 0, NULL, 'dalsiUcet', NULL),
(10, NULL, 2, 0, 4, 'posledni pokus', 2),
(18, 1, 2, 0, 1, 'sdhajksdha', 1),
(19, 3, 2, 0, NULL, 'jdasjbjk', NULL),
(20, 1, 1, 0, 1, 'ucet', 1);

-- --------------------------------------------------------

--
-- Table structure for table 'accountstatustype'
--

--
-- Dumping data for table 'accountstatustype'
--

INSERT INTO accountstatustype (accountStatusTypeID, name, note, isDeleted) VALUES
(1, 'zaplaceno', 'aaa', 0),
(2, 'nezaplaceno', 'bbb', 0),
(3, 'nemusi platit', '', 0),
(4, 'test', 'test', 0);

-- --------------------------------------------------------

--
-- Table structure for table 'depreciation'
--


--
-- Dumping data for table 'depreciation'
--


-- --------------------------------------------------------

--
-- Table structure for table 'discount'
--

--
-- Dumping data for table 'discount'
--


-- --------------------------------------------------------

--
-- Table structure for table 'discounttype'
--


-- Dumping data for table 'discounttype'
--

INSERT INTO discounttype (discountTypeID, name, isDeleted) VALUES
(1, '50%', 0),
(2, 'zadarmo', 0),
(4, 'Sekera', 0);

-- --------------------------------------------------------

--
-- Table structure for table 'expenditure'
--

--
-- Dumping data for table 'expenditure'
--

INSERT INTO expenditure (expenditureID, materialID, quantity, userId, date, note, isDeleted) VALUES
(1, 305, 1011, 1, '2009-12-08 17:38:12', 'Zrcadlo', 1),
(2, 308, 9963, 1, '2009-12-08 17:48:37', 'Zrcadlo', 1),
(3, 308, 100, 1, '2009-12-08 17:51:41', 'Zrcadlo', 1),
(4, 266, 0, 1, '2009-12-08 17:52:07', 'Zrcadlo', 1),
(5, 303, 103148, 1, '2009-12-08 17:57:14', 'Zrcadlo', 1),
(6, 305, 29, 1, '2009-12-08 17:57:41', 'Zrcadlo', 1),
(7, 313, 350, 1, '2009-12-08 20:16:31', 'Zrcadlo', 1),
(8, 312, 860, 1, '2009-12-08 20:16:31', 'Zrcadlo', 1),
(9, 308, 61, 1, '2009-12-08 20:16:31', 'Zrcadlo', 1),
(10, 305, 11, 1, '2009-12-08 20:16:31', 'Zrcadlo', 1),
(11, 318, 23, 1, '2009-12-08 20:21:10', 'Zrcadlo', 1),
(12, 316, 36124, 1, '2009-12-08 20:21:10', 'Zrcadlo', 1),
(13, 317, 39, 1, '2009-12-08 20:21:10', 'Zrcadlo', 1),
(14, 312, 43241, 1, '2009-12-08 20:57:36', 'Zrcadlo', 1),
(15, 303, 20463, 1, '2009-12-08 20:57:36', 'Zrcadlo', 1),
(16, 305, 2266, 1, '2009-12-08 20:57:36', 'Zrcadlo', 1),
(17, 301, 1403, 1, '2009-12-08 20:57:36', 'Zrcadlo', 1),
(18, 314, 8060, 1, '2009-12-08 21:01:47', 'Zrcadlo', 1),
(19, 316, 3900, 1, '2009-12-08 21:01:47', 'Zrcadlo', 1),
(20, 315, 3214, 1, '2009-12-08 21:01:47', 'Zrcadlo', 1),
(21, 318, 86720, 1, '2009-12-08 21:01:47', 'Zrcadlo', 1),
(22, 305, 563, 1, '2009-12-08 21:01:47', 'Zrcadlo', 1),
(23, 305, 84, 1, '2009-12-09 16:06:29', 'Zrcadlo', 1),
(24, 328, 957.159177456207, 1, '2009-12-16 09:53:02', 'Zrcadlo', 0),
(25, 325, 3147.19717757742, 1, '2009-12-16 11:02:52', 'Zrcadlo', 0),
(26, 331, 2547.72393538913, 1, '2009-12-16 11:02:52', 'Zrcadlo', 0),
(27, 344, 87, 1, '2009-12-17 22:57:02', 'Zrcadlo', 0),
(28, 331, 2969, 1, '2009-12-17 23:44:16', 'Zrcadlo', 0),
(29, 334, 341, 1, '2009-12-17 23:44:16', 'Zrcadlo', 0),
(30, 327, 1162, 1, '2009-12-17 23:44:16', 'Zrcadlo', 0),
(31, 326, 233, 1, '2009-12-17 23:44:16', 'Zrcadlo', 0),
(32, 324, 1661, 1, '2009-12-17 23:47:05', 'Zrcadlo', 0),
(33, 327, 1393, 1, '2009-12-17 23:47:05', 'Zrcadlo', 0),
(34, 328, 451, 1, '2009-12-17 23:47:05', 'Zrcadlo', 0),
(35, 332, 748, 1, '2009-12-17 23:47:05', 'Zrcadlo', 0),
(36, 336, 1613, 1, '2009-12-17 23:47:50', 'Zrcadlo', 0),
(37, 330, 1000, 1, '2009-12-17 23:48:38', 'Zrcadlo', 0),
(38, 345, 276, 1, '2009-12-18 12:03:03', 'Zrcadlo', 0),
(39, 344, 88, 1, '2009-12-18 12:03:03', 'Zrcadlo', 0);

-- --------------------------------------------------------

--
-- Table structure for table 'income'
--

--
-- Dumping data for table 'income'
--

INSERT INTO income (incomeID, materialID, quantity, price, userId, date, note, isDeleted) VALUES
(1, 3, 2000, 2000, 1, '2009-12-08 00:00:00', '', 1),
(2, 320, 20000, 2, 1, '2009-12-08 00:00:00', '', 1),
(3, 321, 2000, 2, 1, '2009-12-08 00:00:00', '', 1),
(4, 312, 3201, 2302, 1, '2009-12-08 00:00:00', '', 1),
(5, 308, 2032, 23, 1, '2009-12-08 00:00:00', '', 1),
(6, 305, 3000, 30, 1, '2009-12-08 00:00:00', '', 1),
(7, 303, 20403, 4030, 1, '2009-12-08 00:00:00', '', 1),
(8, 316, 4030, 3002, 1, '2009-12-08 00:00:00', '', 1),
(9, 315, 2000, 2, 1, '2009-12-08 00:00:00', '', 1),
(10, 313, 2000, 2, 1, '2009-12-08 00:00:00', '', 1),
(11, 312, 20000, 2, 1, '2009-12-08 00:00:00', '', 1),
(12, 318, 30000, 3, 1, '2009-12-08 00:00:00', '', 1),
(13, 308, 20000, 200, 1, '2009-12-08 00:00:00', '', 1),
(14, 314, 2000, 2, 1, '2009-12-08 00:00:00', '', 1),
(15, 301, 2000, 2, 1, '2009-12-08 00:00:00', '', 1),
(16, 315, 2000, 2, 1, '2009-12-08 00:00:00', '', 1),
(17, 313, 2000, 2, 1, '2009-12-08 00:00:00', '', 1),
(18, 312, 20000, 2, 1, '2009-12-08 00:00:00', '', 1),
(19, 318, 30000, 3, 1, '2009-12-08 00:00:00', '', 1),
(20, 308, 20000, 200, 1, '2009-12-08 00:00:00', '', 1),
(21, 314, 2000, 2, 1, '2009-12-08 00:00:00', '', 1),
(22, 301, 2000, 2, 1, '2009-12-08 00:00:00', '', 1),
(23, 315, 2000, 2, 1, '2009-12-08 00:00:00', '', 1),
(24, 313, 2000, 2, 1, '2009-12-08 00:00:00', '', 1),
(25, 312, 20000, 2, 1, '2009-12-08 00:00:00', '', 1),
(26, 318, 30000, 3, 1, '2009-12-08 00:00:00', '', 1),
(27, 308, 20000, 200, 1, '2009-12-08 00:00:00', '', 1),
(28, 314, 2000, 2, 1, '2009-12-08 00:00:00', '', 1),
(29, 301, 2000, 2, 1, '2009-12-08 00:00:00', '', 1),
(30, 315, 2000, 2, 1, '2009-12-08 00:00:00', '', 1),
(31, 315, 2000, 2, 1, '2009-12-08 00:00:00', '', 1),
(32, 313, 2000, 2, 1, '2009-12-08 00:00:00', '', 1),
(33, 312, 20000, 2, 1, '2009-12-08 00:00:00', '', 1),
(34, 318, 30000, 3, 1, '2009-12-08 00:00:00', '', 1),
(35, 308, 20000, 200, 1, '2009-12-08 00:00:00', '', 1),
(36, 314, 2000, 2, 1, '2009-12-08 00:00:00', '', 1),
(37, 301, 2000, 2, 1, '2009-12-08 00:00:00', '', 1),
(38, 324, 5000, 1, 1, '2009-12-16 00:00:00', '', 0),
(39, 325, 5000, 2, 1, '2009-12-16 00:00:00', '', 0),
(40, 326, 5000, 2, 1, '2009-12-16 00:00:00', '', 0),
(41, 327, 5000, 2, 1, '2009-12-16 00:00:00', '', 0),
(42, 328, 5000, 3, 1, '2009-12-16 00:00:00', '', 0),
(43, 329, 5000, 3, 1, '2009-12-16 00:00:00', '', 0),
(44, 330, 5000, 4, 1, '2009-12-16 00:00:00', '', 0),
(45, 331, 5000, 3, 1, '2009-12-16 00:00:00', '', 0),
(46, 332, 5000, 3, 1, '2009-12-16 00:00:00', '', 0),
(47, 333, 5000, 3, 1, '2009-12-16 00:00:00', '', 0),
(48, 334, 5000, 3, 1, '2009-12-16 00:00:00', '', 0),
(49, 335, 5000, 3, 1, '2009-12-16 00:00:00', '', 0),
(50, 336, 5000, 3, 1, '2009-12-16 00:00:00', '', 0),
(51, 337, 5000, 3, 1, '2009-12-16 00:00:00', '', 0),
(52, 338, 5000, 3, 1, '2009-12-16 00:00:00', '', 0),
(53, 339, 5000, 2, 1, '2009-12-16 00:00:00', '', 0),
(54, 340, 5000, 3, 1, '2009-12-16 00:00:00', '', 0),
(55, 341, 3, 5000, 1, '2009-12-16 00:00:00', '', 0),
(56, 341, 5000, 3, 1, '2009-12-16 00:00:00', '', 0),
(57, 342, 5000, 3, 1, '2009-12-16 00:00:00', '', 0),
(58, 343, 5000, 3, 1, '2009-12-16 00:00:00', '', 0),
(59, 344, 500, 1, 1, '2009-12-17 00:00:00', '', 0),
(60, 345, 1000, 1, 1, '2009-12-17 00:00:00', '', 0),
(61, 344, 87, 1, 1, '2009-12-17 00:00:00', '', 0);

-- --------------------------------------------------------

--
-- Table structure for table 'kontrola'
--

--
-- Dumping data for table 'kontrola'
--

INSERT INTO kontrola (measurementId, id, materialId, noveMnoztvi, stareMnozstvi, rozdil, prodanoVahou, prodanoPokladnou, valid) VALUES
(179, 113, 344, 412, 500, 88, 88, 0, 1),
(178, 113, 345, 724, 1000, 276, 276, 0, 1),
(177, 112, 330, 4000, 5000, 1000, 1000, 0,1),
(176, 111, 336, 3387, 5000, 1613, 1613, 0, 1),
(175, 110, 332, 4252, 5000, 748, 748, 0,1),
(174, 110, 328, 4549, 5000, 451, 451, 0,1),
(173, 110, 327, 2445, 3838, 1393, 1393, 0,1),
(172, 110, 324, 3339, 5000, 1661, 1661, 0,1);

-- --------------------------------------------------------

--
-- Table structure for table 'material'
--


--
-- Dumping data for table 'material'
--

INSERT INTO material (materialID, name, currentQuantity, materialTypeID, unitTypeID, barcode, minimal, isDeleted, density, emptyPackageWeight, packageCapacity) VALUES
(1, 'mrkev', 2, 1, 1, '1', 1, 1, 1, 200, 800),
(2, 'Iron', 4003, 2, 2, '2', 1, 1, 1, 200, 800),
(3, 'Becherovka', 2600, 2, 1, '8586009220561', 1, 1, 1, 200, 800),
(313, 'Pineau Blanc', 380, 5, 1, '25', 0, 1, 1, 200, 800),
(314, 'Sherry  -  Sandeman - medium dry', 2200, 4, 1, '22', 0, 1, 1, 200, 800),
(312, 'Pineau Rose', 1000, 5, 1, '26', 0, 1, 1, 200, 800),
(311, 'Pineau Dupuy � Tr�s Vieux', 2, 5, 3, '27', 0, 1, 1, 200, 800),
(310, 'Pineau M�nard � Tr�s Vieux', 2, 5, 3, '28', 0, 1, 1, 200, 800),
(309, 'Dupuy  V.S.O.P.', 3, 6, 3, '31', 0, 1, 1, 200, 800),
(308, 'Bache Gabrielson X.O.', 10163, 6, 1, '32', 0, 1, 1, 200, 800),
(307, 'Dupuy Extra', 2, 6, 3, '33', 0, 1, 1, 200, 800),
(305, 'Metaxa *****', 1140, 7, 1, '37', 0, 1, 1, 200, 800),
(306, 'M�nard 50 let - R�serve de Famille', 2, 6, 3, '34', 0, 1, 1, 200, 800),
(303, 'Napoleon ', 103248, 7, 2, '39', 0, 1, 1, 200, 800),
(304, 'Metaxa *******', 3, 7, 3, '38', 0, 1, 1, 200, 800),
(302, 'Princ d�Arignac V. S.', 3, 8, 3, '41', 0, 1, 1, 200, 800),
(301, 'Loret', 3, 9, 3, '44', 0, 1, 1, 200, 800),
(300, 'OUZO ', 3, 10, 3, '48', 0, 1, 1, 200, 800),
(299, 'Old Smuggler', 3, 11, 3, '52', 0, 1, 1, 200, 800),
(298, 'Ballantine�s', 0, 11, 3, '53', 0, 1, 1, 200, 800),
(297, 'Teacher�s', 3, 11, 3, '54', 0, 1, 1, 200, 800),
(296, 'Passport', 3, 11, 3, '55', 0, 1, 1, 200, 800),
(295, 'Johnie Walker', 3, 11, 3, '56', 0, 1, 1, 200, 800),
(294, 'Johnie Walker 12 let', 3, 11, 3, '57', 0, 1, 1, 200, 800),
(293, 'Jameson', 3, 12, 3, '59', 0, 1, 1, 200, 800),
(292, 'Paddy', 2, 12, 3, '60', 0, 1, 1, 200, 800),
(291, 'Tullamore Dew', 30, 12, 3, '61', 0, 1, 1, 200, 800),
(290, 'Tullamore Dew 12 let', 3, 12, 3, '62', 0, 1, 1, 200, 800),
(289, 'Canadian Club', 3, 13, 3, '64', 0, 1, 1, 200, 800),
(288, 'Star� Mysliveck�', 3, 14, 3, '66', 0, 1, 1, 200, 800),
(287, 'Four Roses', 3, 15, 3, '68', 0, 1, 1, 200, 800),
(286, 'Jim Beam', 3, 15, 3, '69', 0, 1, 1, 200, 800),
(285, 'Jack Daniel�s ', 3, 15, 3, '70', 0, 1, 1, 200, 800),
(284, 'Tuzemsk� � Bo�kov', 4003, 16, 3, '72', 0, 1, 1, 200, 800),
(283, 'Captain Morgan � Black Label', 3, 16, 3, '74', 0, 1, 1, 200, 800),
(282, 'Captain Morgan � Spiced Gold', 3, 16, 3, '75', 0, 1, 1, 200, 800),
(281, 'Bacardi � Carta Blanca', 3, 16, 3, '76', 0, 1, 1, 200, 800),
(280, 'Stroh 80% ', 100, 16, 3, '77', 0, 1, 1, 200, 800),
(279, 'El Dorado 12 let', 100, 16, 3, '78', 0, 1, 1, 200, 800),
(277, 'Hill�s', 100, 17, 3, '81', 0, 1, 1, 200, 800),
(278, 'El Dorado 15 let', 100, 16, 3, '79', 0, 1, 1, 200, 800),
(276, 'Fior di Vite', 100, 18, 3, '84', 0, 1, 1, 200, 800),
(275, 'Bailey�s', 43, 19, 3, '87', 0, 1, 1, 200, 800),
(274, 'Amundsen', 100, 20, 3, '90', 0, 1, 1, 200, 800),
(273, 'Finlandia', 20, 20, 3, '91', 0, 1, 1, 200, 800),
(272, 'Absolut Pepper', 100, 20, 3, '92', 0, 1, 1, 200, 800),
(270, 'Ko�er b�l�', 100, 21, 3, '96', 0, 1, 1, 200, 800),
(271, 'Excelent 45% ', 100, 21, 3, '95', 0, 1, 1, 200, 800),
(269, 'Ko�er zlat� ', 100, 21, 3, '97', 0, 1, 1, 200, 800),
(268, 'Domac� slivovice', 100, 21, 3, '98', 0, 1, 1, 200, 800),
(267, 'Beefeater', 100, 22, 3, '100', 0, 1, 1, 200, 800),
(265, 'Hendrics', 100, 22, 3, '102', 0, 1, 1, 200, 800),
(266, 'Bombay Saphire', 100, 22, 2, '8594007', 0, 1, 1, 200, 800),
(264, 'El Jimador b�l�', 100, 23, 3, '105', 0, 1, 1, 200, 800),
(263, 'El Jimador zlat�', 100, 23, 3, '106', 0, 1, 1, 200, 800),
(262, 'Pepe Lopez � b�l�', 12, 23, 3, '8594007134445', 0, 1, 1, 200, 800),
(261, 'Pepe Lopez - zlat�', 100, 23, 3, '108', 0, 1, 1, 200, 800),
(260, 'Cointreau', 100, 24, 3, '112', 0, 1, 1, 200, 800),
(259, 'Spi�sk� borovi?ka', 100, 24, 3, '113', 0, 1, 1, 200, 800),
(258, 'Spi�sk� hru�ka', 100, 24, 3, '114', 0, 1, 1, 200, 800),
(257, 'Spi�sk� bor?vka', 100, 24, 3, '115', 0, 1, 1, 200, 800),
(256, 'Grand Marnier � Cordon Rouge', 100, 25, 3, '118', 0, 1, 1, 200, 800),
(255, 'Sambuca � Ramazzotti', 100, 25, 3, '119', 0, 1, 1, 200, 800),
(253, 'Malibu', 100, 25, 3, '121', 0, 1, 1, 200, 800),
(254, 'Amaretto', 100, 25, 3, '120', 0, 1, 1, 200, 800),
(252, 'Kahl�a', 100, 25, 3, '122', 0, 1, 1, 200, 800),
(250, 'Fernet Stock', 100, 25, 3, '124', 0, 1, 1, 200, 800),
(251, 'Jan Becher', 100, 25, 3, '123', 0, 1, 1, 200, 800),
(249, 'Fernet Stock Citrus', 100, 25, 3, '125', 0, 1, 1, 200, 800),
(248, 'J�germeister', 100, 25, 3, '126', 0, 1, 1, 200, 800),
(246, 'Vaje?n� ', 100, 25, 3, '128', 0, 1, 1, 200, 800),
(247, 'Griotka Jel�nek', 100, 25, 3, '127', 0, 1, 1, 200, 800),
(245, 'Zelen� � peprmintka', 100, 25, 3, '129', 0, 1, 1, 200, 800),
(244, 'Broskvov� � Koskenkorva', 100, 25, 3, '130', 0, 1, 1, 200, 800),
(243, 'Curacao modr�', 100, 25, 3, '131', 0, 1, 1, 200, 800),
(241, 'Pun? vinn�', 100, 25, 3, '136', 0, 1, 1, 200, 800),
(242, 'Southern Comfort', 100, 25, 3, '132', 0, 1, 1, 200, 800),
(239, 'Stoln� perliv�', 100, 26, 3, '141', 0, 1, 1, 200, 800),
(240, 'Medovina', 100, 25, 3, '135', 0, 1, 1, 200, 800),
(238, 'Stoln� neperliv�', 4430, 26, 3, '144', 0, 1, 1, 200, 800),
(237, 'Voda jemn? perliv� Toma', 100, 26, 3, '147', 0, 1, 1, 200, 800),
(236, 'Miner�ln� voda Mattoni', 100, 26, 3, '148', 0, 1, 1, 200, 800),
(235, '�stn� hygienick�', 100, 26, 3, '149', 0, 1, 1, 200, 800),
(234, 'Pilsner Urquell 12 �', 100, 27, 3, '154', 0, 1, 1, 200, 800),
(233, 'Staropramen10 �', 100, 27, 3, '156', 0, 1, 1, 200, 800),
(232, 'Hoegaarden b�l� pivo', 100, 27, 3, '158', 0, 1, 1, 200, 800),
(231, 'Birell nealko. � l�hev sklen?n�', 100, 27, 3, '161', 0, 1, 1, 200, 800),
(230, 'Staropramen nealko. -  l�hev', 100, 27, 3, '162', 0, 1, 1, 200, 800),
(229, 'Pilsner Urquell 12 � l�hev sklen?n�', 100, 27, 3, '164', 0, 1, 1, 200, 800),
(228, 'Gran�t-polotmav�', 100, 27, 3, '166', 0, 1, 1, 200, 800),
(227, 'B�l� � Tram�n ?erven� ', 100, 28, 3, '170', 0, 1, 1, 200, 800),
(226, '(Morava)', 100, 3, 3, '171', 0, 1, 1, 200, 800),
(225, '?erven�  � Cabernet Savignon', 100, 28, 3, '173', 0, 1, 1, 200, 800),
(224, '?erven�  � Crianza 2003 Monastrell (�pan?lsko)', 1, 28, 3, '176', 0, 1, 1, 200, 800),
(223, 'Don Pascual, tannat, VCP, 2008, Uruguay', 100, 28, 3, '178', 0, 1, 1, 200, 800),
(222, 'v�no p?es ulici (s sebou)', 100, 28, 3, '181', 0, 1, 1, 200, 800),
(221, 'Retsina � Tsantali (b�l�)', 100, 28, 3, '183', 0, 1, 1, 200, 800),
(220, 'Retsina � Tsantali (r?�ov�)', 100, 28, 3, '184', 0, 1, 1, 200, 800),
(219, 'Bohemia demi sec', 4, 29, 3, '186', 0, 1, 1, 200, 800),
(315, 'Portsk� - Royal Oporto - tawny ', 4, 4, 3, '21', 0, 1, 1, 200, 800),
(316, 'Portsk� - Royal Oporto � white dry', 36154, 4, 3, '20', 0, 1, 1, 200, 800),
(317, 'Portsk� - Royal Oporto - ruby ', 43, 4, 3, '19', 0, 1, 1, 200, 800),
(318, 'Portsk� - Sandeman � white dry', 43, 4, 3, '18', 0, 1, 1, 200, 800),
(319, 'Portsk� - Sandeman - rich ruby ', 43, 4, 3, '17', 0, 1, 1, 200, 800),
(320, 'Campari bitter', 103043, 3, 3, '14', 0, 1, 1, 200, 800),
(321, 'Martini extra dry', 14043, 3, 3, '13', 0, 1, 1, 200, 800),
(322, 'Martini rosso', 43, 3, 3, '12', 0, 1, 1, 200, 800),
(323, 'Martini bianco', 43, 3, 3, '11', 0, 1, 1, 200, 800),
(324, 'Slivovice', 3339, 2, 3, '1', 1, 0, 1.0845, 304, 500),
(325, 'Tresnovice', 5000, 2, 3, '2', 1, 0, 1.0204, 510, 500),
(326, 'Beefeater', 4767, 2, 3, '3', 1, 0, 1.0616, 635, 1000),
(327, 'Bombay Sapphire', 2445, 2, 3, '4', 1, 0, 1.0718, 718, 1000),
(328, 'Gordons', 4549, 2, 1, '5', 1, 0, 1.0504, 525, 1000),
(329, 'Silver TOp', 5000, 2, 3, '6', 1, 0, 1.0504, 604, 700),
(330, 'Soda 1l', 4000, 2, 3, '7', 1, 0, 1, 0, 1000),
(331, 'Becherovka', 2031, 2, 3, '8', 1, 0, 1.0215, 769, 1000),
(332, 'Becherovka Lime', 4252, 2, 3, '9', 1, 0, 0.9803, 742, 1000),
(333, 'Fernet', 5000, 2, 3, '10', 1, 0, 1.0604, 587, 1000),
(334, 'Fernet Citrus', 4659, 2, 3, '11', 1, 0, 1.0101, 587, 1000),
(335, 'Jagermeister', 5000, 2, 3, '12', 1, 0, 1.093, 739, 1000),
(336, 'Absente Franc.', 3387, 2, 3, '13', 1, 0, 1.044, 767, 700),
(337, 'Absinth hills', 5000, 2, 3, '15', 1, 0, 1.1438, 677, 700),
(338, 'Amaretto', 5000, 2, 3, '16', 1, 0, 0.9763, 503, 700),
(339, 'Baileys', 5000, 2, 3, '17', 1, 0, 0.9625, 723, 1000),
(340, 'Berentzen jablko', 5000, 2, 3, '20', 1, 0, 0.9794, 571, 1000),
(341, 'Cointreau', 5003, 2, 3, '21', 1, 0, 0.9735, 660, 700),
(342, 'Curacao', 5000, 2, 3, '22', 1, 0, 0.9901, 377, 700),
(343, 'Griotte Jelinke', 5000, 2, 3, '23', 1, 0, 0.9208, 515, 500),
(344, 'Eier Kognak', 412, 2, 3, '8594005012851', 1, 0, 0.9363, 376, 500),
(345, 'Peprmint', 724, 2, 3, '8594005011762', 1, 0, 0.9372, 550, 1000);

-- --------------------------------------------------------

--
-- Table structure for table 'materialtype'
--


-- Dumping data for table 'materialtype'
--

INSERT INTO materialtype (materialTypeID, name, note, isDeleted) VALUES
(1, 'Zelenina', '', 1),
(2, 'Alkohol - tvrd�', '', 0),
(3, 'Aperitivy', '', 1),
(4, 'Dezertn� v�na', '', 1),
(5, 'Pineau', '', 1),
(6, 'Cognac', '', 1),
(7, 'Brandy', '', 1),
(8, 'Armagnac', '', 1),
(9, 'Calvados', '', 1),
(10, 'Pastis', '', 1),
(11, 'Whiskey skotsk�', '', 1),
(12, 'Whiskey Irsk�', '', 1),
(13, 'Whiskey kanadsk�', '', 1),
(14, 'Whisky ?esk�', '', 1),
(15, 'Whiskey a Bourbon', '', 1),
(16, 'Rum', '', 1),
(17, 'Absint', '', 1),
(18, 'Grappa', '', 1),
(19, 'Kr�m', '', 1),
(20, 'Vodka', '', 1),
(21, 'Slivovice', '', 1),
(22, 'Gin', '', 1),
(23, 'Tequila', '', 1),
(24, 'P�lenka', '', 1),
(25, 'Lik�r', '', 1),
(26, 'Voda', '', 1),
(27, 'Pivo', '', 1),
(28, 'V�no', '', 1),
(29, 'Sekt', '', 1);

-- --------------------------------------------------------

--
-- Table structure for table 'menu'
--
--
-- Dumping data for table 'menu'
--

INSERT INTO menu (menuID, name, userId, date, isDeleted) VALUES
(1, 'jidlo', 1, '2009-11-14 00:00:00', 0),
(2, 'piti', 1, '2009-11-14 00:00:00', 0);

-- --------------------------------------------------------

--
-- Table structure for table 'menuitem'
--

--
-- Dumping data for table 'menuitem'
--

INSERT INTO menuitem (menuItemID, name, price, quantity, isAvailable, isDeleted, menuItemTypeID) VALUES
(1, 'Sv��kov�, knedl�k', 79, '10', 1, 0, 1),
(2, '��zek, brambor', 99, '10', 1, 0, 1),
(3, 'Koprovka, knedl�k', 99, '10', 1, 0, 1),
(4, 'Gambrinus, 10�', 19, '10', 1, 0, 2),
(5, 'Eier Kognak', 20, '1', 1, 0, 2),
(6, 'Plze�, 12�', 26, '20', 1, 0, 2);

-- --------------------------------------------------------

--
-- Table structure for table 'menuitemtype'
--

--
-- Dumping data for table 'menuitemtype'
--

INSERT INTO menuitemtype (menuItemTypeID, name, itemCount, isDeleted) VALUES
(1, 'jidlo', 3, 0),
(2, 'piti', 2, 0);

-- --------------------------------------------------------

--
-- Table structure for table 'menu_menuitem'
--


--
-- Dumping data for table 'menu_menuitem'
--

INSERT INTO menu_menuitem (menuMenuItemID, menuID, menuItemID, isDeleted) VALUES
(1, 1, 1, 0),
(2, 1, 2, 0),
(3, 1, 3, 0),
(4, 2, 4, 0),
(5, 2, 5, 0);

-- --------------------------------------------------------

--
-- Table structure for table 'order_menuitem'
--

--
-- Dumping data for table 'order_menuitem'
--

INSERT INTO order_menuitem (orderMenuItemID, orderID, menuItemID, isDeleted) VALUES
(1, 1, 1, 0),
(2, 2, 2, 0),
(3, 3, 2, 0),
(4, 4, 1, 0),
(5, 5, 1, 0),
(6, 6, 1, 0),
(7, 7, 3, 0),
(8, 8, 3, 0),
(9, 9, 2, 0),
(10, 10, 4, 0),
(11, 11, 4, 0),
(12, 12, 4, 0),
(13, 13, 5, 0),
(14, 14, 5, 0),
(15, 15, 5, 0),
(16, 16, 5, 0),
(17, 17, 5, 0),
(18, 18, 5, 0),
(19, 19, 5, 0),
(20, 20, 5, 0),
(21, 21, 5, 0),
(22, 22, 4, 0),
(23, 23, 4, 0),
(24, 24, 1, 0),
(25, 25, 1, 0),
(26, 26, 1, 0),
(27, 27, 3, 0),
(28, 28, 3, 0),
(29, 29, 5, 0),
(30, 30, 5, 0),
(31, 31, 5, 0),
(32, 32, 4, 0),
(33, 33, 5, 0),
(34, 34, 4, 0),
(35, 35, 5, 0),
(36, 36, 4, 0),
(37, 37, 4, 0),
(38, 38, 4, 0),
(39, 39, 5, 0),
(40, 40, 5, 0),
(41, 41, 5, 0),
(42, 42, 5, 0),
(43, 43, 5, 0),
(44, 44, 5, 0),
(45, 45, 5, 0),
(46, 46, 5, 0),
(47, 47, 5, 0),
(48, 48, 5, 0),
(49, 49, 5, 0),
(50, 50, 5, 0),
(51, 51, 1, 0),
(52, 52, 1, 0),
(53, 53, 1, 0),
(54, 54, 1, 0),
(55, 55, 2, 0),
(56, 56, 2, 0),
(57, 57, 2, 0),
(58, 58, 2, 0),
(59, 59, 3, 0),
(60, 60, 3, 0),
(61, 61, 3, 0),
(62, 62, 3, 0),
(63, 63, 5, 0),
(64, 64, 5, 0),
(65, 65, 4, 0),
(66, 66, 4, 0),
(67, 67, 4, 0),
(68, 68, 4, 0),
(69, 69, 4, 0),
(70, 70, 2, 0),
(71, 71, 1, 0),
(72, 72, 3, 0);

-- --------------------------------------------------------

--
-- Table structure for table 'reasontype'
--

--
-- Dumping data for table 'reasontype'
--


-- --------------------------------------------------------

--
-- Table structure for table 'role'
--


-- Dumping data for table 'role'
--

INSERT INTO role (roleID, name, isDeleted) VALUES
(1, 'manager', 0),
(2, 'waiter', 0),
(3, 'cook', 0),
(4, 'customer', 0);

-- --------------------------------------------------------

--
-- Table structure for table 'unittype'
--

--
-- Dumping data for table 'unittype'
--

INSERT INTO unittype (unitTypeID, name, abbreviation, typeID, isDeleted) VALUES
(1, 'gram', 'g', 1, 0),
(2, 'kilogram', 'kg', 1, 0),
(3, 'mililitr', 'ml', 2, 0),
(4, 'decilitr', 'dl', 2, 0),
(5, 'litr', 'l', 2, 0),
(6, 'sud 50 l', 'keg 50', 2, 0),
(7, 'sud 30 l', 'keg 30', 2, 0),
(8, 'kus', 'ks', 3, 0);

-- --------------------------------------------------------

--
-- Table structure for table 'usedmaterial'
--


-- Dumping data for table 'usedmaterial'
--

INSERT INTO usedmaterial (usedMaterialID, quantity, materialID, menuItemID, isDeleted) VALUES
(1, 20, 344, 6, 1),
(2, 20, 344, 5, 0);

-- --------------------------------------------------------

--
-- Table structure for table 'user'
--


--
-- Dumping data for table 'user'
--

INSERT INTO USER_TABLE (userID, firstName, lastName, personalIdentificationNumber, username, password, isDeleted, credit) VALUES
(1, 'Jan', 'Novak', '123456', 'novak', '1234', 0, 0),
(2, 'Petr', 'Mach', '234563', 'mach', '1234', 0, 0),
(3, 'Karel', 'Novotny', '999999', 'stamgast', '', 0, 100),
(4, 'Jindrich', 'Dlouhy', '111', 'opilec', '', 0, 0),
(5, 'Karel', 'Vocasek', '44', 'vocasek', '', 0, 200),
(6, 'Jakub', 'Jambor', 'aaa', 'Arnoui', '', 0, 0),
(7, 'Lojza', 'Bojza', 'bbb', 'Bojza', '', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table 'user_role'
--

-- Dumping data for table 'user_role'
--

INSERT INTO user_role (userRoleID, userId, roleId, isDeleted) VALUES
(1, 1, 1, 0),
(2, 2, 2, 1),
(3, 3, 4, 0),
(4, 4, 4, 0),
(5, 5, 4, 0);

-- --------------------------------------------------------

--
-- Table structure for table 'uzaverka'
--


-- Dumping data for table 'uzaverka'
--

INSERT INTO uzaverka (id, userId, date) VALUES
(113, 1, '2009-12-18 12:03:03'),
(112, 1, '2009-12-17 23:48:38'),
(111, 1, '2009-12-17 23:47:50'),
(110, 1, '2009-12-17 23:47:05');

-- --------------------------------------------------------

--
-- Table structure for table 'xorder'
--


-- Dumping data for table 'xorder'
--

INSERT INTO xorder (orderID, isPaid, time, accountID, userID, isDeleted) VALUES
(1, 0, '2009-11-14 16:14:35', 3, 1, 0),
(2, 1, '2009-11-14 17:17:52', 5, 1, 0),
(3, 0, '2009-11-14 17:17:53', 5, 1, 0),
(4, 1, '2009-11-16 13:04:48', 7, 1, 0),
(5, 1, '2009-11-16 13:04:50', 7, 1, 0),
(6, 1, '2009-11-16 13:04:51', 7, 1, 0),
(7, 0, '2009-11-16 13:04:51', 7, 1, 0),
(8, 0, '2009-11-16 13:04:51', 7, 1, 0),
(9, 1, '2009-11-16 13:04:52', 7, 1, 0),
(10, 1, '2009-11-16 13:04:54', 7, 1, 0),
(11, 0, '2009-11-16 13:04:54', 7, 1, 0),
(12, 0, '2009-11-16 13:04:54', 7, 1, 0),
(13, 1, '2009-11-16 13:04:55', 7, 1, 0),
(14, 1, '2009-11-16 13:04:55', 7, 1, 0),
(15, 1, '2009-11-16 13:04:55', 7, 1, 0),
(16, 1, '2009-11-16 13:04:55', 7, 1, 0),
(17, 1, '2009-11-16 13:04:55', 7, 1, 0),
(18, 1, '2009-11-16 13:04:55', 7, 1, 0),
(19, 0, '2009-11-16 13:04:55', 7, 1, 0),
(20, 0, '2009-11-16 13:04:56', 7, 1, 0),
(21, 0, '2009-11-16 13:04:56', 7, 1, 0),
(22, 0, '2009-12-02 11:30:21', 7, 2, 0),
(23, 0, '2009-12-02 11:30:21', 7, 2, 0),
(24, 0, '2009-12-02 11:30:25', 2, 2, 1),
(25, 0, '2009-12-02 11:30:25', 3, 2, 0),
(26, 1, '2009-12-02 11:39:23', 2, 2, 0),
(27, 0, '2009-12-09 10:43:09', 3, 1, 0),
(28, 0, '2009-12-09 10:43:10', 3, 1, 0),
(29, 1, '2009-12-09 10:43:15', 2, 1, 0),
(30, 1, '2009-12-09 10:43:15', 2, 1, 0),
(31, 0, '2009-12-17 20:10:18', 19, 1, 0),
(32, 0, '2009-12-17 20:10:18', 19, 1, 0),
(33, 0, '2009-12-17 20:10:19', 19, 1, 0),
(34, 0, '2009-12-17 20:10:19', 19, 1, 0),
(35, 1, '2009-12-17 20:34:38', 2, 1, 0),
(36, 1, '2009-12-17 20:34:39', 2, 1, 0),
(37, 1, '2009-12-17 20:34:40', 2, 1, 0),
(38, 1, '2009-12-17 20:34:40', 2, 1, 0),
(39, 1, '2009-12-17 20:34:41', 2, 1, 0),
(40, 1, '2009-12-17 20:37:39', 2, 1, 0),
(41, 1, '2009-12-17 20:37:39', 2, 1, 0),
(42, 1, '2009-12-17 20:37:40', 2, 1, 0),
(43, 0, '2009-12-17 23:52:16', 4, 1, 0),
(44, 0, '2009-12-17 23:52:16', 4, 1, 0),
(45, 0, '2009-12-17 23:52:17', 4, 1, 0),
(46, 0, '2009-12-17 23:52:17', 4, 1, 0),
(47, 0, '2009-12-17 23:52:17', 4, 1, 0),
(48, 0, '2009-12-17 23:52:18', 4, 1, 0),
(49, 0, '2009-12-17 23:52:18', 4, 1, 0),
(50, 0, '2009-12-17 23:52:18', 4, 1, 0),
(51, 0, '2009-12-17 23:52:27', 4, 1, 0),
(52, 0, '2009-12-17 23:52:28', 4, 1, 0),
(53, 0, '2009-12-17 23:52:28', 4, 1, 0),
(54, 0, '2009-12-17 23:52:28', 4, 1, 0),
(55, 0, '2009-12-17 23:52:28', 4, 1, 0),
(56, 0, '2009-12-17 23:52:29', 4, 1, 0),
(57, 0, '2009-12-17 23:52:29', 4, 1, 0),
(58, 0, '2009-12-17 23:52:29', 4, 1, 0),
(59, 0, '2009-12-17 23:52:29', 4, 1, 0),
(60, 0, '2009-12-17 23:52:30', 4, 1, 0),
(61, 0, '2009-12-17 23:52:30', 4, 1, 0),
(62, 0, '2009-12-17 23:52:30', 4, 1, 0),
(63, 0, '2009-12-17 23:52:32', 4, 1, 0),
(64, 0, '2009-12-17 23:52:32', 4, 1, 0),
(65, 0, '2009-12-17 23:52:32', 4, 1, 0),
(66, 0, '2009-12-17 23:52:32', 4, 1, 0),
(67, 0, '2009-12-17 23:52:32', 4, 1, 0),
(68, 0, '2009-12-17 23:52:33', 4, 1, 0),
(69, 0, '2009-12-17 23:52:33', 4, 1, 0),
(70, 0, '2009-12-18 10:35:59', 10, 1, 0),
(71, 0, '2009-12-18 10:36:01', 10, 1, 0),
(72, 0, '2009-12-18 10:36:02', 10, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table 'xtable'
--

--
-- Dumping data for table 'xtable'
--

INSERT INTO xtable (tableID, tableNumber, numberOfPlaces, isDeleted) VALUES
(1, 1, 8, 0),
(2, 2, 3, 0),
(3, 3, 4, 0);

