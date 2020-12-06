SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
DROP DATABASE IF EXISTS `proyecto`;
CREATE DATABASE IF NOT EXISTS `proyecto` DEFAULT CHARACTER SET utf8 ;
USE `proyecto` ;

CREATE TABLE IF NOT EXISTS `proyecto`.`areaIngrediente` (
  `idAreaIngrediente` INT NOT NULL AUTO_INCREMENT,
  `nombreArea` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idAreaIngrediente`),
  UNIQUE INDEX `nombreArea_UNIQUE` (`nombreArea` ASC))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `proyecto`.`ingrediente` (
  `id_Ingrediente` INT NOT NULL AUTO_INCREMENT,
  `nombreIngrediente` VARCHAR(45) NOT NULL,
  `idAreaIngrediente` INT NOT NULL,
  PRIMARY KEY (`id_Ingrediente`),
  UNIQUE INDEX `nombreIngrediente_UNIQUE` (`nombreIngrediente` ASC),
  INDEX `fk_Ingrediente_AreaIngrediente_idx` (`idAreaIngrediente` ASC),
  CONSTRAINT `fk_Ingrediente_AreaIngrediente`
    FOREIGN KEY (`idAreaIngrediente`)
    REFERENCES `proyecto`.`areaIngrediente` (`idAreaIngrediente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `proyecto`.`tipoMenu` (
  `idTipoMenu` INT NOT NULL AUTO_INCREMENT,
  `nombreTMenu` VARCHAR(45) NOT NULL,
  `modoMenu` TINYINT NOT NULL,
  PRIMARY KEY (`idTipoMenu`),
  UNIQUE INDEX `nombreTMenu_UNIQUE` (`nombreTMenu` ASC))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `proyecto`.`sucursal` (
  `idSucursal` INT NOT NULL AUTO_INCREMENT,
  `nombreSucursal` VARCHAR(45) NOT NULL,
  `idDireccion` INT NOT NULL,
  PRIMARY KEY (`idSucursal`),
  CONSTRAINT `fk_sucursal_idDireccion`
    FOREIGN KEY (`idDireccion`)
    REFERENCES `proyecto`.`direccion` (`idDireccion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `proyecto`.`menu` (
  `idMenu` INT NOT NULL AUTO_INCREMENT,
  `nombreMenu` VARCHAR(50) NOT NULL,
  `idTipoMenu` INT NOT NULL,
  `idSucursal` INT NOT NULL,
  PRIMARY KEY (`idMenu`),
  INDEX `fk_Menu_TipoMenu1_idx` (`idTipoMenu` ASC),
  INDEX `fk_Menu_Sucursal1_idx` (`idSucursal` ASC),
  CONSTRAINT `fk_Menu_TipoMenu1`
    FOREIGN KEY (`idTipoMenu`)
    REFERENCES `proyecto`.`tipoMenu` (`idTipoMenu`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Menu_Sucursal1`
    FOREIGN KEY (`idSucursal`)
    REFERENCES `proyecto`.`sucursal` (`idSucursal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `proyecto`.`tipoPlatillo` (
  `idTipoPlatillo` INT NOT NULL AUTO_INCREMENT,
  `nombreTipo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idTipoPlatillo`),
  UNIQUE INDEX `nombreTipo_UNIQUE` (`nombreTipo` ASC))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `proyecto`.`platillo` (
  `idPlatillo` INT NOT NULL AUTO_INCREMENT,
  `nombrePlatillo` VARCHAR(70) NOT NULL,
  `tiempoPreparacion` INT NOT NULL,
  `descripcion` TEXT NULL,
  `idTipoPlatillo` INT NOT NULL,
  PRIMARY KEY (`idPlatillo`),
  UNIQUE INDEX `nombrePlatillo_UNIQUE` (`nombrePlatillo` ASC) ,
  INDEX `fk_Platillo_TipoPlatillo1_idx` (`idTipoPlatillo` ASC) ,
  CONSTRAINT `fk_Platillo_TipoPlatillo1`
    FOREIGN KEY (`idTipoPlatillo`)
    REFERENCES `proyecto`.`tipoPlatillo` (`idTipoPlatillo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `proyecto`.`unidadMedida` (
  `idUnidadMedida` INT NOT NULL AUTO_INCREMENT,
  `tipoUnidad` VARCHAR(45) NOT NULL,
  `abreviatura` VARCHAR(6) NOT NULL,
  PRIMARY KEY (`idUnidadMedida`),
  UNIQUE INDEX `tipoUnidad_UNIQUE` (`tipoUnidad` ASC) ,
  UNIQUE INDEX `abreviatura_UNIQUE` (`abreviatura` ASC) )
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `proyecto`.`ingredienteEnPlatillo` (
  `idIngrediente` INT NOT NULL,
  `idPlatillo` INT NOT NULL,
  `porcion` FLOAT NOT NULL,
  `idUnidadMedida` INT NOT NULL,
  PRIMARY KEY (`idIngrediente`, `idPlatillo`),
  INDEX `fk_Ingrediente_has_Platillo_Platillo1_idx` (`idPlatillo` ASC) ,
  INDEX `fk_Ingrediente_has_Platillo_Ingrediente1_idx` (`idIngrediente` ASC) ,
  INDEX `fk_Ingrediente_has_Platillo_UnidadMedida1_idx` (`idUnidadMedida` ASC) ,
  CONSTRAINT `fk_Ingrediente_has_Platillo_Ingrediente1`
    FOREIGN KEY (`idIngrediente`)
    REFERENCES `proyecto`.`ingrediente` (`id_Ingrediente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ingrediente_has_Platillo_Platillo1`
    FOREIGN KEY (`idPlatillo`)
    REFERENCES `proyecto`.`platillo` (`idPlatillo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ingrediente_has_Platillo_UnidadMedida1`
    FOREIGN KEY (`idUnidadMedida`)
    REFERENCES `proyecto`.`unidadMedida` (`idUnidadMedida`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `proyecto`.`preparacion` (
  `idPreparacion` INT NOT NULL AUTO_INCREMENT,
  `descripcion` TEXT NOT NULL,
  `idPlatillo` INT NOT NULL,
  PRIMARY KEY (`idPreparacion`),
  INDEX `fk_Preparacion_Platillo1_idx` (`idPlatillo` ASC) ,
  CONSTRAINT `fk_Preparacion_Platillo1`
    FOREIGN KEY (`idPlatillo`)
    REFERENCES `proyecto`.`platillo` (`idPlatillo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `proyecto`.`platilloEnMenu` (
  `idMenuPlatillo` INT NOT NULL,
  `cantidadEstimada` INT NOT NULL,
  `status` TINYINT NOT NULL,
  `idMenu` INT NOT NULL,
  `idPlatillo` INT NOT NULL,
  INDEX `fk_Menu_has_Platillo_Platillo1_idx` (`idPlatillo` ASC) ,
  INDEX `fk_Menu_has_Platillo_Menu1_idx` (`idMenu` ASC) ,
  PRIMARY KEY (`idMenuPlatillo`),
  CONSTRAINT `fk_Menu_has_Platillo_Menu1`
    FOREIGN KEY (`idMenu`)
    REFERENCES `proyecto`.`menu` (`idMenu`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Menu_has_Platillo_Platillo1`
    FOREIGN KEY (`idPlatillo`)
    REFERENCES `proyecto`.`platillo` (`idPlatillo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `proyecto`.`dia` (
  `idDia` INT NOT NULL AUTO_INCREMENT,
  `nombreDia` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`idDia`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `proyecto`.`tipoDia` (
  `idTipoDia` INT NOT NULL AUTO_INCREMENT,
  `diaCompra` TINYINT NOT NULL,
  `diaRegistro` TINYINT NOT NULL,
  `idSucursal` INT NOT NULL,
  `idDia` INT NOT NULL,
  PRIMARY KEY (`idTipoDia`),
  INDEX `fk_tipoDia_Sucursal1_idx` (`idSucursal` ASC) ,
  INDEX `fk_tipoDia_dia1_idx` (`idDia` ASC) ,
  CONSTRAINT `fk_tipoDia_Sucursal1`
    FOREIGN KEY (`idSucursal`)
    REFERENCES `proyecto`.`sucursal` (`idSucursal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tipoDia_dia1`
    FOREIGN KEY (`idDia`)
    REFERENCES `proyecto`.`dia` (`idDia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `proyecto`.`menuDia` (
  `idMenuDia` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATE NOT NULL,
  `status` TINYINT NOT NULL,
  `idMenu` INT NOT NULL,
  PRIMARY KEY (`idMenuDia`),
  INDEX `fk_Carta_Semanal_Menu1_idx` (`idMenu` ASC) ,
  CONSTRAINT `fk_Carta_Semanal_Menu1`
    FOREIGN KEY (`idMenu`)
    REFERENCES `proyecto`.`menu` (`idMenu`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `proyecto`.`imagenPlatillo` (
  `idImagenPlatillo` INT NOT NULL AUTO_INCREMENT,
  `idPlatillo` INT NOT NULL,
  `img` LONGBLOB NOT NULL,
  PRIMARY KEY (`idImagenPlatillo`),
  INDEX `fk_Imagen_Platillo_Platillo1_idx` (`idPlatillo` ASC) ,
  CONSTRAINT `fk_Imagen_Platillo_Platillo1`
    FOREIGN KEY (`idPlatillo`)
    REFERENCES `proyecto`.`platillo` (`idPlatillo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `proyecto`.`rol` (
  `idRol` INT NOT NULL AUTO_INCREMENT,
  `nombreRol` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`idRol`),
  UNIQUE INDEX `nombreRol_UNIQUE` (`nombreRol` ASC) )
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `proyecto`.`persona` (
  `idPersona` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `primerApellido` VARCHAR(45) NOT NULL,
  `segundoApellido` VARCHAR(45) NULL,
  PRIMARY KEY (`idPersona`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `proyecto`.`usuario` (
  `nombreUsuario` VARCHAR(20) NOT NULL,
  `contrasenia` VARCHAR(100) NOT NULL,
  `token` INT NOT NULL,
  `idPersona` INT NOT NULL,
  `idRol` INT NOT NULL,
  PRIMARY KEY (`nombreUsuario`),
  INDEX `fk_Usuario_Persona1_idx` (`idPersona` ASC) ,
  INDEX `fk_Usuario_Rol1_idx` (`idRol` ASC) ,
  CONSTRAINT `fk_Usuario_Persona1`
    FOREIGN KEY (`idPersona`)
    REFERENCES `proyecto`.`persona` (`idPersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Usuario_Rol1`
    FOREIGN KEY (`idRol`)
    REFERENCES `proyecto`.`rol` (`idRol`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `proyecto`.`tipoContacto` (
  `idTipoContacto` INT NOT NULL AUTO_INCREMENT,
  `nombreTipo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idTipoContacto`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `proyecto`.`contacto` (
  `idContacto` INT NOT NULL AUTO_INCREMENT,
  `idPersona` INT NOT NULL,
  `idTipoContacto` INT NOT NULL,
  `contacto` VARCHAR(45) NOT NULL,
  INDEX `fk_contacto_tipoContacto1_idx` (`idTipoContacto` ASC) ,
  PRIMARY KEY (`idContacto`),
  CONSTRAINT `fk_contacto_Persona1`
    FOREIGN KEY (`idPersona`)
    REFERENCES `proyecto`.`persona` (`idPersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contacto_tipoContacto1`
    FOREIGN KEY (`idTipoContacto`)
    REFERENCES `proyecto`.`tipoContacto` (`idTipoContacto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `proyecto`.`direccion` (
  `idDireccion` INT NOT NULL AUTO_INCREMENT,
  `latitud` DECIMAL NOT NULL,
  `longitud` DECIMAL NOT NULL,
  `altitud` DECIMAL NOT NULL,
  PRIMARY KEY (`idDireccion`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `proyecto`.`sucursalTieneUsuario` (
  `idSucursal` INT NOT NULL,
  `nombreUsuario` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`idSucursal`, `nombreUsuario`),
  INDEX `fk_Sucursal_has_Usuario_Usuario1_idx` (`nombreUsuario` ASC) ,
  INDEX `fk_Sucursal_has_Usuario_Sucursal1_idx` (`idSucursal` ASC) ,
  CONSTRAINT `fk_Sucursal_has_Usuario_Sucursal1`
    FOREIGN KEY (`idSucursal`)
    REFERENCES `proyecto`.`sucursal` (`idSucursal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Sucursal_has_Usuario_Usuario1`
    FOREIGN KEY (`nombreUsuario`)
    REFERENCES `proyecto`.`usuario` (`nombreUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `proyecto`.`pedido` (
  `idPedido` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATETIME NOT NULL,
  `costoTotal` DECIMAL NOT NULL,
  `cantidadPago` DECIMAL NOT NULL,
  `horaEntrega` TIME NULL,
  `status` VARCHAR(25) NOT NULL,
  `nombreUsuario` VARCHAR(20) NOT NULL,
  `idDireccion` INT NOT NULL,
  `idSucursal` INT NOT NULL,
  PRIMARY KEY (`idPedido`),
  INDEX `fk_Pedido_Usuario1_idx` (`nombreUsuario` ASC) ,
  INDEX `fk_Pedido_Direccion1_idx` (`idDireccion` ASC) ,
  INDEX `fk_Pedido_Sucursal1_idx` (`idSucursal` ASC) ,
  CONSTRAINT `fk_Pedido_Usuario1`
    FOREIGN KEY (`nombreUsuario`)
    REFERENCES `proyecto`.`usuario` (`nombreUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedido_Direccion1`
    FOREIGN KEY (`idDireccion`)
    REFERENCES `proyecto`.`direccion` (`idDireccion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedido_Sucursal1`
    FOREIGN KEY (`idSucursal`)
    REFERENCES `proyecto`.`sucursal` (`idSucursal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `proyecto`.`pedidoTienePlatillo` (
  `idPedido` INT NOT NULL,
  `idMenuPlatillo` INT NOT NULL,
  `cantidad` INT NOT NULL,
  `comentario` TEXT NULL,
  PRIMARY KEY (`idPedido`, `idMenuPlatillo`),
  INDEX `fk_Pedido_has_Platillos_en_Menu_Platillos_en_Menu1_idx` (`idMenuPlatillo` ASC) ,
  INDEX `fk_Pedido_has_Platillos_en_Menu_Pedido1_idx` (`idPedido` ASC) ,
  CONSTRAINT `fk_Pedido_has_Platillos_en_Menu_Pedido1`
    FOREIGN KEY (`idPedido`)
    REFERENCES `proyecto`.`pedido` (`idPedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedido_has_Platillos_en_Menu_Platillos_en_Menu1`
    FOREIGN KEY (`idMenuPlatillo`)
    REFERENCES `proyecto`.`platilloEnMenu` (`idMenuPlatillo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `proyecto`.`promocion` (
  `idPromocion` INT NOT NULL AUTO_INCREMENT,
  `descrpicion` VARCHAR(100) NOT NULL,
  `fechaInicio` DATETIME NOT NULL,
  `fechaFin` DATETIME NOT NULL,
  `status` TINYINT NOT NULL,
  `precio` DECIMAL NOT NULL,
  PRIMARY KEY (`idPromocion`))
ENGINE = InnoDB;



-- `pedidoTienePromocion`

CREATE TABLE IF NOT EXISTS `proyecto`.`pedidoTienePromocion` (
  `idPedido` INT NOT NULL,
  `idPromocion` INT NOT NULL,
  `cantidad` INT NOT NULL,
  PRIMARY KEY (`idPedido`, `idPromocion`),
  INDEX `fk_pedido_has_promocion_promocion1_idx` (`idPromocion` ASC) ,
  INDEX `fk_pedido_has_promocion_pedido1_idx` (`idPedido` ASC) ,
  CONSTRAINT `fk_pedido_has_promocion_pedido1`
    FOREIGN KEY (`idPedido`)
    REFERENCES `proyecto`.`pedido` (`idPedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedido_has_promocion_promocion1`
    FOREIGN KEY (`idPromocion`)
    REFERENCES `proyecto`.`promocion` (`idPromocion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `proyecto`.`ponderacion` (
  `idPonderacion` INT NOT NULL AUTO_INCREMENT,
  `ponderacion` INT NULL,
  `comentario` VARCHAR(200) NULL,
  `idPedido` INT NOT NULL,
  PRIMARY KEY (`idPonderacion`),
  INDEX `fk_ponderacion_pedido1_idx` (`idPedido` ASC) ,
  CONSTRAINT `fk_ponderacion_pedido1`
    FOREIGN KEY (`idPedido`)
    REFERENCES `proyecto`.`pedido` (`idPedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `proyecto`.`precio` (
  `idPrecio` INT NOT NULL AUTO_INCREMENT,
  `precio` DECIMAL NOT NULL,
  `idPlatillo` INT NOT NULL,
  PRIMARY KEY (`idPrecio`),
  INDEX `fk_precio_platillo1_idx` (`idPlatillo` ASC) ,
  CONSTRAINT `fk_precio_platillo1`
    FOREIGN KEY (`idPlatillo`)
    REFERENCES `proyecto`.`platillo` (`idPlatillo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `proyecto`.`promocionTienePlatillo` (
  `idPromocion` INT NOT NULL,
  `idPlatillo` INT NOT NULL,
  PRIMARY KEY (`idPromocion`, `idPlatillo`),
  INDEX `fk_promocion_has_platillo_platillo1_idx` (`idPlatillo` ASC) ,
  INDEX `fk_promocion_has_platillo_promocion1_idx` (`idPromocion` ASC) ,
  CONSTRAINT `fk_promocion_has_platillo_promocion1`
    FOREIGN KEY (`idPromocion`)
    REFERENCES `proyecto`.`promocion` (`idPromocion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_promocion_has_platillo_platillo1`
    FOREIGN KEY (`idPlatillo`)
    REFERENCES `proyecto`.`platillo` (`idPlatillo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


USE proyecto;
INSERT INTO rol (nombreRol) VALUES ("Administrador"), ("Chef"), ("Repartidor"), ("Cliente");
INSERT INTO persona (nombre, primerApellido, segundoApellido) VALUES ("María", "Leticia", "Mendoza"), ("Esmeralda", "Lara", "Arroyo"), 
("Manuel", "Aldana", "Molina"), ("Noelia Guadalupe" ,"Bahena", "Popoca"), ("Sebastián", "Sota", "García");
INSERT INTO usuario (nombreUsuario, contrasenia, token, idPersona, idRol) VALUES 
("MariLe", "mari01", 987654323, 1, 1), ("EsmeLa", "esme62", 345698765, 2, 2), ("Manito", "mani35", 567896132, 3, 2), 
("NoeBap", "noe1616", 987531457, 4, 3), ("Sebita", "seba45", 986317642, 5, 4);
INSERT INTO direccion (latitud, longitud, altitud) VALUES 
(20.9137, -99.6106, 17.2345), (23.4338, -104.2710, 20.2134), 
(17.3925, -97.0753, 15.5426), (17.3213, -90.1034, 15.7262), (24.7273, -97.6718, 23.9324);
INSERT INTO sucursal (nombreSucursal, idDireccion) VALUES ("Temixco", 1), ("Cuernavaca", 2), ("Jiutepec", 3), ("Xochitepec", 4), ("Alpuyeca", 5);
INSERT INTO sucursalTieneUsuario (idSucursal, nombreUsuario) VALUES 
(1, "MariLe"), (2,"EsmeLa"), (3, "Manito"), (4, "NoeBap"), (5, "Sebita");
INSERT INTO tipoContacto (nombreTipo) VALUES ("Whatsapp"), ("Facebook");
INSERT INTO contacto (idPersona, idTipoContacto, contacto) VALUES (1, 1, "777-123-1244"), (2, 1, "777-875-4312"), (3, 1, "777-986-4528"), (4, 1, "777-263-7352"), (5, 1, "777-937-6241");
INSERT INTO tipoMenu (nombreTMenu, modoMenu) VALUES ("Desayuno", 0), ("Almuerzo", 1), ("Comida", 1);
INSERT INTO menu (nombreMenu, idTipoMenu, idSucursal) VALUES ("Enchiladazo", 1, 2), ("Pozolero", 2, 1), ("Taquiza", 3, 3), ("Sopazo", 1, 4), ("Chilaquilazo", 2, 5);
INSERT INTO menuDia (fecha, status, idMenu) VALUES ("2020-11-27", 1, 1), ("2020-12-01", 8, 2), ("2020-12-12", 4, 3), ("2020-12-24", 13, 4), ("2020-12-31", 5, 5);
INSERT INTO tipoPlatillo (nombreTipo) VALUES ("Entrada"), ("Plato fuerte"), ("Guarnición"), ("Postre"), ("Bebida");
INSERT INTO platillo (nombrePlatillo, tiempoPreparacion, descripcion, idTipoPlatillo) VALUES 
("Enchiladas rojas", 35 , "Aperitivo con chiles guajillo, se prepara en aproximadamente 35 minutos.", 2), 
("Mole de olla", 105, "Platillo típico del país caracterizado por su mezcla de chiles y especias.", 1), 
("Alfajor de chocolate", 60, "Galleta redonda separada por relleno de cajeta y bañada en chocolate.", 4), 
("Ensalada", 30, "Ensalada de quinoa con adelezo de limón", 3), ("Cocktail negroni", 15, "Clásico italiano ideal para el plato fuerte.", 5);
INSERT INTO preparacion (descripcion, idPlatillo) VALUES 
("Tuesta ligeramente los chiles en un comal caliente, presionándolos con la ayuda de una espátula, pero asegurándote de no quemarlos. (Este paso toma sólo unos segundos en cada lado).Una vez asados los chiles colócalos en una cacerola con agua y cocínalos a fuego lento durante 15 minutos, o hasta que estén blandos.", 1), 
("Enjuaga la carne y sécala con una toalla de papel. En una olla grande, coloca la carne, la cebolla, el ajo y las hojas de laurel. Cúbrala con 8 tazas de agua. Lleva a ebullición, cubra y reduzca el fuego a lento. Usa una cuchara con ranuras para eliminar la espuma que flota en  la superficie. La carne tardará de 1 ½ a 2 horas en cocinarse. Mientras la carne se cocina, prepara la salsa. Llena una olla pequeña con 2 tazas de agua caliente y reserve. Me gusta asar los ingredientes de la salsa ya que le da un sabor rústico a la sopa. Ahora, asa ligeramente los chiles en una comal caliente, recuerda darles la vuelta, este paso solo demora unos segundos, si dejas los chiles más tiempo en la comal caliente, tendrán un sabor amargo. Coloca los chiles asados ​​en la olla con el agua caliente para ablandarlos, al menos 15 minutos.", 2), 
("Hacer la masa tal como se muestra en el video, agregandole la azúcar, la manteca líquida, la fécula de maíz, 1 huevo, miel y cacao a gusto.", 3), 
("En un bowl mezcla la quinoa con las espinacas, el pimiento morrón, los arándanos y el aguacate. Para la vinagreta, mezcla el jugo de limón con el aceite de oliva, el jengibre y la sal hasta emulsionar. Baña la ensalada con la vinagreta.", 4), 
("Verter todos los ingredientes dentro de un vaso con hielo, revolver y decorar con una rodaja de naranja.", 5);
INSERT INTO unidadMedida (tipoUnidad, abreviatura) VALUES ("Litro", "l"), ("Kilogramo", "kg"), ("Gramo", "g"), ("Onza", "oz"), ("Mililitro", "ml");
INSERT INTO areaIngrediente (nombreArea) VALUES ("Carnes"), ("Verduras"), ("Frutas"), ("Pescados"), ("Lacteos");
INSERT INTO ingrediente (nombreIngrediente, idAreaIngrediente) VALUES ("Chile guajillo", 3), ("Brocoli", 2), ("Ajo", 2), ("Lechuga", 2), ("Carnde de cerdo", 1);
INSERT INTO ingredienteEnPlatillo (idIngrediente, idPlatillo, porcion, idUnidadMedida) VALUES (1, 1, 9, 3), (3, 1, 3.5, 3), (2, 4, 10, 3), (5, 1, 30, 3), (4, 4, 20, 3);
INSERT INTO dia (nombreDia) VALUES ("Lunes"), ("Martes"), ("Miércoles"), ("Jueves"), ("Viernes");
INSERT INTO precio (idPrecio, precio, idPlatillo) VALUES (1, 150.0, 1),(2,50.0,2),(3,149.50,3),(4,90.0,4),(5,80.50,5);
INSERT INTO tipodia(diaCompra,diaRegistro,idSucursal,idDia) VALUES (17,18,1,1),(20,22,1,2),(7,10,1,3),(10,11,1,4),(25,26,1,5);
INSERT INTO platilloenmenu(idMenuPlatillo,cantidadEstimada,status,idMenu,idPlatillo) VALUES (1,10,1,1,1),(2,8,2,2,2),(3,20,1,3,3),(4,10,2,4,4),(5,14,2,5,5);
INSERT INTO pedido(fecha,costoTotal,cantidadPago,horaEntrega,status,nombreUsuario,idDireccion,idSucursal) 
VALUES ("2020-12-01","120","150","14:30","Preparación","Sebita","1","1"), ("2020-12-01","120","150","14:30","Preparación","Sebita","1","1"),("2020-11-29","80","80","10:30","Entregado","Sebita","1","1"),
("2020-11-30","420","500","15:30","Entregado","Sebita","4","4"),("2020-12-01","120","150","12:30","Preparación","Sebita","2","2");
INSERT INTO promocion(idPromocion, descrpicion,fechaInicio,fechaFin,status,precio) VALUES (1, "Lleva 2 tacos mas en tu orden","2020-12-01","2020-12-03","1","12.00"),
(2, "Lleva 4 tacos mas en tu orden","2020-11-12","2020-11-12","0","15.00"),(3, "Lleva una orden de tacos en tu pozole","2020-12-05","2020-12-05","1","0.00"),
(4, "Lleva dos ordenes de chilaquiles rojos","2020-12-02","2020-12-03","1","45.00"),(5, "Lleva 2 enchiladas extra en la compra de una orden","2020-12-03","2020-12-03","1","0.00");
INSERT INTO promociontieneplatillo(idPromocion,idPlatillo) VALUES (1,1),(2,2),(3,3),(4,4),(5,5);
INSERT INTO pedidotieneplatillo (idPedido,idMenuPlatillo,cantidad,comentario) VALUES (1,2,3,"Sin salsa verde, solo roja"), (1,3,2,""),(3,1,5,"Sin cebolla y sin jitomate"), (3,4,1,""), (4,2,2,"Sin limón");
INSERT INTO ponderacion(idPonderacion, ponderacion, comentario, idPedido) VALUES (1, 5, 'La comida estaba muy rica', 1),
(2, 3, 'Llego muy tarde mi comida', 2), (3, 4, 'La comida estaba buena', 3), (4, 2, 'No me llego lo que ordene', 4), (5, 5, 'Muy buena la comida', 5);
INSERT INTO ImagenPlatillo (idPlatillo, img) VALUES (1, 178560), (2, 32), (3, 2), (4, 65), (5, 87);
INSERT INTO pedidoTienePromocion(idPedido, idPromocion, cantidad) VALUES (1, 1, 2), (2, 2, 4), (3, 3, 6), (4, 4, 8), (5, 5, 10);