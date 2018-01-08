USE `hospital`;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
CREATE TABLE `patient` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) CHARACTER SET utf8 NOT NULL,
  `last_name` varchar(45) CHARACTER SET utf8 NOT NULL,
  `ward` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Table structure for table `diagnosis`
--

DROP TABLE IF EXISTS `diagnosis`;
CREATE TABLE `diagnosis` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- Table structure for table `patient_diagnosis`
--

DROP TABLE IF EXISTS `patient_diagnosis`;
CREATE TABLE `patient_diagnosis` (
  `id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL,
  `diagnosis_id` int(11) NOT NULL,
  `consultation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `patient_fkey_idx` (`patient_id`),
  KEY `diagnosis_fkey_idx` (`diagnosis_id`),
  CONSTRAINT `diagnosis_fkey` FOREIGN KEY (`diagnosis_id`) REFERENCES `diagnosis` (`id`),
  CONSTRAINT `patient_fkey` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `title` varchar(45) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Table structure for table `treatment_type`
--

DROP TABLE IF EXISTS `treatment_type`;
CREATE TABLE `treatment_type` (
  `id` int(11) NOT NULL,
  `title` varchar(45) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Table structure for table `role_treatment_type`
--

DROP TABLE IF EXISTS `role_treatment_type`;
CREATE TABLE `role_treatment_type` (
  `id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `treatment_type_id` int(11) NOT NULL,
  `allow` enum('PERFORM','ASSIGN') COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `role_id_fkey_idx` (`role_id`),
  KEY `treatment_type_id_fkey_idx` (`treatment_type_id`),
  CONSTRAINT `role_id_fkey` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `treatment_type_id_fkey` FOREIGN KEY (`treatment_type_id`) REFERENCES `treatment_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_login_UNIQUE` (`login`),
  KEY `user_idx_role` (`role_id`),
  CONSTRAINT `user_role_id_fkey` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

--
-- Table structure for table `treatment`
--

DROP TABLE IF EXISTS `treatment`;
CREATE TABLE `treatment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `patient_diagnosis_id` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  `performer_id` int(11) NOT NULL,
  `done` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `treatment_idx_user` (`performer_id`),
  KEY `treatment_idx_patient_diagnosis` (`patient_diagnosis_id`),
  KEY `treatment_idx_treatment_type` (`type_id`),
  CONSTRAINT `treatment_patient_id_fkey` FOREIGN KEY (`patient_diagnosis_id`) REFERENCES `patient_diagnosis` (`id`),
  CONSTRAINT `treatment_treatment_type_id_fkey` FOREIGN KEY (`type_id`) REFERENCES `treatment_type` (`id`),
  CONSTRAINT `treatment_user_id_fkey` FOREIGN KEY (`performer_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;