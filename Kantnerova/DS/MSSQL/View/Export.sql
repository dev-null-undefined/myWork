USE [master]
GO
/****** Object:  Database [kos3]    Script Date: 21/03/2020 21:08:25 ******/
CREATE DATABASE [kos3]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'kos3', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\kos3.mdf' , SIZE = 4288KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'kos3_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\kos3_log.ldf' , SIZE = 1072KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [kos3] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
    EXEC [kos3].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [kos3] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [kos3] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [kos3] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [kos3] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [kos3] SET ARITHABORT OFF 
GO
ALTER DATABASE [kos3] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [kos3] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [kos3] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [kos3] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [kos3] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [kos3] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [kos3] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [kos3] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [kos3] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [kos3] SET  ENABLE_BROKER 
GO
ALTER DATABASE [kos3] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [kos3] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [kos3] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [kos3] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [kos3] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [kos3] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [kos3] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [kos3] SET RECOVERY FULL 
GO
ALTER DATABASE [kos3] SET  MULTI_USER 
GO
ALTER DATABASE [kos3] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [kos3] SET DB_CHAINING OFF 
GO
ALTER DATABASE [kos3] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [kos3] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [kos3] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'kos3', N'ON'
GO
USE [kos3]
GO
/****** Object:  Table [dbo].[osoba]    Script Date: 21/03/2020 21:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[osoba]
(
    [id] [int] IDENTITY(1,1) NOT NULL,
    [primeni] [varchar](20) NOT NULL,
    [jmeno] [varchar](20) NOT NULL,
    [plat] [int] NULL,
    [profese_id] [int] NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[profese]    Script Date: 21/03/2020 21:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[profese]
(
    [id] [int] IDENTITY(1,1) NOT NULL,
    [nazev] [varchar](10) NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[obsazeni_profesi]    Script Date: 21/03/2020 21:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[obsazeni_profesi]
AS
    SELECT count(*) as pocet, profese.nazev as profese
    from osoba
        inner join profese on profese.id=osoba.profese_id
    group by profese.id,profese.nazev
GO
/****** Object:  View [dbo].[seznam_zamestnancu]    Script Date: 21/03/2020 21:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[seznam_zamestnancu]
AS
    SELECT osoba.jmeno, osoba.primeni, profese.nazev as profese
    from osoba
        inner join profese on profese.id=osoba.profese_id
GO
SET IDENTITY_INSERT [dbo].[osoba] ON 
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (1, N'Bugg', N'Kissiah', 3700, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (2, N'Terbruggen', N'Tori', 16376, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (3, N'Duxfield', N'Lindsey', 4783, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (4, N'Gallamore', N'Abba', 11865, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (5, N'Spreag', N'Trenton', 16450, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (6, N'Tippler', N'Faina', 11463, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (7, N'Plum', N'Jan', 7304, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (8, N'Castanos', N'Jemima', 14323, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (9, N'Ivanilov', N'Emelita', 20941, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (10, N'Tiller', N'Bertrand', 5732, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (11, N'Josowitz', N'Clerkclaude', 2375, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (12, N'McGenn', N'Renelle', 4121, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (13, N'Ambrogetti', N'Pearce', 24922, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (14, N'Kiefer', N'Holmes', 10694, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (15, N'Jacmar', N'Bourke', 12063, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (16, N'Fligg', N'Charil', 9955, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (17, N'English', N'Marcello', 6751, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (18, N'Wheatland', N'Yelena', 2683, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (19, N'McGeffen', N'Noami', 7411, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (20, N'Verecker', N'Brockie', 9360, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (21, N'McTrustram', N'Cristi', 13884, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (22, N'Tire', N'Dorie', 16149, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (23, N'Moors', N'Stern', 12722, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (24, N'Pardy', N'Winifred', 3902, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (25, N'Pund', N'Yardley', 17624, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (26, N'Fransseni', N'Gabrila', 8812, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (27, N'McKeever', N'Isabelita', 15613, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (28, N'Bellefonte', N'Benita', 5229, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (29, N'Thiem', N'Kikelia', 17472, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (30, N'Jerrems', N'Kandace', 14431, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (31, N'Faulks', N'Elberta', 15239, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (32, N'Pead', N'Jillane', 22973, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (33, N'Peachey', N'Wynne', 5963, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (34, N'Bovingdon', N'Reid', 19591, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (35, N'Patrie', N'Cathlene', 4582, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (36, N'Blazek', N'Rufus', 17703, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (37, N'Willwood', N'Henrieta', 16367, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (38, N'Sargant', N'Prudence', 2684, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (39, N'Wimbury', N'Rafaello', 12603, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (40, N'Byneth', N'Caitlin', 16658, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (41, N'Loblie', N'Haskel', 13728, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (42, N'Brandolini', N'Nelie', 22798, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (43, N'Madeley', N'Aurora', 16079, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (44, N'Tapenden', N'Neilla', 19384, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (45, N'Cantero', N'Olav', 21382, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (46, N'Mowsdell', N'Normand', 23986, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (47, N'Frodsham', N'Jocelyn', 14441, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (48, N'Kopf', N'Dianne', 14701, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (49, N'Lewknor', N'Catie', 3445, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (50, N'Abrahamian', N'Vale', 22965, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (51, N'Eagland', N'Arielle', 21404, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (52, N'Akast', N'Rubina', 23809, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (53, N'Grishanov', N'Cathlene', 9619, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (54, N'Spendley', N'Lorelle', 15599, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (55, N'Dyball', N'Rosabella', 24077, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (56, N'Blakeston', N'Rafe', 22650, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (57, N'French', N'Sigfried', 14598, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (58, N'Wain', N'Helaina', 21120, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (59, N'Cabel', N'Glori', 12505, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (60, N'Sproul', N'Yasmin', 6433, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (61, N'Mattielli', N'Lonni', 10492, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (62, N'Hollingsby', N'Ingmar', 23710, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (63, N'Nibloe', N'Amy', 18972, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (64, N'Merck', N'Sayer', 3166, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (65, N'Hallford', N'Lucina', 17719, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (66, N'Radwell', N'Kerwinn', 9392, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (67, N'Bertl', N'Hillary', 2903, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (68, N'Opfer', N'Norbie', 14877, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (69, N'Odgaard', N'Lory', 13343, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (70, N'Fronzek', N'Inna', 21916, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (71, N'Measey', N'Taddeo', 6221, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (72, N'Kolyagin', N'Cristie', 6793, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (73, N'McCullen', N'Dillon', 4252, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (74, N'Luparti', N'Amelie', 4355, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (75, N'Bifield', N'Twyla', 3464, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (76, N'Yallowley', N'Anita', 9204, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (77, N'Gothrup', N'Dalli', 17745, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (78, N'Germon', N'Vilhelmina', 5043, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (79, N'Clemo', N'Eamon', 10810, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (80, N'Daulby', N'Osgood', 3526, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (81, N'Ashelford', N'Berget', 10332, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (82, N'Kiddell', N'Christian', 19805, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (83, N'Conquer', N'Arri', 2601, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (84, N'Ruller', N'Juieta', 24025, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (85, N'Anshell', N'Clerkclaude', 13594, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (86, N'Wyllis', N'Elvina', 15472, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (87, N'Durak', N'Lilyan', 7339, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (88, N'Bleaden', N'Oliviero', 11922, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (89, N'Winram', N'Martita', 24174, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (90, N'Simek', N'Mayer', 6998, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (91, N'Hein', N'Dena', 14131, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (92, N'Syversen', N'Carlin', 3203, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (93, N'Stonier', N'Kendall', 13102, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (94, N'Kloska', N'Lucina', 15697, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (95, N'Axel', N'Archie', 11958, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (96, N'Yacob', N'Kurt', 8694, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (97, N'Ross', N'Rainer', 8154, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (98, N'Dunk', N'Talyah', 7007, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (99, N'Gait', N'Gianna', 20131, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (100, N'McCarney', N'Stephenie', 19001, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (101, N'Schule', N'Sly', 2147, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (102, N'Reade', N'Angelo', 19035, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (103, N'Melpuss', N'Maryl', 10090, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (104, N'Deftie', N'Gaylord', 7500, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (105, N'Gajownik', N'Hillary', 23433, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (106, N'D''Ruel', N'Thornie', 11572, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (107, N'Mannion', N'Brianne', 19911, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (108, N'Kendell', N'Gasparo', 10196, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (109, N'Handford', N'Hashim', 13327, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (110, N'Loveman', N'Natalya', 24566, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (111, N'Bateup', N'Frannie', 8181, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (112, N'Duell', N'Pancho', 11222, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (113, N'Prior', N'Liam', 21957, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (114, N'Cameron', N'Jessika', 10516, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (115, N'Baverstock', N'Crichton', 17592, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (116, N'Bollard', N'Jay', 8028, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (117, N'Bour', N'Irvin', 10139, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (118, N'Phifer', N'Boy', 2819, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (119, N'Redbourn', N'Leupold', 11256, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (120, N'Knowlton', N'Kristopher', 21751, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (121, N'Rosgen', N'Hayley', 15605, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (122, N'Bark', N'Ema', 3138, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (123, N'Gorick', N'Mariam', 16156, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (124, N'Heiner', N'Mick', 12364, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (125, N'Oakwood', N'Darcie', 6806, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (126, N'Suckling', N'Mureil', 16880, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (127, N'Gillott', N'Kamila', 2077, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (128, N'Geary', N'Oliviero', 4046, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (129, N'McNerlin', N'Teodorico', 3087, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (130, N'Danzig', N'Wrennie', 14042, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (131, N'MacDuff', N'Orren', 7048, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (132, N'Dooland', N'Tobye', 19087, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (133, N'Yandle', N'Sherline', 9041, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (134, N'Bedinn', N'Claresta', 15553, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (135, N'Denman', N'Theo', 15325, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (136, N'Benck', N'Fred', 9845, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (137, N'Munnery', N'Tarrah', 9421, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (138, N'Locock', N'Sherill', 15311, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (139, N'Paffitt', N'Garvin', 12018, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (140, N'Dabling', N'Lonny', 23822, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (141, N'Baldrick', N'Alick', 15369, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (142, N'Corder', N'Liana', 4885, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (143, N'Habens', N'Kristi', 3199, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (144, N'Moncarr', N'Yuri', 10096, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (145, N'Canaan', N'Tiffani', 21087, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (146, N'MacNamara', N'Angel', 19111, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (147, N'Prendeville', N'Pattie', 5740, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (148, N'Rogerot', N'Deny', 5796, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (149, N'Blissett', N'Starla', 7105, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (150, N'Dallemore', N'Dianne', 19845, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (151, N'Hiddsley', N'Bryan', 21771, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (152, N'Deary', N'Lizbeth', 20029, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (153, N'Tweedle', N'Kerry', 16832, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (154, N'Castilljo', N'Mikael', 24982, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (155, N'Steere', N'Deborah', 23362, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (156, N'Carlan', N'Jilly', 6026, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (157, N'Dainty', N'Caresse', 11558, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (158, N'Curtoys', N'Husein', 4426, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (159, N'Skeels', N'Carlos', 4743, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (160, N'Whall', N'Gabbey', 8070, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (161, N'Plumbe', N'Noami', 20658, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (162, N'Doutch', N'Esme', 17749, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (163, N'La Torre', N'Park', 21924, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (164, N'Haxbie', N'Roddie', 5266, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (165, N'Wombwell', N'Shay', 11166, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (166, N'Huskinson', N'Antonetta', 6593, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (167, N'Belderson', N'Alard', 23905, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (168, N'Hove', N'Perkin', 2252, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (169, N'Shippam', N'Belvia', 9273, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (170, N'Leet', N'Raymond', 14426, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (171, N'Penhaligon', N'Egor', 4230, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (172, N'Tremblett', N'Birgit', 9007, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (173, N'Maneylaws', N'Shepherd', 21479, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (174, N'Lockitt', N'Ardeen', 4635, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (175, N'Kliche', N'Gustavus', 15512, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (176, N'McLucas', N'Mackenzie', 17664, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (177, N'Daveley', N'Janel', 21999, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (178, N'Farnsworth', N'Tedmund', 17551, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (179, N'Aguilar', N'Barny', 6275, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (180, N'Butchart', N'Maxim', 4330, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (181, N'Sumnall', N'Doy', 12753, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (182, N'Millmore', N'Rivi', 8168, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (183, N'Renak', N'Jessie', 22271, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (184, N'Prime', N'Keefer', 21028, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (185, N'Paulino', N'Nick', 20175, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (186, N'Goodlife', N'Kip', 12765, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (187, N'Karsh', N'Damaris', 12839, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (188, N'Pancast', N'Gerrie', 17093, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (189, N'Pizer', N'Ester', 12791, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (190, N'Elvins', N'Ernestus', 4031, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (191, N'McInnerny', N'Romain', 16903, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (192, N'Sangwin', N'Michele', 16295, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (193, N'Koeppke', N'Janeczka', 22435, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (194, N'Monkeman', N'Kathy', 24326, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (195, N'Inglese', N'Sax', 18786, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (196, N'Flowers', N'Kerwinn', 15831, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (197, N'Hassin', N'Vinson', 22987, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (198, N'Nunson', N'Cammie', 18368, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (199, N'McLugish', N'Jeno', 22881, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (200, N'Formoy', N'Bonnee', 17819, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (201, N'Cullerne', N'Jeddy', 12592, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (202, N'Hounsome', N'Wit', 20708, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (203, N'Blatchford', N'Manuel', 23280, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (204, N'Herculeson', N'Travus', 2992, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (205, N'Stot', N'Adela', 8051, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (206, N'Creeghan', N'Skye', 17930, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (207, N'Kasper', N'Wilie', 21861, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (208, N'Koop', N'Farrand', 2752, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (209, N'Bowers', N'Ravid', 15588, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (210, N'Simkins', N'Duke', 9809, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (211, N'Sim', N'Rip', 5235, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (212, N'Cakes', N'Reynold', 6139, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (213, N'Stebbin', N'Rebeca', 13170, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (214, N'Elman', N'Delinda', 19366, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (215, N'Baumadier', N'Cesya', 12926, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (216, N'MacPake', N'Barry', 17561, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (217, N'Grindall', N'Burton', 18191, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (218, N'Radsdale', N'Heath', 9886, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (219, N'Buttery', N'Tarra', 10860, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (220, N'While', N'Stafford', 7021, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (221, N'Dougary', N'Cecilia', 3945, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (222, N'Bordman', N'Lionello', 19385, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (223, N'McFadin', N'Edmon', 17166, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (224, N'Swatheridge', N'Sula', 13376, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (225, N'Jakeman', N'Ardelia', 18974, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (226, N'Windows', N'Aharon', 5473, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (227, N'Mee', N'Padriac', 4756, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (228, N'Easseby', N'Allyn', 14372, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (229, N'De Domenico', N'Herby', 20096, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (230, N'Joseff', N'Dix', 18563, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (231, N'Cheston', N'Beauregard', 10776, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (232, N'Burbidge', N'Tammi', 17447, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (233, N'Guilloton', N'Loella', 10688, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (234, N'MacAvddy', N'Raina', 17941, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (235, N'People', N'Meier', 16934, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (236, N'Schade', N'Boone', 14730, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (237, N'Ive', N'Griffith', 12496, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (238, N'Korting', N'Anette', 18513, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (239, N'Cumberledge', N'Eve', 15566, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (240, N'Wardhough', N'Clementius', 5625, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (241, N'Penkman', N'Tova', 4977, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (242, N'Dowthwaite', N'Jillene', 21799, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (243, N'Grishukov', N'Aldric', 8035, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (244, N'Decourcy', N'Sayres', 8858, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (245, N'Wilcher', N'Alexis', 10515, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (246, N'Celez', N'Rhett', 20806, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (247, N'Hedin', N'Abraham', 18120, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (248, N'Gai', N'Huberto', 19356, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (249, N'Yokelman', N'Judas', 8522, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (250, N'Simao', N'Ginelle', 24668, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (251, N'Stenning', N'Darelle', 24377, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (252, N'Everwin', N'Inglis', 9139, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (253, N'Meacher', N'Quincy', 7959, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (254, N'Duly', N'Cleve', 6486, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (255, N'Pickring', N'Lew', 16091, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (256, N'Cases', N'Ulberto', 14958, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (257, N'Bees', N'Abagael', 7734, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (258, N'McInility', N'Rhys', 10524, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (259, N'Creeber', N'Kiley', 8759, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (260, N'Fullagar', N'Isaiah', 21267, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (261, N'Eefting', N'Minta', 18151, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (262, N'Craigheid', N'Jilleen', 20966, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (263, N'Tranter', N'Rodolfo', 24001, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (264, N'Furney', N'Blaire', 7656, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (265, N'Faragan', N'Faustina', 17491, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (266, N'Tailby', N'Cordi', 14270, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (267, N'Kiddy', N'Dollie', 23024, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (268, N'Salatino', N'Pauli', 22968, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (269, N'Blight', N'Trixy', 7675, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (270, N'Batrop', N'Othello', 14617, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (271, N'Novacek', N'Garrik', 14339, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (272, N'Blackler', N'Dolph', 9780, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (273, N'Penson', N'Emanuele', 4113, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (274, N'Cotton', N'Silvio', 16696, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (275, N'Lahrs', N'Northrop', 18389, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (276, N'Eyer', N'Melvyn', 22680, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (277, N'Zoanetti', N'Dinah', 20425, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (278, N'Kahen', N'Luca', 8627, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (279, N'Halbord', N'Katy', 5859, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (280, N'Colquyte', N'Amabelle', 12778, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (281, N'Eplate', N'Jacenta', 24300, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (282, N'Woolen', N'Tessy', 10853, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (283, N'Ravenhill', N'Ulysses', 14746, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (284, N'Oldam', N'Stevie', 11956, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (285, N'Tomczykowski', N'Lydon', 3820, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (286, N'Eagan', N'Morgan', 2862, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (287, N'Beckers', N'Roi', 18313, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (288, N'Pannett', N'Kyle', 7529, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (289, N'Stook', N'Chan', 9180, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (290, N'Conibere', N'Renato', 12388, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (291, N'Kryszkiecicz', N'Lisle', 16113, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (292, N'Sarson', N'Cynthia', 15322, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (293, N'Thomton', N'Scot', 2426, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (294, N'Di Filippo', N'Kelli', 22200, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (295, N'Gullick', N'Mayer', 23162, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (296, N'Trapp', N'Teena', 20397, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (297, N'Hearfield', N'Liana', 14097, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (298, N'Lindbergh', N'James', 4595, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (299, N'Matteo', N'Alexandr', 5113, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (300, N'Silley', N'Cthrine', 9860, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (301, N'Dilley', N'Astrid', 4481, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (302, N'Greber', N'Adolf', 11064, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (303, N'MacCall', N'Aube', 21005, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (304, N'Hankard', N'Nanice', 16683, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (305, N'Pignon', N'Phip', 13600, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (306, N'Sinfield', N'Giavani', 4119, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (307, N'Circuit', N'Verla', 23262, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (308, N'Trundler', N'Colan', 3755, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (309, N'Caser', N'Nadiya', 14420, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (310, N'Batalle', N'Rodrique', 23885, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (311, N'Andren', N'Ashli', 22057, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (312, N'Mariaud', N'Sarena', 24711, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (313, N'Janney', N'Marlo', 4643, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (314, N'Schwartz', N'Rachel', 20978, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (315, N'McKomb', N'Aguistin', 23079, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (316, N'Duffin', N'Seumas', 20619, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (317, N'Corbie', N'Rennie', 5728, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (318, N'MacGrath', N'Carly', 18352, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (319, N'Marzello', N'Leta', 9464, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (320, N'Di Baudi', N'Irene', 17083, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (321, N'Nashe', N'Pavla', 2072, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (322, N'Dolman', N'Gladi', 8212, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (323, N'Ranvoise', N'Federica', 15959, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (324, N'Yashin', N'Thorndike', 3388, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (325, N'Icom', N'Ewan', 14848, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (326, N'Rhys', N'Selina', 12635, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (327, N'Schankelborg', N'Yale', 4433, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (328, N'Arran', N'Rosette', 2022, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (329, N'Wornum', N'Marius', 20860, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (330, N'Fitchett', N'Kendra', 3738, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (331, N'Necrews', N'Theressa', 2945, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (332, N'Sodeau', N'Tiphanie', 3636, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (333, N'Matuskiewicz', N'Dottie', 12808, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (334, N'Merriott', N'Jermain', 3799, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (335, N'Channing', N'Amanda', 13132, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (336, N'Sagg', N'Tucker', 4497, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (337, N'Petkovic', N'Cammi', 4966, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (338, N'Losano', N'Kaylil', 12085, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (339, N'Dotson', N'Theo', 10722, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (340, N'Bigham', N'Matty', 15855, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (341, N'McCulley', N'Leonard', 13766, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (342, N'Burman', N'Franky', 7826, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (343, N'Girtin', N'Valera', 19553, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (344, N'Dillingstone', N'Dimitri', 23207, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (345, N'Horche', N'Codi', 21770, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (346, N'Calabry', N'Pamella', 23687, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (347, N'Trimble', N'Adrianne', 18093, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (348, N'Shitliff', N'Ringo', 3902, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (349, N'Collabine', N'Frasier', 24379, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (350, N'Rodbourne', N'Roberto', 10551, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (351, N'Kruschev', N'Olly', 9391, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (352, N'Lewsley', N'Martainn', 21560, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (353, N'Espadero', N'Elton', 24959, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (354, N'Hansley', N'Loria', 19761, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (355, N'Krishtopaittis', N'Cayla', 20802, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (356, N'Bigly', N'Leanor', 13322, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (357, N'Trowler', N'Clyde', 9497, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (358, N'Piele', N'Stanford', 23406, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (359, N'Skitch', N'Percival', 2657, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (360, N'Piccard', N'Demetris', 21346, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (361, N'Hanning', N'Munmro', 5248, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (362, N'Chiddy', N'Bailie', 11696, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (363, N'Broxap', N'Fredek', 8877, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (364, N'Cavolini', N'Kerby', 19245, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (365, N'Vittori', N'Allsun', 11732, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (366, N'Ackery', N'Madelina', 6738, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (367, N'Risely', N'Tabbitha', 22029, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (368, N'Linde', N'Avram', 23303, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (369, N'Denney', N'Bard', 18549, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (370, N'Le Frank', N'Tierney', 19833, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (371, N'Howroyd', N'Cecilius', 22954, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (372, N'Pennacci', N'Celestine', 22508, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (373, N'Wilshaw', N'Shanta', 2899, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (374, N'Crocker', N'Lesley', 5043, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (375, N'Lourens', N'Olimpia', 4380, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (376, N'Laurent', N'Harli', 8628, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (377, N'Hazleton', N'Burnard', 8318, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (378, N'Ennals', N'Templeton', 4851, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (379, N'Wrintmore', N'Carlie', 5913, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (380, N'Bunkle', N'Paolo', 2726, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (381, N'Furness', N'Wilhelm', 14959, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (382, N'Brundle', N'Vernon', 17929, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (383, N'Wipfler', N'Frannie', 13343, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (384, N'Astman', N'Ollie', 11784, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (385, N'Neicho', N'Stesha', 9257, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (386, N'MacClinton', N'Brit', 12092, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (387, N'Vanin', N'Joellyn', 7907, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (388, N'Seton', N'Kiersten', 16325, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (389, N'Duchart', N'Gasper', 12370, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (390, N'Redgewell', N'Ynes', 4241, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (391, N'Fearneley', N'Emerson', 11886, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (392, N'Christon', N'Arlette', 23059, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (393, N'Yeulet', N'Beverlie', 20715, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (394, N'Conibeer', N'Bartholomeo', 19009, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (395, N'Reade', N'Norean', 24768, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (396, N'Leeke', N'Ursuline', 18224, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (397, N'Hodge', N'Lyssa', 20420, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (398, N'Tailour', N'Kania', 3859, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (399, N'Enrique', N'Ham', 18738, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (400, N'Otley', N'Taddeo', 14222, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (401, N'Keelin', N'Esta', 16916, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (402, N'Russilll', N'Ezra', 5824, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (403, N'Peron', N'Tessie', 9923, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (404, N'Ritmeyer', N'Hew', 4481, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (405, N'Grabert', N'Kimbell', 18889, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (406, N'Force', N'Rana', 6431, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (407, N'Butt', N'Jerrie', 12809, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (408, N'Spavins', N'Bartholomeo', 11762, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (409, N'Bodocs', N'Jori', 7044, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (410, N'Curds', N'Farrell', 23513, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (411, N'O''Hartagan', N'Emmit', 21011, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (412, N'McBrearty', N'Reggi', 6696, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (413, N'Lambdin', N'Ted', 10094, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (414, N'Lomis', N'Rutter', 21133, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (415, N'Biddulph', N'Gary', 10466, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (416, N'Benzies', N'Rodolph', 21492, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (417, N'Ackland', N'Florette', 10294, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (418, N'Vonasek', N'Laverna', 11408, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (419, N'McCrillis', N'Christen', 13301, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (420, N'Sandland', N'Cairistiona', 13996, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (421, N'Brunone', N'Mischa', 9379, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (422, N'McEvoy', N'Hildagard', 13786, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (423, N'Beagan', N'Ardeen', 22522, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (424, N'Pipe', N'Gregorio', 7157, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (425, N'Wooland', N'Lavinie', 9531, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (426, N'Brownbridge', N'Magdalena', 18833, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (427, N'Bradlaugh', N'Rudolph', 13206, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (428, N'Furminger', N'Shawn', 17361, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (429, N'Adamoli', N'Artemus', 24746, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (430, N'King', N'Aarika', 9119, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (431, N'Duddle', N'Johnathon', 6797, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (432, N'Seward', N'Gabi', 24052, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (433, N'Filov', N'Dilly', 15468, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (434, N'Cavendish', N'Noelani', 5325, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (435, N'Eakly', N'Gisella', 19862, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (436, N'Gorrissen', N'Cindi', 17973, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (437, N'Woodroof', N'Mavra', 3675, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (438, N'Town', N'Felecia', 22651, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (439, N'Heakins', N'Adam', 19655, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (440, N'Lerigo', N'Farris', 14570, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (441, N'Nyssens', N'Barrett', 23547, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (442, N'Tarney', N'Rhea', 10345, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (443, N'Tenpenny', N'Tadeas', 2329, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (444, N'Manifould', N'Blythe', 2146, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (445, N'Schreiner', N'Townsend', 23504, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (446, N'Winnister', N'Tibold', 24768, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (447, N'Dufour', N'Damita', 14495, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (448, N'Eisikowitch', N'Costa', 4268, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (449, N'Bick', N'Tawnya', 23422, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (450, N'Capey', N'Doris', 22221, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (451, N'Candie', N'Gonzales', 7155, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (452, N'Hullock', N'Cary', 11697, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (453, N'Boissier', N'Tedie', 18442, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (454, N'Verna', N'Greer', 2084, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (455, N'Slaney', N'Shay', 18225, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (456, N'Dominiak', N'Westbrook', 2748, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (457, N'Crudge', N'Nolana', 6786, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (458, N'Piatek', N'Lesley', 22637, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (459, N'Dulwitch', N'Carolann', 15807, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (460, N'Doneld', N'Nigel', 21502, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (461, N'Egdal', N'Murvyn', 22399, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (462, N'Carlett', N'Karlie', 21781, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (463, N'Rudkin', N'Reggi', 12042, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (464, N'Clapton', N'Gavin', 11959, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (465, N'Quittonden', N'Roselle', 14928, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (466, N'McNeish', N'Pandora', 8225, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (467, N'Bromell', N'Yale', 22136, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (468, N'Eatock', N'Naoma', 17088, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (469, N'Wager', N'Sherye', 10462, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (470, N'O''Shea', N'Prisca', 20977, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (471, N'Wattingham', N'Thorn', 22190, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (472, N'Player', N'Idalina', 6837, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (473, N'Dilley', N'Timoteo', 11998, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (474, N'Elmhurst', N'Jess', 21790, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (475, N'Dollen', N'Beatrix', 20039, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (476, N'Willingham', N'Darell', 11747, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (477, N'Brashaw', N'Gayla', 2959, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (478, N'McGarrity', N'Garik', 23431, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (479, N'Fiorentino', N'Arlin', 7782, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (480, N'Wathen', N'Genevieve', 13417, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (481, N'Bushaway', N'Jenni', 19356, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (482, N'Breakspear', N'Winston', 22781, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (483, N'Joint', N'Rollo', 13485, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (484, N'Hamments', N'Nicoline', 10742, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (485, N'Craney', N'Hana', 7714, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (486, N'MacGorman', N'Mae', 10899, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (487, N'Abramovic', N'Cary', 14084, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (488, N'Syddie', N'Linet', 24167, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (489, N'Newberry', N'Fianna', 23062, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (490, N'Mallinder', N'Roldan', 24429, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (491, N'Perren', N'Caryl', 5758, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (492, N'Teale', N'Magdalena', 22868, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (493, N'Schnitter', N'Craggie', 5463, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (494, N'Denney', N'Bill', 6718, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (495, N'Cordell', N'Aleksandr', 20225, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (496, N'Ogers', N'Enrique', 23870, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (497, N'Bertomier', N'Teresina', 16858, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (498, N'Degoe', N'Alix', 2488, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (499, N'Swabey', N'Annice', 20684, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (500, N'Bushaway', N'Carol-jean', 21770, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (501, N'Pratley', N'Barbe', 3282, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (502, N'Ismead', N'Elsa', 10475, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (503, N'Hammant', N'Brad', 17919, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (504, N'Keddie', N'Roberto', 5202, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (505, N'Halleybone', N'Carmine', 17650, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (506, N'Cruddas', N'Clarance', 6483, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (507, N'Luety', N'Anica', 14371, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (508, N'Rebeiro', N'Brand', 21135, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (509, N'Laxton', N'Modesty', 9149, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (510, N'Penylton', N'Nedi', 20045, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (511, N'McKenna', N'Jean', 21904, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (512, N'Steanyng', N'Riley', 12351, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (513, N'Von Helmholtz', N'Felicity', 2748, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (514, N'Coger', N'Shara', 18995, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (515, N'Tackett', N'Colene', 9012, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (516, N'Wield', N'Marcelle', 7085, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (517, N'MacClure', N'Janek', 15064, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (518, N'Olifard', N'Bryana', 19450, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (519, N'Jory', N'Anna', 19558, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (520, N'Glas', N'Ainslee', 3840, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (521, N'Crucitti', N'Antoine', 17688, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (522, N'Durston', N'Agnes', 6365, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (523, N'Domenichelli', N'Vanni', 7582, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (524, N'MacFarlane', N'Prescott', 5394, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (525, N'Mottram', N'Melita', 9426, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (526, N'Garfield', N'Cyrus', 23426, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (527, N'Fulks', N'Cacilia', 15859, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (528, N'Colleran', N'Herculie', 22690, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (529, N'Neilly', N'Lindsy', 12723, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (530, N'Gouda', N'Elyse', 24361, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (531, N'Goodread', N'Asa', 21102, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (532, N'Vanacci', N'Sylvan', 16178, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (533, N'Wareing', N'Udale', 7912, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (534, N'Taks', N'Alvina', 24979, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (535, N'Wollen', N'Candi', 19023, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (536, N'Christofor', N'Binky', 19780, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (537, N'Very', N'Arlyn', 17418, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (538, N'Ambrogini', N'Marchall', 21366, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (539, N'Bolles', N'Carney', 5095, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (540, N'Morena', N'Duncan', 17039, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (541, N'Kryzhov', N'Lew', 23145, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (542, N'Hanney', N'Alexia', 23601, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (543, N'Gallaccio', N'Deeanne', 8191, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (544, N'Quarless', N'Garrott', 11651, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (545, N'Skace', N'Nikolaos', 11862, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (546, N'McPharlain', N'Eustacia', 3677, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (547, N'Asher', N'Lucho', 24369, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (548, N'Cauley', N'Davida', 18102, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (549, N'Wilfinger', N'Darren', 5515, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (550, N'Cozby', N'Siouxie', 11107, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (551, N'Gower', N'Con', 19266, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (552, N'Snell', N'Garvey', 10200, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (553, N'Ellacott', N'Robbyn', 15983, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (554, N'Lambricht', N'Bartholemy', 22076, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (555, N'Dadswell', N'Beilul', 16303, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (556, N'Sambrok', N'Devinne', 7196, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (557, N'Strut', N'Claiborne', 18517, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (558, N'Brittian', N'Wilburt', 20576, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (559, N'Blondell', N'Gage', 24829, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (560, N'Dulieu', N'Matilde', 3953, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (561, N'Leeke', N'Reilly', 11987, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (562, N'McLaughlin', N'Remus', 24395, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (563, N'Carrigan', N'Alisun', 13338, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (564, N'Greensmith', N'Ruthie', 12442, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (565, N'Eyree', N'Jewelle', 2787, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (566, N'Maplestone', N'Kimmy', 15972, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (567, N'Dockree', N'Kelley', 22479, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (568, N'Strivens', N'Beltran', 2052, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (569, N'Allaway', N'Charlena', 23112, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (570, N'Hallt', N'Conni', 7694, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (571, N'Dayes', N'Bonnie', 4290, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (572, N'Munnis', N'Bernelle', 17703, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (573, N'Bagniuk', N'Nicol', 23208, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (574, N'Riddiford', N'Danny', 3331, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (575, N'Scarisbrick', N'Tersina', 15804, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (576, N'Keaves', N'Bryn', 3865, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (577, N'Mashro', N'Sheree', 3972, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (578, N'Colleford', N'Isaak', 8096, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (579, N'MacIan', N'Ardisj', 16345, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (580, N'Sparkwell', N'Tarah', 5101, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (581, N'Shurey', N'Mela', 21469, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (582, N'Beer', N'Barney', 6233, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (583, N'Dobbins', N'Johan', 17223, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (584, N'Zuenelli', N'Ebony', 8108, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (585, N'Chuter', N'Maryrose', 6516, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (586, N'Rosenblum', N'Brigitta', 2420, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (587, N'Forde', N'Winston', 19308, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (588, N'Hemerijk', N'Torey', 9150, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (589, N'Gillings', N'Merrie', 2077, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (590, N'Allender', N'Casandra', 17097, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (591, N'Megson', N'Daryle', 9498, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (592, N'Poole', N'Jarvis', 6250, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (593, N'Wyett', N'Kym', 14184, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (594, N'Curee', N'Eba', 22405, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (595, N'Taree', N'Lazar', 20529, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (596, N'Nickell', N'Jennie', 9750, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (597, N'Nobes', N'Christye', 24818, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (598, N'L''argent', N'Dylan', 10865, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (599, N'Laudham', N'Saw', 22962, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (600, N'Dibden', N'Hermione', 20334, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (601, N'Lambarton', N'Cordi', 23982, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (602, N'Tranmer', N'Min', 10021, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (603, N'Renwick', N'Wilbur', 23702, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (604, N'Lyddyard', N'Hartwell', 8509, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (605, N'Vossing', N'Galven', 9110, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (606, N'Crilley', N'Carmencita', 8716, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (607, N'Breakspear', N'Alexis', 21736, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (608, N'De Filippo', N'Gabi', 14056, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (609, N'Burnapp', N'Jule', 11571, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (610, N'Fraulo', N'Engracia', 20599, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (611, N'St. Quintin', N'Phillipe', 3893, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (612, N'Simonetto', N'Joanna', 7666, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (613, N'Stinton', N'Leone', 21964, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (614, N'Bouts', N'Lurline', 17961, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (615, N'Crocumbe', N'Catlee', 14466, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (616, N'Fingleton', N'Kyle', 3644, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (617, N'Pottage', N'Simonne', 8773, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (618, N'Grint', N'Camille', 2792, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (619, N'Bridle', N'Britteny', 12703, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (620, N'Gartery', N'Ruggiero', 4692, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (621, N'Lamperti', N'Clemmie', 9511, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (622, N'Seer', N'Norman', 19838, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (623, N'Langdon', N'Meggie', 18919, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (624, N'Butting', N'Cornall', 2724, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (625, N'Bearsmore', N'Myrtle', 23096, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (626, N'Sanzio', N'Ario', 20382, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (627, N'Leeburn', N'Alvan', 3862, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (628, N'Slyford', N'Luisa', 10710, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (629, N'Mustoo', N'Nels', 14790, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (630, N'Hardaway', N'Adamo', 13871, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (631, N'Adlard', N'Andrey', 9196, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (632, N'Ferbrache', N'Hendrika', 4225, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (633, N'Coffee', N'Barbra', 15480, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (634, N'Faragan', N'Sharleen', 21299, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (635, N'Cordero', N'Gothart', 5246, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (636, N'de Najera', N'Sharlene', 16762, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (637, N'Marr', N'John', 20049, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (638, N'Rieme', N'Sid', 17378, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (639, N'Tripp', N'Concordia', 22306, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (640, N'O'' Driscoll', N'Eloisa', 20435, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (641, N'Walster', N'Odette', 3794, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (642, N'Robbe', N'Jamal', 24553, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (643, N'Seeks', N'Warren', 17574, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (644, N'Aloigi', N'Thane', 23092, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (645, N'Rohlfs', N'Patty', 6898, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (646, N'Tully', N'La verne', 24520, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (647, N'Joist', N'Stephenie', 10899, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (648, N'Folbige', N'Hurlee', 24200, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (649, N'Finder', N'Mahmud', 20622, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (650, N'Clute', N'Rudie', 13990, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (651, N'Dunwoody', N'Berne', 11265, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (652, N'Fricker', N'Garrott', 6237, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (653, N'MacDonagh', N'Janella', 14514, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (654, N'Wrought', N'Svend', 10530, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (655, N'Jubb', N'Erl', 12342, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (656, N'Ilyin', N'Chauncey', 8538, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (657, N'Leasor', N'Roxi', 9735, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (658, N'Groves', N'Griffie', 8843, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (659, N'Coppin', N'Kynthia', 12969, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (660, N'McAleese', N'Tabby', 14511, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (661, N'Kensit', N'Milty', 16746, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (662, N'Irnys', N'Penny', 19230, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (663, N'Crinion', N'Darbee', 17721, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (664, N'Garcia', N'Bobette', 13931, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (665, N'Steels', N'Oates', 24714, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (666, N'Kerley', N'Moses', 13460, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (667, N'Rickets', N'Ardys', 3065, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (668, N'Wateridge', N'Silvan', 19611, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (669, N'Dible', N'Gabi', 13631, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (670, N'Andrag', N'Hermy', 21879, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (671, N'Muress', N'Travis', 24913, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (672, N'Moorrud', N'Perice', 6798, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (673, N'Goodspeed', N'Martyn', 7568, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (674, N'Sturney', N'Hermon', 17261, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (675, N'Yanez', N'Sella', 7684, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (676, N'Lourenco', N'Sim', 9724, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (677, N'Petriello', N'Joan', 5549, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (678, N'Franzoli', N'Gayle', 14996, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (679, N'Tawn', N'Rorke', 7991, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (680, N'Worlock', N'Norby', 14083, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (681, N'Netti', N'Cati', 12810, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (682, N'Curteis', N'Lilias', 18897, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (683, N'Wilse', N'Kristos', 11459, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (684, N'Rioch', N'Madlen', 20853, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (685, N'Langdon', N'Dieter', 20148, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (686, N'Chapple', N'Averell', 9812, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (687, N'Pamment', N'Meir', 23301, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (688, N'Sulman', N'Charles', 3691, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (689, N'Skelding', N'Lynsey', 17682, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (690, N'Barock', N'Marlene', 11471, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (691, N'Spender', N'Fernandina', 23188, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (692, N'Pulver', N'Mala', 19423, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (693, N'Caven', N'Lelia', 19739, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (694, N'Howlett', N'Julissa', 11295, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (695, N'Osmond', N'Devonna', 5451, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (696, N'Fritchly', N'Deane', 19603, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (697, N'Dingivan', N'Brian', 9104, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (698, N'Kingh', N'Jacinthe', 20917, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (699, N'O''Shesnan', N'Harley', 8824, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (700, N'Hayto', N'Gustaf', 22089, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (701, N'Yallop', N'Gale', 11459, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (702, N'Pietrowski', N'Merrick', 6538, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (703, N'Pentony', N'Dirk', 12819, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (704, N'Greig', N'Rockwell', 18886, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (705, N'Stockdale', N'Eldin', 19803, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (706, N'Clulee', N'Otho', 11647, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (707, N'Jumonet', N'Fernanda', 19422, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (708, N'Scraney', N'Zacharie', 20961, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (709, N'MacIlhagga', N'Lindy', 3606, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (710, N'Brackenridge', N'Jordanna', 21524, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (711, N'Tabrett', N'Marcelia', 18984, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (712, N'Klich', N'Ewen', 4498, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (713, N'Trett', N'Marilee', 5925, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (714, N'Skeggs', N'Afton', 19405, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (715, N'Calderon', N'Rosetta', 23808, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (716, N'Zimmerman', N'Richmound', 12849, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (717, N'McCroft', N'Kendal', 11221, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (718, N'Pettendrich', N'Melisandra', 22185, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (719, N'Stritton', N'Cristian', 22232, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (720, N'Lenormand', N'Jephthah', 8580, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (721, N'Izhaky', N'Morgun', 12933, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (722, N'Player', N'Kellby', 9206, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (723, N'Woolger', N'Sutherlan', 20883, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (724, N'Deem', N'Rosa', 22077, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (725, N'Castagnier', N'Gherardo', 15037, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (726, N'Imlen', N'Solly', 20056, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (727, N'Fanton', N'Christean', 18900, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (728, N'Tennison', N'Naoma', 11235, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (729, N'Strutz', N'Perren', 19982, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (730, N'Tripe', N'Arel', 21885, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (731, N'Zeale', N'Gerek', 17229, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (732, N'Chominski', N'Nancy', 11524, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (733, N'Cobelli', N'Ronnica', 11123, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (734, N'Oxbrow', N'Terrell', 10318, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (735, N'McEnteggart', N'Mae', 14772, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (736, N'Leatherborrow', N'Nikita', 9105, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (737, N'Staniforth', N'Debby', 19239, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (738, N'Hamblyn', N'Rayner', 13085, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (739, N'Pessolt', N'Kenneth', 10543, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (740, N'Rubrow', N'Sutherlan', 20843, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (741, N'Dienes', N'Ammamaria', 18110, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (742, N'Kingdom', N'Jo-ann', 20591, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (743, N'Comben', N'Talia', 15734, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (744, N'Durgan', N'Venus', 5528, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (745, N'Duhig', N'Arlyne', 3085, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (746, N'Mertsching', N'Mallissa', 8493, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (747, N'Grzegorek', N'Colas', 21351, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (748, N'Gooderidge', N'Monique', 24136, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (749, N'Cullington', N'Kala', 15893, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (750, N'Kleinmintz', N'Kristopher', 6931, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (751, N'Rochester', N'Eula', 19298, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (752, N'Swinley', N'Danita', 8841, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (753, N'Arbon', N'Kissie', 9856, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (754, N'Dovington', N'Maybelle', 2113, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (755, N'Chadderton', N'Benton', 15361, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (756, N'Gammidge', N'Fredrick', 12573, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (757, N'Blackeby', N'Rafael', 10909, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (758, N'Quakley', N'Fin', 11348, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (759, N'Loffel', N'Nester', 10007, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (760, N'Pymm', N'Neale', 5820, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (761, N'Pirelli', N'Norry', 6142, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (762, N'Van den Velde', N'Michael', 3531, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (763, N'Louthe', N'Sandie', 16413, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (764, N'Brunskill', N'Cchaddie', 15150, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (765, N'Hulson', N'Merry', 19888, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (766, N'Leighton', N'Darcey', 16121, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (767, N'Haylock', N'Katlin', 17173, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (768, N'Samarth', N'Aguie', 22092, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (769, N'Barkworth', N'Elane', 17728, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (770, N'Nelissen', N'Gracia', 11855, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (771, N'Hilliam', N'Elsa', 24264, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (772, N'Brameld', N'Page', 24746, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (773, N'Goulthorp', N'Ricca', 17901, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (774, N'Hubbert', N'Andrea', 20306, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (775, N'Smitham', N'Felicity', 5248, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (776, N'Puddan', N'Sharyl', 12215, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (777, N'Brusby', N'Carolin', 7699, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (778, N'Keasley', N'Nero', 14835, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (779, N'Allon', N'Bel', 14796, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (780, N'Blanchard', N'Tedi', 13346, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (781, N'Matteo', N'Pebrook', 7525, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (782, N'Martinolli', N'Danika', 15910, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (783, N'Duffit', N'Valaria', 12955, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (784, N'Tschersich', N'Felice', 17728, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (785, N'Germon', N'Clayton', 19559, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (786, N'Groves', N'Rebecka', 18074, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (787, N'Dubock', N'Nobe', 10707, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (788, N'Prickett', N'Carmelia', 17463, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (789, N'Lillow', N'Lennard', 21842, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (790, N'Stoneman', N'Eugene', 7432, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (791, N'Saintsbury', N'Zaria', 13191, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (792, N'Bartkiewicz', N'Caresse', 23637, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (793, N'Petty', N'Lanette', 5963, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (794, N'Falls', N'Susann', 9701, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (795, N'Comizzoli', N'Putnem', 12602, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (796, N'Humburton', N'Alecia', 20978, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (797, N'Kemme', N'Willi', 3188, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (798, N'Conduit', N'Sheilakathryn', 15947, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (799, N'Borwick', N'Hartley', 12719, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (800, N'Hame', N'Rozelle', 8949, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (801, N'Drewes', N'Sheryl', 12615, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (802, N'Mortell', N'Trev', 20617, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (803, N'Shewan', N'Patrick', 21136, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (804, N'Lindemann', N'Elizabet', 10615, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (805, N'Cud', N'Abagail', 4227, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (806, N'Harbertson', N'Dorolice', 11559, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (807, N'McGregor', N'Benedict', 17218, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (808, N'O''Sharkey', N'Hartwell', 3829, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (809, N'Turmel', N'Sadie', 21169, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (810, N'O''Cuddie', N'Tommy', 4676, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (811, N'Cayle', N'Fidela', 18627, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (812, N'Bullan', N'Vasilis', 7942, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (813, N'Yele', N'Doll', 2174, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (814, N'Lisle', N'Broderick', 23481, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (815, N'Zoanetti', N'Doloritas', 12432, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (816, N'Provest', N'Grant', 17235, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (817, N'Manuelli', N'Papageno', 19460, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (818, N'Kmietsch', N'Kellby', 15053, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (819, N'Alenshev', N'Deloris', 4902, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (820, N'Daunter', N'Rebecca', 20101, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (821, N'Adolfsen', N'Beret', 6975, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (822, N'MacCumiskey', N'Euphemia', 13708, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (823, N'Kehoe', N'Drake', 18327, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (824, N'Bartosinski', N'Ferne', 22671, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (825, N'Puckett', N'Carney', 3601, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (826, N'Jiras', N'Luther', 14640, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (827, N'Jobbins', N'Erv', 7892, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (828, N'Farr', N'Falito', 22672, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (829, N'Markie', N'Beret', 9005, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (830, N'Weakley', N'Damara', 12333, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (831, N'Benadette', N'Aimil', 15630, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (832, N'Ivakhno', N'Lindsay', 14988, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (833, N'Brettell', N'Kelcie', 3439, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (834, N'Starte', N'Dannel', 11156, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (835, N'Friary', N'Katine', 5535, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (836, N'Cavanagh', N'Nicolai', 22194, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (837, N'Iveagh', N'Myrle', 19633, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (838, N'Kindell', N'Dasha', 18141, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (839, N'Phythien', N'Anna-diane', 6435, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (840, N'Jack', N'Hasty', 24171, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (841, N'Snow', N'Shurlock', 18859, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (842, N'Stait', N'Franklyn', 12427, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (843, N'Gatecliffe', N'Bernhard', 14226, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (844, N'Caw', N'Towny', 8143, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (845, N'Yukhin', N'Rollins', 12789, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (846, N'Haithwaite', N'Othella', 16557, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (847, N'Marlin', N'Bobby', 18866, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (848, N'Cluet', N'Bibi', 23105, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (849, N'Loveard', N'Gilbert', 21260, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (850, N'Manes', N'Hannah', 19076, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (851, N'Withrop', N'Wenona', 8203, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (852, N'Mortel', N'Ketty', 20487, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (853, N'Wetherick', N'Dyann', 19271, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (854, N'Northgraves', N'Kimberly', 10553, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (855, N'Braisted', N'Marlane', 3556, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (856, N'Size', N'Even', 5451, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (857, N'Mitie', N'Bonnibelle', 12927, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (858, N'Taysbil', N'Emyle', 8026, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (859, N'Klyn', N'Earvin', 7535, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (860, N'Warwicker', N'Christoforo', 21352, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (861, N'Fullerton', N'Abagael', 20187, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (862, N'Duchatel', N'Falkner', 13101, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (863, N'Diggar', N'Zeb', 4472, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (864, N'Tootell', N'Kliment', 17317, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (865, N'Date', N'Ambros', 10793, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (866, N'Casaletto', N'Jessy', 2705, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (867, N'Slograve', N'Hesther', 11983, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (868, N'Scotchmore', N'Jeffrey', 7420, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (869, N'Sherratt', N'Kean', 15322, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (870, N'Sheara', N'Sheelagh', 24713, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (871, N'Domney', N'Moselle', 15191, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (872, N'Fenners', N'Nita', 16788, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (873, N'Windrass', N'Preston', 16550, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (874, N'Beckett', N'Lizbeth', 20425, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (875, N'Bartrop', N'Malva', 20106, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (876, N'Marchand', N'Darline', 8457, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (877, N'Berrycloth', N'Englebert', 14469, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (878, N'Iacovone', N'Cullie', 2265, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (879, N'Gerok', N'Scarlett', 14740, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (880, N'Roofe', N'Karl', 7156, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (881, N'MacGibbon', N'Edmon', 19785, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (882, N'Hatje', N'Carmella', 23225, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (883, N'Cancellieri', N'Leora', 13875, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (884, N'Tomkies', N'Jocelyne', 18087, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (885, N'Chicchelli', N'Kimberley', 11943, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (886, N'Roscam', N'Kerrin', 3518, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (887, N'MacMillan', N'Shantee', 2420, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (888, N'Calterone', N'Ellis', 21989, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (889, N'Rumble', N'Hannis', 18008, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (890, N'Saurin', N'Homerus', 4392, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (891, N'Zarfai', N'Kerrie', 8268, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (892, N'Josskoviz', N'Frasco', 8507, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (893, N'Jessup', N'Margie', 17524, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (894, N'Hamments', N'Sim', 20429, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (895, N'Adnams', N'Devonne', 10489, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (896, N'O''Hagirtie', N'Stanleigh', 11721, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (897, N'Tilzey', N'Emory', 6767, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (898, N'Aston', N'Kellen', 19629, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (899, N'Weale', N'Kristan', 16396, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (900, N'Reignard', N'Karlis', 13650, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (901, N'Losseljong', N'Leonidas', 3423, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (902, N'Schoolfield', N'Rhea', 6269, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (903, N'Blanchette', N'Rozanna', 15801, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (904, N'Potier', N'Adolphus', 7766, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (905, N'Deschelle', N'Sande', 18371, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (906, N'Starking', N'Elianora', 5261, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (907, N'Strippel', N'Flori', 21329, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (908, N'Polak', N'Elvina', 17687, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (909, N'Emmatt', N'Quinta', 18431, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (910, N'Trenaman', N'Richmound', 13122, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (911, N'Shaxby', N'Basile', 2762, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (912, N'Tite', N'Leodora', 5237, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (913, N'Cowing', N'Minerva', 22835, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (914, N'Skaid', N'Townsend', 20343, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (915, N'Aris', N'Kirby', 7657, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (916, N'Easey', N'Monty', 12126, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (917, N'Cameron', N'Rania', 8624, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (918, N'Capron', N'Agustin', 16305, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (919, N'Tiley', N'Dan', 8266, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (920, N'Ramsby', N'Carmine', 20813, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (921, N'Novelli', N'Elvera', 20930, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (922, N'Emblem', N'Inglis', 11761, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (923, N'Prestner', N'Tabina', 5473, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (924, N'Wicher', N'Jo-anne', 10512, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (925, N'Finnick', N'Nissa', 10401, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (926, N'Hurch', N'Erin', 15859, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (927, N'Berthe', N'Kassi', 7557, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (928, N'Petras', N'Davin', 11811, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (929, N'Shakshaft', N'Clarita', 6247, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (930, N'Kightly', N'Almire', 5103, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (931, N'Lorraway', N'Cthrine', 17685, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (932, N'McAulay', N'Demetris', 5007, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (933, N'Denney', N'Misty', 16086, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (934, N'Whittlesea', N'Gregorio', 10120, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (935, N'Lighten', N'Marjory', 11226, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (936, N'Clethro', N'Clarice', 12492, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (937, N'Szymczyk', N'Percival', 24731, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (938, N'Pleasance', N'Nehemiah', 11127, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (939, N'Shoreman', N'Rafaelia', 3336, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (940, N'Vineall', N'Elysee', 16643, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (941, N'Nulty', N'Tova', 23431, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (942, N'Armsby', N'Udale', 11792, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (943, N'Knights', N'Shep', 22672, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (944, N'Studholme', N'Mark', 16275, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (945, N'Bayless', N'Adriena', 6704, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (946, N'Lebbern', N'Web', 17354, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (947, N'Crisp', N'Kirby', 3040, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (948, N'Pleass', N'Juieta', 19188, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (949, N'Pogosian', N'Ilene', 16521, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (950, N'Tollfree', N'Holli', 14915, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (951, N'Bowring', N'Patrick', 23485, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (952, N'Monument', N'Page', 17096, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (953, N'Edbrooke', N'Nerissa', 2476, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (954, N'Brounsell', N'Andrei', 8614, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (955, N'Sawrey', N'Cirillo', 6175, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (956, N'Bangs', N'Brady', 12539, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (957, N'Vizor', N'Ginger', 12163, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (958, N'Luto', N'Donella', 10505, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (959, N'Lark', N'Simonette', 5094, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (960, N'Winteringham', N'Friedrick', 23287, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (961, N'Noonan', N'Adham', 21945, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (962, N'Nolan', N'Egor', 8610, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (963, N'Burney', N'Erinna', 5757, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (964, N'McCormack', N'Rafaelia', 13277, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (965, N'Skoggins', N'Alissa', 11895, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (966, N'Keable', N'Maximo', 4070, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (967, N'Mearing', N'Nial', 16144, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (968, N'Marrow', N'Bree', 18277, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (969, N'Wakeford', N'Kara', 21594, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (970, N'Gamlyn', N'Tanney', 15445, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (971, N'Sussems', N'Gianni', 8761, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (972, N'Bowld', N'Dulsea', 5728, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (973, N'Symers', N'Mischa', 12603, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (974, N'Potzold', N'Marlane', 16471, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (975, N'Ducham', N'Lisle', 4569, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (976, N'Kettlesing', N'Andras', 11223, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (977, N'Lawdham', N'Bernete', 14935, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (978, N'Gibbeson', N'Berke', 3760, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (979, N'Heinssen', N'Lamond', 23700, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (980, N'Pidwell', N'Fairleigh', 19189, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (981, N'Harbisher', N'Ian', 16247, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (982, N'Kaplan', N'Marlow', 2757, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (983, N'Paffett', N'Merissa', 15532, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (984, N'Looks', N'Danette', 11406, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (985, N'Jonin', N'Catherine', 8189, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (986, N'Tooke', N'Brewster', 18947, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (987, N'Cours', N'Dannie', 8813, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (988, N'Ashlee', N'Christian', 23627, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (989, N'Wark', N'Ahmed', 5576, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (990, N'Hatherley', N'Ardine', 15576, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (991, N'Yter', N'Samuel', 24694, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (992, N'Petrussi', N'Bambi', 4343, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (993, N'Stolle', N'Lorenzo', 7899, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (994, N'Plain', N'Shelbi', 16151, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (995, N'Gershom', N'Greggory', 15826, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (996, N'Mapes', N'Hamel', 19657, 3)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (997, N'Blackaller', N'Marcelia', 10142, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (998, N'Griston', N'Billi', 16783, 2)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (999, N'Vase', N'Engracia', 11821, 1)
GO
INSERT [dbo].[osoba]
    ([id], [primeni], [jmeno], [plat], [profese_id])
VALUES
    (1000, N'McHenry', N'Cacilia', 7691, 3)
GO
SET IDENTITY_INSERT [dbo].[osoba] OFF
GO
SET IDENTITY_INSERT [dbo].[profese] ON 
GO
INSERT [dbo].[profese]
    ([id], [nazev])
VALUES
    (1, N'Kuchař')
GO
INSERT [dbo].[profese]
    ([id], [nazev])
VALUES
    (2, N'Číšník')
GO
INSERT [dbo].[profese]
    ([id], [nazev])
VALUES
    (3, N'Skladník')
GO
SET IDENTITY_INSERT [dbo].[profese] OFF
GO
ALTER TABLE [dbo].[osoba]  WITH CHECK ADD FOREIGN KEY([profese_id])
REFERENCES [dbo].[profese] ([id])
GO
USE [master]
GO
ALTER DATABASE [kos3] SET  READ_WRITE 
GO
