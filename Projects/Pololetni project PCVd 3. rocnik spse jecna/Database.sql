CREATE TABLE Senzor(
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nazev` varchar(15) NOT NULL,
  PRIMARY KEY (`Id`)
);
CREATE TABLE Report(
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Senzor_id` int(11) NOT NULL,
  `Date_time` datetime NOT NULL,
  `HTS221_temp` float(10,3),
  `HTS221_hum` float(10,3),
  `LPS22HB_temp` float(10,3),
  `LPS22HB_press` float(10,3),
  `LSM303AGR_mag_x` int(32),
  `LSM303AGR_mag_y` int(32),
  `LSM303AGR_mag_z` int(32),
  `LSM303AGR_acc_x` int(32),
  `LSM303AGR_acc_y` int(32),
  `LSM303AGR_acc_z` int(32),
  `LSM6DSL_gyro_x` int(32),
  `LSM6DSL_gyro_y` int(32),
  `LSM6DSL_gyro_z` int(32),
  `LSM6DSL_acc_x` int(32),
  `LSM6DSL_acc_y` int(32),
  `LSM6DSL_acc_z` int(32),
  PRIMARY KEY (`Id`),
  CONSTRAINT `Report_fk` FOREIGN KEY (`Senzor_id`) REFERENCES `Senzor` (`Id`)
);