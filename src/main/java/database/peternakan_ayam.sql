-- phpMyAdmin SQL Dump
-- version 5.2.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Oct 15, 2026 at 12:36 PM
-- Server version: 8.4.3
-- PHP Version: 8.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `peternakan_ayam`
--

-- --------------------------------------------------------

--
-- Table structure for table `jenis_ayam`
--

CREATE TABLE `jenis_ayam` (
  `id_jenis_ayam` int NOT NULL,
  `nama_jenis` varchar(60) NOT NULL,
  `harga_per_kg` double NOT NULL,
  `umur_panen` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `jenis_ayam`
--

INSERT INTO `jenis_ayam` (`id_jenis_ayam`, `nama_jenis`, `harga_per_kg`, `umur_panen`) VALUES
(1, 'Broiler', 35000, 35),
(2, 'Layer', 45000, 150),
(3, 'Ayam Kampung', 60000, 70);

-- --------------------------------------------------------

--
-- Table structure for table `kandang`
--

CREATE TABLE `kandang` (
  `id_kandang` int NOT NULL,
  `nama_kandang` varchar(80) NOT NULL,
  `kapasitas` int NOT NULL,
  `jumlah_ayam` int NOT NULL,
  `jenis_ayam` varchar(60) NOT NULL,
  `pakan_dibutuhkan` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `kandang`
--

INSERT INTO `kandang` (`id_kandang`, `nama_kandang`, `kapasitas`, `jumlah_ayam`, `jenis_ayam`, `pakan_dibutuhkan`) VALUES
(1, 'Kandang A', 100, 80, 'Broiler', 20),
(2, 'Kandang B', 150, 120, 'Layer', 30),
(3, 'Kandang C', 80, 60, 'Ayam Kampung', 15);

-- --------------------------------------------------------

--
-- Table structure for table `pakan`
--

CREATE TABLE `pakan` (
  `id_pakan` int NOT NULL,
  `nama_pakan` varchar(80) NOT NULL,
  `stok` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `pakan`
--

INSERT INTO `pakan` (`id_pakan`, `nama_pakan`, `stok`) VALUES
(1, 'Jagung', 500),
(2, 'Dedak', 300),
(3, 'Konsentrat', 250);

-- --------------------------------------------------------

--
-- Table structure for table `pemberian_pakan`
--

CREATE TABLE `pemberian_pakan` (
  `id_pemberian` int NOT NULL,
  `jumlah_pakan` int NOT NULL,
  `hari` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `pemberian_pakan`
--

INSERT INTO `pemberian_pakan` (`id_pemberian`, `jumlah_pakan`, `hari`) VALUES
(1, 20, 'Senin'),
(2, 25, 'Selasa'),
(3, 30, 'Rabu');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `jenis_ayam`
--
ALTER TABLE `jenis_ayam`
  ADD PRIMARY KEY (`id_jenis_ayam`);

--
-- Indexes for table `kandang`
--
ALTER TABLE `kandang`
  ADD PRIMARY KEY (`id_kandang`);

--
-- Indexes for table `pakan`
--
ALTER TABLE `pakan`
  ADD PRIMARY KEY (`id_pakan`);

--
-- Indexes for table `pemberian_pakan`
--
ALTER TABLE `pemberian_pakan`
  ADD PRIMARY KEY (`id_pemberian`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
