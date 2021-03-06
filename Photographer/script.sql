USE [master]
GO
/****** Object:  Database [Photographer]    Script Date: 4/27/2020 13:28:28 ******/
CREATE DATABASE [Photographer]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Photographer', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MINHTB\MSSQL\DATA\Photographer.mdf' , SIZE = 3264KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'Photographer_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MINHTB\MSSQL\DATA\Photographer_log.ldf' , SIZE = 832KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [Photographer] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Photographer].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Photographer] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Photographer] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Photographer] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Photographer] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Photographer] SET ARITHABORT OFF 
GO
ALTER DATABASE [Photographer] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [Photographer] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Photographer] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Photographer] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Photographer] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Photographer] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Photographer] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Photographer] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Photographer] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Photographer] SET  ENABLE_BROKER 
GO
ALTER DATABASE [Photographer] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Photographer] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Photographer] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Photographer] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Photographer] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Photographer] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Photographer] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Photographer] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Photographer] SET  MULTI_USER 
GO
ALTER DATABASE [Photographer] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Photographer] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Photographer] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Photographer] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [Photographer] SET DELAYED_DURABILITY = DISABLED 
GO
USE [Photographer]
GO
/****** Object:  Table [dbo].[Gallery]    Script Date: 4/27/2020 13:28:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Gallery](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [nvarchar](50) NULL,
	[content] [nvarchar](max) NULL,
	[main_image] [nvarchar](50) NULL,
 CONSTRAINT [PK_Gallery] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Image]    Script Date: 4/27/2020 13:28:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Image](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[gallery_id] [int] NULL,
	[image_source] [nvarchar](50) NULL,
 CONSTRAINT [PK_Image] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[PhotographerInfo]    Script Date: 4/27/2020 13:28:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhotographerInfo](
	[address] [nvarchar](200) NULL,
	[city] [nvarchar](200) NULL,
	[country] [nvarchar](50) NULL,
	[phone] [nvarchar](50) NULL,
	[email] [nvarchar](200) NULL,
	[about] [nvarchar](max) NULL,
	[special_image] [nvarchar](50) NULL,
	[title] [nvarchar](50) NULL,
	[map] [nvarchar](50) NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET IDENTITY_INSERT [dbo].[Gallery] ON 

INSERT [dbo].[Gallery] ([id], [title], [content], [main_image]) VALUES (1, N'Gallery 1', N'Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation', N'1.jpg')
INSERT [dbo].[Gallery] ([id], [title], [content], [main_image]) VALUES (2, N'Gallery 2', N'Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation', N'2.jpg')
INSERT [dbo].[Gallery] ([id], [title], [content], [main_image]) VALUES (3, N'Gallery 3', N'Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation', N'3.jpg')
INSERT [dbo].[Gallery] ([id], [title], [content], [main_image]) VALUES (4, N'Gallery 4', N'Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation', N'4.jpg')
INSERT [dbo].[Gallery] ([id], [title], [content], [main_image]) VALUES (5, N'Gallery 5', N'Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation', N'5.jpg')
INSERT [dbo].[Gallery] ([id], [title], [content], [main_image]) VALUES (6, N'Gallery 6', N'Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation', N'6.jpg')
INSERT [dbo].[Gallery] ([id], [title], [content], [main_image]) VALUES (7, N'Gallery 7', N'Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation', N'7.jpg')
INSERT [dbo].[Gallery] ([id], [title], [content], [main_image]) VALUES (8, N'Gallery 8', N'Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation', N'8.jpg')
INSERT [dbo].[Gallery] ([id], [title], [content], [main_image]) VALUES (9, N'Gallery 9', N'Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation', N'1.jpg')
INSERT [dbo].[Gallery] ([id], [title], [content], [main_image]) VALUES (10, N'Gallery 10', N'Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation', N'2.jpg')
SET IDENTITY_INSERT [dbo].[Gallery] OFF
SET IDENTITY_INSERT [dbo].[Image] ON 

INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (1, 1, N'1.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (2, 1, N'2.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (3, 1, N'3.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (4, 1, N'4.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (5, 1, N'5.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (6, 1, N'6.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (7, 1, N'7.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (8, 1, N'8.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (9, 2, N'1.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (10, 2, N'2.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (11, 2, N'3.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (12, 2, N'4.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (13, 2, N'5.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (14, 2, N'6.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (15, 2, N'7.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (16, 2, N'8.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (17, 3, N'1.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (18, 3, N'2.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (19, 3, N'3.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (20, 3, N'4.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (21, 3, N'5.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (22, 3, N'6.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (23, 3, N'7.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (24, 3, N'8.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (25, 4, N'1.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (26, 4, N'2.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (27, 4, N'3.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (28, 4, N'4.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (29, 4, N'5.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (30, 4, N'6.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (31, 4, N'7.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (32, 4, N'8.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (33, 5, N'1.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (34, 5, N'2.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (35, 5, N'3.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (36, 5, N'4.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (37, 5, N'5.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (38, 5, N'6.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (39, 5, N'7.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (40, 5, N'8.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (41, 6, N'1.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (42, 6, N'2.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (43, 6, N'3.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (44, 6, N'4.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (45, 6, N'5.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (46, 6, N'6.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (47, 6, N'7.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (48, 6, N'8.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (49, 7, N'1.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (50, 7, N'2.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (51, 7, N'3.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (52, 7, N'4.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (53, 7, N'5.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (54, 7, N'6.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (55, 7, N'7.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (56, 7, N'8.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (57, 8, N'1.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (58, 8, N'2.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (59, 8, N'3.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (60, 8, N'4.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (61, 8, N'5.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (62, 8, N'6.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (63, 8, N'7.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (64, 8, N'8.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (65, 9, N'1.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (66, 9, N'2.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (67, 9, N'3.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (68, 9, N'4.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (69, 9, N'5.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (70, 9, N'6.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (71, 9, N'7.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (72, 9, N'8.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (73, 10, N'1.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (74, 10, N'2.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (75, 10, N'3.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (76, 10, N'4.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (77, 10, N'5.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (78, 10, N'6.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (79, 10, N'7.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (80, 10, N'8.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (81, 1, N'1.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (82, 1, N'2.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (83, 1, N'3.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (84, 1, N'5.jpg')
INSERT [dbo].[Image] ([id], [gallery_id], [image_source]) VALUES (85, 1, N'6.jpg')
SET IDENTITY_INSERT [dbo].[Image] OFF
INSERT [dbo].[PhotographerInfo] ([address], [city], [country], [phone], [email], [about], [special_image], [title], [map]) VALUES (N'Lục Nam', N'Bắc Giang', N'Việt Nam', N'0968266589', N'minhtbhe130746', N'Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim', N'max.jpg', N'Lorem ipsum dolor sit amet', N'map.png')
ALTER TABLE [dbo].[Image]  WITH CHECK ADD  CONSTRAINT [FK_Image_Gallery] FOREIGN KEY([gallery_id])
REFERENCES [dbo].[Gallery] ([id])
GO
ALTER TABLE [dbo].[Image] CHECK CONSTRAINT [FK_Image_Gallery]
GO
USE [master]
GO
ALTER DATABASE [Photographer] SET  READ_WRITE 
GO
