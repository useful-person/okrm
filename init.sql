-- 创建数据库（如果不存在）
CREATE DATABASE IF NOT EXISTS user_db CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- 创建用户（如果不存在）并设置密码
CREATE USER IF NOT EXISTS 'myuser'@'%' IDENTIFIED BY 'secret';

-- 授予 user_db 所有权限给该用户
GRANT ALL PRIVILEGES ON user_db.* TO 'myuser'@'%';

-- 使权限立即生效
FLUSH PRIVILEGES;
