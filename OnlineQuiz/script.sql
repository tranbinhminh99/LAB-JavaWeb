USE [onlineQuiz]
GO
ALTER TABLE [dbo].[Option] DROP CONSTRAINT [FK_Option_Question]
GO
ALTER TABLE [dbo].[Account] DROP CONSTRAINT [FK_Account_UserType]
GO
/****** Object:  Table [dbo].[UserType]    Script Date: 4/27/2020 13:22:26 ******/
DROP TABLE [dbo].[UserType]
GO
/****** Object:  Table [dbo].[Question]    Script Date: 4/27/2020 13:22:26 ******/
DROP TABLE [dbo].[Question]
GO
/****** Object:  Table [dbo].[Option]    Script Date: 4/27/2020 13:22:26 ******/
DROP TABLE [dbo].[Option]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 4/27/2020 13:22:26 ******/
DROP TABLE [dbo].[Account]
GO
USE [master]
GO
/****** Object:  Database [onlineQuiz]    Script Date: 4/27/2020 13:22:26 ******/
DROP DATABASE [onlineQuiz]
GO
/****** Object:  Database [onlineQuiz]    Script Date: 4/27/2020 13:22:26 ******/
CREATE DATABASE [onlineQuiz]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'onlineQuiz', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MINHTB\MSSQL\DATA\onlineQuiz.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'onlineQuiz_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MINHTB\MSSQL\DATA\onlineQuiz_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [onlineQuiz] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [onlineQuiz].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [onlineQuiz] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [onlineQuiz] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [onlineQuiz] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [onlineQuiz] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [onlineQuiz] SET ARITHABORT OFF 
GO
ALTER DATABASE [onlineQuiz] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [onlineQuiz] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [onlineQuiz] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [onlineQuiz] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [onlineQuiz] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [onlineQuiz] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [onlineQuiz] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [onlineQuiz] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [onlineQuiz] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [onlineQuiz] SET  DISABLE_BROKER 
GO
ALTER DATABASE [onlineQuiz] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [onlineQuiz] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [onlineQuiz] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [onlineQuiz] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [onlineQuiz] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [onlineQuiz] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [onlineQuiz] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [onlineQuiz] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [onlineQuiz] SET  MULTI_USER 
GO
ALTER DATABASE [onlineQuiz] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [onlineQuiz] SET DB_CHAINING OFF 
GO
ALTER DATABASE [onlineQuiz] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [onlineQuiz] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [onlineQuiz] SET DELAYED_DURABILITY = DISABLED 
GO
USE [onlineQuiz]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 4/27/2020 13:22:27 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Account](
	[UserName] [varchar](100) NOT NULL,
	[PassWord] [varchar](100) NOT NULL,
	[Email] [varchar](100) NOT NULL,
	[t_id] [int] NOT NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[UserName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Option]    Script Date: 4/27/2020 13:22:27 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Option](
	[o_id] [int] IDENTITY(1,1) NOT NULL,
	[O_content] [nvarchar](500) NOT NULL,
	[q_id] [int] NOT NULL,
	[status] [bit] NOT NULL,
 CONSTRAINT [PK_Option] PRIMARY KEY CLUSTERED 
(
	[o_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Question]    Script Date: 4/27/2020 13:22:27 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Question](
	[q_id] [int] IDENTITY(1,1) NOT NULL,
	[content] [varchar](500) NOT NULL,
	[date_Create] [date] NOT NULL,
 CONSTRAINT [PK_Question] PRIMARY KEY CLUSTERED 
(
	[q_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[UserType]    Script Date: 4/27/2020 13:22:27 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[UserType](
	[t_id] [int] NOT NULL,
	[Type] [varchar](100) NOT NULL,
 CONSTRAINT [PK_UserType] PRIMARY KEY CLUSTERED 
(
	[t_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Account] ([UserName], [PassWord], [Email], [t_id]) VALUES (N'', N'admin1', N'', 2)
INSERT [dbo].[Account] ([UserName], [PassWord], [Email], [t_id]) VALUES (N'admin1', N'admin1', N'thaont.design@gmail.com', 2)
INSERT [dbo].[Account] ([UserName], [PassWord], [Email], [t_id]) VALUES (N'admin7', N'admin9', N'thaont.design@gmail.com', 2)
INSERT [dbo].[Account] ([UserName], [PassWord], [Email], [t_id]) VALUES (N'admin8', N'admin9', N'thaont.design@gmail.com', 2)
INSERT [dbo].[Account] ([UserName], [PassWord], [Email], [t_id]) VALUES (N'admin9', N'admin9', N'thaont.design@gmail.com', 2)
INSERT [dbo].[Account] ([UserName], [PassWord], [Email], [t_id]) VALUES (N'Student1', N'123', N'stu', 1)
INSERT [dbo].[Account] ([UserName], [PassWord], [Email], [t_id]) VALUES (N'studenta', N'aaa', N'studenta@gmail.com', 1)
INSERT [dbo].[Account] ([UserName], [PassWord], [Email], [t_id]) VALUES (N'Teaacher', N'123', N'teacher', 2)
SET IDENTITY_INSERT [dbo].[Option] ON 

INSERT [dbo].[Option] ([o_id], [O_content], [q_id], [status]) VALUES (275, N'Ho Chi Minh City', 71, 0)
INSERT [dbo].[Option] ([o_id], [O_content], [q_id], [status]) VALUES (276, N'Ha Noi City', 71, 1)
INSERT [dbo].[Option] ([o_id], [O_content], [q_id], [status]) VALUES (277, N'Hai Phong City', 71, 0)
INSERT [dbo].[Option] ([o_id], [O_content], [q_id], [status]) VALUES (278, N'Da Lat City', 71, 0)
INSERT [dbo].[Option] ([o_id], [O_content], [q_id], [status]) VALUES (279, N't', 72, 1)
INSERT [dbo].[Option] ([o_id], [O_content], [q_id], [status]) VALUES (280, N't', 72, 1)
INSERT [dbo].[Option] ([o_id], [O_content], [q_id], [status]) VALUES (281, N'f', 72, 0)
INSERT [dbo].[Option] ([o_id], [O_content], [q_id], [status]) VALUES (282, N'f', 72, 0)
INSERT [dbo].[Option] ([o_id], [O_content], [q_id], [status]) VALUES (283, N't', 73, 1)
INSERT [dbo].[Option] ([o_id], [O_content], [q_id], [status]) VALUES (284, N'f', 73, 0)
INSERT [dbo].[Option] ([o_id], [O_content], [q_id], [status]) VALUES (285, N'f', 73, 0)
INSERT [dbo].[Option] ([o_id], [O_content], [q_id], [status]) VALUES (286, N't', 73, 1)
INSERT [dbo].[Option] ([o_id], [O_content], [q_id], [status]) VALUES (287, N't', 74, 1)
INSERT [dbo].[Option] ([o_id], [O_content], [q_id], [status]) VALUES (288, N't', 74, 1)
INSERT [dbo].[Option] ([o_id], [O_content], [q_id], [status]) VALUES (289, N't', 74, 1)
INSERT [dbo].[Option] ([o_id], [O_content], [q_id], [status]) VALUES (290, N'f', 74, 0)
INSERT [dbo].[Option] ([o_id], [O_content], [q_id], [status]) VALUES (295, N'New York', 76, 1)
INSERT [dbo].[Option] ([o_id], [O_content], [q_id], [status]) VALUES (296, N'Mexico', 76, 0)
INSERT [dbo].[Option] ([o_id], [O_content], [q_id], [status]) VALUES (297, N'Ha Noi', 76, 0)
INSERT [dbo].[Option] ([o_id], [O_content], [q_id], [status]) VALUES (298, N'Hong Kong', 76, 0)
SET IDENTITY_INSERT [dbo].[Option] OFF
SET IDENTITY_INSERT [dbo].[Question] ON 

INSERT [dbo].[Question] ([q_id], [content], [date_Create]) VALUES (71, N'The name''s Capital of Viet Name', CAST(N'2020-03-29' AS Date))
INSERT [dbo].[Question] ([q_id], [content], [date_Create]) VALUES (72, N'Question to check 1', CAST(N'2020-03-29' AS Date))
INSERT [dbo].[Question] ([q_id], [content], [date_Create]) VALUES (73, N'Question to check 2', CAST(N'2020-03-29' AS Date))
INSERT [dbo].[Question] ([q_id], [content], [date_Create]) VALUES (74, N'Question to check 3', CAST(N'2020-03-29' AS Date))
INSERT [dbo].[Question] ([q_id], [content], [date_Create]) VALUES (76, N'The name''s Capital of USA', CAST(N'2020-03-29' AS Date))
SET IDENTITY_INSERT [dbo].[Question] OFF
INSERT [dbo].[UserType] ([t_id], [Type]) VALUES (1, N'Student')
INSERT [dbo].[UserType] ([t_id], [Type]) VALUES (2, N'Teacher')
ALTER TABLE [dbo].[Account]  WITH CHECK ADD  CONSTRAINT [FK_Account_UserType] FOREIGN KEY([t_id])
REFERENCES [dbo].[UserType] ([t_id])
GO
ALTER TABLE [dbo].[Account] CHECK CONSTRAINT [FK_Account_UserType]
GO
ALTER TABLE [dbo].[Option]  WITH CHECK ADD  CONSTRAINT [FK_Option_Question] FOREIGN KEY([q_id])
REFERENCES [dbo].[Question] ([q_id])
GO
ALTER TABLE [dbo].[Option] CHECK CONSTRAINT [FK_Option_Question]
GO
USE [master]
GO
ALTER DATABASE [onlineQuiz] SET  READ_WRITE 
GO
