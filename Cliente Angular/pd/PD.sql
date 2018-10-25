

DROP TABLE IF EXISTS `equipe`;
CREATE TABLE `equipe` (
  `idEquipe` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  `lider_idlider` int(11) NOT NULL,
  PRIMARY KEY (`idEquipe`,`lider_idlider`),
  KEY `fk_Equipe_lider_idx` (`lider_idlider`),
  CONSTRAINT `fk_Equipe_lider` FOREIGN KEY (`lider_idlider`) REFERENCES `lider` (`idlider`)
) ;
DROP TABLE IF EXISTS `lider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lider` (
  `idlider` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `hierarquia` varchar(45) NOT NULL,
  `lider_idLider` int(11),
  CONSTRAINT `Fk_lider_conjugue` FOREIGN KEY(`lider_idLider`) REFERENCES `lider` (`idlider`),
  PRIMARY KEY (`idlider`)
) ;

DROP TABLE IF EXISTS `pd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pd` (
  `idpd` int(11) NOT NULL AUTO_INCREMENT,
  `individual` float NOT NULL,
  `celula` float NOT NULL,
  `data` date NOT NULL,
  `Equipe_idEquipe` int(11) NOT NULL,
  `lider_idLider` int(11) NOT NULL,
  `semana` int(1) NOT NULL,
  PRIMARY KEY (`idpd`,`Equipe_idEquipe`),
  KEY `fk_pd_Equipe1_idx` (`Equipe_idEquipe`),
  KEY `fk_pd_lider` (`lider_idLider`),
  CONSTRAINT `fk_pd_Equipe1` FOREIGN KEY (`Equipe_idEquipe`) REFERENCES `equipe` (`idequipe`),
  CONSTRAINT `fk_pd_lider` FOREIGN KEY (`lider_idLider`) REFERENCES `lider` (`idlider`)
);


DROP TABLE IF EXISTS `sub`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sub` (
  `idsub` int(11) NOT NULL AUTO_INCREMENT,
  `lider_idlider` int(11) NOT NULL,
  `Equipe_idEquipe` int(11) NOT NULL,
  `nome` varchar(200) NOT NULL,
  `sub_idsub` int(11) DEFAULT NULL,
  PRIMARY KEY (`idsub`),
  KEY `fk_sub_lider1_idx` (`lider_idlider`),
  KEY `fk_sub_Equipe1_idx` (`Equipe_idEquipe`),
  KEY `fk_sub_sub1_idx` (`sub_idsub`),
  CONSTRAINT `fk_sub_Equipe1` FOREIGN KEY (`Equipe_idEquipe`) REFERENCES `equipe` (`idequipe`),
  CONSTRAINT `fk_sub_lider1` FOREIGN KEY (`lider_idlider`) REFERENCES `lider` (`idlider`),
  CONSTRAINT `fk_sub_sub1` FOREIGN KEY (`sub_idsub`) REFERENCES `sub` (`idsub`)
);