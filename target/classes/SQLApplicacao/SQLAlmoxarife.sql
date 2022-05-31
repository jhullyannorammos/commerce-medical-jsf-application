-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tempo de Geração: 28/07/2016 às 18:30
-- Versão do servidor: 5.5.50-0ubuntu0.14.04.1
-- Versão do PHP: 5.5.9-1ubuntu4.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Banco de dados: `drugsore`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `fabricante`
--
use drugstore;
CREATE TABLE IF NOT EXISTS `fabricante` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(50) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=18 ;

--
-- Fazendo dump de dados para tabela `fabricante`
--
SELECT * FROM fabricante;
INSERT INTO `fabricante` (`codigo`, `descricao`) VALUES
(1, 'Laboratório Gross'),
(2, 'Laboratório Industrial Farmacêutico Lifar '),
(3, 'Laboratório Madrevita'),
(4, 'Laboratório Melpoejo'),
(5, 'Laboratório Rocha '),
(6, 'Laboratório Simões'),
(7, 'Laboratório Teuto Brasileiro '),
(8, 'Laboratório Zurita '),
(9, 'Laboratórios Britania'),
(10, 'Laboratórios Farmacêuticos Sten-Kal '),
(11, 'Red Queen Laboratory'),
(12, 'Genetic Saving'),
(13, 'Umbrella Medical'),
(14, 'AVMedical'),
(15, 'Laboratorio Cebrom'),
(17, 'Hidra Tecnologias Medicas LTDA');

-- --------------------------------------------------------

--
-- Estrutura para tabela `produto`
--

CREATE TABLE IF NOT EXISTS `produto` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(50) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `preco` double NOT NULL,
  `codigo_fabricante` int(11) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Fazendo dump de dados para tabela `produto`
SELECT * FROM PRODUTO;
INSERT INTO `produto` (`codigo`, `descricao`, `quantidade`, `preco`, `fabricante_codigo`) VALUES
(1, 'novalgina com 10 comprimidos', 32, 5.5, 1),
(2, 'Atenolol', 0, 0, 3),
(5, 'Atezolizumabe', 1189, 1.99, 5),
(6, 'Atomoxetina', 7, 1.3, 4),
(7, 'Atorvastatina', 257, 1.58, 12),
(8, 'Benzilpenicilinas', 16, 32.45, 3),
(9, 'Fenoximetilpenicilina', 2, 12.346, 1),
(10, 'Abendazol', 12, 7.33, 8);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
