/**
 * Author:  nb
 * Created: 23 avr. 2023
 */
CREATE USER 'thali_util'@'localhost' IDENTIFIED BY 'secret';
GRANT ALL PRIVILEGES ON Thali.* to 'thali_util'@'localhost';

