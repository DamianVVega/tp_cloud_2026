-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-06-2026 a las 23:14:30
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `baratito`
--
CREATE DATABASE IF NOT EXISTS `baratito` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `baratito`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `apertura`
--

DROP TABLE IF EXISTS `apertura`;
CREATE TABLE `apertura` (
  `idaperturas` int(11) NOT NULL,
  `ape_fecha` date NOT NULL,
  `ape_hora` time NOT NULL,
  `ape_monto` int(11) NOT NULL,
  `idusuarios` int(11) NOT NULL,
  `ape_estado` varchar(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cierre`
--

DROP TABLE IF EXISTS `cierre`;
CREATE TABLE `cierre` (
  `idcierre` int(11) NOT NULL,
  `cie_fecha` date NOT NULL,
  `cie_hora` time NOT NULL,
  `cie_monto` int(11) NOT NULL,
  `idaperturas` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

DROP TABLE IF EXISTS `clientes`;
CREATE TABLE `clientes` (
  `idclientes` int(11) NOT NULL,
  `cli_nombre` varchar(30) NOT NULL,
  `cli_apellido` varchar(30) NOT NULL,
  `cli_ci` varchar(15) NOT NULL,
  `cli_telefono` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`idclientes`, `cli_nombre`, `cli_apellido`, `cli_ci`, `cli_telefono`) VALUES
(2, 'Mara', 'Herrera', '5678678', '0992333111'),
(4, 'Juan', 'Perez', '11111', '121212'),
(5, 'Emilio', 'Sosa', '123123', '22222');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compras`
--

DROP TABLE IF EXISTS `compras`;
CREATE TABLE `compras` (
  `idcompras` int(11) NOT NULL,
  `com_fecha` date DEFAULT NULL,
  `com_condicion` varchar(15) NOT NULL,
  `com_estado` varchar(15) NOT NULL,
  `idproveedores` int(11) NOT NULL,
  `idusuarios` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallecompras`
--

DROP TABLE IF EXISTS `detallecompras`;
CREATE TABLE `detallecompras` (
  `idcompras` int(11) NOT NULL,
  `idproductos` int(11) NOT NULL,
  `det_cantidad` int(11) NOT NULL,
  `det_precio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalleventas`
--

DROP TABLE IF EXISTS `detalleventas`;
CREATE TABLE `detalleventas` (
  `idventas` int(11) NOT NULL,
  `idproductos` int(11) NOT NULL,
  `det_cantidad` int(11) NOT NULL,
  `det_precio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detalleventas`
--

INSERT INTO `detalleventas` (`idventas`, `idproductos`, `det_cantidad`, `det_precio`) VALUES
(6, 4, 50, 10000),
(7, 4, 10, 10000),
(8, 4, 5, 10000),
(9, 7, 4, 8700),
(10, 6, 1, 5600),
(11, 6, 1, 5600),
(12, 6, 1, 5600);

--
-- Disparadores `detalleventas`
--
DROP TRIGGER IF EXISTS `trg_before_insert_detalleventas`;
DELIMITER $$
CREATE TRIGGER `trg_before_insert_detalleventas` BEFORE INSERT ON `detalleventas` FOR EACH ROW BEGIN
    DECLARE stock_actual INT;

    -- Obtener la cantidad actual del producto
    SELECT pro_cantidad INTO stock_actual
    FROM productos
    WHERE idproductos = NEW.idproductos;

    -- Verificar si hay suficiente stock
    IF stock_actual < NEW.det_cantidad OR stock_actual < 1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'No hay suficiente stock para realizar la venta';
    ELSE
        -- Actualizar la cantidad en productos
        UPDATE productos
        SET pro_cantidad = pro_cantidad - NEW.det_cantidad
        WHERE idproductos = NEW.idproductos;
    END IF;

END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personales`
--

DROP TABLE IF EXISTS `personales`;
CREATE TABLE `personales` (
  `idpersonales` int(11) NOT NULL,
  `per_nombre` varchar(30) NOT NULL,
  `per_apellido` varchar(30) NOT NULL,
  `per_ci` varchar(15) NOT NULL,
  `per_telefono` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `personales`
--

INSERT INTO `personales` (`idpersonales`, `per_nombre`, `per_apellido`, `per_ci`, `per_telefono`) VALUES
(1, 'Angel', 'Vega', '5794932', '0992341886'),
(2, 'lupe', 'allende', '123123', '12434324');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

DROP TABLE IF EXISTS `productos`;
CREATE TABLE `productos` (
  `idproductos` int(11) NOT NULL,
  `pro_nombre` varchar(30) NOT NULL,
  `pro_precio` int(11) NOT NULL,
  `pro_cantidad` int(11) NOT NULL,
  `pro_iva` varchar(8) DEFAULT NULL,
  `pro_costo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`idproductos`, `pro_nombre`, `pro_precio`, `pro_cantidad`, `pro_iva`, `pro_costo`) VALUES
(4, 'Leche Trebol', 10000, 100, '10', 5000),
(6, 'Pan Integral- Paquete', 5600, 67, '5', 4200),
(7, 'Leche Crecimiento', 8700, 8, 'EXENTA', 8000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

DROP TABLE IF EXISTS `proveedores`;
CREATE TABLE `proveedores` (
  `idproveedores` int(11) NOT NULL,
  `prov_nombre` varchar(30) NOT NULL,
  `prov_ruc` varchar(15) NOT NULL,
  `prov_telefono` varchar(15) NOT NULL,
  `prov_correo` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `proveedores`
--

INSERT INTO `proveedores` (`idproveedores`, `prov_nombre`, `prov_ruc`, `prov_telefono`, `prov_correo`) VALUES
(4, 'Enzo Calderon', '123123', '0992345345', 'enzo@gmail.com'),
(5, 'Luis Medina', '44444', '4314567', 'luis@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `idusuarios` int(11) NOT NULL,
  `usuario` varchar(20) DEFAULT NULL,
  `clave` varchar(100) DEFAULT NULL,
  `tipo` varchar(20) DEFAULT NULL,
  `estado` varchar(20) DEFAULT NULL,
  `idpersonales` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`idusuarios`, `usuario`, `clave`, `tipo`, `estado`, `idpersonales`) VALUES
(2, 'test', '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08', 'administrador', 'Activo', 1),
(4, 'root', '4813494d137e1631bba301d5acab6e7bb7aa74ce1185d456565ef51d737677b2', 'administrador', 'Activo', 1),
(5, 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'administrador', 'Activo', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

DROP TABLE IF EXISTS `ventas`;
CREATE TABLE `ventas` (
  `idventas` int(11) NOT NULL,
  `ven_fecha` date DEFAULT NULL,
  `ven_condicion` varchar(15) NOT NULL,
  `ven_estado` varchar(15) NOT NULL,
  `idclientes` int(11) NOT NULL,
  `idusuarios` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`idventas`, `ven_fecha`, `ven_condicion`, `ven_estado`, `idclientes`, `idusuarios`) VALUES
(6, '2025-06-30', 'Contado', 'Pendiente', 2, 2),
(7, '2025-06-30', 'Contado', 'Pendiente', 2, 2),
(8, '2025-06-30', 'Contado', 'Pendiente', 4, 2),
(9, '2025-06-30', 'Contado', 'Pendiente', 4, 2),
(10, '2025-07-01', 'Contado', 'Pendiente', 2, 2),
(11, '2025-07-01', 'Contado', 'Pendiente', 2, 2),
(12, '2025-07-01', 'Contado', 'Pendiente', 2, 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `apertura`
--
ALTER TABLE `apertura`
  ADD PRIMARY KEY (`idaperturas`);

--
-- Indices de la tabla `cierre`
--
ALTER TABLE `cierre`
  ADD PRIMARY KEY (`idcierre`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`idclientes`);

--
-- Indices de la tabla `compras`
--
ALTER TABLE `compras`
  ADD PRIMARY KEY (`idcompras`);

--
-- Indices de la tabla `personales`
--
ALTER TABLE `personales`
  ADD PRIMARY KEY (`idpersonales`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`idproductos`);

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`idproveedores`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`idusuarios`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`idventas`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `apertura`
--
ALTER TABLE `apertura`
  MODIFY `idaperturas` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cierre`
--
ALTER TABLE `cierre`
  MODIFY `idcierre` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `idclientes` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `compras`
--
ALTER TABLE `compras`
  MODIFY `idcompras` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `personales`
--
ALTER TABLE `personales`
  MODIFY `idpersonales` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `idproductos` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  MODIFY `idproveedores` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `idusuarios` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `idventas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
