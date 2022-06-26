SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

use drugstore;
CREATE TABLE IF NOT EXISTS `fabricante` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(50) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=18 ;


SELECT * FROM usuario;
SELECT * FROM pessoa;
SELECT * FROM cidade;
SELECT * FROM estado;

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
(16, 'Hidra Tecnologias Medicas LTDA');

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

select cpf, senha from usuario as u join pessoa as p on u.pessoa_codigo = p.codigo where p.cpf = "254.397.854-73" and u.senha = "1q2w3e4r";
insert into usuario values(1, true, "123456", "ADMINISTRADOR", 1);

insert into estado values(1, "ACRE", "AC");
    insert into estado values(2, "AMAZONAS", "AM");
    insert into estado values(3, "ALAGOAS", "AL");
    insert into estado values(4, "Bahia", "BA");
