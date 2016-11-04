-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 04, 2016 at 10:36 AM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 7.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `huit_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_tbl`
--

CREATE TABLE `admin_tbl` (
  `phone` varchar(25) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(70) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `admin_tbl`
--

INSERT INTO `admin_tbl` (`phone`, `email`, `password`) VALUES
('8754301005', 'abhishek.g@incture.com', '123456'),
('8984008534', 'wasim.anwar@incture.com', '123456'),
('9802787529', 'kailash.visnoi@incture.com', '654321');

-- --------------------------------------------------------

--
-- Table structure for table `candidate_tbl`
--

CREATE TABLE `candidate_tbl` (
  `PHONE_NO` varchar(30) NOT NULL,
  `NAME` varchar(45) DEFAULT NULL,
  `EMAIL_ID` varchar(45) DEFAULT NULL,
  `PASSWORD` varchar(45) DEFAULT NULL,
  `ADDRESS` varchar(250) DEFAULT NULL,
  `GENDER` varchar(45) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `ATTEMPT` int(11) DEFAULT NULL,
  `SCORE_APTI` int(11) DEFAULT NULL,
  `STATUS` varchar(10) DEFAULT NULL,
  `BATCH` varchar(10) DEFAULT NULL,
  `SCORE_ENG` int(11) DEFAULT NULL,
  `SCORE_LR` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `candidate_tbl`
--

INSERT INTO `candidate_tbl` (`PHONE_NO`, `NAME`, `EMAIL_ID`, `PASSWORD`, `ADDRESS`, `GENDER`, `DOB`, `ATTEMPT`, `SCORE_APTI`, `STATUS`, `BATCH`, `SCORE_ENG`, `SCORE_LR`) VALUES
('-329425204', 'wasim', 'wasim.anwar@incture.com', '24284', NULL, NULL, '1993-10-18', 1, 40, '1', '11', 50, 60),
('098765432187', 'shdbvnh', 'sharmee.biswas@incture.com', '10656', NULL, NULL, NULL, 0, 0, NULL, NULL, 0, 0),
('1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2', NULL, NULL),
('1112054262207140', 'Abhishek Gupta', 'abhi', '25339', NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL),
('1130000000', '113005349695375303055', 'a', '21705', NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL),
('113005349695375303055', 'wasim anwar', 'wasim', '26593', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('1214312', 'q', 'dasfd@inasfd.com', '15378', NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL),
('123', 'Abhishek', '', '28247', NULL, NULL, NULL, NULL, 10, NULL, 'NIT-21', 14, 21),
('12345', 'wasim', 'wasim.anwar@incture.com', '23603', NULL, NULL, NULL, NULL, 11, NULL, 'NIT-21', 15, 20),
('123456', 'chandra', 'wasim.anwar@incture.com', '25226', NULL, NULL, NULL, NULL, 12, NULL, 'NIT-21', 16, 19),
('1234563677', 'Abhisheka', 'wasim.anwar@incture.com', '29745', NULL, NULL, NULL, NULL, NULL, NULL, 'NIT-21', NULL, NULL),
('1234567', 'kailash', 'wasim.anwar@incture.com', '13065', NULL, NULL, NULL, NULL, 13, NULL, 'NIT-21', 17, 18),
('123456767', 'Abhisheka', 'wasim.anwar@incture.com', '18412', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('12345676711', 'Abhisheka', 'wasim.anwar@incture.com', '28421', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('123456767111', 'Abhisheka', 'wasim.anwar@incture.com', '28597', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('123456789', 'Wasim Anwar', 'wasim.anwar@incture.com', '19427', 'Ghar+Bengaluru+Karnataka+India+560103', 'male', '1993-10-18', 1, 20, NULL, '37', 3, 33),
('2', 'wasim', '89898989', NULL, NULL, NULL, NULL, NULL, NULL, '1', '1', NULL, NULL),
('3', 'wasim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL),
('45', 'WASIM', 'wasim.anwar@incture.com', '14539', 'incture', 'M', '1993-10-18', 1, 0, NULL, 'KT-1', 0, 0),
('4564564567', 'MD. WASIM ANWAR csc', '1205325@kiit.ac.in', '26699', 'Kolkata+Kolkata+West Bengal+India+700016', 'male', '1993-10-18', 0, NULL, NULL, NULL, NULL, NULL),
('46', 'wasim', 'wasim.anwar@incture.com', '27124', NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL),
('49', 'wasim', 'wasim.anwar@incture.com', '27366', NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL),
('50', 'wasim', 'wasim.anwar@incture.com', '10786', NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL),
('51', 'wasim', 'wasim.anwar@incture.com', '27276', NULL, NULL, NULL, NULL, NULL, NULL, '3', NULL, NULL),
('54', NULL, 'wasim.anwar@incture.com', '29911', NULL, NULL, NULL, NULL, NULL, NULL, '4', NULL, NULL),
('555555', NULL, NULL, NULL, NULL, NULL, NULL, 1, 10, NULL, 'DEMO-TEST', 10, 10),
('56', '', 'wasim.anwar@incture.com', '16017', NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL),
('565', 'Kailash Vishnoi', 'ahsh', '12555', 'incture+Bengaluru+Karnataka+India+560103', 'male', '1993-11-25', 1, 0, NULL, 'KNIT-207', 0, 0),
('678', 'bhjh', 'wasim.anwar@incture.com', '17212', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('7676767', 'wasim', 'wasim.anwar@incture.com', '29585', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('76767676767', 'ads', 'b', '22596', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('77676767', 'wasim', 'wasim.anwar@incture.com', '18120', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('7777777777', 'Md Wasim Anwar', 'bdamas', '23117', 'kolkata+Kolkata+West Bengal+India+700016', 'male', '1993-10-18', 0, NULL, NULL, NULL, NULL, NULL),
('787', 'Abhishek Gupta', '12', '23580', 'Incture technologies+Rajgarh+Madhya Pradesh+India+465691', 'male', '1992-12-30', 1, 20, NULL, 'KNIT-207', 20, 20),
('788787887', 'abhi', 'abhi', '25133', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('8223039872', 'Shubham Chandra', 'Shubham.chandra@incture.com', '14039', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('8260509388', 'Md Wasim Anwar', 'abc', '25203', 'Sns pg bangalore+Kolkata+West Bengal+India+700016', 'male', '1993-10-18', 1, 40, NULL, 'KNIT-207', 40, 20),
('8456053106', 'Sharmee Biswas', 'sharmee.biswas@incture.com', '29115', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL),
('8754301005', 'Abhishek', 'abhishek.g@incture.com', '13337', 'Incture+Bengaluru+Karnataka+India+560103', 'male', '1992-12-30', 1, 0, NULL, 'KT-5432', 20, 0),
('8766543210', 'sharmee', 'sharmee.biswas@incture.com', '12565', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL),
('8989898989', 'wasim', 'wasim.anwar@incture.com', '18591', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('8989898989899', 'wasim', 'c', '23603', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('9669711444', 'manoj kumar', 'tmk.bza@gmail.com', '27349', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL),
('9669711445', 'manoj kumar', 'tmkmanoj12@gmail.com', '20980', 'banglore+Bhopal+Madhya Pradesh+India+462003', 'male', '1994-12-23', 1, 0, '1', '126', 8, 0),
('9802787529', 'Kailash Vishnoi', 'kailash.vishnoi@incture.com', '12760', 'Bajju, Bikaner+Bengaluru+Karnataka+India+560103', 'male', '1993-11-25', 1, 60, NULL, 'KT-5432', 60, 20),
('9831579896', 'Wasim', 'wasim.anwar@incture.com', '123456', 'Kolkata+Kolkata+West Bengal+India+700016', 'male', '1993-10-18', 1, 40, '0', 'KT-5432', 60, 20),
('9898989898', 'wasim', 'wasim.anwar@incture.com', '29552', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('98989898989', 'wasim', 'wasim.anwar@incture.com', '27233', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `can_edu_tbl`
--

CREATE TABLE `can_edu_tbl` (
  `PHONE_NO` varchar(25) NOT NULL,
  `BORD_TENTH` varchar(255) DEFAULT NULL,
  `PERCENTAGE_TENTH` int(11) DEFAULT NULL,
  `YEAR_TENTH` int(11) DEFAULT NULL,
  `BORD_TWELVE` varchar(255) DEFAULT NULL,
  `PERCENTAGE_TWELVE` int(11) DEFAULT NULL,
  `YEAR_TWELVE` int(11) DEFAULT NULL,
  `COLLAGE_UG` varchar(255) DEFAULT NULL,
  `SPECIALIZATION` varchar(45) DEFAULT NULL,
  `PERCENTAGE_UG` int(11) DEFAULT NULL,
  `YEAR_UG` int(11) DEFAULT NULL,
  `COURSE` varchar(145) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `can_edu_tbl`
--

INSERT INTO `can_edu_tbl` (`PHONE_NO`, `BORD_TENTH`, `PERCENTAGE_TENTH`, `YEAR_TENTH`, `BORD_TWELVE`, `PERCENTAGE_TWELVE`, `YEAR_TWELVE`, `COLLAGE_UG`, `SPECIALIZATION`, `PERCENTAGE_UG`, `YEAR_UG`, `COURSE`) VALUES
('098765432187', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('113005349695375303055', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('1234563', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('12345635555', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('1234563677', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('123456789', NULL, 88, 2012, 'I.S.E', 88, 2012, 'National Institute Of Technology, Tiruchirappalli', 'Computer Science', 87, 2016, 'Bachelors of Technology(B.Tech)'),
('4564564567', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('76767676767', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('7777777777', 'ISC', 86, 2010, 'ISC', 87, 2012, 'Kalinga Institue Of Industrial Technology', 'CS', 88, 2016, 'Bachelors of Technology(B.Tech)'),
('788787887', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('8223039872', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('8260509388', 'ICSE', 88, 2010, 'ISC', 89, 2012, 'Kalinga Institue Of Industrial Technology', 'CSE', 87, 2016, 'Bachelors of Technology(B.Tech)'),
('8456053106', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('8754301005', 'State Board', 80, 2008, 'State Board', 82, 2010, 'National Institute Of Technology, Tiruchirappalli', 'MCA', 81, 2016, 'MCA'),
('8766543210', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('8989898989899', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('9669711444', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('9669711445', 'AP STATE', 91, 2010, 'AP STATE ', 90, 2012, 'Maulana Azad National Institute of Technology, Bhopal', 'ECE', 50, 2017, 'Btech'),
('9802787529', 'RBSE', 80, 2010, 'RBSE', 78, 2012, 'NITKKR', 'electrical', 89, 2016, 'Bachelors of Technology(B.Tech)'),
('9831579896', 'ISCI', 90, 2010, 'ISCI', 88, 2012, 'Kalinga Institue Of Industrial Technology', 'CS', 98, 2016, 'Bachelors of Technology(B.Tech)');

-- --------------------------------------------------------

--
-- Table structure for table `feedback_tbl`
--

CREATE TABLE `feedback_tbl` (
  `PHONE_NO` varchar(15) DEFAULT NULL,
  `FEEDBACK` varchar(255) DEFAULT NULL,
  `FID` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `feedback_tbl`
--

INSERT INTO `feedback_tbl` (`PHONE_NO`, `FEEDBACK`, `FID`) VALUES
('9831579896', 'hello', 1),
(NULL, NULL, 2),
(NULL, 'bye', 3),
('9831579896', 'bye', 4),
('8754301005', 'abhishek.g@incture.com', 5),
('9831579896', '                                                ', 6),
('9831579896', 'wasim', 7),
('9831579896', '                            ', 8),
('9831579896', '                                                ', 9),
('9831579896', '                                                ', 10),
('9831579896', '                                                ', 11),
('9831579896', '                                                wasim', 12),
('9831579896', 'was', 13),
('9831579896', 'was', 14),
('9831579896', 'Hello', 15),
('9831579896', 'wasimmmmm', 16),
('9669711445', 'hello u suck', 17),
('9669711445', 'avcadvWDV', 18),
('9669711445', 'BEABQENQTN', 19);

-- --------------------------------------------------------

--
-- Table structure for table `question_tbl`
--

CREATE TABLE `question_tbl` (
  `QID` int(11) NOT NULL,
  `QUESTION` varchar(255) DEFAULT NULL,
  `OPTION_1` varchar(100) DEFAULT NULL,
  `OPTION_2` varchar(100) DEFAULT NULL,
  `OPTION_3` varchar(100) DEFAULT NULL,
  `OPTION_4` varchar(100) DEFAULT NULL,
  `CORRECT_ANS` varchar(30) DEFAULT NULL,
  `CATEGORY` varchar(45) DEFAULT NULL,
  `TYPE` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `question_tbl`
--

INSERT INTO `question_tbl` (`QID`, `QUESTION`, `OPTION_1`, `OPTION_2`, `OPTION_3`, `OPTION_4`, `CORRECT_ANS`, `CATEGORY`, `TYPE`) VALUES
(1, 'The least perfect square, which is divisible by each of 21, 36 and 66 is:\n', '213444', '214344', '214434', '231444', 'A', 'Apti', '1'),
(2, 'If a person walks at 14 km/hr instead of 10 km/hr, he would have walked 20 km more. The actual distance travelled by him is:', '50 km', '56 km', '70 km', '80 km', 'A', 'Apti', '1'),
(3, 'Find Out one Out (2, 5, 10, 17, 26, 37, 50, 64)', '50', '26', '37', '64', 'D', 'Eng', '1'),
(4, 'In 100 m race, A covers the distance in 36 seconds and B in 45 seconds. In this race A beats B by:', '20 m', '25 m', '22.5 m', '9 m', 'A', 'Lr', '1'),
(5, 'A can run 22.5 m while B runs 25 m. In a kilometre race B beats A by:', '100 m', '111.11 m', '25 m', '50 m', 'A', 'lr', '2'),
(6, 'On selling 17 balls at Rs. 720, there is a loss equal to the cost price of 5 balls. The cost price of a ball is:', 'Rs. 45', 'Rs. 50', 'Rs. 55', 'Rs. 60', 'D', 'Lr', '1'),
(7, 'A clock is started at noon. By 10 minutes past 5, the hour hand has turned through:', '145º', '150º', '155º', '160º', 'C', 'Eng', '2'),
(8, 'A watch which gains uniformly is 2 minutes low at noon on Monday and is 4 min. 48 sec fast at 2 p.m. on the following Monday. When was it correct?', '2 p.m. on Tuesday', '2 p.m. on Wednesday', '3 p.m. on Thursday', '1 p.m. on Friday', 'B', 'Eng', '1'),
(9, 'Which of the following fraction is the largest ?', '7/8', '13/16', '31/40', '63/80', 'A', 'Apti', '1'),
(10, 'If log10 5 + log10 (5x + 1) = log10 (x + 5) + 1, then x is equal to:', '1', '3', '5', '10', 'B', 'Apti', '2'),
(11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(12, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(13, 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'),
(14, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(15, 'The ', '23', '23', '23', '23', 'A', 'Eng', '2'),
(16, 'The aa', '23', '23', '23', '23', 'A', 'Eng', '1'),
(17, '2+2', '4', '3', '2', '1', 'A', 'apti', 'apti'),
(18, 'The aaaaaa', '23', '23', '23', '23', 'A', 'Eng', '1'),
(19, 'The bbb', '23', '23', '23', '23', 'A', 'Eng', '1'),
(20, 'ques', 'op1', 'op2', 'op3', 'op4', 'op5', 'category', 'diffe\r'),
(21, 'ques1', 'op1', 'op3', 'op6', 'op7', 'op8', 'category', 'do\r'),
(22, 'ques', 'op1', 'op2', 'op3', 'op4', 'op5', 'category', 'diffe\r'),
(23, 'ques1', 'op1', 'op3', 'op6', 'op7', 'op8', 'category', 'do\r'),
(24, 'A train running at the speed of 60 km/hr crosses a pole in 9 seconds. What is the length of the train?"', '120 metres', '180 metres', '324metres', '150 metres', 'D', 'Apti', '1\r'),
(25, '', '', '', '', '', '', '', '\r'),
(26, 'ques', 'op1', 'op2', 'op3', 'op4', 'op5', 'category', 'diffe\r'),
(27, 'ques1', 'op1', 'op3', 'op6', 'op7', 'op8', 'category', 'do\r'),
(28, '"1', '2"', '"3', '4"', '"5', '6"', '"7', '8"'),
(29, '', '', '', '', '', '', '', '\r'),
(30, 'dhatjatrjt', 'erah', 'aerh', 'ehae', 'aehe', 'erah', 'zvdv', 'vddvdsv\r'),
(31, 'hi ', 'hi', 'ho', 'he', 'hj', 'A', 'Eng', '1');

-- --------------------------------------------------------

--
-- Table structure for table `test_tbl`
--

CREATE TABLE `test_tbl` (
  `PASS_KEY` varchar(45) NOT NULL,
  `NO_OF_APTI` int(11) DEFAULT NULL,
  `NO_OF_ENG` int(11) DEFAULT NULL,
  `NO_OF_LR` int(11) DEFAULT NULL,
  `TIME` int(11) DEFAULT NULL,
  `BATCH` varchar(45) DEFAULT NULL,
  `COLLAGE_NAME` varchar(255) DEFAULT NULL,
  `CUT_OFF` int(11) DEFAULT NULL,
  `DOT` date DEFAULT NULL,
  `DIFFICULTY` varchar(45) DEFAULT NULL,
  `STATUS` varchar(45) DEFAULT NULL,
  `TIME_APTI` int(11) DEFAULT NULL,
  `TIME_ENG` int(11) DEFAULT NULL,
  `TIME_LR` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `test_tbl`
--

INSERT INTO `test_tbl` (`PASS_KEY`, `NO_OF_APTI`, `NO_OF_ENG`, `NO_OF_LR`, `TIME`, `BATCH`, `COLLAGE_NAME`, `CUT_OFF`, `DOT`, `DIFFICULTY`, `STATUS`, `TIME_APTI`, `TIME_ENG`, `TIME_LR`) VALUES
('D566', 10, 10, 10, 0, 'DEMO-TEST', 'Demo', 0, '2016-09-23', '1', '1', 10, 10, 10),
('i227', 12, 12, 12, 0, '101', 'incture', 0, '2016-10-27', '1', '2', 12, 12, 12),
('K26', 5, 10, 5, 0, NULL, NULL, 0, '2016-09-19', '2', NULL, 2, 20, 2),
('K651', 31, 31, 31, 91, '1', 'National Institute of Technology', 45, '2016-09-10', 'Easy', '2', 0, 0, 0),
('K785', 5, 5, 5, 0, 'KNIT-207', 'Kiit', 0, '2016-09-23', '1', '1', 2, 2, 2),
('k940', 2, 2, 2, 0, '2', 'kit', 0, '1993-10-19', 'easy', '0', 2, 2, 2),
('N89', 20, 25, 25, 0, NULL, NULL, 0, '2016-09-24', '1', NULL, 30, 20, 30),
('nh173', 12, 12, 12, 0, '126', 'new horizon', 0, '2016-10-31', '1', '2', 20, 20, 20),
('NI225', 3, 30, 30, 90, '17', 'National Institute', 45, '2016-09-22', '1', '1', NULL, NULL, NULL),
('NI267', 4, 0, 0, 90, '2', 'National Institute', 45, '2016-09-23', '1', '1', NULL, NULL, NULL),
('NI308', 30, 30, 30, 90, '3', 'National Institute', 45, NULL, 'Easy', 'ABCD', NULL, NULL, NULL),
('NI432', 30, 30, 30, 90, '4', 'National Institute', 45, NULL, 'Easy', 'ABCD', NULL, NULL, NULL),
('NI448', 30, 30, 30, 90, '5', 'National Institute', 45, '2016-10-08', '2', '1', NULL, NULL, NULL),
('NI528', 30, 30, 30, 90, '6', 'National Institute', 45, NULL, 'Easy', 'ABCD', NULL, NULL, NULL),
('NI61', 30, 30, 30, 90, '7', 'National Institute', 45, NULL, 'Easy', 'ABCD', NULL, NULL, NULL),
('NI700', 30, 30, 30, 90, '8', 'National Institute', 45, NULL, 'Easy', 'ABCD', NULL, NULL, NULL),
('NI710', 30, 30, 30, 90, '9', 'National Institute', 45, NULL, 'Easy', 'ABCD', NULL, NULL, NULL),
('NI790', 30, 30, 30, 90, '10', 'National Institute', 45, NULL, 'Easy', 'ABCD', NULL, NULL, NULL),
('NIoT158', 30, 30, 30, 90, '1', 'National Institute of Technology', 45, '2016-09-10', '2', '2', 0, 0, 0),
('NIoT236', 30, 30, 30, 90, '11', 'National Institute of Technology', 45, '2016-09-14', '1', '2', NULL, NULL, NULL),
('NIoT265', 30, 30, 30, 90, '12', 'National Institute of Technology', 45, NULL, 'Easy', 'ABCD', NULL, NULL, NULL),
('NIoT354', 30, 30, 30, 90, '13', 'National Institute of Technology', 45, NULL, 'Easy', 'ABCD', NULL, NULL, NULL),
('NIoT413', 30, 30, 30, 90, '14', 'National Institute of Technology', 45, NULL, 'Easy', 'ABCD', NULL, NULL, NULL),
('NIoT42', 10, 30, 3, 90, '37', 'National Institute of Technology', 45, '2016-09-18', '1', '2', 25, 1, 30),
('NIoT500', 30, 30, 30, 90, '16', 'National Institute of Technology', 45, NULL, 'Easy', 'ABCD', NULL, NULL, NULL),
('NIoT546', 30, 30, 30, 90, '33', 'National Institute of Technology', 45, '2016-09-10', '1', '2', NULL, NULL, NULL),
('NIoT556', 4, 30, 30, 90, '25', 'National Institute of Technology', 45, '2013-09-24', '1', '1', NULL, NULL, NULL),
('NIoT59', 30, 30, 30, 90, '18', 'National Institute of Technology', 45, NULL, 'Easy', 'ABCD', NULL, NULL, NULL),
('NIoT737', 30, 30, 30, 90, '19', 'National Institute of Technology', 45, '2016-08-10', '1', '2', NULL, NULL, NULL),
('NIoT748', 30, 30, 30, 90, '20', 'National Institute of Technology', 45, NULL, 'Easy', 'ABCD', NULL, NULL, NULL),
('Sm855', 11, 11, 11, 0, '111', 'SJC mysore', 0, '2016-10-30', '1', '0', 11, 11, 11);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin_tbl`
--
ALTER TABLE `admin_tbl`
  ADD PRIMARY KEY (`phone`);

--
-- Indexes for table `candidate_tbl`
--
ALTER TABLE `candidate_tbl`
  ADD PRIMARY KEY (`PHONE_NO`);

--
-- Indexes for table `can_edu_tbl`
--
ALTER TABLE `can_edu_tbl`
  ADD PRIMARY KEY (`PHONE_NO`);

--
-- Indexes for table `feedback_tbl`
--
ALTER TABLE `feedback_tbl`
  ADD PRIMARY KEY (`FID`);

--
-- Indexes for table `question_tbl`
--
ALTER TABLE `question_tbl`
  ADD PRIMARY KEY (`QID`);

--
-- Indexes for table `test_tbl`
--
ALTER TABLE `test_tbl`
  ADD PRIMARY KEY (`PASS_KEY`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `feedback_tbl`
--
ALTER TABLE `feedback_tbl`
  MODIFY `FID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT for table `question_tbl`
--
ALTER TABLE `question_tbl`
  MODIFY `QID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
